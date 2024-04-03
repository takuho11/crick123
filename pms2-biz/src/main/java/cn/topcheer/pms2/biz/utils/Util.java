package cn.topcheer.pms2.biz.utils;

import cn.topcheer.pms2.api.annotation.FieldOthername;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.PmsProjectbaseService;
import cn.topcheer.pms2.biz.project.service.impl.enumUtil.TableMappingEnum;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.hibernate.transform.BasicTransformerAdapter;
import org.json.XML;
import org.springblade.core.tool.utils.SpringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Util {
    public final static int DateType_START = 1;
    public final static int DateType_CURRENT = 2;
    public final static int DateType_END = 3;

    public static final boolean isProduct = JudgeIsWorking();

    public static final boolean isTest = JudgeIsTestWorking();

    public static final boolean isProductReview = JudgeIsWorkingForReview();
    /**
     * 当前IOC
     */
    public static ApplicationContext applicationContext;

    /**
     * createSqlQuery toList转list<Map> map key值保持小写
     */
    public static final BasicTransformerAdapter ALIAS_TO_LOWER_TO_ENTITY_MAP = new AliasToLowerToEntityMapResultTransformer();


    public static final String linkNumber = "0851-85817379";

    /**
     * 评审技术支持电话
     */
    public static final String linkNumber_review = "0851-85817379";

    public static final String REVIEW_URL = "todo_guizhou_review_url";

    public static final File RUNNING_PATH_FILE = new File("");

    //public static final String messageUrl = "http://111.41.51.120:8611/HljMessage/";
    //public static final String messageUrl = "http://localhost:8661/HljMessage/";
    //public static final String messageUrl = "http://localhost:8111/HljMessage/";
    //public static final String messageUrl = "http://111.40.160.180:8611/HljMessage/";
    public static String messageUrl;

//    @Value("${pms.messageUrl}")
//    public void setMessageUrl(String messageUrl) {
//        this.messageUrl = messageUrl;
//    }


    //联通的
    public static final String UNUrl = "http://localhost:8661/UNMessage/";
    /**
     * 获取Bean对象
     *
     * @param id BeanName
     * @return 返回Bean
     */
    public static Object getBeanObject(String id) {
        Object object = null;
        object = applicationContext.getBean(id);
        return object;
    }

    /**
     * 生产GUID
     *
     * @return UUID{...}格式
     */
    public static String NewGuid() {
        String uid = UUID.randomUUID().toString().toUpperCase();
        return uid;
    }

    /**
     * 获取JOSONObject对象的PropertyName属性值
     *
     * @param obj          JSONObject 对象
     * @param propertyName 属性
     * @return
     */
    public static Object getJSONValue(JSONObject obj, String propertyName) {
        Object rv = null;
        if (obj == null)
            return null;
        if (!obj.has(propertyName))
            return null;
        try {
            rv = obj.get(propertyName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rv;
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

    public static String readWord(String Str, String b_flag, String e_flag) {
        String rv = "";
        int b_index = Str.indexOf(b_flag);
        int e_index = Str.indexOf(e_flag);
        if (b_index > -1 && e_index > b_index) {
            rv = Str.substring(b_index + b_flag.length(), e_index);

        } else {
            rv = "";

        }
        return rv;
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

    public static Object getFieldValue(Object obj, String fieldname) {
        if (obj == null)
            return null;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.containsKey(fieldname))
                return map.get(fieldname);
            else
                return null;
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
     * 传入两个对象，返回值不一样的字段和值
     *
     * @param objA old
     * @param objB new
     * @return
     */  //
    public static JSONArray compareTwoObj(Object objA, Object objB, String objName) {
        JSONArray jsonArray = new JSONArray();
        List<String> FieldA = getFiledList(objA);
        List<String> FieldB = getFiledList(objB);
        for (int i = 0; i < FieldA.size(); i++) {
            Object valueA = getFieldValue(objA, FieldA.get(i));
            for (int j = 0; j < FieldB.size(); j++) {
                if (FieldA.get(i).toLowerCase().trim().equals(FieldB.get(j).toLowerCase().trim())) {
                    Object valueB = getFieldValue(objB, FieldB.get(j));

                    if (!Util.isEoN(valueA) && !valueA.equals(valueB)) {
                        if (valueA instanceof Date) {
                            if (Util.isEoN(valueB) || ((Date) valueA).getTime() != ((Date) valueB).getTime()) {
                                valueA = (Date) valueA;
                                JSONArray valueArray = new JSONArray();
                                JSONObject jsonObjectA = new JSONObject();
                                // 时间转成年月日 时分秒
                                if (valueA.toString().indexOf("CST") == -1) {
                                    jsonObjectA.put(FieldA.get(i), Util.formatDate(Util.parseDate(valueA + ""), "yyyy-MM-dd HH:mm:ss"));
                                } else {
                                    jsonObjectA.put(FieldA.get(i), Util.formatDate((Date) valueA, "yyyy-MM-dd HH:mm:ss"));
                                }
                                jsonObjectA.put("field_nameXX", getFileName(FieldA.get(i), objName));
                                jsonObjectA.put("old", true);
                                JSONObject jsonObjectB = new JSONObject();
                                if (Util.isEoN(valueB))
                                    jsonObjectB.put(FieldB.get(j), valueB);
                                else if (valueB.toString().indexOf("CST") == -1)
                                    jsonObjectB.put(FieldB.get(j), Util.formatDate(Util.parseDate(valueB + ""), "yyyy-MM-dd HH:mm:ss"));
                                else
                                    jsonObjectB.put(FieldB.get(j), Util.formatDate((Date) valueB, "yyyy-MM-dd HH:mm:ss"));
                                jsonObjectB.put("new", true);
                                valueArray.add(jsonObjectA);
                                valueArray.add(jsonObjectB);
                                jsonArray.add(valueArray);
                            }
                        } else {
                            //valueArray 添加的object 的顺序很重要
                            JSONArray valueArray = new JSONArray();
                            JSONObject jsonObjectA = new JSONObject();
                            if (Util.isEoN(valueA)) {
                                jsonObjectA.put(FieldA.get(i), valueA);
                            } else if (valueA instanceof Date) {
                                if (valueA.toString().indexOf("CST") == -1) {
                                    jsonObjectA.put(FieldA.get(i), Util.formatDate(Util.parseDate(valueA + ""), "yyyy-MM-dd HH:mm:ss"));
                                } else {
                                    jsonObjectA.put(FieldA.get(i), Util.formatDate((Date) valueA, "yyyy-MM-dd HH:mm:ss"));
                                }
                            } else {
                                jsonObjectA.put(FieldA.get(i), valueA);
                            }
                            jsonObjectA.put("field_nameXX", getFileName(FieldA.get(i), objName));
                            jsonObjectA.put("old", true);
                            JSONObject jsonObjectB = new JSONObject();
                            if (Util.isEoN(valueB)) {
                                jsonObjectB.put(FieldB.get(j), valueB);
                            } else if (valueB instanceof Date) {
                                if (valueB.toString().indexOf("CST") == -1)
                                    jsonObjectB.put(FieldB.get(j), Util.formatDate(Util.parseDate(valueB + ""), "yyyy-MM-dd HH:mm:ss"));
                                else
                                    jsonObjectB.put(FieldB.get(j), Util.formatDate((Date) valueB, "yyyy-MM-dd HH:mm:ss"));
                            } else {
                                jsonObjectB.put(FieldB.get(j), valueB);
                            }
                            jsonObjectB.put("new", true);
                            valueArray.add(jsonObjectA);
                            valueArray.add(jsonObjectB);
                            jsonArray.add(valueArray);
                        }
                    } else if (valueA == null && !Util.isEoN(valueB)) {
                        JSONArray valueArray = new JSONArray();
                        JSONObject jsonObjectA = new JSONObject();
                        if (Util.isEoN(valueA)) {
                            jsonObjectA.put(FieldA.get(j), valueA);
                        } else if (valueA instanceof Date) {
                            if (valueA.toString().indexOf("CST") == -1) {
                                jsonObjectA.put(FieldA.get(i), Util.formatDate(Util.parseDate(valueA + ""), "yyyy-MM-dd HH:mm:ss"));
                            } else {
                                jsonObjectA.put(FieldA.get(i), Util.formatDate((Date) valueA, "yyyy-MM-dd HH:mm:ss"));
                            }
                        } else {
                            jsonObjectA.put(FieldA.get(j), valueA);
                        }
                        jsonObjectA.put("old", true);
                        jsonObjectA.put("field_nameXX", getFileName(FieldA.get(i), objName));
                        JSONObject jsonObjectB = new JSONObject();
                        if (Util.isEoN(valueB)) {
                            jsonObjectB.put(FieldB.get(j), valueB);
                        } else if (valueB instanceof Date) {
                            if (valueB.toString().indexOf("CST") == -1)
                                jsonObjectB.put(FieldB.get(j), Util.formatDate(Util.parseDate(valueB + ""), "yyyy-MM-dd HH:mm:ss"));
                            else
                                jsonObjectB.put(FieldB.get(j), Util.formatDate((Date) valueB, "yyyy-MM-dd HH:mm:ss"));
                        } else {
                            jsonObjectB.put(FieldB.get(j), valueB);
                        }
                        jsonObjectB.put("new", true);
                        valueArray.add(jsonObjectA);
                        valueArray.add(jsonObjectB);
                        jsonArray.add(valueArray);
                    }
                }
            }
        }
        return jsonArray;
    }

    // 通过传入字段 判断取出字段名称
    private static String getFileName(String field, String objectName) {
        //验收申请
        if (!Util.isEoN(objectName) && objectName.equals("acceptanceApplication")) {
            JSONObject app = new JSONObject();
            app.put("remarks", "备注");
            app.put("acceptancedata", "验收资料准备情况");
            app.put("developmentcontent", "研发内容和指标完成情况");
            app.put("provincialfinancial", "省财政拨款经费");
            app.put("countyfinancial", "县部门配套经费");
            app.put("selffinancing", "自筹经费");
            app.put("budgetrequirements", "经费使用对照合同预算要求");
            app.put("projectprofile", "项目简介（包括项目研究开发的主要内容，与国内外同类技术的比较等）");
            app.put("contractrequirements", "项目合同规定的主要内容、技术经济指标及完成情况");
            app.put("keytechnology", "关键技术及创新点、获自主知识产权情况、成果应用和产业化情况");
            app.put("use", "项目资金使用情况");
            app.put("datadirectory", "提供验收的技术资料目录");
            app.put("acceptanceform", "验收形式");
            app.put("acceptancelocation", "验收地点");
            app.put("acceptancetime", "验收时间");
            Iterator iteratorB = app.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return app.getString(key);
                }
            }
            return field;
        }
        //项目基本信息+研究内容+部分经费预算
        if (!Util.isEoN(objectName) && objectName.equals("poject")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("Showprojectbasename", "项目名称");
            projectObj.put("Chiefdepartment", "主管处室");
            projectObj.put("Chieldofficals", "项目主管");
            projectObj.put("Projectplantype", "计划类别");
            projectObj.put("Technologicalsource", "技术来源");
            projectObj.put("Technologicalmode", "创新方式");
            projectObj.put("Xmbzfs", "补助方式");
            projectObj.put("Startdate", "开始时间");
            projectObj.put("Enddate", "结束时间");

            projectObj.put("Researchcontent", "主要研发内容和关键技术内容");
            projectObj.put("Projectimplementation", "研发目标任务和主要技术经济指标（分别明确约束性指标与预期性指标）");


            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }
        //主办承担单位
        if (!Util.isEoN(objectName) && objectName.equals("dis_organization")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("Organizationname", "单位名称");
            projectObj.put("Organizationtype", "单位类型");
            projectObj.put("Economiccategory", "所有制类型");
            projectObj.put("Sshy", "所属行业");
            projectObj.put("Legalrepresentative", "法人代表");
            projectObj.put("Shehuixingyong", "社会信用代码");
            projectObj.put("Legalcode", "法人代码（组织机构代码）");
            projectObj.put("Certificatename", "证件类型");
            projectObj.put("Sfz", "法人代表身份证号");
            projectObj.put("Swdjzh", "税务登记证号");
            projectObj.put("Totalassets", "法人代表身份证号");
            projectObj.put("Sfz", "法人代表身份证号");
            projectObj.put("Swdjzh", "税务登记证号");
            projectObj.put("Totalassets", "注册资本（万元）");
            projectObj.put("Postcard", "邮政编码");
            projectObj.put("Organizationcontact", "联 系 人");
            projectObj.put("Mobile", "手 机");
            projectObj.put("Organizationphone", "联系电话/传真");
            projectObj.put("Organizaitonemail", "E-mail");
            projectObj.put("Accountbank", "开户银行");
            projectObj.put("Banknumber", "银行账号");
            projectObj.put("Commintucateaddress", "通讯地址");
            projectObj.put("Enterprisescale", "企业规模");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }
        //共同承担单位
        if (!Util.isEoN(objectName) && objectName.equals("unitbindings")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("Gngwdw", "国内外单位");
            projectObj.put("Organizationname", "单位名称");
            projectObj.put("Shehuixingyong", "社会信用代码");
            projectObj.put("Legalcode", "法人代码（组织机构代码）");
            projectObj.put("Organizationcontact", "联系人");
            projectObj.put("Organizationphone", "联系人手机");
            projectObj.put("Seq", "排序");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }

        //合作单位
        if (!Util.isEoN(objectName) && objectName.equals("organization")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("Gngwdw", "是否国外单位");
            projectObj.put("Organizationname", "单位名称");
            projectObj.put("Shehuixingyong", "社会信用代码");
            projectObj.put("Legalcode", "法人代码（组织机构代码）");
            projectObj.put("Organizationcontact", "联系人");
            projectObj.put("Organizationphone", "联系人手机");
            projectObj.put("Seq", "排序");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }
        //主要项目负责人
        if (!Util.isEoN(objectName) && objectName.equals("dis_projectparty")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("Name", "姓名");
            projectObj.put("Certificatename", "证件类型");
            projectObj.put("Certificatenumber", "证件号码");
            projectObj.put("Education", "学历");
            projectObj.put("Degree", "学位");
            projectObj.put("Professionalpost", "技术职务");
            projectObj.put("Workforprofession", "从事专业");
            projectObj.put("Mobilephone", "手 机");
            projectObj.put("Email", "Email");
            projectObj.put("Postaladdress", "通讯地址");
            projectObj.put("Workplace", "工作单位");
            projectObj.put("Postalcode", "邮政编码");
            projectObj.put("Shehuixingyong", "社会信用代码");
            projectObj.put("Legalcode", "法人代码（组织机构代码）");
            projectObj.put("Programrole", "项目分工");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }
        //项目组成员
        if (!Util.isEoN(objectName) && objectName.equals("projectparty")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("Gngwdw", "国内外人员");
            projectObj.put("Name", "姓名");
            projectObj.put("Certificatename", "证件类型");
            projectObj.put("Certificatenumber", "证件号码");
            projectObj.put("Mobilephone", "手 机");
            projectObj.put("Workplace", "工作单位");
            projectObj.put("Professionalpost", "技术职务");
            projectObj.put("Workforprofession", "从事专业");
            projectObj.put("Programrole", "项目分工");
            projectObj.put("Ncjsj", "年参加时间(人月)");
            projectObj.put("Seq", "排序");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }
        //仪器设备
        if (!Util.isEoN(objectName) && objectName.equals("instrument")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("Nameandtypecode", "名称及规格型号");
            projectObj.put("Instrumentcount", "数量(台/套)");
            projectObj.put("Instrumentprise", "单价(万元)");
            projectObj.put("Instrumenttotalsum", "金额(万元)");
            projectObj.put("Skjtbk", "省厅拨款(万元)");
            projectObj.put("Zcqt", "自筹或其他(万元)");
            projectObj.put("Capitaluse", "用途说明");
            projectObj.put("Seq", "排序");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }
        //计划进度
        if (!Util.isEoN(objectName) && objectName.equals("projectschedule")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("Schedulestartdate", "起止日期");
            projectObj.put("Scheduleenddate", "截止日期");
            projectObj.put("Scheduleachievement", "进度目标要求");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }

        //经费
        if (!Util.isEoN(objectName) && objectName.equals("jingfei")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("Zje", "金额");
            projectObj.put("Px", "排序");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }
        //经费信息
        if (!Util.isEoN(objectName) && objectName.equals("jfxx")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("Je", "金额");
            projectObj.put("Px", "排序");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }
        //预算
        if (!Util.isEoN(objectName) && objectName.equals("jfys")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("instrumentsum", "预算经费总额(设备费)");
            projectObj.put("equipmentone", "其中省科技厅经费(设备费)");
            projectObj.put("materialexpense", "预算经费总额(材料费)");
            projectObj.put("materialone", "其中省科技厅经费(材料费)");
            projectObj.put("proceedingscharges", "预算经费总额(测试化验加工费)");
            projectObj.put("processingtestone", "其中省科技厅经费(测试化验加工费)");
            projectObj.put("fuelandpower", "预算经费总额(燃料动力费)");
            projectObj.put("expenditureone", "其中省科技厅经费(燃料动力费)");
            projectObj.put("businesstripsum", "预算经费总额(差旅/会议/国际合作交流费)");
            projectObj.put("inlandtravelone", "其中省科技厅经费(差旅/会议/国际合作交流费)");
            projectObj.put("coferemce", "预算经费总额(会议费)");
            projectObj.put("conferenceone", "其中省科技厅经费(会议费)");
            projectObj.put("coorperationresearchsum", "预算经费总额(合作协作研究与交流费（国内合作）)");
            projectObj.put("collaborativeresearchone", "其中省科技厅经费(合作协作研究与交流费（国内合作）)");
            projectObj.put("adminstrativeservicesum", "预算经费总额(出版/文献/信息传播/知识产权事务费)");
            projectObj.put("publishedone", "其中省科技厅经费(出版/文献/信息传播/知识产权事务费)");
            projectObj.put("servicefee", "预算经费总额(人员劳务经费)");
            projectObj.put("laborone", "其中省科技厅经费(人员劳务经费)");
            projectObj.put("adviceforexpertsum", "预算经费总额(专家咨询费)");
            projectObj.put("consultingone", "其中省科技厅经费(专家咨询费)");
            projectObj.put("managesum", "预算经费总额(间接费用(包含管理费与激励费))");
            projectObj.put("managementone", "其中省科技厅经费(间接费用(包含管理费与激励费))");
            projectObj.put("ysjfotherfee", "预算经费总额(其他支出)");
            projectObj.put("kjtotherfee", "其中省科技厅经费(其他支出)");


            projectObj.put("Projecttotalsum", "研发总经费");
            projectObj.put("Jfbzjf", "甲方补助");
            projectObj.put("Projectsumforself", "乙方自筹");
            projectObj.put("Totalsum", "丙方配套");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }

        //科技报告
        if (!Util.isEoN(objectName) && objectName.equals("kjbg")) {
            JSONObject projectObj = new JSONObject();
            projectObj.put("titlechn", "报告中文名称");
            projectObj.put("titleeng", "报告英文名称");
            projectObj.put("projecttype", "报告类型");
            projectObj.put("publicscpe", "是否公开");
            projectObj.put("publicscope", "是否公开");
            projectObj.put("remarks", "延期备注");
            projectObj.put("undertakeorgcode", "组织机构代码");
            projectObj.put("writedate", "编制时间");
            projectObj.put("delaytime", "延期年限");
            projectObj.put("abstractchn", "中文摘要");
            projectObj.put("abstracteng", "英文摘要");
            projectObj.put("keywordschn", "中文关键字");
            projectObj.put("keywordseng", "英文关键字");
            projectObj.put("authornamecn", "作者(中文)");
            projectObj.put("authornameen", "作者(英文)");
            projectObj.put("authorunitcn", "单位(中文)");
            projectObj.put("authoruniten", "单位(英文)");
            projectObj.put("subject", "学科分类");
            projectObj.put("savedate", "保存时间");
            projectObj.put("submitdate", "上报时间");
            projectObj.put("Totalamount", "项目总经费");
            projectObj.put("Provinceamount", "省拨经费");
            projectObj.put("Partamount", "地方财政经费");
            projectObj.put("Countyamount", "县区财政经费");
            Iterator iteratorB = projectObj.keys();
            while (iteratorB.hasNext()) {
                String key = String.valueOf(iteratorB.next());
                if (field.toLowerCase().equals(key.toLowerCase())) {
                    return projectObj.getString(key);
                }
            }
            return field;
        }

        return null;
    }

    // list的Where方法
    public static <T> List<T> ListDoWhere(List<T> list, String FieldName, Object Value) {
        List<T> reslist = new ArrayList<T>();
        if (list == null)
            return reslist;
        for (int i = 0; i < list.size(); i++) {
            T obj = list.get(i);
            Object myobj = getFieldValue(obj, FieldName);
            if (myobj != null || (myobj == null && Value == null)) {
                if (myobj.equals(Value)) {
                    reslist.add(obj);
                }
            }
        }
        return reslist;
    }

    public static <T> T ListDoWhereFirstOrDefault(List<T> list, String FieldName, Object Value) {
        List<T> temp = ListDoWhere(list, FieldName, Value);
        if (temp != null && temp.size() > 0)
            return temp.get(0);
        else
            return null;
    }

    // list的Select方法
    public static <T> List<Object> ListDoSelect(List<T> list, String FieldName) {
        List<Object> reslist = new ArrayList<Object>();
        for (int i = 0; i < list.size(); i++) {
            T obj = list.get(i);
            Object myobj = getFieldValue(obj, FieldName);
            if (myobj != null) {
                reslist.add(myobj);
            }
        }
        return reslist;
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

    public static boolean isNumeric(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]*([Ee]{1}[0-9]+)?");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isEoN(Object testStr) {
        return (testStr == null || testStr.toString().trim().equals("") || testStr.toString().trim().equals("null")
                || testStr.toString().trim().equals("undefined"));//|| testStr.toString().trim().equals("[]")|| testStr.toString().trim().equals("{}")
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str) || "null".equals(str);
    }

    /**
     * 传入两个对象，将A对象的值复制给B对象，B对象中不存在的属性不赋值
     *
     * @param objA
     * @param objB
     * @return
     */
    public static JSONObject copyFromObjectAToObjectB(Object objA, Object objB) {
        List<String> FieldA = getFiledList(objA);
        List<String> FieldB = getFiledList(objB);
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < FieldA.size(); i++) {
            Object valueA = getFieldValue(objA, FieldA.get(i));
            for (int j = 0; j < FieldB.size(); j++) {
                if (FieldA.get(i).equals(FieldB.get(j).toLowerCase().trim())) {
                    jsonObject.put(FieldB.get(j).toLowerCase().trim(), valueA);
                }
            }
        }
        return jsonObject;
    }


    /**
     * date日期
     *
     * @param
     * @return日期格式
     */
    private static String analyseDateFormat(String date) {
        String result = null;
        String input = date.trim();
        if (Pattern.matches("\\d{2,4}-\\d{1,2}-\\d{1,2}.*", input)) {
            result = "yyyy-MM-dd";
        } else if (Pattern.matches("\\d{1,2}-\\d{1,2}-\\d{2,4}.*", input)) {
            result = "MM-dd-yyyy";
        } else if (Pattern.matches("\\d{2,4}/\\d{1,2}/\\d{1,2}.*", input)) {
            result = "yyyy/MM/dd";
        } else if (Pattern.matches("\\d{1,2}/\\d{1,2}/\\d{2,4}.*", input)) {
            result = "MM/dd/yyyy";
        } else if (Pattern.matches("\\d{6,8}.*", input)) {
            result = "yyyyMMdd";
        }
        if (date.trim().length() > 12) {
            result = result + " HH:mm:ss";
        }
        return result;
    }

    public static Date parseDate(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (date != null && date.indexOf("CST") != -1) {
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

    public static String formatDate(Date date, String format) {
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @param date
     * @return yyyy-MM-dd格式
     */
    public static String formatDate(Date date) {
        if (date == null)
            return null;
        int mYear = date.getYear() + 1900;
        int mMonth = date.getMonth();
        int mDay = date.getDate();
        StringBuilder sb = new StringBuilder();
        sb.append(mYear).append("-").append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay);
        return sb.toString();
    }

    public static String formatDate(Date date, int DateType) {
        SimpleDateFormat sdf;
        switch (DateType) {
            case DateType_START:
                sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
                break;
            case DateType_END:
                sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
                break;
            default:
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
        }
        return sdf.format(date);
    }

    public static Date parseDateN(Object date) {
        if (date != null) {
            return parseDate(date.toString());
        } else {
            return parseDate("1900-01-01");
        }
    }

    public static double parseDoubleN(Object data) {
        if (data != null) {
            try {
                return Double.parseDouble(data.toString());
            } catch (Exception ex) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static float parseFloatN(Object data) {
        if (data != null) {
            try {
                return Float.parseFloat(data.toString());
            } catch (Exception ex) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static int parseIntN(Object data) {
        return parseIntN(data, 0);

    }

    public static int parseIntN(Object data, int def) {
        if (data != null) {
            try {
                return Integer.parseInt(data.toString());
            } catch (Exception ex) {
                return def;
            }
        } else {
            return def;
        }
    }

    public static String parseStringN(Object data) {
        if (data == null || data.toString().toLowerCase().equals("null")) {
            return "";
        } else {
            return data.toString();
        }
    }

    public static Date parseDate(String date) {
        try {
            if (!Util.isEoN(date) && date.indexOf(".") != -1) {
                date = date.replaceAll("\\.", "-");
            }
            String formatStr = analyseDateFormat(date);
            if (formatStr == null) {
                DateFormat sd = SimpleDateFormat.getDateTimeInstance();
                return sd.parse(date);// .parse(date);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
                return sdf.parse(date);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> toMap(JSONObject json) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        // 将json字符串转换成jsonObject
        // SONObject jsonObject = JSONObject.fromObject(object);
        Iterator it = json.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value;
            try {
                value = json.get(key);
                data.put(key, value);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return data;
    }

    public static String toString(Document doc) throws TransformerFactoryConfigurationError, TransformerException {
        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);
        return (writer.getBuffer().toString());
    }

    public static Long getTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 得到 4 位验证码
     *
     * @return
     */
    public static String getMobileCode() {
        Random rd = new Random();
        String checkCode = "";
        for (int i = 0; i < 4; i++) {
            checkCode += rd.nextInt(10);
        }
        checkCode = checkCode.trim();
        return checkCode;
    }

    public static void setField(Object obj, String fieldname, Object value) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        // if(fieldname.length()>0)
        // {
        // String
        // methodname="set"+fieldname.substring(0,1).toUpperCase()+fieldname.substring(1,
        // fieldname.length());
        // Method m=obj.getClass().getMethod(methodname,value.getClass());
        // m.invoke(obj,value);
        // }
        setField(obj, fieldname, value, value.getClass());

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
    public static void setField(Object obj, String fieldname, Object value, Class<?> valueClass)
            throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        if (fieldname.length() > 0) {
            if (PropertyUtils.isWriteable(obj, fieldname)) {
                Class<?> valueclass = BeanUtils.findPropertyType(fieldname, obj.getClass());
                Object rObject = Caster(valueclass, String.valueOf(value));
                PropertyUtils.setProperty(obj, fieldname, rObject);
            }

            // String methodname = "set" + fieldname.substring(0,
            // 1).toUpperCase() + fieldname.substring(1, fieldname.length());
            // Method m = obj.getClass().getMethod(methodname, valueClass);
            // m.invoke(obj, value);
        }

    }

    public static void setFieldEx(Object obj, String fieldName, Object value) throws IllegalArgumentException,
            SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        int dotIndex = fieldName.indexOf(".");
        if (dotIndex > 0) {
            Object subObj = getField(obj, fieldName.substring(0, dotIndex));
            setFieldEx(subObj, fieldName.substring(dotIndex + 1), value);
        } else {
            setField(obj, fieldName, value);
        }

    }

    public static Object getFieldEx(Object obj, String fieldName) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
        int dotIndex = fieldName.indexOf(".");
        if (dotIndex > 0) {
            Object subObj = getField(obj, fieldName.substring(0, dotIndex));
            return getFieldEx(subObj, fieldName.substring(dotIndex + 1));
        } else {
            return getField(obj, fieldName);
        }
    }

    /**
     * 字符串到其他类型转换
     *
     * @param type
     * @param value
     * @return
     */
    public static Object Caster(Class<?> type, String value) {

        Object returnValue = null;
        if (value == null || value == "null" || "".equals(value))
            return null;

        if (type.getName().equals("java.util.Date")) {
            if (value.indexOf("Z") != -1 && value.indexOf("T") != -1) {
                returnValue = value;
            } else {
                returnValue = Util.parseDate(value);
            }
        } else if (type.getName().equals("java.sql.Timestamp")) {
            if (value.indexOf("Z") != -1 && value.indexOf("T") != -1) {
                value = value.replace("Z", "");
                value = value.replace("T", " ");
                Date d = Util.parseDate(value);
                Timestamp t = new Timestamp(d.getTime());
                returnValue = t;
            } else {
                Date d = Util.parseDate(value);
                Timestamp t = new Timestamp(d.getTime());
                returnValue = t;
            }
        } else if (type.getName().endsWith("Long")) {
            if (isNumeric(value))
                returnValue = Long.valueOf(value);
        } else if (type.getName().endsWith("Integer")) {
            if (isNumeric(value))
                returnValue = Integer.valueOf(value);
        } else if (type.getName().endsWith("int")) {
            if (isNumeric(value))
                returnValue = Integer.valueOf(value);
            else
                returnValue = 0;
        } else if (type.getName().endsWith("Double")) {
            if (isNumeric(value))
                returnValue = Double.valueOf(value);
        } else if (type.getName().endsWith("double")) {
            if (isNumeric(value))
                returnValue = Double.valueOf(value);
            else
                returnValue = 0.0;
        } else if (type.getName().endsWith("Byte")) {
            if (isNumeric(value))
                returnValue = Byte.valueOf(value);
        } else if (type.getName().endsWith("byte")) {
            if (isNumeric(value))
                returnValue = Byte.valueOf(value);
            else
                returnValue = 0;
        } else if (type.getName().endsWith("Boolean") || type.getName().endsWith("boolean")) {
            returnValue = Boolean.parseBoolean(value);
        } else if (type.getName().endsWith("bool")) {
            returnValue = Boolean.parseBoolean(value);
        } else if (type.getName().endsWith("BigDecimal")) {
            returnValue = new BigDecimal(value);
        } else if (type.getName().endsWith("Clob")) {
//            Object obj = Hibernate.createClob(value);
//            obj = ClobProxy.generateProxy(value);
//             SerializableClobProxy proxy = obj;
//            returnValue = obj;// proxy.getWrappedClob();
        } else {
            returnValue = value.toString();
        }

        return returnValue;
    }

    /**
     * 将json中存在的属性填充到Object对象对应的属性中去,如果Json对象中不存在的将忽略
     *
     * @param obj  目标对象
     * @param json
     * @return
     */
    public static Object ApplyObject(Object obj, JSONObject json) {
        if (json == null)
            return obj;
        if (obj == null)
            return obj;
        Iterator it = json.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value;
            try {
                value = json.get(key);
                if (!(value instanceof JSONObject) && !(value instanceof JSONArray))
                    setField(obj, key, value);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return obj;
    }

    /**
     * 将json中存在的属性填充到Object对象对应的属性中去,如果Json对象中不存在的将忽略
     *
     * @param obj           目标对象
     * @param json
     * @param applyChildren 子对象赋值
     * @return
     */
    public static Object ApplyObject(Object obj, JSONObject json, Boolean applyChildren) {
        if (json == null)
            return obj;
        if (obj == null)
            return obj;
        Iterator it = json.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value;
            try {
                value = json.get(key);
                if (!(value instanceof JSONObject) && !(value instanceof JSONArray))
                    setField(obj, key, value);
                else if (value instanceof JSONObject) {
                    if (applyChildren) {
                        Method method = getField(obj, key);
                        if (method != null) {
                            try {
                                Object newObject = method.getReturnType().newInstance();
                                if (newObject != null) {
                                    ApplyObject(newObject, (JSONObject) value, applyChildren);
                                }
                            } catch (Exception ec) {

                            }
                        }

                    }
                } else if (value instanceof JSONArray) {
                    JSONArray jarray = (JSONArray) value;
                    for (Object jobj : jarray) {
                        if (jobj instanceof JSONObject) {
                            JSONObject jsonObj = (JSONObject) jobj;
                            if (applyChildren) {
                                Method method = getField(obj, key);
                                if (method != null) {
                                    try {
                                        Object newObject = method.getReturnType().newInstance();
                                        if (newObject != null) {
                                            ApplyObject(newObject, jsonObj, applyChildren);
                                        }
                                    } catch (Exception ec) {

                                    }
                                }

                            }
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return obj;
    }

    /**
     * 获取json内部对象的属性值，即：json对象.json内部对象.属性
     *
     * @param json
     * @param innerObjName
     * @return 返回值分三种类型：1）返回内部对象属性值; 2）返回null,代表该属性未定义，一般后续不对其及所属内部对象做任何操作; 3)
     * 返回 "",代表该属性置空，如属性为id，一般指要设置该对象为空：set 对象.内部对象=null，
     * 当属性值为以下值时：null,"","null",返回该值 一对多关系操作时，多方将一方成员变量置空就是要设置成该值
     */
    public static String getInnerObjectId(JSONObject json, String innerObjName, String fieldName) {
        Object innerObj = json.get(innerObjName);
        if (innerObj == null)
            return null; // 内部对象不存在，返回null
        if (!(innerObj instanceof JSONObject))
            return null; // 内部对象不是标准对象，返回null
        if (!(((JSONObject) innerObj).containsKey(fieldName)))
            return null;// 内部对象不包括指定属性，返回null

        if (((JSONObject) innerObj).get(fieldName) == null)
            return "";
        String result = ((JSONObject) innerObj).get(fieldName).toString();
        if (result.trim().toLowerCase().equals("") || result.trim().toLowerCase().equals("null")) {
            return "";
        }

        return ((JSONObject) innerObj).get(fieldName).toString();

    }

    /**
     * 将字符串object 用ch字符填充,总长度为length 如果object 长度超过length,则返回object
     * trim
     *
     * @param object
     * @param
     * @param length
     * @return
     */
    public static String trim(String object, char ch, int length) {
        String result = object;
        if (object.length() >= length)
            return object;
        else {
            for (int i = object.length(); i <= length; i++) {
                result = String.valueOf(ch).concat(result);
            }
            return result;
        }
    }

    public static String json2Str(JSONObject propertys, String propertyName) {

        return json2Str(propertys, propertyName, null);
    }

    /**
     * 用来处理pdf数据
     *
     * @param str
     * @return
     */
    public static String changeTopdf(String str) {
        if (str != null) {
            String strForpdf = str.replaceAll("&", "&amp;");
            strForpdf = strForpdf.replaceAll("<", "&lt;");
            strForpdf = strForpdf.replaceAll(">", "&gt;");
            strForpdf = strForpdf.replaceAll("\n", "<w:br/>");
            strForpdf = strForpdf.replaceAll("\\u0003", "");//老系统乱码数据替换
            return strForpdf;
        } else {
            return "";
        }
    }

    /**
     * 用来处理文件夹名字数据
     *
     * @param str
     * @return
     */
    public static String changeToFileName(String str) {
        if (str != null) {
            String strForpdf = str.replaceAll("\\\\", "");
            strForpdf = strForpdf.replaceAll("\\/", "");
            strForpdf = strForpdf.replaceAll("\"", "");
            strForpdf = strForpdf.replaceAll(":", "");
            strForpdf = strForpdf.replaceAll(" ", "");
            strForpdf = strForpdf.replaceAll("\\[", "");
            strForpdf = strForpdf.replaceAll("\\]", "");
            strForpdf = strForpdf.replaceAll("\\(", "");
            strForpdf = strForpdf.replaceAll("\\)", "");
            strForpdf = strForpdf.replaceAll("\\（", "");
            strForpdf = strForpdf.replaceAll("\\）", "");
            strForpdf = strForpdf.replaceAll("&", "");
            strForpdf = strForpdf.replaceAll("\n", "");
            strForpdf = strForpdf.replace("'\'", "").replace("/", "").
                    replace(":", "").replace("*", "").replace("?", "").replace("\"", "").replace("<", "").replace(">", "").replace("!", "");
            return strForpdf;
        } else {
            return "";
        }
    }


    public static String json2Str(JSONObject propertys, String propertyName, String defValue) {

        try {
            if (propertys.containsKey(propertyName)) {
                if (emptyStr2Null(propertys.get(propertyName).toString()) == null) {
                    return defValue;
                } else {
                    return propertys.get(propertyName).toString();
                }
            } else {
                return defValue;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return defValue;
        }
    }

    public static String[] json2StrArray(JSONObject propertys, String propertyName) {

        return json2StrArray(propertys, propertyName, null);
    }

    public static String[] json2StrArray(JSONObject propertys, String propertyName, String[] defValue) {

        try {
            if (propertys.containsKey(propertyName)) {
                return emptyStrArray2Null(propertys.get(propertyName).toString().split(","));
            } else {
                return defValue;
            }

        } catch (Exception ex) {
            return defValue;
        }
    }

    public static int json2Int(JSONObject propertys, String propertyName) {
        return json2Int(propertys, propertyName, 0);
    }

    public static int json2Int(JSONObject propertys, String propertyName, int defaultValue) {
        try {
            if (propertys.containsKey(propertyName)) {
                return propertys.getInt(propertyName);
            } else {
                return defaultValue;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static boolean json2Boolean(JSONObject propertys, String propertyName) {
        return json2Boolean(propertys, propertyName, false);
    }

    public static boolean json2Boolean(JSONObject propertys, String propertyName, boolean defaultValue) {
        try {
            if (propertys.containsKey(propertyName)) {

                return propertys.getBoolean(propertyName);

            } else {
                return defaultValue;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return defaultValue;
        }

    }

    // 随机打乱 LIst 中的顺序
    public static List<Map> randomList(List<Map> sourceList) {
        if (sourceList == null) {
            return sourceList;
        }
        ArrayList<Map> randomList = new ArrayList<Map>(sourceList.size());
        do {
            int randomIndex = Math.abs(new Random().nextInt(sourceList.size()));
            randomList.add(sourceList.remove(randomIndex));
        } while (sourceList.size() > 0);
        return randomList;
    }

    /**
     * 将空字符串转成null
     *
     * @param oriString
     * @return
     */
    public static String emptyStr2Null(String oriString) {

        if (oriString != null && oriString.trim().equals("")) {
            return null;
        }
        return oriString;
    }

    /**
     * 将空字符串转成null
     * oriString
     *
     * @param
     * @return
     */
    public static String Null2EmptyStr(Object value) {

        if (value == null)
            return "";
        return value.toString();
    }

    public static String[] emptyStrArray2Null(String[] oStringArray) {

        if (oStringArray == null || oStringArray.length == 0
                || (oStringArray.length == 1 && oStringArray[0].trim().equals(""))) {

            return null;
        }
        for (int i = 0; i < oStringArray.length; i++) {
            if (oStringArray[i].trim().equals("")) {

                oStringArray[i] = null;
            }
        }
        return oStringArray;

    }

    // 得到目录中文件名
    public static List<String> getAllFilePathByDir(String dirpath) {
        List<String> result = new ArrayList<String>();
        dirpath = dirpath.trim();
        File dir = new File(dirpath);
        if (dir == null) {
            return null;
        } else {
            if (!dir.exists()) {
                System.out.println("不存在该路径" + dir);
                return null;
            }
            if (!dir.isDirectory()) {
                System.out.println("不是一个目录" + dir + "-length-");
                return null;
            }
            File[] files = dir.listFiles();
            if (files == null)
                return null;
            List<File> fileList = new ArrayList<File>();
            for (File f : files) {
                fileList.add(f);
            }
            Collections.sort(fileList);
            for (File f : fileList) {
                result.add(f.getName());
            }
        }
        return result;
    }


    //Object转换成control中返回jason的string
    public static String ReturnJasonArray(Object obj) {
        JSONArray jsondata = JSONArray.fromObject(obj);
        JSONObject result = new JSONObject();
        result.put("data", jsondata);
        return result.toString();
    }

    public static String ReturnSuccess(Object success) {
        JSONObject result = new JSONObject();
        result.put("success", success);
        return result.toString();
    }

    // 导出excel
    public static void expExcelBy(HttpServletRequest request, HttpServletResponse response, String tableName, String[] columnName, String[] columnField, JSONArray jsonArray) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建HSSFSheet对象(excel的表单对象)
        HSSFSheet sheet = wb.createSheet(tableName);
        // 创建样式对象（HSSFCellStyle ）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 创建字体对象
        HSSFFont font = wb.createFont();
        // 设置粗体
        font.setBold(true);
        // 将字体对象赋给单元格样式对象
        cellStyle.setFont(font);

        // 在sheet里创建第一行，参数为行索引
        HSSFRow row1 = sheet.createRow(0);
        HSSFRow row;
        for (int i = 0; i < columnName.length; i++) {
            HSSFCell cell = row1.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(columnName[i]);
        }

        for (int j = 1; j < jsonArray.size() + 1; j++) {
            row = sheet.createRow(j);
            try {
                JSONObject job = jsonArray.getJSONObject(j - 1);
                for (int s = 0; s < columnField.length; s++) {
                    String mycell = columnField[s];
                    if (job.containsKey(mycell)) {
                        row.createCell(s).setCellValue(job.getString(mycell));
                    }
                }
            } catch (Exception e) {
                System.out.println("expExcelBy-报错");
            }
        }

        // 输出excel文件
        OutputStream out = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition",
                "attachment; filename=deal.xls");
        response.setContentType("application/msexcel");
        wb.write(out);
        out.close();
    }

    // 生成随机数
    public static String GetNumber(int len) {
        Random ran = new Random();
        String code = "";
        for (int i = 0; i < len; i++) {
            String s = ran.nextInt(10) + "";
            code += s;
        }
        return code;
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

    //根据请求获取ip
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
            if("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
              
            }
        }
        return ip;
    }

    // 判断一个字符是否是uuid
    public static boolean isUUID(Object str) {
        if (Util.isEoN(str) || str.toString().length() > 50)
            return false;
        try {
            UUID taruuid = UUID.fromString(str.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String convertNumberToSimpleNumber(int d) {

//      String[] str = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String[] str = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
//      String ss[] = new String[] { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿" };
        String ss[] = new String[]{"", "十", "百", "千", "万", "十", "百", "千", "亿"};
        String s = String.valueOf(d);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            String index = String.valueOf(s.charAt(i));
            sb = sb.append(str[Integer.parseInt(index)]);
        }
        String sss = String.valueOf(sb);
        int i = 0;
        for (int j = sss.length(); j > 0; j--) {
            sb = sb.insert(j, ss[i++]);
        }
        return sb.toString();
    }

    // 去除字符串的最后或者最前的逗号
    public static String getFinshString(String sourcestr, String strspilt) {
        if (Util.isEoN(sourcestr))
            return sourcestr;
        if (sourcestr.startsWith(strspilt))
            sourcestr = sourcestr.substring(strspilt.length(), sourcestr.length());
        if (sourcestr.endsWith(strspilt))
            sourcestr = sourcestr.substring(0, sourcestr.length() - strspilt.length());
        return sourcestr;
    }

    // 项目地区编码对应权利地区编码
    public static String convertLocalCode(String procode) {
        if (Util.isEoN(procode))
            return procode;
        // 杭州
        if (procode.startsWith("3301")) {
            return "33010000000001";
        } else if (procode.startsWith("3302")) {
            return "33020100000001";
        } else if (procode.startsWith("3303")) {
            return "33000100000001";
        } else if (procode.startsWith("3304")) {
            return "33000033040101";
        } else if (procode.startsWith("3305")) {
            return "33000033050101";
        } else if (procode.startsWith("3306")) {
            return "33000100000001";
        } else if (procode.startsWith("3307")) {
            return "33000033070101";
        } else if (procode.startsWith("3308")) {
            return "33000100000001";
        } else if (procode.startsWith("3309")) {
            return "33090000000001";
        } else if (procode.startsWith("3310")) {
            return "33000100000001";
        } else if (procode.startsWith("3311")) {
            return "331101000001";
        }
        return null;
    }

    // 当前时间加上 个数月份
    public static String AddIntDate(Date date, int addmonth, String dateFormat) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH, addmonth);//在日历的月份上增加6个月
        return sdf.format(c.getTime());
        //System.out.println(sdf.format(c.getTime()));//的到你想要得6个月后的日期
    }


    /**
     * @param objA
     * @param objB
     * @return
     */
    public static JSONObject copyFromObjectAToObjectB2(Object objA, Object objB) {
        List<String> FieldA = getFiledList(objA);
        List<String> FieldB = getFiledList(objB);
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < FieldA.size(); i++) {
            Object valueA = getFieldValue(objA, FieldA.get(i));
            for (int j = 0; j < FieldB.size(); j++) {
                if (FieldA.get(i).toLowerCase().trim().equals(FieldB.get(j).toLowerCase().trim())) {
                    jsonObject.put(FieldB.get(j).toLowerCase().trim(), valueA);
                }
            }
        }
        return jsonObject;
    }

    /**
     * 传入两个对象，返回值不一样的字段和值
     *
     * @param objA old
     * @param objB new
     * @return
     */
    public static JSONArray compareTwoObjXMZG(Object objA, Object objB, String objName) {
        JSONArray jsonArray = new JSONArray();
        List<String> FieldA = getFiledList(objA);
        List<String> FieldB = getFiledList(objB);
        for (int i = 0; i < FieldA.size(); i++) {
            if (!"chieldofficals".equals(FieldA.get(i).toLowerCase().trim())) {
                continue;
            }
            Object valueA = getFieldValue(objA, FieldA.get(i));
            for (int j = 0; j < FieldB.size(); j++) {
                if (FieldA.get(i).toLowerCase().trim().equals(FieldB.get(j).toLowerCase().trim())) {
                    Object valueB = getFieldValue(objB, FieldB.get(j));

                    if (!Util.isEoN(valueA) && !valueA.equals(valueB)) {
                        if (valueA instanceof Date) {
                            if (Util.isEoN(valueB) || ((Date) valueA).getTime() != ((Date) valueB).getTime()) {
                                valueA = (Date) valueA;
                                JSONArray valueArray = new JSONArray();
                                JSONObject jsonObjectA = new JSONObject();
                                // 时间转成年月日 时分秒
                                if (valueA.toString().indexOf("CST") == -1) {
                                    jsonObjectA.put(FieldA.get(i), Util.formatDate(Util.parseDate(valueA + ""), "yyyy-MM-dd HH:mm:ss"));
                                } else {
                                    jsonObjectA.put(FieldA.get(i), Util.formatDate((Date) valueA, "yyyy-MM-dd HH:mm:ss"));
                                }
                                jsonObjectA.put("field_nameXX", getFileName(FieldA.get(i), objName));
                                jsonObjectA.put("old", true);
                                JSONObject jsonObjectB = new JSONObject();
                                if (Util.isEoN(valueB))
                                    jsonObjectB.put(FieldB.get(j), valueB);
                                else if (valueB.toString().indexOf("CST") == -1)
                                    jsonObjectB.put(FieldB.get(j), Util.formatDate(Util.parseDate(valueB + ""), "yyyy-MM-dd HH:mm:ss"));
                                else
                                    jsonObjectB.put(FieldB.get(j), Util.formatDate((Date) valueB, "yyyy-MM-dd HH:mm:ss"));
                                jsonObjectB.put("new", true);
                                valueArray.add(jsonObjectA);
                                valueArray.add(jsonObjectB);
                                jsonArray.add(valueArray);
                            }
                        } else {
                            //valueArray 添加的object 的顺序很重要
                            JSONArray valueArray = new JSONArray();
                            JSONObject jsonObjectA = new JSONObject();
                            if (Util.isEoN(valueA)) {
                                jsonObjectA.put(FieldA.get(i), valueA);
                            } else if (valueA instanceof Date) {
                                if (valueA.toString().indexOf("CST") == -1) {
                                    jsonObjectA.put(FieldA.get(i), Util.formatDate(Util.parseDate(valueA + ""), "yyyy-MM-dd HH:mm:ss"));
                                } else {
                                    jsonObjectA.put(FieldA.get(i), Util.formatDate((Date) valueA, "yyyy-MM-dd HH:mm:ss"));
                                }
                            } else {
                                jsonObjectA.put(FieldA.get(i), valueA);
                            }
                            jsonObjectA.put("field_nameXX", getFileName(FieldA.get(i), objName));
                            jsonObjectA.put("old", true);
                            JSONObject jsonObjectB = new JSONObject();
                            if (Util.isEoN(valueB)) {
                                jsonObjectB.put(FieldB.get(j), valueB);
                            } else if (valueB instanceof Date) {
                                if (valueB.toString().indexOf("CST") == -1)
                                    jsonObjectB.put(FieldB.get(j), Util.formatDate(Util.parseDate(valueB + ""), "yyyy-MM-dd HH:mm:ss"));
                                else
                                    jsonObjectB.put(FieldB.get(j), Util.formatDate((Date) valueB, "yyyy-MM-dd HH:mm:ss"));
                            } else {
                                jsonObjectB.put(FieldB.get(j), valueB);
                            }
                            jsonObjectB.put("new", true);
                            valueArray.add(jsonObjectA);
                            valueArray.add(jsonObjectB);
                            jsonArray.add(valueArray);
                        }
                    } else if (valueA == null && !Util.isEoN(valueB)) {
                        JSONArray valueArray = new JSONArray();
                        JSONObject jsonObjectA = new JSONObject();
                        if (Util.isEoN(valueA)) {
                            jsonObjectA.put(FieldA.get(j), valueA);
                        } else if (valueA instanceof Date) {
                            if (valueA.toString().indexOf("CST") == -1) {
                                jsonObjectA.put(FieldA.get(i), Util.formatDate(Util.parseDate(valueA + ""), "yyyy-MM-dd HH:mm:ss"));
                            } else {
                                jsonObjectA.put(FieldA.get(i), Util.formatDate((Date) valueA, "yyyy-MM-dd HH:mm:ss"));
                            }
                        } else {
                            jsonObjectA.put(FieldA.get(j), valueA);
                        }
                        jsonObjectA.put("old", true);
                        jsonObjectA.put("field_nameXX", getFileName(FieldA.get(i), objName));
                        JSONObject jsonObjectB = new JSONObject();
                        if (Util.isEoN(valueB)) {
                            jsonObjectB.put(FieldB.get(j), valueB);
                        } else if (valueB instanceof Date) {
                            if (valueB.toString().indexOf("CST") == -1)
                                jsonObjectB.put(FieldB.get(j), Util.formatDate(Util.parseDate(valueB + ""), "yyyy-MM-dd HH:mm:ss"));
                            else
                                jsonObjectB.put(FieldB.get(j), Util.formatDate((Date) valueB, "yyyy-MM-dd HH:mm:ss"));
                        } else {
                            jsonObjectB.put(FieldB.get(j), valueB);
                        }
                        jsonObjectB.put("new", true);
                        valueArray.add(jsonObjectA);
                        valueArray.add(jsonObjectB);
                        jsonArray.add(valueArray);
                    }
                }
            }
        }
        return jsonArray;
    }

    /**
     * List中随机返回n条数据
     *
     * @return
     */
    public static List<Map> generateRandomDataNoRepeat(List<Map> list, Integer generateNum) {
        List<Map> tList = new ArrayList<Map>();
        if (!Util.isEoN(list)) {
            for (Integer num : generateRandomNoRepeat(list.size(), generateNum)) {
                tList.add(list.get(num));
            }
        }
        return tList;
    }

    public static Set<Integer> generateRandomNoRepeat(Integer totalCount, Integer generateNum) {
        if (isLessThanHalfTotalCount(totalCount, generateNum)) {
            return getRandomNoRepeat(totalCount, generateNum);
        }
        return getReverseRandomNoRepeat(totalCount, generateNum);
    }

    private static Set<Integer> getRandomNoRepeat(Integer totalCount, Integer generateNum) {
        Set<Integer> set = new HashSet<Integer>();
        while (true) {
            set.add(RandomUtils.nextInt(totalCount));
            if (set.size() == generateNum) {
                return set;
            }
        }
    }

    private static Boolean isLessThanHalfTotalCount(Integer totalCount, Integer generateNum) {
        if (generateNum < totalCount / 2) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private static Set<Integer> getReverseRandomNoRepeat(Integer totalCount, Integer generateNum) {
        Set<Integer> set = new HashSet<Integer>();
        while (true) {
            set.add(RandomUtils.nextInt(totalCount));
            if (set.size() == totalCount - generateNum) {
                Set<Integer> setALL = getSetALL(totalCount);
                setALL.removeAll(set);
                return setALL;
            }
        }
    }

    private static Set<Integer> getSetALL(Integer totalCount) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < totalCount; i++) {
            set.add(i);
        }
        return set;
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

    public static String getExceptionAllinformation(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        try {
            pout.close();
        } catch (Exception e) {
        }
        try {
            out.close();
        } catch (Exception e) {
        }
        return ret;
    }


    /**
     * map转对象方法
     *
     * @param entity
     * @param params
     * @return
     */
    public static <T> T mapToObjModle(T entity, Map<String, Object> params) {
        Class<?> clazz = entity.getClass();
        // 得到对象的字段
        List<Field> fields = getAccessibleFields(clazz);
        // 迭代字段
        for (Field f : fields) {
            String name = f.getName();
            Object objVal = params.get(name);
            // 找到对应值，进行转化设置
            if (objVal != null) {
                try {
                    if (f.getType().equals(String.class)) {
                        objVal = String.valueOf(objVal);
                    } else if (!f.getType().isAssignableFrom(objVal.getClass())) {
                        if (Util.isEoN(String.valueOf(objVal))) {
                            //转换依赖方法：org.apache.commons.beanutils.ConvertUtils.convert(Object, Class<?>)
                            objVal = ConvertUtils.convert(objVal, f.getType());
                        } else {
                            objVal = null;
                        }
                    }
                    f.set(entity, objVal);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return entity;

    }


    /**
     * 循环向上转型, 获取对象所有的DeclaredField
     * <p>
     * 如向上转型到Object仍无法找到, 返回null.
     */
    public static List<Field> getAccessibleFields(final Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            for (Field f : superClass.getDeclaredFields()) {
                boolean hasInSubClass = false;
                for (Field f2 : fields) {
                    if (f2.getName().equals(f.getName())) {
                        hasInSubClass = true;
                        break;
                    }
                }
                if (!hasInSubClass) {
                    makeAccessible(f);
                    fields.add(f);
                }
            }
        }
        return fields;
    }

    /**
     * 改变private/protected的成员变量为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
     */
    public static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())
                || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }


    /**
     * 字符串数组添加 字符串
     *
     * @param arr
     * @param str
     * @return
     */
    public static String[] insertString(String[] arr, String str) {
        int size = arr.length;
        String[] tmp = new String[size + 1];
        for (int i = 0; i < size; i++) {  //先遍历将原来的字符串数组数据添加到临时字符串数组
            tmp[i] = arr[i];
        }
        tmp[size] = str;
        return tmp;
    }

    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    /**
     * 判断一个字符串是否带中文
     *
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


    /**
     * 判断一个字符串是否含有数字
     *
     * @param content
     * @return
     */
    public static boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    public static boolean judgeContainsStr(String str) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(str);
        return m.matches();
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
     * 更换boolean类型，目前是数据迁移有用到
     *
     * @param list
     * @param columnname
     * @return
     */
    public static List<Map> changeBoolean(List<Map> list, String columnname) {
        String[] array = columnname.split(",");
        for (Map map : list) {
            for (int i = 0; i < array.length; i++) {
                if (map.containsKey(array[i]) && !Util.isEoN(map.get(array[i]))) {
                    if ("1".equals(map.get(array[i]) + "")) {
                        map.put(array[i], "true");
                    } else {
                        map.put(array[i], "false");
                    }
                }
            }
        }
        return list;
    }

    /**
     * 获取Keys值，目前是数据迁移有用到
     *
     * @param List
     * @return
     */
    public static String[] getKeys(List<Map> List) {
        Map map = List.get(0);
        String temp = map.keySet().toString();
        temp = temp.replaceAll("[\\[\\]]", "");
        temp = temp.replaceAll(" ", "");
        String[] Syskey = temp.split(",");
        return Syskey;
    }

    /**
     * 测试库正式库，数据迁移使用
     *
     * @param list
     * @param wb
     * @param cellStyle
     * @param key1
     * @param key
     * @param name
     * @return
     */
    public static HSSFWorkbook getSheetAll(List list, HSSFWorkbook wb, HSSFCellStyle cellStyle, String[] key1, String[] key, String name) {
        HSSFCellStyle linkStyle = wb.createCellStyle();
        HSSFFont cellFont = wb.createFont();
        cellFont.setUnderline((byte) 1);
        cellFont.setColor((short) 12);
        linkStyle.setFont(cellFont);
        //上面为链接样式
        JSONArray jsonArray = JSONArray.fromObject(list);
        HSSFSheet sheet = wb.createSheet(name);
        HSSFRow row1 = sheet.createRow(0);
        HSSFRow row;
        for (int i = 0; i < key1.length; i++) {
            HSSFCell cell = row1.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(key1[i]);
        }

        for (int j = 1; j < jsonArray.size() + 1; j++) {
            row = sheet.createRow(j);
            JSONObject job = jsonArray.getJSONObject(j - 1);
            for (int s = 0; s < key.length; s++) {
                String mycell = key[s];
                if (job.containsKey(mycell)) {
                    row.createCell(s).setCellValue(job.getString(mycell));
                    if (key[s].equals("gslink")) {
                        row.createCell(s).setCellFormula("HYPERLINK(\"" + job.getString(mycell) + "\")");
                        //row.createCell(s).setCellStyle(linkStyle);
                    }
                }
            }
        }
        return wb;
    }

    /**
     * 根据符号来进行数据比较  Int类型
     *
     * @param symbol
     * @param dataValue
     * @param paramValue
     * @return
     */
    public static Boolean judgeSymbolInt(String symbol, int dataValue, int paramValue) {
        switch (symbol) {
            case "小于":
                return dataValue < paramValue;
            case "小于等于":
                return dataValue <= paramValue;
            case "等于":
                return dataValue == paramValue;
            case "大于":
                return dataValue > paramValue;
            case "大于等于":
                return dataValue >= paramValue;
            default:
                return false;
        }
    }

    /**
     * 根据符号来进行数据比较  Double类型
     *
     * @param symbol
     * @param dataValue
     * @param paramValue
     * @return
     */
    public static Boolean judgeSymbolDouble(String symbol, double dataValue, double paramValue) {
        switch (symbol) {
            case "小于":
                return dataValue < paramValue;
            case "小于等于":
                return dataValue <= paramValue;
            case "等于":
                return dataValue == paramValue;
            case "大于":
                return dataValue > paramValue;
            case "大于等于":
                return dataValue >= paramValue;
            default:
                return false;
        }
    }

    /**
     * 根据符号来进行数据比较 Long类型
     *
     * @param symbol
     * @param value1
     * @param value2
     * @return
     */
    public static Boolean judgeSymbolLong(String symbol, long value1, long value2) {
        switch (symbol) {
            case "小于":
                return value1 < value2;
            case "小于等于":
                return value1 <= value2;
            case "等于":
                return value1 == value2;
            case "大于":
                return value1 > value2;
            case "大于等于":
                return value1 >= value2;
            default:
                return false;
        }
    }

    /**
     * Double精确的加法运算。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static Double double_add(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * Double精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static Double double_sub(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * Double精确的乘法运算，不四舍五入，不保留2位小数。
     */
    public static Double double_mul(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 处理大字段原始数据，将前台对象的属性拆分为多个对象，例如：{xxx:"XXX", yyy:"YYY"} => [{xxx:"XXX"},{yyy:"YYY"}]
     *
     * @param originalData 原始数据
     * @param type         类型
     * @param sourceid     主表id
     * @return
     */
    public static JSONArray dealOriginalColbData(JSONArray originalData, String type, String sourceid) {
        JSONArray newData = new JSONArray();
        JSONObject newObject = null;
        for (int i = 0; i < originalData.size(); i++) {
            Map<String, Object> map = originalData.getJSONObject(i);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                newObject = new JSONObject();
                newObject.put("columnname", entry.getKey());
                newObject.put("source", entry.getValue().toString());
                newObject.put("type", type);
                newObject.put("sourceid", sourceid);
                newData.add(newObject);
            }
        }
        return newData;
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

    /**
     * 导入Excel时，获取单元格的值
     */
    public static String getCellValue(SimpleDateFormat fmt, Cell cell, CellType cellType) {
        String cellValue;
        switch (cellType) {
            case STRING: //文本
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC: //数字、日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = fmt.format(cell.getDateCellValue()); //日期型
                } else {
                    cellValue = String.valueOf(cell.getNumericCellValue()); //数字
                }
                break;
            case BOOLEAN: //布尔型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK: //空白
                cellValue = "";
                break;
            case ERROR: //错误
                cellValue = "错误";
            case FORMULA: //公式
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "错误";
        }
        return cellValue;
    }

    public static <T> Object dellNullData(T obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj.getClass());
            Method rm = pd.getReadMethod();
            Method rm_set = pd.getWriteMethod();
            if (rm.invoke(obj) == null) {
                rm_set.invoke(obj, new Object[]{""});
            }
        }
        return (T) obj;
    }

    /**
     * 通过前台映射表名获取Class对象
     *
     * @param tableMappingName
     * @return
     */
    public static Class getClassByTableObject(String tableMappingName) {
        //前台表名与后台数据表映射信息 枚举对象
        TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableMappingName);
        //存储字符串型的service类名，例如：Pms_ProjectbaseService
        String tempServiceNameStr = "";
        //serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
        String servicePathNameStr = "";
        //判断枚举信息中是否包含service名称
        if (!Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
            tempServiceNameStr = tableMappingEnum.getServiceclassname().getSimpleName();
            servicePathNameStr = tableMappingEnum.getServiceclassname().getName();
        } else {
            tempServiceNameStr = (tableMappingEnum.getClassname().getSimpleName() + "Service");
            servicePathNameStr = PmsProjectbaseService.class.getPackage().toString().substring(8) + "." + tempServiceNameStr;
        }
        Class clazz = null;
        try {
            //通过反射创建cn.topcheer.pms.service.PmsProjectpartyService
            clazz = Class.forName(servicePathNameStr);
        } catch (Exception e) {
            clazz = null;
        }  
        return clazz;
        
    }

    /**
     * 处理回参对接（一）
     */
    public static JSONObject dealResJson(JSONObject resJson, boolean success, String reason) {
        resJson.put("success", success);
        resJson.put("reason", reason);
        return resJson;
    }

    /**
     * 处理回参对接（二）
     */
    public static JSONObject dealResJson(JSONObject resJson, boolean success, JSONObject json) {
        resJson.put("success", success);
        resJson.put("data", json);
        return resJson;
    }

    /**
     * 处理回参对接（三）
     */
    public static JSONObject dealResJson(JSONObject resJson, boolean success) {
        resJson.put("success", success);
        return resJson;
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

    public static <T> String fetchFieldName(String databaseTablename, String fieldOtherName) {
        String result = null;
        TableMappingEnum enumByDatabaseTablename = TableMappingEnum.getEnumByDatabaseTablename(databaseTablename);
        result = fetchFieldName(enumByDatabaseTablename.getClassname(), fieldOtherName);
        return result;
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
     * 动态Sql语句IN参数拼接
     *
     * @param array      参数列表，用于计算需要生成多少个问号(?)
     * @param objectname 需匹配的字段名，例如：t.id（推荐），或者id
     * @return 例如：t.id in ( ?, ? ) 或者 t.id in ( ?, ? ) or t.id in ( ?, ? )、“”
     */
    public static String sqlSplicingForInStatement(Object[] array, String objectname) {
        StringBuilder insql = new StringBuilder(" ");
        //in最多支持传1000个参数，推荐不要太多
        if (array.length > 0) {
            int loopSize = (int) Math.ceil(array.length / 500.0);
            for (int i = 0; i < loopSize; i++) {
                if (i == 0) {
                    insql.append(" " + objectname + " in ( ");
                } else {
                    insql.append(" or " + objectname + " in ( ");
                }
                for (int j = i * 500; j < (i + 1) * 500 && j < array.length; j++) {
                    insql.append(" ?, ");
                }
                insql.delete(insql.length() - 2, insql.length());
                insql.append(" ) ");
            }
        }
        return insql.toString();
    }


    public static JSONObject xml2Json(String xml) {
        JSONObject json = new JSONObject();

        try {
            json = JSONObject.fromObject(XML.toJSONObject(xml).toString());
            System.out.println(json.toString());
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }

        return json;
    }


    /**
     * 前台userid password base64解码
     *
     * @param json
     * @return
     * @throws Exception
     */
    public static JSONObject deCodeUseridAndPassword(JSONObject json) throws Exception {
        if (json.containsKey("userid")) {
            json.put("userid", Base64Convert.decode(json.getString("userid"), "utf-8"));
        }
        if (json.containsKey("password")) {
            json.put("password", Base64Convert.decode(json.getString("password"), "utf-8"));
        }
        return json;
    }

    public static String GetRunningPath() {
        try {
            return RUNNING_PATH_FILE.getCanonicalPath().replaceAll("\\\\", "/") + "----" + RUNNING_PATH_FILE.getAbsolutePath().replaceAll("\\\\", "/") + "----" + RUNNING_PATH_FILE.getPath().replaceAll("\\\\", "/");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
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
        } else if(osName.contains("Mac")) {
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
     * @AUTHOR 会飞的王浩然
     * 判断是否是正式环境
     * 是为true  不是false
     */
    public static boolean JudgeIsWorking() {
        try {
            Properties props = System.getProperties(); //获得系统属性集
            String osName = props.getProperty("os.name"); //操作系统名称
            String urlPrefix = "";
            if (osName.contains("Windows")) {
                urlPrefix = "D://";
            } else {
                urlPrefix = "/";
            }
            File file = new File(urlPrefix + "judge.xyz");
            if (file.exists()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @AUTHOR 会飞的王浩然
     * 判断是否是测试环境
     * 是为true  不是false
     */
    public static boolean JudgeIsTestWorking() {
        try {
            Properties props = System.getProperties(); //获得系统属性集
            String osName = props.getProperty("os.name"); //操作系统名称
            String urlPrefix = "";
            if (osName.contains("Windows")) {
                urlPrefix = "D://";
            } else {
                urlPrefix = "/";
            }
            File file = new File(urlPrefix + "judge-test.xyz");
            if (file.exists()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 评审定时任务开关
     * @return
     */
    public static boolean JudgeIsWorkingForReview() {
        try {
            Properties props = System.getProperties(); //获得系统属性集
            String osName = props.getProperty("os.name"); //操作系统名称
            String urlPrefix = "";
            if (osName.contains("Windows")) {
                urlPrefix = "D://";
            } else {
                urlPrefix = "/";
            }
            File file = new File(urlPrefix + "judge_review.xyz");
            if (file.exists()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static <T> T parseObject(String jsonStr, Class<T> tClass) {
        T myInstance = null;
        try {
            myInstance = JSON.parseObject(jsonStr, tClass);
        } catch (Exception e) {
            myInstance = null;
        }
        return myInstance;
    }

    public static <T> List<T> parseList(String jsonStr, Class<T> tClass) {
        List<T> result = new ArrayList<>();
        try {
            result = com.alibaba.fastjson.JSONArray.parseArray(jsonStr, tClass);
        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * 清洗下sql的 1=1
     */
    public static String dealOneEqualOne(String sql) {
        return sql.replaceAll("1=1 and", " ");
    }

    /**
     * 读取系统中的文件夹，如果没有就新建一个文件夹
     */
    public static void MkdirFolder(String folderPath) {
        System.out.println(folderPath);
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /**
     * 计算数组里某个数字字段的合计
     */
    public static String computeTotalForListField(List<Map> list , String field){
        if(list.size()==0){
            return "0";
        }
        return list.stream().map(i -> new BigDecimal(i.get(field)+"")).reduce(BigDecimal.ZERO,BigDecimal::add).toString();
    }

    /**
     * 判断你一个类是否存在某个属性（字段）
     *
     * @param fieldName 字段
     * @param obj   类对象
     * @return true:存在，false:不存在, null:参数不合法
     */
    public static Boolean isExistFieldName(String fieldName, Object obj) throws NoSuchFieldException {
        if (obj == null || isEoN(fieldName)) {
            return null;
        }
        //获取这个类的所有属性
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean flag = false;
        //循环遍历所有的fields
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(fieldName)) {
                flag = true;
                break;
            }
        }

        return flag;
    }
    public static void main(String[] args) {
        String path = Util.GetFileRealPath("D://PdfTmpFile/Allpdf/系统外验收/c86a26c2-a934-46dd-ed40-33f4dfd9aa4d//tempdocCg.pdf");
        String passWordOne = getPassWordOne();
        String passWordOne1 = getPassWordOne();
        String passWordOne2 = getPassWordOne();
        System.out.println(passWordOne);
        System.out.println(passWordOne1);
        System.out.println(passWordOne2);
    }

    public static String StringMD5(String input){
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            byte[] bytes = input.getBytes();
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            return byteArrayToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);

    }

    /**
     * 无request获取ip地址
     * @return
     */
    public static String getIPWithoutRequest() {

        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if(attributes==null){
            return "null";
        }
        HttpServletRequest request = attributes.getRequest();
        return Util.getIpAddr(request);
    }

    public static String trunChinese(String  href ) {
        String url = "";
        for (int i = 0; i < href.length(); i++) {
            char charAt = href.charAt(i);
            if (isChineseChar(charAt)) {
                String encode = null;
                try {
                    encode = URLEncoder.encode(charAt + "", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url += encode;
            } else {
                url += charAt;
            }
        }
        System.out.println(url);
        return url;
    }

    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    /**
     * 生成随机密码，4位大小写字母+5位数字+1位标点
     */
    public static String getPassWordOne(){
        int i=0; //生成的随机数
        int count = 0; //生成的密码的长度
        // 密码字典
        //字母字典
        char[] letterStr = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        //数字字典
        char[] numStr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] symbolStr = {'~', '!', '@', '#', '$', '%', '^', '-', '+'};

        StringBuffer stringBuffer = new StringBuffer("");//密码串
        Random r = new Random();

        while(count < 4){
            //取随机大小写字母4个
            i = r.nextInt(letterStr.length);
            stringBuffer.append(letterStr[i]);
            count ++;
        }
        while(4 <=count && count< 9){
            //取随机数字5个
            i = r.nextInt(numStr.length);
            stringBuffer.append(numStr[i]);
            count ++;
        }
        if (count==9){
            //取随机标点符号1个
            i = r.nextInt(symbolStr.length);
            stringBuffer.append(symbolStr[i]);
            count ++;
        }
        return stringBuffer.toString();
    }


    public static <T extends Serializable> T clone(T obj){
        T clonedObj = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clonedObj = (T) ois.readObject();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return clonedObj;
    }

    public static Boolean judgeParseInt(String str) {

        Boolean result = true;
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public static Boolean judgeParseDouble(String str) {

        Boolean result = true;
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public static boolean isEmail(String email) {
        if (email == null || email.length() < 1 || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(email).matches();
    }

    /**
     * 去掉指定字符串前面和后面指定的字符
     * @param str
     * @param c
     * @return
     */
    public static String custom_trim(String str, char c) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int st = 0;
        while ((st < len) && (chars[st] == c)){
            st++;
        }
        while ((st < len) && (chars[len-1] == c)){
            len--;
        }
        return ((st > 0) || (len < chars.length)) ? str.substring(st, len) : str;
    }

    /**
     * 去掉指定字符串前面指定的字符
     * @param str
     * @param c
     * @return
     */
    public static String custom_ltrim(String str, char c) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int st = 0;
        while ((st < len) && (chars[st] == c)) {
            st++;
        }
        return (st > 0) ? str.substring(st, len) : str;
    }

    /**
     * 去掉指定字符串后面指定的字符
     * @param str
     * @param c
     * @return
     */
    public static String custom_rtrim(String str, char c) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int st = 0;
        while ((st < len) && (chars[len-1] == c)) {
            len--;
        }
        return (len < chars.length) ? str.substring(st, len) : str;
    }

    /**
     *  拆分集合
     *  @param <T>
     *  @param resList 要拆分的集合
     *  @param count 每个集合的元素个数
     *  @return 返回拆分后的各个集合
     **/
    public static <T> List<List<T>> split(List<T> resList, int count) {
        if (resList == null || count < 1){
            return null;
        }
        List<List<T>> ret = new ArrayList<List<T>>();
        int size = resList.size();
        if (size <= count) {
            // 数据量不足count指定的大小
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            // 前面pre个集合，每个大小都是count个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<T>();
                for (int j = 0; j < count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            // last的进行处理
            if (last > 0) {
                List<T> itemList = new ArrayList<T>();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }

    /**
     * 下划线转驼峰
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
     * JSON的key下划线转驼峰
     * @param map JSONObject
     * @return JSONObject
     */
    public static Map jsonKeyToCamelCase(Map map) {
        Map newMap = new HashMap<>();
        for (Object key : map.keySet()) {
            String keyStr = key.toString();
            newMap.put(toCamelCase(keyStr, false), map.get(keyStr));
        }
        return newMap;
    }
}
