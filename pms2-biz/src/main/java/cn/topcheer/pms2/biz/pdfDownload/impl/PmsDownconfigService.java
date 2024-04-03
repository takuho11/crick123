/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-1-19 15:17:52
 *
 */
package cn.topcheer.pms2.biz.pdfDownload.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.pdfDownload.PmsDownconfig;
import cn.topcheer.pms2.api.sys.SysParamvalue;
import cn.topcheer.pms2.biz.enums.SysParamDataEnum;
import cn.topcheer.pms2.biz.sys.SysParamvalueService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.jpa.pdf.PmsDownconfigDao;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * PmsDownconfig 服务类
 */
@Slf4j
@Service(value="PmsDownconfigService")
public class PmsDownconfigService extends GenericService<PmsDownconfig> {
//	@Autowired
//    PmsAffixService pmsAffixService;
	@Autowired
	PmsDownconfigconvertService pmsDownconfigconvertService;
//	@Autowired
//	PmsDownconfigwaterlinkService pmsDownconfigwaterlinkService;
	@Autowired
	PmsDownconfigwaterService pmsDownconfigwaterService;
	@Autowired
	SysParamvalueService sysParamvalueService;
	@Autowired
	DBHelper dbHelper;
//	@Autowired
//    PmsTxtSave pmsTxtSave;
//    @Autowired
//    private TwoDimCodeService twoDimCodeService;
    @Autowired
    PmsDownloadtypeService pmsDownloadtypeService;
    @Autowired
    PmsImageconfigService pmsImageconfigService;

