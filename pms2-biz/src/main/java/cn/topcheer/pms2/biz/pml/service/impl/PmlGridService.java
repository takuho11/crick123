/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2019-3-3 15:54:33
 */
package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.biz.modules.resource.builder.oss.OssBuilder;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.sys.SysOperationrecord;
import cn.topcheer.pms2.api.pml.entity.PmlGrid;
import cn.topcheer.pms2.api.pml.entity.PmlGridButton;
import cn.topcheer.pms2.api.pml.entity.PmlGridColumn;
import cn.topcheer.pms2.common.enumerate.ErrorCodeEnum;
import cn.topcheer.pms2.common.enumerate.SysVersionEnum;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.dao.pml.PmlGridDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.oss.model.BladeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.logging.Logger;

/**
 * PmlGrid 服务类
 */
@Service(value = "PmlGridService")
public class PmlGridService extends GenericPageService<PmlGrid> {


    @Autowired
    private OssBuilder ossBuilder;

    @Autowired
    private DBHelper dbHelper;
    @Autowired
    private SysOperationrecordService sysOperationrecordService;
    @Autowired
    private PmlCommonGridService pmsCommonGridService;
    @Autowired
    private PmlGridColumnService pmsGridColumnService;
    @Autowired
    private PmlGridButtonService pmsGridButtonService;
    @Autowired
    private PmlGridtabsGridService pmsGridtabsGridService;
    @Autowired
    private SysDownRecordService sysDownRecordService;
    @Autowired
    private PmlCollectionService pmsCollectionService;
    //	@Autowired
//	private PmlSearchboxService pmsSearchboxService;
    @Autowired
    private PmlSearchboxCollectionService pmsSearchboxCollectionService;

    private final static Logger logger = Logger.getLogger(String.valueOf(PmlGridService.class));

    public PmlGridDao getPmlGridDao() {
        return (PmlGridDao) this.getGenericDao();
    }

    @Autowired
    public void setPmlGridDao(PmlGridDao pmsGridDao) {

        this.setGenericDao(pmsGridDao);
    }


    /**
     * 【列表配置信息的初始化和搜索方法】
     *
     * @param json
     * @return
     */
    public PageResult<List<Map>> getGridData(JSONObject json) {
        //参数集合
        List paramList = new ArrayList<>();
        //判断是否有搜索条件
        String insql = "";
        if (json.has("searchContent")) {
            insql = " and (e.gridtype like ? or e.gridname like ? or e.remarks like ?)";
            paramList.add("%" + json.get("searchContent") + "%");
            paramList.add("%" + json.get("searchContent") + "%");
            paramList.add("%" + json.get("searchContent") + "%");
        }
        if (json.has("businesstype")) {
            if (!Util.isEoN(json.get("businesstype"))) {
                insql = insql + " and e.businesstype = ? ";
                paramList.add(json.get("businesstype") + "");
            }
        }
        //sql语句
        String sql = "select e.*," +
                " (case when e.havesearchbox = '1' then '有' else '无' end) as havesearchbox_as," +
                " (case when e.haveremindcolor = '1' then '有' else '无' end) as haveremindcolor_as," +
                " (case when e.readscript = '1' then '是' else '否' end) as readscript_as," +
                " to_char(e.createdate,'yyyy-mm-dd') as createdate_as " +
                " from pml_grid e " +
                " where 1=1	" + insql + " order by e.seq";
        //分页配置
        JSONObject pageConfig = json.getJSONObject("pageConfig");
        //分页处理
        PageResult<List<Map>> page = this.findPageBySql(sql, paramList, Page.of(pageConfig));
        return page;
    }


