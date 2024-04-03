package cn.topcheer.pms2.common.utils;

import cn.topcheer.pms2.common.enumerate.EnumBase;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

@Slf4j
public class EnumBaseUtils {
    public static R getEnumParams(String packageName,String enumName){
        try {
            List<String> classNames = new ArrayList<>();
            List<Class<?>> classList=getClasses(packageName);
            if(enumName!=null)
            {
                classList = classList.stream().filter(item->item.getName().equals(enumName.substring(0, 1).toUpperCase() + enumName.substring(1))).collect(Collectors.toList());
            }
//            if(enumName == null){
//                //***** 这种方式调试模式下可以，但打包后运行就不是文件协议了,无法获取到
//                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//                String pathName = packageName.replace(".", "/");
//                Enumeration<URL> enumeration = classLoader.getResources(pathName);
//                while (enumeration.hasMoreElements()) {
//                    URL url = enumeration.nextElement();
//                    if (url.getProtocol().equals("file")) {
//                        File curFile = new File(url.toURI());
//                        for (File f : curFile.listFiles()){
//                            //获取文件名,并删除后缀
//                            String fileName = f.getName();
//                            fileName = fileName.substring(0,fileName.lastIndexOf("."));
//                            //添加到结果中
//                            classNames.add(fileName);
//                        }
//                    }
//                    else if(url.getProtocol().equals("jar"))
//                    {
//
//                    }
//                    log.info("enumeration: {}", JSON.toJSONString(url));
//                }
//            }else {
//                // 参数转大写
//                String className = enumName.substring(0, 1).toUpperCase() + enumName.substring(1);
//                classNames.add(className);
//            }
//            log.info("classNames:{}",JSON.toJSONString(classNames));
//
//            HashMap<String, List> categories = new HashMap<>();
//            if(classNames.size()>0){
//                classNames.forEach(className ->{
//                    try {
//                        // 通过完整的类名获取
//                        Class<Enum> clazz = (Class<Enum>)Class.forName(packageName+"."+className);
//
//                        if(EnumBase.class.isAssignableFrom(clazz)){
//                            Method method = clazz.getMethod("values");
//                            EnumBase[] enumBases = (EnumBase[]) method.invoke(null);
//
//                            List<Map> list = new ArrayList<>();
//                            for(EnumBase enumBase:enumBases){
//                                HashMap<String, String> hashMap = new HashMap<>();
//                                hashMap.put("name",enumBase.name());
//                                hashMap.put("describe",enumBase.getDescribe());
//                                list.add(hashMap);
//                            }
//                            categories.put(className,list);
//                        }
////                        else
////                            return R.fail("所要查找的枚举没有继承EnumBase接口");
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }catch (NoSuchMethodException e){
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }

            HashMap<String, List> categories = new HashMap<>();
            classList.forEach(clazz->{
                if(EnumBase.class.isAssignableFrom(clazz)){
                    Method method = null;
                    try {
                        method = clazz.getMethod("values");
                        EnumBase[] enumBases = (EnumBase[]) method.invoke(null);
                        List<Map> list = new ArrayList<>();
                        for(EnumBase enumBase:enumBases){
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("name",enumBase.name());
                            hashMap.put("describe",enumBase.getDescribe());
                            list.add(hashMap);
                        }
                        categories.put(clazz.getSimpleName(),list);

                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                }
            });

            return R.data(categories);
        } catch (Exception e) {
            return R.fail("查找枚举类失败，"+e.getMessage());
        }
    }




    /**
     * 从包package中获取所有的Class
     * @param packageName
     * @return
     */
    public static List<Class<?>> getClasses(String packageName){

        //第一个class类的集合
        List<Class<?>> classes = new ArrayList<Class<?>>();
        //是否循环迭代
        boolean recursive = true;
        //获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', '/');
        //定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            //循环迭代下去
            while (dirs.hasMoreElements()){
                //获取下一个元素
                URL url = dirs.nextElement();
                //得到协议的名称
                String protocol = url.getProtocol();
                //如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    //获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    //以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);


                } else if ("jar".equals(protocol)){
                    //如果是jar包文件
                    //定义一个JarFile
                    JarFile jar;
                    try {
                        //获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        //从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        //同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            //如果是以/开头的
                            if (name.charAt(0) == '/') {
                                //获取后面的字符串
                                name = name.substring(1);
                            }
                            //如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                //如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    //获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                //如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive){
                                    //如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        //去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            //添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }


    /**
     * 以文件的形式来获取包下的所有Class
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes){
        //获取此包的目录 建立一个File
        File dir = new File(packagePath);
        //如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        //如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        //循环所有文件
        for (File file : dirfiles) {
            //如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
                        file.getAbsolutePath(),
                        recursive,
                        classes);
            }
            else {
                //如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    //添加到集合中去
                    classes.add(Class.forName(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