	public PmsDownconfigDao getPmsDownconfigDao() {
		return (PmsDownconfigDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsDownconfigDao(PmsDownconfigDao pmsDownconfigDao) {

		this.setGenericDao(pmsDownconfigDao);
	}

	/*
	* 批次id：batchid，业务主表id：sourceid，业务类别:businesstype，水印名称：为空的话根据传的来
	* */
	public Map<String, Object> loadPdfConfigByBatchidAndSourceid(String batchid,String sourceid,String businesstype, String watermarkname){
		Map<String, Object> hashmap = new HashMap<String, Object>();
		/*获取最外层配置*/
		List<PmsDownconfig> pcList=this.findByHql("from cn.topcheer.pms.pojo.PmsDownconfig t  where t.sourceid like ? and t.businesstype = ? ","%"+batchid+"%",businesstype);
		if(pcList.size()>0){
			log.info("【redis获取配置参数--PmsDownconfig】，sourceid为："+sourceid+"；batchid为："+batchid+"；businesstype为："+businesstype);
			PmsDownconfig pc=pcList.get(0);
			Map<String, Object> pdfData=new HashMap<String, Object>();//主要参数
			ObjInHashMap(JSONObject.fromObject(pc),pdfData);
			SysParamvalue sv=this.sysParamvalueService.findParamsClassByCodeAndTcodeOne("sysparamdata",SysParamDataEnum.AREACODE.getMessage());
			pdfData.put("areaCode",sv.getValue());//系统代码
			String signsql=pdfData.get("signsql")+"";
			if (Util.isEoN(signsql)) {//如果不需要盖章
				pdfData.put("isEncry",true);//加密
			} else {
				pdfData.put("isEncry",false);
			}
			pdfData.put("id",sourceid+businesstype);
			pdfData.put("projectbatchid",batchid);
			pdfData.put("tmpPDFFile",pdfData.get("tmpPDFFile").toString().replaceAll("【sourceid】",sourceid));
			pdfData.put("tmpWordFile",pdfData.get("tmpWordFile").toString().replaceAll("【sourceid】",sourceid));
			hashmap.put("pdfData",pdfData);
			SysParamvalue sv2=this.sysParamvalueService.findParamsClassByCodeAndTcodeOne("sysparamdata",SysParamDataEnum.PDFDOWNURL.getMessage());
			hashmap.put("retrunUrlStr",sv2.getValue()+pc.getTmpPDFFile().replace("【sourceid】",sourceid).replace("tempdoc","tempdocAll1"));

            /*优先用原配置表的模板和水印id，如果没有用新表*/
            String modeloraffixlist=pdfData.get("modeloraffixlist").toString();
            String waterid=pdfData.get("waterid").toString();

            List<Map> convertArray;
            if (modeloraffixlist.split("；").length>0) {
                convertArray = getDownConvert(batchid, sourceid, businesstype, watermarkname);
                if(convertArray.size()<0){//如果批次id没找到就用配置表id
                    convertArray = getDownConvert(pc.getId(), sourceid, businesstype, watermarkname);
                }
            } else {
                convertArray = getDownConvertOld(modeloraffixlist, sourceid,waterid, watermarkname,JSONObject.fromObject(pcList.get(0)));
            }

			hashmap.put("convertArray",convertArray);
		}else{
			log.info("【redis获取配置参数--pms_downloadtype】，sourceid为："+sourceid+"；batchid为："+batchid+"；businesstype为："+businesstype);
			List<Map> listdowns=new ArrayList<>();
			if (Util.isEoN(batchid)) {
				listdowns=this.dbHelper.getRows("select t.addpagenum as isaddpage,t.* from pms_downloadtype t where t.type=? ",new Object[]{businesstype});
			} else {
				listdowns=this.dbHelper.getRows("select t.addpagenum as isaddpage,t.* from pms_downloadtype t where t.planprojectid like ? and t.type=? ",new Object[]{"%"+batchid+"%",businesstype});
			}


			Map<String, Object> pdfData=new HashMap<String, Object>();//主要参数
			ObjInHashMap(JSONObject.fromObject(listdowns.get(0)),pdfData);
			SysParamvalue sv=this.sysParamvalueService.findParamsClassByCodeAndTcodeOne("sysparamdata",SysParamDataEnum.AREACODE.getMessage());
			pdfData.put("areaCode",sv.getValue());//系统代码
			String signsql=pdfData.get("signsql")+"";
			if (Util.isEoN(signsql)) {//如果不需要盖章
				pdfData.put("isEncry",true);//加密
			} else {
				pdfData.put("isEncry",false);
			}
			pdfData.put("id",sourceid+businesstype);
			pdfData.put("projectbatchid",batchid);
			pdfData.put("tmpPDFFile",pdfData.get("tmppdffile").toString().replaceAll("【sourceid】",sourceid));
			pdfData.put("tmpWordFile",pdfData.get("tmpwordfile").toString().replaceAll("【sourceid】",sourceid));
			hashmap.put("pdfData",pdfData);
			SysParamvalue sv2=this.sysParamvalueService.findParamsClassByCodeAndTcodeOne("sysparamdata", SysParamDataEnum.PDFDOWNURL.getMessage());
			if (listdowns.size()>0) {
				hashmap.put("retrunUrlStr",sv2.getValue()+listdowns.get(0).get("tmppdffile").toString().replace("【sourceid】",sourceid).replace("tempdoc","tempdocAll1"));
			}
			/*获取二维码*/
			log.info("【redis获取配置参数--获取二维码】，sourceid为："+sourceid+"；batchid为："+batchid+"；businesstype为："+businesstype);
//			String listdownid=listdowns.get(0).get("id").toString();
//			getTwoDim(hashmap,sourceid,listdownid);
            /*获取图片*/
			log.info("【redis获取配置参数--获取图片】，sourceid为："+sourceid+"；batchid为："+batchid+"；businesstype为："+businesstype);
            List imageList=this.pmsImageconfigService.getImageConfig(sourceid,businesstype);
            hashmap.put("imageData",imageList);//图片参数
			/*优先用原配置表的模板和水印id，如果没有用新表*/
			String modeloraffixlist=pdfData.get("modeloraffixlist").toString();
            String waterid=pdfData.get("waterid").toString();

            List<Map> convertArray;
            if (modeloraffixlist.split("；").length<1||Util.isEoN(modeloraffixlist)) {
                convertArray = getDownConvert(batchid, sourceid, businesstype, watermarkname);
            } else {
                convertArray = getDownConvertOld(modeloraffixlist, sourceid,waterid, watermarkname,JSONObject.fromObject(listdowns.get(0)));
            }
            hashmap.put("convertArray",convertArray);
		}
		return hashmap;
	}
//
    /*获取循环模板配置--新配置表*/
	private List<Map> getDownConvert(String batchid, String sourceid, String businesstype, String watermarkname) {
    /*附件拼接顺序*/
		log.info("【redis获取配置参数--getDownConvert】，sourceid为："+sourceid+"；batchid为："+batchid+"；businesstype为："+businesstype);
		List<Map> pccList=this.pmsDownconfigconvertService.getListBySql("select t.* from Pms_Downconfigconvert t  where t.sourceid=? and t.businesstype = ?  order by t.seq",new Object[]{batchid,businesstype});
		List<Map> convertArray=new ArrayList<>();
		for (int i = 0; i < pccList.size(); i++) {
			Map m=pccList.get(i);
			String type=m.get("type")+"";
			String pccid=m.get("waterid")+"";//根据关联表的id（即当前拼接的waterid）去查水印
            String conditionsql=m.get("conditionsql")+"";
			String url=m.get("url")+"";
			m=getWater(m, pccid,sourceid,new HashMap());
            if (!Util.isEoN(conditionsql)) {//有条件语句
                List<Map> list = this.dbHelper.getRows(conditionsql,new Object[]{sourceid});
                if(list.size()>0){//如果大于0，就取该模板或附件
                    if (type.indexOf("affix")<0) {//不是附件
						String[] model=url.split("/");
						if(model.length>1){
							m.put("url",model[1]);
							m.put("modelFilePath","pdfmodel/"+model[0]);//xml地址
						}else{
							m.put("url",url);
							m.put("modelFilePath","pdfmodel");//xml地址
						}
                        m.put("adress",1);
                        convertArray.add(m);
                    } else {//附件
                        String categroy=pccList.get(i).get("category")+"";
                        convertArray=getAttachmentByPid(convertArray,m, sourceid,categroy);
                    }
                }
            }else{//如果没有条件语句，就取该模板或附件
                if (type.indexOf("affix")<0) {//不是附件
                    m.put("modelFilePath","pdfmodel");//xml地址
                    m.put("adress",1);
                    convertArray.add(m);
                } else {//附件
                    String categroy=pccList.get(i).get("category")+"";
                    convertArray=getAttachmentByPid(convertArray,m, sourceid,categroy);
                }
            }
		}
		return convertArray;
	}
//
    /*获取循环模板配置--原配置表*/
    private List<Map> getDownConvertOld(String modeloraffix, String sourceid, String waterid, String watermarkname,JSONObject obj) {
		log.info("【redis获取配置参数--getDownConvertOld】，sourceid为："+sourceid);
        List<Map> convertArray=new ArrayList<>();
        /*获取条件配置*/
        String ModeloraffixlistS=this.getString(sourceid,modeloraffix);
        String wateridS=this.getString(sourceid,waterid);
        /*附件拼接顺序*/
        String[] modeloraffixlist = ModeloraffixlistS.split("；");
        String[] listwaterid = wateridS.split("；");
        if(modeloraffixlist.length>0){
            for(int i=0;i<modeloraffixlist.length;i++){
                Map m=new HashMap<>();
                /*先处理水印*/
				if (listwaterid.length>i&&!Util.isEoN(listwaterid[i])) {
					m=getWater(m, listwaterid[i],sourceid,new HashMap());
					m.put("id",waterid);
					m.put("adress",1);
					m.put("watermarkname",watermarkname);//水印
				}else{
					String isaddpasswatersql=obj.get("isaddpasswatersql").toString();
					String waterMarkStyle=obj.get("watermarkstyle").toString();
					String fillopacity=obj.get("opacity").toString();
					watermarkname=Util.isEoN(watermarkname)?(Util.isEoN(obj.get("watername"))?"":obj.get("watername").toString()):watermarkname;//优先传进来的,再判断有没有值
                    if (!Util.isEoN(isaddpasswatersql)) {
                        watermarkname=this.dbHelper.getOnlyStringValue(isaddpasswatersql,new Object[]{sourceid});
                        if(Util.isEoN(watermarkname)){
                            watermarkname="尚未通过  打印无效";
                        }else if(watermarkname.equals("1")){
                            watermarkname="审核通过";
                        }
                    }
					m.put("watermarkname",watermarkname);

					if (waterMarkStyle.equals("Watermark")||waterMarkStyle.equals("WatermarkNotEncry")) {
						getWater(m,"bbe4ada3-2c30-56eb-2b5b-3db35341001",sourceid,m);
					}else if (waterMarkStyle.equals("Watermark02")) {
						getWater(m,"xmht02",sourceid,m);
					}else if(waterMarkStyle.indexOf("setWatermarkWithSize")>-1) {//根据字段对应样式
						String[] aa=waterMarkStyle.split("_");
						String waterstyle=aa[1];
						int fontsize=(Integer.valueOf(aa[2]));
						Boolean isEncry=true;//好像用不着
						if (aa.length>3){
							isEncry=Boolean.parseBoolean(aa[3].toString());
						}
						m.put("fontsize",fontsize);
						m.put("fillopacity",fillopacity);
						if (waterstyle.equals("Watermark")||waterstyle.equals("WatermarkNotEncry")) {
							getWater(m,"bbe4ada3-2c30-56eb-2b5b-3db35341001",sourceid,m);
						}else if (waterstyle.equals("Watermark02")) {
							getWater(m,"xmht02",sourceid,m);
						}else if (waterstyle.equals("WatermarkRL")) {//左右均匀分布
							getWater(m,"WatermarkRL",sourceid,m);
						}
					}
				}
				/*-----------------------------------------处理完水印了*/
                if(modeloraffixlist[i].indexOf("xml")>0){
					String[] modeltype=modeloraffixlist[i].split("&");
                    String[] model=modeltype[0].split("/");
                    if(model.length>1){
                        m.put("url",model[1]);
                        m.put("modelFilePath","pdfmodel/"+model[0]);//xml地址
                    }else{
                        m.put("url",modeltype[0]);
                        m.put("modelFilePath","pdfmodel");//xml地址
                    }
                    m.put("type",modeltype[1]);
					convertArray.add(m);
                }else{
					if (listwaterid.length>i) {
						m.put("id", listwaterid[i]);
					}
					convertArray=getAttachmentByPid(convertArray,m, sourceid,modeloraffixlist[i]);
                }
            }
        }
        return convertArray;
    }
//
	private Map getWater(Map map, String pccid,String sourceid,Map fixmap) {//fixmap为需固定传参的
		List<Map> waterList=this.pmsDownconfigwaterService.getListBySql("select t.* from pms_downconfigwater t  where t.sourceid like ? order by t.seq",new Object[]{"%"+pccid+"%"});
		if(waterList.size()>0){
            String conditionsql=waterList.get(0).get("conditionsql")+"";
			String waternamesql=waterList.get(0).get("waternamesql")+"";
            if (!Util.isEoN(conditionsql)) {//有条件语句
                List<Map> list = this.dbHelper.getRows(conditionsql,new Object[]{sourceid});
                if(list.size()>0) {//如果大于0，就取该模板或附件
					putWater(map, sourceid, waterList, waternamesql,fixmap);
				}
            }else{
				putWater(map, sourceid, waterList, waternamesql,fixmap);
			}
		}
		return map;
	}
//
	private void putWater(Map map, String sourceid, List<Map> waterList, String waternamesql,Map fixmap) {
    	String watermarkname=Util.isEoN(fixmap.get("watermarkname"))?"":fixmap.get("watermarkname").toString();
		String fontsize=Util.isEoN(fixmap.get("fontsize"))?"":fixmap.get("fontsize").toString();
		String fillopacity=Util.isEoN(fixmap.get("fillopacity"))?"":fixmap.get("fillopacity").toString();
		map.put("fillopacity", Util.isEoN(fillopacity) ? Float.parseFloat(waterList.get(0).get("fillopacity") + ""):Float.parseFloat(fillopacity));//填充不透明度
		if (!Util.isEoN(waterList.get(0).get("strokeopacity"))) {
			map.put("strokeopacity", Float.parseFloat(waterList.get(0).get("strokeopacity") + ""));//中风不透明度
		}
		if (Util.isEoN(waternamesql)) {//如果不为空就取可变水印
			if (Util.isEoN(waterList.get(0).get("watermarkname")) && Util.isEoN(watermarkname)) {
				map.put("watermarkname", "");//水印名称,如果为空就用传进来的水印
			} else {
				map.put("watermarkname", Util.isEoN(watermarkname) ? waterList.get(0).get("watermarkname"):watermarkname);//水印名称,如果传进来的水印为空就用配置的
			}
		} else {
			watermarkname = this.dbHelper.getOnlyStringValue(waternamesql, new Object[]{sourceid});
			map.put("watermarkname", watermarkname);
		}
		if (!Util.isEoN(waterList.get(0).get("textx"))) {
			map.put("textx", waterList.get(0).get("textx"));//宽
		}
		if (!Util.isEoN(waterList.get(0).get("texty"))) {
			map.put("texty", waterList.get(0).get("texty"));//高
		}
		if (!Util.isEoN(waterList.get(0).get("plusx"))) {
			map.put("plusx", waterList.get(0).get("plusx"));//宽
		}
		if (!Util.isEoN(waterList.get(0).get("plusy"))) {
			map.put("plusy", waterList.get(0).get("plusy"));//高
		}
		map.put("rotation", waterList.get(0).get("rotation"));//斜度
		map.put("sybj", waterList.get(0).get("sybj"));//水印发布形式，如果是有值就循环处理
		map.put("fontsize", Util.isEoN(fontsize) ? waterList.get(0).get("fontsize"):fontsize);//文字大小
		if (!Util.isEoN(waterList.get(0).get("sfpage") + "")) {
			map.put("sfpage", Integer.parseInt(waterList.get(0).get("sfpage") + ""));//当前PDF页数
		} else {
			map.put("sfpage", "200");//当前PDF页数
		}
		map.put("waterJson", waterList);//好像没啥用了，用sybj来作用多个水印
	}
//
    /*处理二维码路径*/
//    private void getTwoDim(Map<String, Object> hashmap, String sourceid,String listdownid) {
//        PmsDownloadtype pyd=this.pmsDownloadtypeService.findById(listdownid);
//        HashMap<String,Object> imageEwmData=new HashMap<String, Object>();
//        if (!Util.isEoN(pyd.getTwodimsql())) {
//            String twoDimImgPath=twoDimCodeService.getTwoDimImgPath(sourceid,pyd);
//            if(!Util.isEoN(twoDimImgPath)){
//                String serverIp="";
//                String serverPort="";
//                try {
//                    MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
//                    Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
//                            Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
//                    serverIp = InetAddress.getLocalHost().getHostAddress();
//                    serverPort = objectNames.iterator().next().getKeyProperty("port");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                String servername="http://"+serverIp +":"+ serverPort;
//                int index = twoDimImgPath.indexOf("\\twoDimCode");
//                twoDimImgPath = twoDimImgPath.substring(index);
//                twoDimImgPath =servername+ twoDimImgPath;
//                twoDimImgPath = twoDimImgPath.replace("\\", "/");
//                imageEwmData.put("ewmUrl", twoDimImgPath);
//                imageEwmData.put("absoluteX",0);
//                imageEwmData.put("absoluteY",0);
//                imageEwmData.put("width",60);
//                imageEwmData.put("height",60);
//            }
//        }
//        hashmap.put("imageEwmData",imageEwmData);//二维码参数
//    }
//
//	/*获取生成路径*/
//	public String getRetrunUrlStr(String batchid,String sourceid,String businesstype) {
//		List<PmsDownconfig> pcList=this.findByHql("from cn.topcheer.pms.pojo.PmsDownconfig t  where t.sourceid=? and t.businesstype = ? ",batchid,businesstype);
//		String retrunUrlStr="";
//		SysParamvalue sv2=this.sysParamvalueService.findParamsClassByCodeAndTcodeOne("sysparamdata", SysParamDataEnum.PDFDOWNURL.getMessage());
//		if(pcList.size()>0){
//			retrunUrlStr=sv2.getValue()+pcList.get(0).getTmpPDFFile().replace("【sourceid】",sourceid).replace("tempdoc","tempdocAll1");
//		}else{
//			List<Map> listdowns=new ArrayList<>();
//			if (Util.isEoN(batchid)) {
//				listdowns=this.dbHelper.getRows("select t.addpagenum as isaddpage,t.* from pms_downloadtype t where t.type=? ",new Object[]{businesstype});
//			} else {
//				listdowns=this.dbHelper.getRows("select t.addpagenum as isaddpage,t.* from pms_downloadtype t where t.planprojectid=? and t.type=? ",new Object[]{batchid,businesstype});
//			}
//			if(listdowns.size()>0){
//				retrunUrlStr=sv2.getValue()+listdowns.get(0).get("tmppdffile").toString().replace("【sourceid】",sourceid).replace("tempdoc","tempdocAll1");
//			}
//		}
//		return retrunUrlStr;
//	}
//
	/*
    * 按一定的格式把不循环的数据放到hashmap
    * */
	public void ObjInHashMap(JSONObject obj,Map<String, Object> hashmap) {
		
		Iterator<String> sIterator = obj.keys();
		while(sIterator.hasNext()){
			// 获得key
			String key = sIterator.next();
			// 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
			String value = obj.get(key)+"";
			if("null".equals(obj.get(key))){
				value="";
			}
			hashmap.put(key,value);
		}
	}
//
//

	/**
	 * 根据type添加附件拼接----PmsAttachment表
	 *
	 * @param type
	 * @throws Exception
	 */
	public List<Map> getAttachmentByPid(List<Map> convertArray,Map map,String sourceid, String type) {
		String hql = "select t.id from PMS_ATTACHMENT t where t.sourceid=? and t.category=? order by createtime ";
		List<Map> lis = dbHelper.getRows(hql, new Object[]{sourceid,type});
		if (lis.size() > 0) {
			for(int i=0;i<lis.size();i++){
				map.put("type","affix");//路径，如果type是freemark，xml，rtf就是模板路径，如果是affix，就是附件id，如果是affixurl就是附件地址
				map.put("url",lis.get(i).get("id")+"");//模版路径或附件路径或附件id
				map.put("modelFilePath","pdfmodel");//xml地址
				map.put("adress",1);
			}
			convertArray.add(map);
		}
		return convertArray;
	}
//
//	//查询信息
//	public List<PmsDownconfig> pdfConfigFind(JSONObject json) {
//		String type="";
//		if(!Util.isEoN(json.get("openname"))){
//			type=json.get("openname")+"".trim();
//		}
//		List<PmsDownconfig> list=this.findByHql("select t from PmsDownconfig t where t.openname like ?","%"+type+"%");
//		return list;
//	}
//
//	//获取信息
//	public JSONObject pdfConfigFindbyid(String id) {
//		PmsDownconfig pw=this.findById(id);
//		List<PmsDownconfigconvert> list=this.pmsDownconfigconvertService.findByProperty("sourceid",id);
//		JSONObject obj=new JSONObject();
//		obj=JSONObject.fromObject(pw);
//		obj.put("convertList",list);
//		return obj;
//	}
//
//	/*pdf配置保存*/
//	public ReturnToJs pdfConfigSave(JSONObject obj, String id, ReturnToJs returnToJs) {
//
//		PmsDownconfig p=this.findById(id);
//		if(p==null){
//			p=new PmsDownconfig();
//		}
//		Util.ApplyObject(p,obj);
//		p.setId(id);
//		p.setUpdatetime(new Date());
//		this.merge(p);
//		/*pdf具体模板及附件*/
//		JSONArray jsonArray=obj.getJSONArray("convertList");
//		List<PmsDownconfigconvert> pmsArray = JSON.parseArray(jsonArray.toString(), PmsDownconfigconvert.class);
//		List<PmsDownconfigconvert> pmsList = this.pmsDownconfigconvertService.findByProperty("sourceid",id);
//		String currentIds = "";
//		if (pmsArray != null && pmsArray.size() >= 0) {
//			for (int i = 0; i < jsonArray.size(); i++) {
//				PmsDownconfigconvert pps = pmsArray.get(i);
//				if(Util.isEoN(pps.getId())){//前台没传id就new一个
//					pps.setId(UUID.randomUUID().toString());
//				}
//				pps.setSourceid(id);
//				pps.setSeq(i);
//				this.pmsDownconfigconvertService.merge(pps);
//				currentIds += pps.getId()+",";
//			}
//		}
//		for(int i = 0; i < pmsList.size(); i++){//遍历原有的项目，如果前台传过来的对象不存在则删除该项目
//			if(currentIds.indexOf(pmsList.get(i).getId())>-1){
//			}else{
//				this.pmsDownconfigconvertService.deleteById(pmsList.get(i).getId());
//			}
//		}
//		JSONObject jsonObject=new JSONObject();
//		jsonObject.put("data",obj);
//		pmsTxtSave.saveTxt(id,jsonObject,"PmsDownconfig","pdfConfigSave");
//		return returnToJs;
//	}
//
//	//删除信息
//	public ReturnToJs pdfConfigDelete(String id, ReturnToJs returnToJs) {
//		PmsDownconfig isHave = this.findById(id);
//		/*有关联不能删除*/
//		List<PmsDownconfigconvert> convertList=this.pmsDownconfigconvertService.findByProperty("waterid",id);
//		if (convertList.size()<1) {
//			try {
//				if(!Util.isEoN(isHave)){
//                    /*说明这个版本id在数据库已经保存过了，要先清除相关联的数据，重新保存新给的对象*/
//					//1、PmsDownconfig表
//					List<PmsDownconfigconvert> List = this.pmsDownconfigconvertService.findByProperty("sourceid",id);
//					if(!Util.isEoN(List)&&List.size()>0){
//						this.pmsDownconfigconvertService.deleteList(List);
//					}
//					//主表删除
//					this.deleteById(id);
//				}
//				returnToJs.setSuccess(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//				returnToJs.setSuccess(false);
//			}
//		}else{
//			returnToJs.setSuccess(false);
//			returnToJs.setErrMsg("已关联pdf配置，不可删除");
//		}
//		return  returnToJs;
//
//	}
//
//
//	/**
//	 * 【版本配置】---复制：通过id生成新的样式版本以及相关信息，返回一个全新的版本id
//	 * @param sourceid
//	 * @return
//	 */
//	public String pdfConfigCopyById(String sourceid,	SecurityUser user){
//		try{
//			{
//				//版本表
//				PmsDownconfig oldwaterlink = this.findById(sourceid);
//				String newlinkid = Util.NewGuid();
//				PmsDownconfig newwaterlink = (PmsDownconfig)oldwaterlink.clone();
//				newwaterlink.setId(newlinkid);
//				newwaterlink.setOpenname(oldwaterlink.getOpenname()+"复制版");
//				//复制版本表
//				newwaterlink = this.merge(newwaterlink);
//				if(!Util.isEoN(oldwaterlink)){
//					//参数表
//					List<PmsDownconfigconvert> oldPmsDownconfigconvertList = this.pmsDownconfigconvertService.findByProperty("sourceid",sourceid);
//					if(!Util.isEoN(oldPmsDownconfigconvertList)&&oldPmsDownconfigconvertList.size()>0){
//						//复制参数表
//						for (int k = 0; k <oldPmsDownconfigconvertList.size() ; k++) {
//							String paramid = oldPmsDownconfigconvertList.get(k).getId();
//							this.PmsDownconfigconvertCopyById(paramid,newlinkid,user);
//						}
//					}
//					return newlinkid;
//				}else{
//					return "";
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			return "";
//		}
//	}
//
//
//	/**
//	 * 【版本配置】---复制：通过id生成新的水印版本以及相关信息，返回一个全新的版本id
//	 * @param sourceid
//	 * @return
//	 */
//	public String PmsDownconfigconvertCopyById(String sourceid, String newUpperid, SecurityUser user){
//		try{
//			{
//				//版本表
//				PmsDownconfigconvert oldPmsDownconfigconvert = this.pmsDownconfigconvertService.findById(sourceid);
//				String newparamid = Util.NewGuid();
//				PmsDownconfigconvert newPmsDownconfigconvert = (PmsDownconfigconvert)oldPmsDownconfigconvert.clone();
//				newPmsDownconfigconvert.setId(newparamid);
//				newPmsDownconfigconvert.setSourceid(!Util.isEoN(newUpperid)?newUpperid:oldPmsDownconfigconvert.getSourceid());
//				//复制版本表
//				newPmsDownconfigconvert = this.pmsDownconfigconvertService.merge(newPmsDownconfigconvert);
//				if(!Util.isEoN(oldPmsDownconfigconvert)){
//					return newparamid;
//				}else{
//					return "";
//				}
//			}
//		}catch(Exception e){
//			return "";
//		}
//	}

    public String getString(String sourceid, String modeloraffix) {
        if(modeloraffix.indexOf("##")>-1&&!Util.isEoN(sourceid)){//如果有这个，说明要根据条件判断
            String[]  conditionModelOrAffixlis= modeloraffix.split("##");
            if(conditionModelOrAffixlis.length>0){
                for (int i = 0; i < conditionModelOrAffixlis.length; i++) {
                    String conditionModelOrAffixlisObj=conditionModelOrAffixlis[i];
                    String[]  sqllist=conditionModelOrAffixlisObj.split("@@");
                    if(sqllist.length>1){//去条件
                        String sql=sqllist[1];
                        List<Map> list = this.dbHelper.getRows(sql,new Object[]{sourceid});
                        if(list.size()>0){//如果大于0，就取该模板
                            modeloraffix=sqllist[0];
                            return modeloraffix;
                        }
                    }
                }
            }
        }
        return modeloraffix;
    }


}