    /**
     * 【根据id获取列表配置信息】
     *
     * @param json
     */
    public Map getGridDataById(JSONObject json) {
        String id = json.get("id") + "";
        List<Map> resList = this.dbHelper.getRows("select e.* from pml_grid e where e.id = ?", new Object[]{id});
        Map resMap = new HashMap();
        if (!Util.isEoN(resList) && resList.size() > 0) {
            resMap = resList.get(0);
        }
        //获取列名数据
        List<Map> columnData = this.dbHelper.getRows("select t.*,(case when t.remarks is null then t.columnname_ch else t.columnname_ch || '(备注:' || t.remarks || ')' end) as showname " +
                " from pml_grid_column e " +
                " left join pml_column t on e.columnid = t.id " +
                " where e.gridid = ? order by e.seq", new Object[]{id});
        resMap.put("columnData", columnData);
        //获取操作列按钮数据
        List<Map> gridBtnData = this.dbHelper.getRows("select t.*,(case when t.remarks is null then t.buttonname else t.buttonname || '(备注:' || t.remarks || ')' end) as showname" +
                " from pml_grid_button e " +
                " left join pml_button t on e.buttonid = t.id  " +
                " where e.gridid = ? and t.buttontype = 'gridbtn'  order by e.seq", new Object[]{id});
        resMap.put("gridBtnData", gridBtnData);
        //获取列表外按钮数据
        List<Map> otherBtnData = this.dbHelper.getRows("select t.*,(case when t.remarks is null then t.buttonname else t.buttonname || '(备注:' || t.remarks || ')' end) as showname" +
                " from pml_grid_button e " +
                " left join pml_button t on e.buttonid = t.id " +
                " where e.gridid = ?  and t.buttontype = 'otherbtn'  order by e.seq", new Object[]{id});
        resMap.put("otherBtnData", otherBtnData);
        return resMap;
    }

