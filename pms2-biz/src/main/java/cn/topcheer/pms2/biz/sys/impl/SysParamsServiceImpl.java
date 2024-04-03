package cn.topcheer.pms2.biz.sys.impl;

import cn.topcheer.pms2.api.sys.SysParams;
import cn.topcheer.pms2.api.sys.vo.SysParamsVO;
import cn.topcheer.pms2.biz.sys.SysParamsService;
import cn.topcheer.pms2.dao.sys.mapper.SysParamsMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.tool.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.biz.utils.Util;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
@Service
public class SysParamsServiceImpl extends ServiceImpl<SysParamsMapper,SysParams> implements SysParamsService {

    @Resource
    private SysParamsMapper sysParamsMapper;

    public List<SysParamsVO> selectByCon(String applyType){
        return sysParamsMapper.selectByCon(applyType);
    }

    public List<SysParamsVO> selectByParentvalue(Map param){
        List<String> selectInitNameMap=(List<String>)param.get("selectInitNameMap");
        return sysParamsMapper.selectByParentvalue(selectInitNameMap);
    }

    public void saveData(SysParamsVO sysParamsVO){
        SysParams sysParams=new SysParams();
        BeanUtil.copy(sysParamsVO,sysParams);
        String id=sysParamsVO.getId();
        if(null!=getById(id)){
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("parentid",id);
            remove(queryWrapper);
        }
        sysParamsVO.getChildrensList().forEach(child-> saveOrUpdate(child));
        saveOrUpdate(sysParams);
    }

    public void deleteById(String id){
        if(null!=getById(id)){
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("parentid",id);
            remove(queryWrapper);
            removeById(id);
        }
    }

    @Autowired
    private DBHelper dbHelper;

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
//                            List<SysParams> list = this.findByHql("select t from SysParams t where t.parentid = ?",new Object[]{id});
//                            List<SysParams> list = this.list(new LambdaQueryWrapper<SysParams>()
//                                    .eq(SysParams::getParentid,id));
//                            if(list.size()>0){
                                this.remove(new LambdaQueryWrapper<SysParams>()
                                        .eq(SysParams::getParentid,id));
//                            }
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
