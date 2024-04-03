/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-4-15 14:46:39
 *
 */
package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.SysParams;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.sys.SysParamsDao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysParams 服务类
 */
@Service(value="SysParamsService")
public class OldSysParamsService extends GenericService<SysParams> {

	public SysParamsDao getSysParamsDao() {
		return (SysParamsDao) this.getGenericDao();
	}

	@Autowired
	public void setSysParamsDao(SysParamsDao sysParamsDao) {

		this.setGenericDao(sysParamsDao);
	}

    @Autowired
    private DBHelper dbHelper;


	/**
     * 【字典配置】---初始化获取所有父节点
     * @return
     */
    public List<Map> initData(JSONObject param){
        String insql = " and 1 = 1 ";
        if (!Util.isEoN(param.get("applytype"))){
            insql += " and applytype = '"+param.getString("applytype")+"'";
        }
        String sql = "select t.* from sys_params t where t.parentid is null ";
        List<Map> list = dbHelper.getRows(sql+insql,null);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).put("intvalue","");
        }

        return list;
    }


    /**
     * 【字典配置】---根据parentid获取所有子节点
     * @return
     */
    public List<Map> initDataByparentid(String parentid){
        String sql = "select t.* from sys_params t where t.parentid = ? order by seq";
        List<Map> list = dbHelper.getRows(sql,new Object[]{parentid});
        if (list!=null){
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).get("intvalue")==null){
                    if(!"".equals(list.get(i).get("intvalue"))&&list.get(i).get("intvalue")!=null){
                        Integer intvalue = Integer.parseInt(list.get(i).get("intvalue").toString());
                        list.get(i).put("intvalue",intvalue);
                    }
                }
            }
        }else {
            list = new ArrayList<>();
        }
        return list;
    }


    /**
     * 【字典配置】---保存方法(包含子级的删除)
     */
    public void saveData(JSONObject firstobject){
        SysParams firstlevelsysParams = new SysParams();
        Util.ApplyObject(firstlevelsysParams,firstobject);
        this.merge(firstlevelsysParams);

        //删除子级
        JSONArray deletearray = firstobject.getJSONArray("deletearray");
        if(deletearray.size()>0){
            for (int i = 0; i <deletearray.size() ; i++) {
                JSONObject json = deletearray.getJSONObject(i);
                String id = json.getString("id");
                this.deleteById(id);
            }
        }

        JSONArray secondarray = firstobject.getJSONArray("options");
        if(secondarray.size()>0){
            for (int i = 0; i < secondarray.size(); i++) {
                SysParams secondlevelsysParams = new SysParams();
                Util.ApplyObject(secondlevelsysParams,secondarray.getJSONObject(i));
                this.merge(secondlevelsysParams);
            }
        }
    }

    /**
     * 【字典配置】---根据parentid删除父级和他的子级
     */
    public void deleteDataByid(String id) throws SQLException {
        String sql = "delete from sys_params where parentid = ?";
        dbHelper.runSql(sql,new Object[]{id});
        this.deleteById(id);
    }

    /**
     * 【字典配置】---根据父级value获取他的子级
     */
    public Map initDataByparentvalue(JSONObject json) {
        JSONArray array = json.getJSONArray("selectInitNameMap");
        if(array.size()==0){
            String hql = "select t.value from "+SysParams.class.getName()+" t where t.parentid is null and t.value is not null";
            List<SysParams> list = this.findByHql(hql,null);
            for (int i = 0; i < list.size(); i++) {
                array.add(list.get(i));
            }
        }

        Map map = new HashMap();

        for (int i = 0; i < array.size(); i++) {
            String value = array.get(i)+"";
            String sql = "select id from sys_params where value = ?0";
            String parentid = this.getOnlyValueBySql(sql,new Object[]{value});
            String childsql = "select name,value,code,intvalue from sys_params where parentid = ? order by seq";
            List<Map> list = dbHelper.getRows(childsql,new Object[]{parentid});

            //判断是不是int类型
            if(list.size()>0){
                for (int j = 0; j < list.size(); j++) {
                    if(!"".equals(list.get(j).get("intvalue"))&&list.get(j).get("intvalue")!=null){
                        Integer intvalue = Integer.parseInt(list.get(j).get("intvalue")+"");
                        list.get(j).put("value",intvalue);
                    }
                }
            }

            map.put(value,list);
        }

        return map;
    }


    // 指定表名导入数据
    public String importData(File excelFile) throws BiffException, IOException, SQLException {
        // TODO Auto-generated method stub
        Workbook rwb = Workbook.getWorkbook(excelFile);
        // 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...

        for (int i = 0; i < rwb.getSheets().length; i++) {
            Sheet sheet = rwb.getSheets()[i];
            int rsColumns = sheet.getColumns();// 列数
            int rsRows = sheet.getRows();// 行数
            String tablename = sheet.getName();
            cellMerge(sheet, rsColumns, rsRows, "cn.topcheer.pms.pojo.system.", tablename);
        }

        return null;
    }

    private void cellMerge(Sheet sheet, int rsColumns, int rsRows, String preffix, String classname) {
        try {
            Object service = Util.getBeanObject(classname + "Service");
            Class<?> clazz = null;
            try {
                clazz = Class.forName(preffix + classname);
            } catch (ClassNotFoundException e) {
                preffix ="cn.topcheer.pms.pojo.";
                try {
                    clazz = Class.forName(preffix + classname);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            Object object = clazz.newInstance();
            Method merge = service.getClass().getMethod("merge", Object.class);
            Cell[] cellkey = sheet.getRow(0);
            for (int j = 1; j < rsRows; j++) {
                JSONObject json = new JSONObject();
                Cell[] cellvalue = sheet.getRow(j);
                for (int k = 0; k < rsColumns; k++) {
                    json.put(cellkey[k].getContents(), cellvalue[k].getContents());
                }
                System.out.println(json);
                Util.ApplyObject(object, json);
                merge.invoke(service, object);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出列表的配置数据
     */
    public HSSFWorkbook outputDataById(String ids, String type){
        String[] idArr = ids.split(",");
        String id_insql = "";
        for (int i = 0; i < idArr.length; i++) {
            if(i==0){
                id_insql = "('";
            }
            id_insql = id_insql +idArr[i]+ "','";
        }
        if(!Util.isEoN(id_insql)){
            id_insql = id_insql.substring(0,id_insql.length()-2)+")";
        }
        // 创建文档对象(其他对象都是通过文档对象创建)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建样式对象（HSSFCellStyle ）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 导出excel的名称
        if("SysParams".equals(type)){
            //获取父列表
            List<Map> sysParamsList = this.dbHelper.getRows("select t.* from sys_params t where t.id in "+id_insql, new Object[]{});
            //获取子列表
            List<Map> childSysParamsList = this.dbHelper.getRows("select t.* from sys_params t where t.parentid in "+id_insql, new Object[]{});
            String[] sysParamskey = Util.getKeys(sysParamsList);
            String[] childSysparamskey = Util.getKeys(childSysParamsList);
            wb = Util.getSheetAll(sysParamsList, wb, cellStyle, sysParamskey, sysParamskey, "SysParams");
            wb = Util.getSheetAll(childSysParamsList, wb, cellStyle, childSysparamskey, childSysparamskey, "ChildSysParams");
        }else if("PmsMainapp".equals(type)){
            List<Map> mainappList = this.dbHelper.getRows("select t.* from pms_mainapp t where t.id in "+id_insql, new Object[]{});
            String[] mainappkey = Util.getKeys(mainappList);
            wb = Util.getSheetAll(mainappList, wb, cellStyle, mainappkey, mainappkey, "PmsMainapp");
        }
        return wb;
    }

    // 指定表名导入数据
    public Boolean newImportData(File excelFile){
        try{
            Workbook rwb = Workbook.getWorkbook(excelFile);
            // 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...
            for (int i = 0; i < rwb.getSheets().length; i++) {
                Sheet sheet = rwb.getSheets()[i];
                int rsColumns = sheet.getColumns();// 列数
                int rsRows = sheet.getRows();// 行数
                String tablename = sheet.getName();
                this.newCellMerge(sheet, rsColumns, rsRows, "cn.topcheer.pms.pojo.", tablename);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void newCellMerge(Sheet sheet, int rsColumns, int rsRows, String preffix, String classname) {
        try {
            Object service = null;
            if("ChildSysParams".equals(classname)){
                service = Util.getBeanObject("SysParams" + "Service");
            }else {
                service = Util.getBeanObject(classname + "Service");
            }
            Class<?> clazz = null;
            try {
                clazz = Class.forName(preffix + classname);
            } catch (ClassNotFoundException e) {
                preffix ="cn.topcheer.pms.pojo.";
                try {
                    if("ChildSysParams".equals(classname)){
                        clazz = Class.forName(preffix + "SysParams");
                    }else {
                        clazz = Class.forName(preffix + classname);
                    }
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            Object object = clazz.newInstance();
            Method merge = service.getClass().getMethod("merge", Object.class);
            Cell[] cellkey = sheet.getRow(0);
            //先删除子数据
            if("SysParams".equals(classname)){
                for (int j = 1; j < rsRows; j++) {
                    Cell[] cellvalue = sheet.getRow(j);
                    for (int k = 0; k < rsColumns; k++) {
                        if("id".equals(cellkey[k].getContents())){
                            String id = cellvalue[k].getContents();
                            List<SysParams> list = this.findByHql("select t from SysParams t where t.parentid = ?",new Object[]{id});
                            if(list.size()>0){
                                this.deleteList(list);
                            }
                        }
                    }
                }
            }
            for (int j = 1; j < rsRows; j++) {
                JSONObject json = new JSONObject();
                Cell[] cellvalue = sheet.getRow(j);
                for (int k = 0; k < rsColumns; k++) {
                    json.put(cellkey[k].getContents(), cellvalue[k].getContents());
                }
                System.out.println(json);
                Util.ApplyObject(object, json);
                merge.invoke(service, object);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