    /**
     * 【新增和修改方法】
     *
     * @param request
     * @param json
     */
    public void addAndEdit(HttpServletRequest request, JSONObject json) {
        String type = "";//新增或者修改
        PmlGrid pmsGrid = new PmlGrid();
        String id = json.has("id") ? json.getString("id") : "";
        if (StringUtils.isNotBlank(id)) {
            //说明是修改
            pmsGrid = this.findById(id);
            type = "列表修改";
        } else {
            //说明是新增
            json.remove("id");
            pmsGrid.setId(Util.NewGuid());
            pmsGrid.setCreatedate(new Date());
            type = "列表新增";
        }


        // 特殊字段处理
        json.put("havesearchbox", json.has("havesearchbox") && "1".equals(json.getString("havesearchbox")));
        json.put("judgeinit", json.has("judgeinit") && "1".equals(json.getString("judgeinit")));
        json.put("scroll_bar", json.has("scroll_bar") && "1".equals(json.getString("scroll_bar")));
        json.put("haveremindcolor", json.has("haveremindcolor") && "1".equals(json.getString("haveremindcolor")));
        json.put("haveotherbtn", json.has("haveotherbtn") && "1".equals(json.getString("haveotherbtn")));
        json.put("readscript", json.has("readscript") && "1".equals(json.getString("readscript")));
        json.put("judgetips", json.has("judgetips") && "1".equals(json.getString("judgetips")));

        // 是否判断角色，先取出来赋值，之后移除，避免ApplyObjectNew报错
        if ("1".equals(json.get("judgerole") + "")) {
            pmsGrid.setJudgerole(true);
            if (json.has("roleData")) {
                JSONArray roleData = json.getJSONArray("roleData");
                if (!Util.isEoN(roleData) && roleData.size() > 0) {
                    pmsGrid.setRoledata(roleData.toString());
                } else {
                    pmsGrid.setRoledata("");
                }
            } else {
                pmsGrid.setRoledata("");
            }
        } else {
            pmsGrid.setJudgerole(false);
            pmsGrid.setRoledata("");
        }
        json.remove("judgerole");
        json.remove("roleData");
        json.remove("roledata");

        if ("true".equals(json.get("judgetips") + "")) {
            pmsGrid.setJudgetips(true);
            if (json.has("tipsdata")) {
                String tipsData = (String) json.get("tipsdata") + "";
                if (!Util.isEoN(tipsData)) {
                    pmsGrid.setTipsdata(tipsData.toString());
                } else {
                    pmsGrid.setTipsdata("");
                }
            } else {
                pmsGrid.setTipsdata("");
            }
        } else {
            pmsGrid.setJudgetips(false);
            pmsGrid.setTipsdata("");
        }
        json.remove("judgetips");
        json.remove("tipsdata");

        // 前台参数集合
        pmsGrid.setJsonattributes(json.has("jsonAttributes") ? json.getJSONArray("jsonAttributes").toString() : "[]");
        json.remove("jsonAttributes");
        json.remove("jsonattributes");

        // where条件集合
        pmsGrid.setWhereconditions(json.has("whereConditions") ? json.getJSONArray("whereConditions").toString() : "[]");
        json.remove("whereConditions");
        json.remove("whereconditions");

        // JSON数据转实体
        Util.ApplyObjectNew(pmsGrid, json);


        //特殊字段处理
        //有无搜索框
        if (json.getBoolean("havesearchbox")) {
            pmsGrid.setHavesearchbox(true);
        } else {
            pmsGrid.setHavesearchbox(false);
            pmsGrid.setSearchboxtype("");
            pmsGrid.setSearchboxtips("");
        }

//        //是否默认滚动
//        if ("1".equals(json.get("scroll_bar") + "")) {
//            pmsGrid.setScrollBar(true);
//        } else {
//            pmsGrid.setScrollBar(false);
//        }
//        //有无提醒验收
//        if ("1".equals(json.get("haveremindcolor") + "")) {
//            pmsGrid.setHaveremindcolor(true);
//        } else {
//            pmsGrid.setHaveremindcolor(false);
//        }

        //有无列表外按钮
//        if ("1".equals(json.get("haveotherbtn") + "")) {
//            pmsGrid.setHaveotherbtn(true);
//        } else {
//            pmsGrid.setHaveotherbtn(false);
//        }
//        //有无读取脚本
//        if ("1".equals(json.get("readscript") + "")) {
//            pmsGrid.setReadscript(true);
//        } else {
//            pmsGrid.setReadscript(false);
//        }
        if (json.has("columnData")) {
            //先删除原来选择的列名
            List<PmlGridColumn> pmsGridColumnList = this.pmsGridColumnService.findByProperty("gridid", pmsGrid.getId());
            if (!Util.isEoN(pmsGridColumnList) && pmsGridColumnList.size() > 0) {
                this.pmsGridColumnService.deleteList(pmsGridColumnList);
            }
            //选择列名
            JSONArray columnData = json.getJSONArray("columnData");
            if (!Util.isEoN(columnData) && columnData.size() > 0) {
                for (int i = 0; i < columnData.size(); i++) {
                    PmlGridColumn pmsGridColume = new PmlGridColumn();
                    pmsGridColume.setId(Util.NewGuid());
                    pmsGridColume.setColumnid(columnData.getJSONObject(i).get("id") + "");
                    pmsGridColume.setGridid(pmsGrid.getId());
                    pmsGridColume.setSeq(i);
                    this.pmsGridColumnService.merge(pmsGridColume);
                }
            }
        }
        if (json.has("gridBtnData") || json.has("otherBtnData")) {
            //先删除原来选择的所有按钮
            List<PmlGridButton> pmsGridButtonList = this.pmsGridButtonService.findByProperty("gridid", pmsGrid.getId());
            if (!Util.isEoN(pmsGridButtonList) && pmsGridButtonList.size() > 0) {
                this.pmsGridButtonService.deleteList(pmsGridButtonList);
            }
            if (json.has("gridBtnData")) {
                //选择操作列按钮
                JSONArray gridBtnData = json.getJSONArray("gridBtnData");
                if (!Util.isEoN(gridBtnData) && gridBtnData.size() > 0) {
                    for (int i = 0; i < gridBtnData.size(); i++) {
                        PmlGridButton pmsGridButton = new PmlGridButton();
                        pmsGridButton.setId(Util.NewGuid());
                        pmsGridButton.setGridid(pmsGrid.getId());
                        pmsGridButton.setButtonid(gridBtnData.getJSONObject(i).get("id") + "");
                        pmsGridButton.setSeq(i);
                        this.pmsGridButtonService.merge(pmsGridButton);
                    }
                }
            }
            if (json.has("otherBtnData")) {
                //选择列表外按钮
                JSONArray otherBtnData = json.getJSONArray("otherBtnData");
                if (!Util.isEoN(otherBtnData) && otherBtnData.size() > 0) {
                    for (int i = 0; i < otherBtnData.size(); i++) {
                        PmlGridButton pmsGridButton = new PmlGridButton();
                        pmsGridButton.setId(Util.NewGuid());
                        pmsGridButton.setGridid(pmsGrid.getId());
                        pmsGridButton.setButtonid(otherBtnData.getJSONObject(i).get("id") + "");
                        pmsGridButton.setSeq(i);
                        this.pmsGridButtonService.merge(pmsGridButton);
                    }
                }
            }
        }
//        //前台Json参数
//        if (json.has("jsonAttributes")) {
//            JSONArray jsonAttributes = json.getJSONArray("jsonAttributes");
//            if (!Util.isEoN(jsonAttributes) && jsonAttributes.size() > 0) {
//                pmsGrid.setJsonattributes(jsonAttributes.toString());
//            } else {
//                pmsGrid.setJsonattributes("");
//            }
//        } else {
//            pmsGrid.setJsonattributes("");
//        }
//        //Where条件
//        if (json.has("whereConditions")) {
//            JSONArray whereConditions = json.getJSONArray("whereConditions");
//            if (!Util.isEoN(whereConditions) && whereConditions.size() > 0) {
//                pmsGrid.setWhereconditions(whereConditions.toString());
//            } else {
//                pmsGrid.setWhereconditions("");
//            }
//        } else {
//            pmsGrid.setWhereconditions("");
//        }
        //保存或修改
        this.merge(pmsGrid);
        //操作记录
        SysOperationrecord sysOperationrecord = new SysOperationrecord();
        sysOperationrecord.setSourceid(pmsGrid.getId());
        sysOperationrecord.setType("通用列表-列表配置");
        sysOperationrecord.setNote("操作人进行了" + type);
        this.sysOperationrecordService.saveCurrentUserOperation(request, sysOperationrecord);
    }

