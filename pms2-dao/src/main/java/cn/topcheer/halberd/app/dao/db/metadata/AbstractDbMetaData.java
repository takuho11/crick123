package cn.topcheer.halberd.app.dao.db.metadata;

import cn.topcheer.halberd.app.api.framework.db.DbObject;
import cn.topcheer.halberd.app.api.framework.db.DbObjectKey;
import cn.topcheer.halberd.app.api.framework.db.DbPath;
import cn.topcheer.halberd.app.api.framework.db.DbProp;
import cn.topcheer.halberd.app.api.framework.vo.*;
import cn.topcheer.halberd.app.dao.db.DataSourceAndType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public abstract class AbstractDbMetaData implements IDbMetaData {

    protected DataSourceAndType dataSourceWrapper;

    public void setDataSourceWrapper(DataSourceAndType dataSourceWrapper){
        this.dataSourceWrapper = dataSourceWrapper;
    }

    protected JdbcTemplate getJdbcTemplate(){
        return this.dataSourceWrapper.getJdbcTemplate();
    }


    @Override
    public List<String> getSchemasFilterByDorisDataLakeRule() {
        return this.getSchemas();
    }

    @Override
    public String transToDorisDataLakeSchema(String schema) {
        return schema;
    }


    protected String ifBlankGetDefaultSchema(String schema){
        return StringUtils.isBlank(schema)? getCurrentSchema() : schema;
    }

    @Override
    public List<DbTableOrView> getAllTableOrViews(){
        return this.getAllTableOrViews(this.getCurrentSchema());
    }

    @Override
    public DbTableOrView getTableOrView(String tableOrViewName) {
        return this.getTableOrView(this.getCurrentSchema(), tableOrViewName);
    }

    @Override
    public List<DbColumn> getColumns(String tableOrViewName){
        return this.getColumns(this.getCurrentSchema(), tableOrViewName);
    }

    protected String getDbType(){
        return this.dataSourceWrapper.getType();
    }

    private static final Map<String, String> pathLabel;
    static {
        pathLabel = new HashMap<>();
        pathLabel.put("databases", "数据库");
        pathLabel.put("schemas", "模式");
        pathLabel.put("tables", "表");
        pathLabel.put("views", "视图");
        pathLabel.put("columns", "列");
        pathLabel.put("ddl", "DDL");
        pathLabel.put("viewSource", "源");
    }
    private static String getPathLabel(String path){
        return pathLabel.containsKey(path)? pathLabel.get(path) : path;
    }


    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Map<Class<? extends IDbMetaData>, PathNode> class_pathNode_map = new ConcurrentHashMap<>();

    static{
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(IDbMetaData.class));
        String packageName = ClassUtils.getPackageName(IDbMetaData.class);
        Set<BeanDefinition> components = provider.findCandidateComponents(packageName.replace(".", "/"));
        for (BeanDefinition component : components) {
            try{
                Class<? extends IDbMetaData> cls = (Class<? extends IDbMetaData>) Class.forName(component.getBeanClassName());
                getRoot(cls);
            }catch (ClassNotFoundException e){
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * 没有就用父类的
     * @param clazz
     * @return
     */
    private static PathNode getRoot(Class<? extends IDbMetaData> clazz){
        if(class_pathNode_map.containsKey(clazz)){
            return class_pathNode_map.get(clazz);
        }
        PathNode root = initPathTree(clazz);
        if(root.getChildren().isEmpty() && AbstractDbMetaData.class.isAssignableFrom(clazz) && !AbstractDbMetaData.class.equals(clazz)){
            Class<? extends IDbMetaData> superClass = (Class<? extends IDbMetaData>) clazz.getSuperclass();
            root = getRoot(superClass);
        }
        class_pathNode_map.put(clazz, root);
        return root;
    }

    protected static PathNode initPathTree(Class<? extends IDbMetaData> clazz){
        PathNode root = PathNode.builder().children(new ArrayList<>()).build();
        for (Method method : clazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(DbPath.class)){
                DbPath dbPath = method.getAnnotation(DbPath.class);
                List<String> declaredPath = Arrays.stream(dbPath.value().split("/")).filter(StringUtils::isNoneBlank).collect(Collectors.toList());

                PathNode nodeIterator = root;
                for(String p : declaredPath){
                    Optional<PathNode> nodeOptional = nodeIterator.getChildren().stream().filter(n->n.getPath().equals(p)).findFirst();
                    if(nodeOptional.isPresent()){
                        nodeIterator = nodeOptional.get();
                    }else {
                        PathNode newNode = PathNode.builder().path(p).father(nodeIterator).children(new ArrayList<>()).build();
                        nodeIterator.getChildren().add(newNode);
                        nodeIterator = newNode;
                    }
                }
                if(nodeIterator.getMethod()==null){
                    nodeIterator.setMethod(method);
                    nodeIterator.setSeq(dbPath.seq());
                }else {
                    throw new IllegalStateException(clazz + ":\n"+ nodeIterator.getMethod() + "与" + method + "有相同的路径！");
                }
            }
        }
        sortPathNode(root);
        return root;
    }


    private static void sortPathNode(PathNode node){
        if(node.getChildren()!=null && !node.getChildren().isEmpty()){
            node.setChildren(node.getChildren().stream().sorted(Comparator.comparingInt(PathNode::getSeq)).collect(Collectors.toList()));
            for (PathNode childNode : node.getChildren()) {
                sortPathNode(childNode);
            }
        }
    }


    private static PathNode getNode(PathNode root, List<String> path){
        PathNode node = root;
        for(int i=1; i< path.size(); i++){
            String p = path.get(i);
            List<PathNode> children = node.getChildren();
            if(!children.isEmpty()){
                Optional<PathNode> constNode = children.stream().filter(n -> p.equals(n.getPath())).findFirst();
                if(constNode.isPresent()){
                    node = constNode.get();
                    continue;
                }
                Optional<PathNode> paramNode = children.stream().filter(n -> n.getPath().startsWith("{") && n.getPath().endsWith("}")).findFirst();
                if(paramNode.isPresent()){
                    node = paramNode.get();
                    continue;
                }
            }
            throw new IllegalArgumentException("404");
        }
        return node;
    }

    private boolean isParamPath(PathNode node){
        return StringUtils.isBlank(node.getPath()) || (node.getPath().startsWith("{") && node.getPath().endsWith("}"));
    }

    private DBResult getDefaultDbInfoByPath(List<String> path, String type){
        String pathStr = String.join("/", path);
        if(path.size() == 1){
            return DBTree.builder().nodes(
                    Arrays.asList(
                            TreeNode.builder()
                                    .type("folder")
                                    .leaf(false)
                                    .path(pathStr + "/schemas")
                                    .label(getPathLabel("schemas"))
                                    .queryDetail(false)
                                    .build()
                    )).build();
        } else if (path.size() == 2 && "schemas".equals(path.get(1))) {
            return DBTree.builder().nodes(
                    getSchemas().stream().map(s ->
                            TreeNode.builder()
                                    .type("schema")
                                    .leaf(false)
                                    .path(pathStr + "/" + s)
                                    .label(s)
                                    .queryDetail(true)
                                    .build())
                            .collect(Collectors.toList()))
                    .build();
        } else if (path.size() == 3 && "schemas".equals(path.get(1))) {
            if("tree".equals(type)){
                return DBTree.builder().nodes(
                        Arrays.asList(
                                TreeNode.builder()
                                        .type("folder")
                                        .leaf(false)
                                        .path(pathStr + "/tables-and-views")
                                        .label("表和视图")
                                        .queryDetail(false)
                                        .build()
                        )).build();
            }else {
                return DBProperties.builder().properties(
                        Arrays.asList(
                                DBProperty.builder()
                                        .label("表和视图")
                                        .path(pathStr + "/tables-and-views")
                                        .lazy(true)
                                        .build()
                        )).build();
            }
        } else if (path.size() == 4 && "schemas".equals(path.get(1)) && "tables-and-views".equals(path.get(3))) {
            List<DbTableOrView> tableOrViews = getAllTableOrViews(path.get(2));
            if("tree".equals(type)){
                return DBTree.builder().nodes(
                                tableOrViews.stream().map(t ->
                                                TreeNode.builder()
                                                        .type("table")
                                                        .leaf(true)
                                                        .path(pathStr + "/" + t.getName())
                                                        .label(t.getName())
                                                        .queryDetail(true)
                                                        .build())
                                        .collect(Collectors.toList()))
                        .build();
            } else {
                List<GridRow> rows = tableOrViews.stream().map(t ->
                    GridRow.builder()
                            .data(t)
                            .path(pathStr + "/" + t.getName())
                            .build()
                ).collect(Collectors.toList());
                return DBGrid.builder().rows(rows).columns(tableColumns).build();
            }
        } else if (path.size() == 5 && "schemas".equals(path.get(1)) && "tables-and-views".equals(path.get(3))) {
                return DBProperties.builder().properties(
                        Arrays.asList(
                                DBProperty.builder()
                                        .label("列")
                                        .path(pathStr + "/columns")
                                        .lazy(true)
                                        .build()
                        )).build();
        } else if (path.size() == 6 && "schemas".equals(path.get(1)) && "tables-and-views".equals(path.get(3)) && "columns".equals(path.get(5))) {
            List<DbColumn> columns = this.getColumns(path.get(2), path.get(4));
            List<GridRow> rows = columns.stream().map(c -> GridRow.builder().data(c).build()).collect(Collectors.toList());
            return DBGrid.builder().rows(rows).columns(columnColumns).build();
        }
        throw new ServiceException("参数错误");
    }

    private static List<GridColumn> tableColumns = Arrays.asList(
            GridColumn.builder().label("名称").value("name").build(),
            GridColumn.builder().label("注释").value("comment").build(),
            GridColumn.builder().label("是否是视图").value("view").build()
    );

    private static List<GridColumn> columnColumns = Arrays.asList(
            GridColumn.builder().label("列名").value("columnName").build(),
            GridColumn.builder().label("类型").value("dataType").build(),
            GridColumn.builder().label("是否为主键").value("primaryKey").build(),
            GridColumn.builder().label("能否为空").value("nullable").build(),
            GridColumn.builder().label("注释").value("comment").build()
    );


    @Override
    public DBResult getDbInfoByPath(List<String> path, String type) {
        String pathStr = String.join("/", path);
        PathNode root = getRoot(this.getClass());
        if(root.getChildren().isEmpty()){
            return getDefaultDbInfoByPath(path, type);
        }
        PathNode node = getNode(root, path);
        if("tree".equals(type) && isParamPath(node)){
            List<TreeNode> treeNodes = node.getChildren().stream()
                    .filter(childNode -> childNode.getMethod()!=null && Collection.class.isAssignableFrom(childNode.getMethod().getReturnType()) && !childNode.getPath().startsWith("{") && !childNode.getPath().endsWith("}"))
                    .map(childNode ->
                            TreeNode.builder()
                                    .type("folder")
                                    .leaf(false)
                                    .path(pathStr + "/" + childNode.getPath())
                                    .label(getPathLabel(childNode.getPath()))
                                    .queryDetail(false)
                                    .build()).collect(Collectors.toList());
            return DBTree.builder().nodes(treeNodes).build();
        }

        if(node.getMethod() == null){
            throw new IllegalStateException("该路径不能查到库信息！" + pathStr);
        }
        Map<String, String> paramMap = getParamMap(node, path);
        Method method = node.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for(int i=0; i<parameters.length; i++){
            Parameter param = parameters[i];
            if (paramMap.containsKey(param.getName())) {
                args[i] = objectMapper.convertValue(paramMap.get(param.getName()), param.getType());
            } else {
                throw new IllegalArgumentException("未能注入参数" + param);
            }
        }
        Object result;
        try{
            result = method.invoke(this, args);
        }catch (IllegalAccessException e){
            throw new IllegalStateException(method + " 访问受限");
        }catch (InvocationTargetException e){
            throw new IllegalStateException(method + " 执行错误", e);
        }
        return resolveResult(path, result, node, type);
    }

    private DBResult resolveResult(List<String> path, Object result, PathNode node, String type){
        String pathStr = String.join("/", path);
        if(result instanceof String){
            return DBText.builder().text((String) result).textType(this.getDbType()).build();
        }
        if(result instanceof Collection){
            Collection<Object> collection = (Collection<Object>) result;
            Optional<Object> first = collection.stream().findFirst();
            if(!first.isPresent()){
                return "tree".equals(type)? DBTree.builder().nodes(new ArrayList<>()).build() : DBGrid.builder().columns(new ArrayList<>()).rows(new ArrayList<>()).build();
            }
            Class<?> clazz = first.get().getClass();
            boolean clazzIsPrimitive = clazz.isPrimitive();
            Method keyGetter = getPathKeyGetter(clazz);
            boolean hasNextDetail = node.getChildren().stream().anyMatch(childNode -> childNode.getMethod()!=null && childNode.getPath().startsWith("{") && childNode.getPath().endsWith("}"));
            boolean hasNextProp =
                    node.getChildren().stream()
                            .anyMatch(childNode -> childNode.getPath().startsWith("{") && childNode.getPath().endsWith("}") && !childNode.getChildren().isEmpty());
            boolean hasNext = hasNextDetail || hasNextProp;
            if("detail".equals(type)){
                List<GridRow> rows = collection.stream().map(obj -> {
                    String newPath = null;
                    if(hasNext){
                        try {
                            newPath = pathStr + "/" + keyGetter.invoke(obj);
                        } catch (IllegalAccessException | InvocationTargetException e) {

                        }
                    }
                    return GridRow.builder()
                            .data(clazzIsPrimitive? new ValueObject(obj) : obj)
                            .path(newPath)
                            .build();
                }).collect(Collectors.toList());
                return DBGrid.builder().rows(rows).columns(getGridColumns(clazz)).build();
            }else if("tree".equals(type)){
                DbObject dbObject = clazz.getAnnotation(DbObject.class);
                return DBTree.builder().nodes(
                        collection.stream().map(obj -> {
                            String newPath = null;
                            String key = null;
                            try{
                                key = keyGetter.invoke(obj).toString();
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                ///////
                            }
                            if(hasNext){
                                newPath = pathStr + "/" + key;
                            }
                            return TreeNode.builder()
                                    .label(key)
                                    .type(dbObject==null? null:dbObject.type())
                                    .queryDetail(hasNextDetail)
                                    .memo(null)
                                    .leaf(!hasNext)
                                    .path(newPath)
                                    .build();
                        }).collect(Collectors.toList())
                ).build();
            }
        }

        Class<?> clazz = result.getClass();
        if(clazz.isAnnotationPresent(DbObject.class)){
            DbObject dbObject = clazz.getAnnotation(DbObject.class);
            List<String[]> nextPathList = node.getChildren().stream()
                    .filter(n -> n.getMethod()!=null && !n.getPath().startsWith("{") && !n.getPath().endsWith("}"))
                    .map(n -> new String[]{n.getPath() ,pathStr + "/" + n.getPath()})
                    .collect(Collectors.toList());
            if("detail".equals(type)){
                List<DBProperty> properties = getProps(clazz).stream().map(p -> {
                    Object fValue = null;
                    try{
                        fValue = p.getGetter().invoke(result);
                    }catch (IllegalAccessException | InvocationTargetException e){
                        //////////////////////////////
                    }
                    return DBProperty.builder()
                            .lazy(false)
                            .label(p.getLabel())
                            .original(p.isOriginal())
                            .value(fValue)
                            .build();
                }).collect(Collectors.toList());
                properties.addAll(nextPathList.stream().map(arr -> DBProperty.builder()
                        .lazy(true)
                        .label(getPathLabel(arr[0]))
                        .path(arr[1])
                        .build()).collect(Collectors.toList()));
                return DBProperties.builder().properties(properties).build();
            } else if ("tree".equals(type)) {
                return DBTree.builder().nodes(
                        nextPathList.stream().map(
                                arr -> TreeNode.builder()
                                        .type("folder")
                                        .leaf(false)
                                        .path(getPathLabel(arr[1]))
                                        .label(arr[0])
                                        .queryDetail(false)
                                        .build()
                        ).collect(Collectors.toList())
                ).build();
            }
        }
        throw new IllegalStateException("无法解析类型："+ clazz);
    }

    private static Map<Class<?>, Method> class_pathKeyGetter_map = new ConcurrentHashMap<>();

    private static Method getPathKeyGetter(Class<?> clazz){
        return class_pathKeyGetter_map.computeIfAbsent(clazz, c -> {
            if(c.isPrimitive()){
                try {
                    return c.getMethod("toString");
                } catch (NoSuchMethodException e) {
                    throw new IllegalStateException(e);
                }
            }
            for(PropertyDescriptor propertyDescriptor : BeanUtils.getPropertyDescriptors(clazz)){
                Method getter = propertyDescriptor.getReadMethod();
                if(getter == null){
                    continue;
                }
                if(getter.isAnnotationPresent(DbObjectKey.class)){
                    return getter;
                }
                Field field = ReflectionUtils.findField(clazz, propertyDescriptor.getName());
                if(field != null && field.isAnnotationPresent(DbObjectKey.class)){
                    return getter;
                }
            }
            throw new IllegalArgumentException(clazz + " 没有pathkey字段");
        });
    }

    private static Map<Class<?>, List<GridColumn>> class_columns_map = new ConcurrentHashMap<>();

    private static List<GridColumn> getGridColumns(Class<?> clazz){
        return class_columns_map.computeIfAbsent(clazz, c -> {
            if(c.isPrimitive()){
                return Arrays.asList(
                        GridColumn.builder()
                            .value("value")
                            .label("值")
                            .original(true)
                            .build());
            }

            List<GridColumn> columns = new ArrayList<>();
            Map<String, Integer> field_seq_map = new HashMap<>();
            for(PropertyDescriptor propertyDescriptor : BeanUtils.getPropertyDescriptors(clazz)){
                String propName = propertyDescriptor.getName();
                Method getter = propertyDescriptor.getReadMethod();
                if(getter == null){
                    continue;
                }
                DbProp dbProp = getter.getAnnotation(DbProp.class);
                if(dbProp == null){
                    Field field = ReflectionUtils.findField(clazz, propertyDescriptor.getName());
                    if(field != null){
                        dbProp = field.getAnnotation(DbProp.class);
                    }
                }
                if(dbProp != null && dbProp.showInGrid()){
                    columns.add(
                            GridColumn.builder()
                                    .value(propName)
                                    .label(dbProp.label())
                                    .original(dbProp.original())
                                    .build()
                    );
                    field_seq_map.put(propName, dbProp.seq());
                }
            }
            columns.sort(Comparator.comparingInt(col -> field_seq_map.get(col.getValue())));
            return columns;
        });
    }

    private static Map<Class<?>, List<DbPropGetter>> class_props_map = new ConcurrentHashMap<>();

    private static List<DbPropGetter> getProps(Class<?> clazz){
        return class_props_map.computeIfAbsent(clazz, c -> {
            List<DbPropGetter> props = new ArrayList<>();
            Map<String, Integer> getter_seq_map = new HashMap<>();
            for(PropertyDescriptor propertyDescriptor : BeanUtils.getPropertyDescriptors(clazz)){
                Method getter = propertyDescriptor.getReadMethod();
                if(getter == null){
                    continue;
                }
                DbProp dbProp = getter.getAnnotation(DbProp.class);
                if(dbProp == null){
                    Field field = ReflectionUtils.findField(clazz, propertyDescriptor.getName());
                    if(field != null){
                        dbProp = field.getAnnotation(DbProp.class);
                    }
                }
                if(dbProp != null && dbProp.showInProp()){
                    props.add(
                            DbPropGetter.builder()
                                    .label(dbProp.label())
                                    .original(dbProp.original())
                                    .getter(getter)
                                    .build()
                    );
                    getter_seq_map.put(getter.getName(), dbProp.seq());
                }
            }
            props.sort(Comparator.comparingInt(prop -> getter_seq_map.get(prop.getGetter().getName())));
            return props;
        });
    }


    private static Map<String, String> getParamMap(PathNode node, List<String> path){
        Map<String, String> paramMap = new HashMap<>();
        int index = path.size() - 1;

        while (index>=0 && node!=null && StringUtils.isNotBlank(node.getPath())){
            if(node.getPath().startsWith("{") && node.getPath().endsWith("}")){
                paramMap.put(node.getPath().substring(1, node.getPath().length() - 1), path.get(index));
            }
            index --;
            node = node.getFather();
        }
        return paramMap;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ValueObject{
        private Object value;
    }

}
