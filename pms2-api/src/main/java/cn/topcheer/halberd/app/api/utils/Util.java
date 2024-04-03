package cn.topcheer.halberd.app.api.utils;

import cn.topcheer.pms2.api.annotation.FieldOthername;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.transform.BasicTransformerAdapter;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {


    public static final String linkNumber = "0851-85817379";


    public static final BasicTransformerAdapter ALIAS_TO_LOWER_TO_ENTITY_MAP = new AliasToLowerToEntityMapResultTransformer();


    public static boolean isEoN(Object testStr) {
        return (testStr == null || testStr.toString().trim().equals("") || testStr.toString().trim().equals("null")
                || testStr.toString().trim().equals("undefined"));//|| testStr.toString().trim().equals("[]")|| testStr.toString().trim().equals("{}")
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str) || "null".equals(str);
    }


    /**
     * 生产GUID
     *
     * @return UUID{...}格式
     */
    public static String NewGuid() {

        return UUID.randomUUID().toString().toUpperCase();
    }


    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if (inet != null) {
                    ip = inet.getHostAddress();
                }
            }
        }
        return ip;
    }

    /**
     * 这个方法只是不用你传request参数,他还是会从线程变量里去获取request,如果不是http请求,可能获取不到request
     *
     * @return 如果不是http请求, 返回null
     */
    public static String getIPWithoutRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            //这个类没有用日志，所以只能用System.out.println打印信息了
            System.out.println("ERROR:Util.getIPWithoutRequest()方法获取不到request对象，可能不是http请求:requestAttributes==null");
            return null;
        }
        HttpServletRequest request = requestAttributes.getRequest();


        return getIpAddr(request);
    }

    public static Object ApplyObject(Object obj, JSONObject json) {
        if (json == null) {
            return obj;
        }
        if (obj == null) {
            return obj;
        }


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.readerForUpdating(obj).readValue(json.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }


    /**
     * JSON字段转实体-下划线字段自动转驼峰
     *
     * @param obj  Object
     * @param json JSONObject
     * @return Object
     */
    public static Object ApplyObjectNew(Object obj, JSONObject json) {
        if (json == null) {
            return obj;
        }

        if (obj == null) {
            return obj;
        }

        // JSON里面的字段下划线转驼峰
        json = jsonToHump(json);

        // 字段赋值
        return ApplyObject(obj, json);
    }


    private static final Pattern humpPattern = Pattern.compile("[A-Z]");


    /**
     * 驼峰转下划线
     *
     * @param str 驼峰字符串
     * @return 下划线字符串
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static final Pattern linePattern = Pattern.compile("_(\\w)");


    /**
     * 下划线转驼峰
     *
     * @param str 下划线字符串
     * @return 驼峰字符串
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();

    }


    /**
     * JSON里面的字段下划线转驼峰
     *
     * @param json JSONObject
     * @return JSONObject
     */
    public static JSONObject jsonToHump(JSONObject json) {
        JSONObject object = new JSONObject();
        for (Object key : json.keySet()) {
            String keyStr = key.toString();
            object.put(lineToHump(keyStr), json.get(keyStr));
        }

        return object;
    }


    /**
     * 判断该字符串是否可以转换为JSONObject
     *
     * @param jsonObjectStr 需判断的json字符串
     * @return 可以转换返回true，否则返回false
     */
    public static boolean isJSONObject(String jsonObjectStr) {
        try {
            JSONObject jsonObject = JSONObject.fromObject(jsonObjectStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取系统文件目录 linux /PMSFILES   windows D:/PMSFILES   mac /PMSFILES
     */
    public static String GetFileRealPath(String filePath) {
        Properties props = System.getProperties(); //获得系统属性集
        String osName = props.getProperty("os.name"); //操作系统名称
        String urlPrefix = "";
        if (osName.contains("Windows")) {
            urlPrefix = "D://PMSFILES/";
        } else if (osName.contains("Mac")) {
            urlPrefix = "/Users/PMSFILES/";
        } else {
            urlPrefix = "/PMSFILES/";
        }
//        去掉文件路径中可能存在的项目运行真实路径
        filePath = filePath.replaceAll("/usr/share/apache-tomcat-9.0.54/webapps/ROOT/", "");
//        去掉文件路径中的其他开头路径
        int indexOf = filePath.indexOf("PMSFILES");
        if (indexOf != -1) {
            filePath = filePath.substring(indexOf + "PMSFILES".length(), filePath.length());
        }
        indexOf = filePath.indexOf(":");
        if (indexOf != -1) {
            filePath = filePath.substring(indexOf + 1, filePath.length());
        }
        filePath = urlPrefix + filePath;
        //        将文件路径中的"\"替换成"/"为了Linux中的文件读取
        filePath = filePath.replaceAll("\\\\", "/");
//        如果路径后面不包含文件类型，则默认为文件夹，文件夹后面都给添加上 ”/“
        if (filePath.indexOf(".") == -1) {
            filePath += "/";
        }
//        将文件路径中可能存在的连续两个"/"替换成一个
        while (filePath.indexOf("//") != -1) {
            filePath = filePath.replaceAll("//", "/");
        }

        return filePath;
    }

    /**
     * 判断该字符串是否可以转换为JSONArray
     *
     * @param jsonArrayStr 需判断的json字符串
     * @return 可以转换返回true，否则返回false
     */
    public static boolean isJSONArray(String jsonArrayStr) {
        try {
            JSONArray jsonArray = JSONArray.fromObject(jsonArrayStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // list的Where方法
    public static <T> List<T> ListDoWhere(List<T> list, String FieldName, Object Value) {
        List<T> reslist = new ArrayList<T>();
        if (list == null) {
            return reslist;
        }
        for (int i = 0; i < list.size(); i++) {
            T obj = list.get(i);
            Object myobj = getFieldValue(obj, FieldName);
            if (myobj != null) {
                if (myobj.equals(Value)) {
                    reslist.add(obj);
                }
            } else if (Value == null) {
                reslist.add(obj);
            }
        }
        return reslist;
    }

    public static Object getFieldValue(Object obj, String fieldname) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.containsKey(fieldname)) {
                return map.get(fieldname);
            } else {
                return null;
            }
        } else {

            if (fieldname.length() > 0) {
                String methodname = "get" + fieldname.substring(0, 1).toUpperCase()
                        + fieldname.substring(1, fieldname.length());
                String methodname1 = "is" + fieldname.substring(0, 1).toUpperCase()
                        + fieldname.substring(1, fieldname.length());
                Method m;
                try {
                    m = obj.getClass().getMethod(methodname);
                    return m.invoke(obj);
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    try {
                        m = obj.getClass().getMethod(methodname1);
                        return m.invoke(obj);
                    } catch (NoSuchMethodException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (SecurityException e1) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e1) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e1) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    //e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            return null;
        }
    }


    /**
     * 动态Sql语句IN参数拼接
     *
     * @param array      参数列表，用于计算需要生成多少个问号(?)
     * @param objectname 需匹配的字段名，例如：t.id（推荐），或者id
     * @return 例如：t.id in ( ?, ? ) 或者 t.id in ( ?, ? ) or t.id in ( ?, ? )、“”
     */
    public static String sqlSplicingForInStatement(JSONArray array, String objectname) {
        StringBuilder insql = new StringBuilder(" ");
        //in最多支持传1000个参数，推荐不要太多
        if (array.size() > 0) {
            int loopSize = (int) Math.ceil(array.size() / 500.0);
            for (int i = 0; i < loopSize; i++) {
                if (i == 0) {
                    insql.append(" " + objectname + " in ( ");
                } else {
                    insql.append(" or " + objectname + " in ( ");
                }
                for (int j = i * 500; j < (i + 1) * 500 && j < array.size(); j++) {
                    insql.append(" ?, ");
                }
                insql.delete(insql.length() - 2, insql.length());
                insql.append(" ) ");
            }
        }
        return insql.toString();
    }

    /**
     * @param serviceName 服务名称
     * @param methodName  方法名称
     * @param params      参数
     * @return
     * @throws Exception
     */
    public static Object springInvokeMethod(String serviceName, String methodName, Object[] params) throws Exception {
        //Object service = applicationContext.getBean(serviceName);
        Object service = SpringUtil.getBean(serviceName);
        Class<? extends Object>[] paramClass = null;
        if (params != null) {
            int paramsLength = params.length;
            paramClass = new Class[paramsLength];
            for (int i = 0; i < paramsLength; i++) {
                if (params[i] == null) {
                    params[i] = "";
                }

                paramClass[i] = params[i].getClass();
            }
        }

        // 找到方法
        Method method = ReflectionUtils.findMethod(service.getClass(), methodName, paramClass);
        // 执行方法
        return ReflectionUtils.invokeMethod(method, service, params);
    }


    /**
     * 转义特殊字符
     *
     * @param content
     * @return
     */
    public static String convertFormatString(String content) {
        if (content != null) {
            content = content.replaceAll("&", "&amp;");
            content = content.replaceAll("<", "&lt;");
            content = content.replaceAll(">", "&gt;");
            content = content.replaceAll("\n", "<w:br/>");
            content = content.replaceAll("\\u0003", "");//老系统乱码数据替换
        } else {
            content = "";
        }
        return content;
    }


    // 判断一个字符是否是uuid
    public static boolean isUUID(Object str) {
        if (Util.isEoN(str) || str.toString().length() > 50) {
            return false;
        }
        try {
            UUID taruuid = UUID.fromString(str.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 【解析】--前台传的参数是否带有乱码  ？？？ ???  或者  为空
     *
     * @param example
     * @return
     */
    public static JSONObject checkFromJson(String example) {
        if (example.contains("???") || example.contains("？？？") || "{}".equals(example) || example == null || "".equals(example)) {
            return null;
        } else {
            return JSONObject.fromObject(example);
        }
    }


    public static Date parseDate(String date, String format) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        if (date.indexOf("CST") != -1) {
            Date dateLocal = parse(date, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            try {
                return df.parse(df.format(dateLocal));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            return df.parse(date.toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }


    public static Date parse(String str, String pattern, Locale locale) {
        if (str == null || pattern == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> String fetchFieldName(Class<T> tClass, String fieldOtherName) {
        String result = null;
        try {
            result = tClass.getDeclaredField(fieldOtherName).getName();
        } catch (Exception e) {
            Field[] declaredFields = tClass.getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(FieldOthername.class) && field.getAnnotation(FieldOthername.class).value().equals(fieldOtherName)) {
                    result = field.getName();
                    break;
                }
            }
        }
        return result;
    }


    public static Method getField(Object obj, String fieldname) {
        if (obj == null)
            return null;
        if (fieldname.length() > 0) {
            String methodname = "get" + fieldname.substring(0, 1).toUpperCase()
                    + fieldname.substring(1, fieldname.length());
            Method m;
            try {
                m = obj.getClass().getMethod(methodname);
                return m;
            } catch (NoSuchMethodException e) {
                System.out.println(obj.getClass().getName() + "中未找到方法 :" + methodname);
                // TODO Auto-generated catch block
                // e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<String> getFiledList(Object obj) {
        List<String> list = new ArrayList<String>();
        try {
            Class clazz = obj.getClass();
            Field[] fields = obj.getClass().getDeclaredFields();// 获得属性
            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();// 获得get方法
                String filed = "";//如果是IS开头的区两位 我也是醉了-_-!!!!
                if (getMethod.getName().toLowerCase().startsWith(("is"))) {
                    filed = getMethod.getName().substring(2);
                } else
                    filed = getMethod.getName().substring(3);
                list.add(filed);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();

        }
        return list;
    }


    public static void setFieldValue(Object obj, String fieldname, Object value) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        // if(fieldname.length()>0)
        // {
        // String
        // methodname="set"+fieldname.substring(0,1).toUpperCase()+fieldname.substring(1,
        // fieldname.length());
        // Method m=obj.getClass().getMethod(methodname,value.getClass());
        // m.invoke(obj,value);
        // }
        if (value != null)
            setFieldValue(obj, fieldname, value, value.getClass());
        else
            setFieldValue(obj, fieldname, value, null);

    }

    public static Method getMethod(Class cls, String methodName) {
        if (cls == null)
            return null;
        for (Method m : cls.getMethods()) {
            if (m.getName().equals(methodName))
                return m;
        }
        return null;
    }

    public static boolean CopyFromOneToOther(Object from, Object to, String Exception) {
        List<String> Field = getFiledList(from);
        if (Exception == null)
            Exception = "";
        for (int i = 0; i < Field.size(); i++) {
            if (!("," + Exception + ",").contains(Field.get(i))) {
                Object value = getFieldValue(from, Field.get(i));
                if (value != null) {
                    try {
                        setFieldValue(to, Field.get(i), value, value.getClass());
                        // setField(to, Field.get(i), value);
                    } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    /**
     * 当属性类型为接口时，传入的实际对象class不等于接口，会导致 NoSuchMethodException 异常
     *
     * @param obj
     * @param fieldname
     * @param value
     * @param valueClass
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author ly
     */
    public static void setFieldValue(Object obj, String fieldname, Object value, Class<?> valueClass)
            throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        if (obj == null)
            return;

        if (obj instanceof Map) {
            Map map = (Map) obj;
            map.put(fieldname, value);
        } else {

            if (fieldname.length() > 0) {

                String methodname = "set" + fieldname.substring(0, 1).toUpperCase()
                        + fieldname.substring(1, fieldname.length());
                try {
                    Method m = null;
                    // if (valueClass != null)
                    // {
                    m = Util.getMethod(obj.getClass(), methodname);// obj.getClass().getMethod(methodname);
                    // } else
                    // m = Util.getMethod(obj.getClass(), methodname);
                    if (m != null)
                        m.invoke(obj, value);
                    else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void setFieldValueEx(Object obj, String fieldName, Object value) {

        try {
            int dotIndex = fieldName.indexOf(".");
            if (dotIndex > 0) {
                Object subObj = getFieldValue(obj, fieldName.substring(0, dotIndex));
                setFieldValueEx(subObj, fieldName.substring(dotIndex + 1), value);
            } else {
                setFieldValue(obj, fieldName, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Object getFieldValueEx(Object obj, String fieldName) {
        int dotIndex = fieldName.indexOf(".");
        try {
            if (dotIndex > 0) {
                Object subObj = getFieldValue(obj, fieldName.substring(0, dotIndex));
                return getFieldValueEx(subObj, fieldName.substring(dotIndex + 1));
            } else {
                return getFieldValue(obj, fieldName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 下划线转驼峰
     *
     * @param input                 有下划线的字符串
     * @param capitalizeFirstLetter 是否首个大写, 默认否
     * @return 驼峰字符串
     */
    public static String toCamelCase(String input, boolean capitalizeFirstLetter) {

        // 处理下划线
        StringBuilder result = new StringBuilder();
        String[] words = input.split("_");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0 && !capitalizeFirstLetter) {
                result.append(word);
            } else {
                result.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    result.append(word.substring(1));
                }
            }
        }

        return result.toString();
    }


    /**
     * JSON的key下划线转驼峰
     *
     * @param json JSONObject
     * @return JSONObject
     */
    public static JSONObject jsonKeyToCamelCase(JSONObject json) {
        JSONObject newJson = new JSONObject();

        for (Object key : json.keySet()) {
            String keyStr = key.toString();
            newJson.put(toCamelCase(keyStr, false), json.get(keyStr));
        }

        return newJson;
    }


    /**
     * Map的key下划线转驼峰
     *
     * @param map Map
     * @return Map
     */
    public static Map mapKeyToCamelCase(Map map) {
        JSONObject newJson = new JSONObject();

        for (Object key : map.keySet()) {
            String keyStr = key.toString();
            newJson.put(toCamelCase(keyStr, false), map.get(keyStr));
        }

        return newJson;
    }


    /**
     * 如果为空，那就转空字符串
     *
     * @param str 字符串
     * @return 空字符串
     * @author szs
     * @date 2024-03-08
     */
    public static String ifEmptyToStr(String str) {

        return StringUtil.isBlank(str) ? "" : str;
    }

    public static String readWord(StringBuffer Str, String split) {
        String rv = "";
        int index = Str.indexOf(split);
        if (index > -1) {
            rv = Str.substring(0, index);
            Str.delete(0, index + split.length());
        } else {
            rv = Str.toString();
            Str.delete(0, Str.length());
        }
        return rv;
    }
}