    /**
     * 【删除方法】--通过id删除
     *
     * @param request
     * @param id
     */
    public boolean deleteDataById(HttpServletRequest request, String id) {
        try {
            //删除列表和列名的关联表
            this.dbHelper.runSql("delete from pml_grid_column e where e.gridid=?", new Object[]{id});
            //删除列表和按钮的关联表
            this.dbHelper.runSql("delete from pml_grid_button e where e.gridid=?", new Object[]{id});
            //删除列表
            PmlGrid pmsGrid = this.findById(id);
            String gridname = pmsGrid.getGridname();
            this.deleteById(id);
            //操作记录
            SysOperationrecord sysOperationrecord = new SysOperationrecord();
            sysOperationrecord.setSourceid(id);
            sysOperationrecord.setType("通用列表-列表配置");
            sysOperationrecord.setNote("操作人进行了列表删除，列表名称:" + gridname + "；列表id:" + id);
            this.sysOperationrecordService.saveCurrentUserOperation(request, sysOperationrecord);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 【获取所有的列表数据】---为了列表tabs选择
     *
     * @return
     */
    public List<Map> getAllDataForGridTabs() {
        String sql = "select t.id, (t.businesstype || ':'||t.gridname||','||'备注:'||t.remarks) as showname,t.gridname,t.businesstype " +
                "  from pml_grid t order by t.seq";
        List<Map> resList = dbHelper.getRows(sql, null);
        return resList;
    }


    /**
     * 【通用的打印方法】---全部打印+选中打印
     *
     * @param json
     */
    public Map commonPrint(SysUser user, JSONObject json, HttpServletRequest request) {
        Map resMap = new HashMap<>();
        try {
            // 创建文档对象(其他对象都是通过文档对象创建)
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象(excel的表单对象)
            HSSFSheet sheet = wb.createSheet("导出清单");
            // 创建样式对象（HSSFCellStyle ）
            HSSFCellStyle cellStyle = wb.createCellStyle();
            // 创建字体对象
            HSSFFont font = wb.createFont();
            // 设置粗体
            font.setBold(true);
            // 将字体对象赋给单元格样式对象
            cellStyle.setFont(font);

            //获取列表配置数据
            String gridType = json.get("gridType") + "";
            List<PmlGrid> pmsGridList = this.findByProperty("gridtype", gridType);
            if (pmsGridList != null && pmsGridList.size() > 0) {
                PmlGrid pmsGrid = pmsGridList.get(0);
                //获取数据源
                JSONArray jsonArray = this.pmsCommonGridService.getAllDataByClass(user, pmsGrid, json);
                //获取列名信息
                List<Map> columnList = dbHelper.getRows("select distinct gc.id ,c.columnname_ch,c.columnname_en,gc.seq  " +
                        " from pml_grid_column gc " +
                        " left join pml_grid g on gc.gridid = g.id " +
                        " left join pml_column c on gc.columnid = c.id " +
                        " where g.gridtype = ? and c.columnname_ch != '操作' " +
                        " order by gc.seq", new Object[]{gridType});
                // 在sheet里创建第一行，参数为行索引
                HSSFRow row1 = sheet.createRow(0);
                HSSFRow row;
                for (int i = 0; i < columnList.size(); i++) {
                    HSSFCell cell = row1.createCell(i);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(columnList.get(i).get("columnname_ch") + "");
                }
                for (int j = 1; j < jsonArray.size() + 1; j++) {
                    row = sheet.createRow(j);
                    JSONObject job = jsonArray.getJSONObject(j - 1);
                    for (int s = 0; s < columnList.size(); s++) {
                        String mycell = columnList.get(s).get("columnname_en") + "";
                        if (job.containsKey(mycell)) {
                            row.createCell(s).setCellValue(job.getString(mycell));
                        }
                    }
                }
                String publishPath = request.getRealPath("/");
                long time = new Date().getTime();
                String excelName = "gridexcel" + time + ".xls";
//                //File file =new File(publishPath+"/helpdocs/gridexcel");
//                File file = new File(Util.GetFileRealPath("D://gridexcel"));
//                if (!file.exists() && !file.isDirectory()) {
//                    //说明文件夹不存在
//                    file.mkdir();
//                }
//                FileOutputStream fout = new FileOutputStream(Util.GetFileRealPath("D://gridexcel//" + excelName));

                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                wb.write(byteArrayOutputStream);
                ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                BladeFile bladeFile = ossBuilder.template().putFile(excelName,byteArrayInputStream,"download");
                byteArrayOutputStream.close();
                byteArrayInputStream.close();


                String recordid = this.sysDownRecordService.commonSaveOperation(request, String.valueOf(time), "列表excel通用下载", excelName, bladeFile.getLink());

                resMap.put("success", "true");
                resMap.put("data", recordid);
                return resMap;
            } else {
                resMap.put("success", "false");
                return resMap;
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCodeEnum.XXX.getMessage());
        }
    }

    /**
     * 根据businesstype获取type
     *
     * @param businesstype
     * @return
     */
    public String getTypeByBusinesstype(String businesstype) {
        if (Util.isEoN(businesstype)) {
            return "";
        }
//		SysVersionEnum e = SysVersionEnum.getEnumByBusinessType(businesstype);
//		if(Util.isEoN(type)){
//			return "";
//		}
        return SysVersionEnum.getTypeByName(businesstype);


//		switch (businesstype){
//			case "申报":
//				return "sb";
//			case "合同填报":
//				return "ht";
//			case "合同变更":
//				return "htbg";
//			case "中期检查":
//				return "zqjc";
//			case "季度报告":
//				return "jb";
//			case "年度报告":
//				return "nb";
//			case "科技报告":
//				return "kjbg";
//			case "验收申请":
//				return "ys";
//			case "验收申请-成果登记":
//				return "cgdj";
//			case "孵化器":
//				return "kc";
//			case "孵化器变更":
//				return "kcbg";
//			case "孵化器注销":
//				return "kczx";
//			case "孵化器年度报告":
//				return "cr";
//			case "孵化器后补助":
//				return "fhqhbz";
//			case "孵化器推荐函":
//				return "fhqtjh";
//			case "科技奖励":
//				return "rw";
//			case "特派员发布需求":
//				return "fbxq";
//			case "特派员推荐表":
//				return "kjtpy_tjb";
//			case "特派员月报":
//				return "kjtpy_yb";
//			case "特派员成果发布":
//				return "kjtpy_cgfb";
//			case "特派员培训计划":
//				return "kjtpy_px";
//			case "特派员工作总结":
//				return "kjtpy_gzzj";
//			case "科技创新平台":
//				return "kjcxpt";
//			case "转移机构":
//				return "zyjg";
//			case "转移机构考核表":
//				return "zyjg_khb";
//			case "转移机构技术经理人":
//				return "zyjg_jsjlr";
//			case "其他":
//				return "other";
//			default :
//				return "";
//		}
    }

    /**
     * 专门给PmlCommonGridService使用
     *
     * @param sql
     * @param params
     * @return
     */
    public String getOnlyValueBySqlForGrid(String sql, Object[] params) {
        return this.getOnlyValueBySql(sql, params);
    }

    /**
     * 给控制首页使用，获取待审核的Gridtype，一般1个用户，对应只有一个待审核列表的
     *
     * @param userid
     */
    public String getDshGridtype(String userid, String businesstype) {
        List<Map> roleList = dbHelper.getRows("select e.roleid from sys_identity e where e.userid = ? group by e.roleid ", new Object[]{userid});
        for (int i = 0; i < roleList.size(); i++) {
            String roleid = roleList.get(i).get("roleid") + "";
            String gridtype = this.getOnlyValueBySql("select e.gridtype from pml_grid e where e.roledata like ? " +
                    " and e.gridname like '%待审核%' " +
                    " and e.businesstype = ?", new Object[]{"%" + roleid + "%", businesstype});
            if (!Util.isEoN(gridtype)) {
                return gridtype;
            }
        }
        return "";
    }

    /**
     * 通过gridtype获取PmlGrid
     *
     * @param gridtype
     * @return
     */
    public PmlGrid getByGridtype(String gridtype) {
        List<PmlGrid> list = this.findByHql("select t from PmlGrid t where t.gridtype = ?", new Object[]{gridtype});
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return new PmlGrid();
    }

//	/**
//	 * 导出列表的配置数据
//	 */
//	public HSSFWorkbook outputDataById(String ids, String type){
//		String[] idArr = ids.split(",");
//		String id_insql = "";
//		for (int i = 0; i < idArr.length; i++) {
//			if(i==0){
//				id_insql = "('";
//			}
//			id_insql = id_insql +idArr[i]+ "','";
//		}
//		if(!Util.isEoN(id_insql)){
//			id_insql = id_insql.substring(0,id_insql.length()-2)+")";
//		}
//		// 创建文档对象(其他对象都是通过文档对象创建)
//		HSSFWorkbook wb = new HSSFWorkbook();
//		// 创建样式对象（HSSFCellStyle ）
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		// 导出excel的名称
//		if("column".equals(type)){
//			//列名
//			List<Map> pmsColumnList = this.dbHelper.getRows("select t.* from pml_column t where t.id in "+id_insql, new Object[]{});
//			String[] pmsColumnkey = Util.getKeys(pmsColumnList);
//			pmsColumnList = Util.changeBoolean(pmsColumnList,"havejump");
//			wb = Util.getSheetAll(pmsColumnList, wb, cellStyle, pmsColumnkey, pmsColumnkey, "PmlColumn");
//		}else if("button".equals(type)){
//			//按钮
//			List<Map> pmsButtonList = this.dbHelper.getRows("select t.* from pml_button t where t.id in "+id_insql, new Object[]{});
//			String[] pmsButtonkey = Util.getKeys(pmsButtonList);
//			pmsButtonList = Util.changeBoolean(pmsButtonList,"alwaysshow,judgerole");
//			wb = Util.getSheetAll(pmsButtonList, wb, cellStyle, pmsButtonkey, pmsButtonkey, "PmlButton");
//		}else if("grid".equals(type)){
//			//列表
//			List<Map> pmsGridList = this.dbHelper.getRows("select t.* from pml_grid t where t.id in "+id_insql, new Object[]{});
//			List<Map> pmsGridColumnList = this.dbHelper.getRows("select t.* from pml_grid_column t where t.gridid in "+id_insql, new Object[]{});
//			List<Map> pmsGridButtonList = this.dbHelper.getRows("select t.* from pml_grid_button t where t.gridid in "+id_insql, new Object[]{});
//			String[] pmsGridkey = Util.getKeys(pmsGridList);
//			String[] pmsGridColumnkey = Util.getKeys(pmsGridColumnList);
//			String[] pmsGridButtonkey = Util.getKeys(pmsGridButtonList);
//			pmsGridList = Util.changeBoolean(pmsGridList,"havesearchbox,haveotherbtn,readscript,judgerole,haveremindcolor,judgeinit");
//			wb = Util.getSheetAll(pmsGridList, wb, cellStyle, pmsGridkey, pmsGridkey, "PmlGrid");
//			wb = Util.getSheetAll(pmsGridColumnList, wb, cellStyle, pmsGridColumnkey, pmsGridColumnkey, "PmlGridColumn");
//			wb = Util.getSheetAll(pmsGridButtonList, wb, cellStyle, pmsGridButtonkey, pmsGridButtonkey, "PmlGridButton");
//
//			//同时处理搜索框
//			List<Map> pmsCollectionList = this.dbHelper.getRows("select t.* from pml_collection t " +
//					"where t.id in (select tt.collectionid from pml_grid tt where tt.id in "+id_insql+" )",new Object[]{});
//			List<Map> pmsSearchboxCollectionList = this.dbHelper.getRows("select t.* from pml_searchbox_collection t " +
//					"where t.collectionid in (select tt.collectionid from pml_grid tt where tt.id in "+id_insql+" )",new Object[]{});
//			List<Map> pmsSearchboxList = this.dbHelper.getRows("select t.* from pml_searchbox t " +
//					"where t.id in (select tt.searchboxid from pml_searchbox_collection tt " +
//					"where tt.collectionid in (select ttt.collectionid from pml_grid ttt where ttt.id in "+id_insql+" ))",new Object[]{});
//			if(pmsCollectionList.size()>0){
//				String[] pmsCollectionkey = Util.getKeys(pmsCollectionList);
//				wb = Util.getSheetAll(pmsCollectionList, wb, cellStyle, pmsCollectionkey, pmsCollectionkey, "PmlCollection");
//			}
//			if(pmsSearchboxCollectionList.size()>0){
//				String[] pmsSearchboxCollectionkey = Util.getKeys(pmsSearchboxCollectionList);
//				wb = Util.getSheetAll(pmsSearchboxCollectionList, wb, cellStyle, pmsSearchboxCollectionkey, pmsSearchboxCollectionkey, "PmlSearchboxCollection");
//			}
//			if(pmsSearchboxList.size()>0) {
//				String[] pmsSearchboxkey = Util.getKeys(pmsSearchboxList);
//				wb = Util.getSheetAll(pmsSearchboxList, wb, cellStyle, pmsSearchboxkey, pmsSearchboxkey, "PmlSearchbox");
//			}
//		}else if("gridtabs".equals(type)){
//			//列表tabs
//			List<Map> pmsGridtabsList = this.dbHelper.getRows("select t.* from pml_gridtabs t where t.id in "+id_insql, new Object[]{});
//			List<Map> pmsGridtabsGridList = this.dbHelper.getRows("select t.* from pml_gridtabs_grid t where t.gridtabsid in "+id_insql, new Object[]{});
//			String[] pmsGridtabskey = Util.getKeys(pmsGridtabsList);
//			String[] pmsGridtabsGridkey = Util.getKeys(pmsGridtabsGridList);
//			wb = Util.getSheetAll(pmsGridtabsList, wb, cellStyle, pmsGridtabskey, pmsGridtabskey, "PmlGridtabs");
//			wb = Util.getSheetAll(pmsGridtabsGridList, wb, cellStyle, pmsGridtabsGridkey, pmsGridtabsGridkey, "PmlGridtabsGrid");
//		}else if("gridauthority".equals(type)){
//			List<Map> gridList = this.dbHelper.getRows("select t.* from fl_authority_grid t", new Object[]{});
//			List<Map> fpList = this.dbHelper.getRows("select t.* from fl_authority_fp t ", new Object[]{});
//			String[] gridkey = Util.getKeys(gridList);
//			String[] fpkey = Util.getKeys(fpList);
//			wb = Util.getSheetAll(gridList, wb, cellStyle, gridkey, gridkey, "flAuthorityGrid");
//			wb = Util.getSheetAll(fpList, wb, cellStyle, fpkey, fpkey, "flAuthorityFp");
//		}
//		return wb;
//	}


//	// 指定表名导入数据
//	public Boolean importData(File excelFile){
//		try{
//			Workbook rwb = Workbook.getWorkbook(excelFile);
//			// 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...
//			for (int i = 0; i < rwb.getSheets().length; i++) {
//				Sheet sheet = rwb.getSheets()[i];
//				int rsColumns = sheet.getColumns();// 列数
//				int rsRows = sheet.getRows();// 行数
//				String tablename = sheet.getName();
//				this.cellMerge(sheet, rsColumns, rsRows, "cn.topcheer.pms2.api.project.entity.", tablename);
//			}
//			return true;
//		}catch (Exception e){
//			return false;
//		}
//	}
//	//处理大字段
//	private void cellMerge(Sheet sheet, int rsColumns, int rsRows, String preffix, String classname) {
//		try {
//			if("flAuthorityFp".equals(classname)||"flAuthorityGrid".equals(classname)){
//				preffix = "cn.topcheer.flow.pojo.";
//			}
//			Object service = Util.getBeanObject(classname + "Service");
//			Class<?> clazz = null;
//			try {
//				if("flAuthorityFp".equals(classname)){
//					classname = "FlAuthorityFp";
//				}
//				if("flAuthorityGrid".equals(classname)){
//					classname = "FlAuthorityGrid";
//				}
//				clazz = Class.forName(preffix + classname);
//			} catch (ClassNotFoundException e) {
//				preffix ="cn.topcheer.pms2.api.project.entity.";
//				try {
//					clazz = Class.forName(preffix + classname);
//				} catch (ClassNotFoundException e1) {
//					e1.printStackTrace();
//				}
//			}
//			Object object = clazz.newInstance();
//			Method merge = service.getClass().getMethod("merge", Object.class);
//			Cell[] cellkey = sheet.getRow(0);
//			//先删除关联表
//			if("PmlGridColumn".equals(classname)||"PmlGridButton".equals(classname)||"PmlGridtabsGrid".equals(classname)||"PmlGrid".equals(classname)){
//				for (int j = 1; j < rsRows; j++) {
//					JSONObject json = new JSONObject();
//					Cell[] cellvalue = sheet.getRow(j);
//					for (int k = 0; k < rsColumns; k++) {
//						json.put(cellkey[k].getContents(), cellvalue[k].getContents());
//					}
//					//判断是否还有值
//					if("PmlGridColumn".equals(classname)){
//						String gridid = json.getString("gridid");
//						List<PmlGridColumn> list = this.findByHql("select t from PmlGridColumn t where t.gridid = ?",new Object[]{gridid});
//						if(list.size()>0){
//							this.pmsGridColumnService.deleteList(list);
//						}
//					}
//					if("PmlGridButton".equals(classname)){
//						String gridid = json.getString("gridid");
//						List<PmlGridButton> list = this.findByHql("select t from PmlGridButton t where t.gridid = ?",new Object[]{gridid});
//						if(list.size()>0){
//							this.pmsGridButtonService.deleteList(list);
//						}
//					}
//					if("PmlGridtabsGrid".equals(classname)){
//						String gridtabsid = json.getString("gridtabsid");
//						List<PmlGridtabsGrid> list = this.findByHql("select t from PmlGridtabsGrid t where t.gridtabsid = ?",new Object[]{gridtabsid});
//						if(list.size()>0){
//							this.pmsGridtabsGridService.deleteList(list);
//						}
//					}
//					if("PmlGrid".equals(classname)){
//						String collectionid = json.getString("collectionid");
//
//						List<PmlSearchbox> list1 = this.findByHql("select t from PmlSearchbox t,PmlSearchboxCollection p where t.id = p.searchboxid and p.collectionid = ?",new Object[]{collectionid});
//						if(list1.size()>0){
//							this.pmsSearchboxService.deleteList(list1);
//						}
//
//						List<PmlCollection> list2 = this.findByHql("select t from PmlCollection t where t.id = ?",new Object[]{collectionid});
//						if(list2.size()>0){
//							this.pmsCollectionService.deleteList(list2);
//						}
//
//						List<PmlSearchboxCollection> list3 = this.findByHql("select t from PmlSearchboxCollection t where t.collectionid = ?",new Object[]{collectionid});
//						if(list3.size()>0){
//							this.pmsSearchboxCollectionService.deleteList(list3);
//						}
//					}
//				}
//			}
//			//赋值
//			for (int j = 1; j < rsRows; j++) {
//				JSONObject json = new JSONObject();
//				Cell[] cellvalue = sheet.getRow(j);
//				for (int k = 0; k < rsColumns; k++) {
//					json.put(cellkey[k].getContents(), cellvalue[k].getContents());
//				}
//				//System.out.println(json);
//				logger.info(json);
//				Util.ApplyObject(object, json);
//				if("PmlButton".equals(classname)){
//					if(!Util.isEoN(json.get("roledata"))){
//						Method method = clazz.getMethod("setRoledata", String.class);
//						method.invoke(object, json.get("roledata").toString() );
//					}
//				}else if("PmlGrid".equals(classname)){
//					if(!Util.isEoN(json.get("jsonattributes"))){
//						Method method = clazz.getMethod("setJsonattributes", String.class);
//						method.invoke(object, json.get("jsonattributes").toString() );
//					}
//					if(!Util.isEoN(json.get("whereconditions"))){
//						Method method = clazz.getMethod("setWhereconditions", String.class);
//						method.invoke(object,  json.get("whereconditions").toString());
//					}
//					if(!Util.isEoN(json.get("roledata"))){
//						Method method = clazz.getMethod("setRoledata", String.class);
//						method.invoke(object, json.get("roledata").toString());
//					}
//				}else if("PmlSearchbox".equals(classname)){
//					if(!Util.isEoN(json.get("code"))){
//						Method method = clazz.getMethod("setCode", String.class);
//						method.invoke(object, json.get("code").toString());
//					}
//					if(!Util.isEoN(json.get("specialclass"))){
//						Method method = clazz.getMethod("setSpecialclass", String.class);
//						method.invoke(object, json.get("specialclass").toString());
//					}
//				}
//				merge.invoke(service, object);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

    public List<Map> fetchGridByBusinesstype(JSONObject jsonObject) {

        List<Map> result = new ArrayList<>();
        String businesstype = jsonObject.get("businesstype") + "";
        if (Util.isEoN(businesstype)) {
            return result;
        }
        String sql = " select t.* from PMS_GRID t where t.businesstype = ? ";
        result = dbHelper.getRows(sql, new Object[]{businesstype});
        return result;
    }
}
