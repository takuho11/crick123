/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-1-26 11:47:55
 *
 */
package cn.topcheer.pms2.api.project.entity;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  经费预算表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ClassInfo(name = "经费预算表", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@Entity
@Table(name="PMS_PROJECTEXPENSE")
public class PmsProjectexpense {

	/**
	 *  主键
	 */
	@FieldDes(name="id",lenth="255",type="String",memo="主键")
	private String id;

	/**
	 *  设备费[总预算]
	 */
	@FieldDes(name="instrumentsum",type="Float",memo="设备费[总预算]")
	private Double instrumentsum;

	/**
	 *  差旅/会议/国际合作与交流费[总预算]
	 */
	@FieldDes(name="businesstripsum",type="Float",memo="差旅/会议/国际合作与交流费[总预算]")
	private Double businesstripsum;

	/**
	 *  专家咨询费[总预算]
	 */
	@FieldDes(name="adviceforexpertsum",type="Float",memo="专家咨询费[总预算]")
	private Double adviceforexpertsum;

	/**
	 *  材料费[总预算]
	 */
	@FieldDes(name="materialexpense",type="Float",memo="材料费[总预算]")
	private Double materialexpense;

	/**
	 *  劳务费[总预算]
	 */
	@FieldDes(name="servicefee",type="Float",memo="劳务费[总预算]")
	private Double servicefee;

	/**
	 *  出版出版/文献/信息传播/知识产权事务费[总预算]
	 */
	@FieldDes(name="adminstrativeservicesum",type="Float",memo="出版出版/文献/信息传播/知识产权事务费[总预算]")
	private Double adminstrativeservicesum;

	/**
	 *  测试化验加工费[总预算]
	 */
	@FieldDes(name="proceedingscharges",type="Float",memo="测试化验加工费[总预算]")
	private Double proceedingscharges;

	/**
	 *  会议费[总预算]
	 */
	@FieldDes(name="coferemce",type="Float",memo="会议费[总预算]")
	private Double coferemce;

	/**
	 *  合作、协作研究与交流费[总预算]
	 */
	@FieldDes(name="coorperationresearchsum",type="Float",memo="合作、协作研究与交流费[总预算]")
	private Double coorperationresearchsum;

	/**
	 *  燃料动力费[总预算]
	 */
	@FieldDes(name="fuelandpower",type="Float",memo="燃料动力费[总预算]")
	private Double fuelandpower;

	/**
	 *  其中：绩效支出[总预算]
	 */
	@FieldDes(name="managesum",type="Float",memo="其中：绩效支出[总预算]")
	private Double managesum;

	/**
	 *  主表关联id
	 */
	@FieldDes(name="mainid",lenth="255",type="String",memo="主表关联id")
	private String mainid;

	/**
	 *  激励费[总预算]
	 */
	@FieldDes(name="incentivefee",type="Float",memo="激励费[总预算]")
	private Double incentivefee;

	/**
	 *  设备费[省拨]
	 */
	@FieldDes(name="equipmentone",type="Float",memo="设备费[省拨]")
	private Double equipmentone;

	/**
	 *  材料费[省拨]
	 */
	@FieldDes(name="materialone",type="Float",memo="材料费[省拨]")
	private Double materialone;

	/**
	 *  测试化验加工费[省拨]
	 */
	@FieldDes(name="processingtestone",type="Float",memo="测试化验加工费[省拨]")
	private Double processingtestone;

	/**
	 *  燃料动力费[省拨]
	 */
	@FieldDes(name="expenditureone",type="Float",memo="燃料动力费[省拨]")
	private Double expenditureone;

	/**
	 *  差旅/会议/国际合作与交流费[省拨]
	 */
	@FieldDes(name="inlandtravelone",type="Float",memo="差旅/会议/国际合作与交流费[省拨]")
	private Double inlandtravelone;

	/**
	 *  会议费[省拨]
	 */
	@FieldDes(name="conferenceone",type="Float",memo="会议费[省拨]")
	private Double conferenceone;

	/**
	 *  出版/文献/信息传播/知识产权事务费[省拨]
	 */
	@FieldDes(name="publishedone",type="Float",memo="出版/文献/信息传播/知识产权事务费[省拨]")
	private Double publishedone;

	/**
	 *  激励费[省拨]
	 */
	@FieldDes(name="incentiveone",type="Float",memo="激励费[省拨]")
	private Double incentiveone;

	/**
	 *  其中：绩效支出[省拨]
	 */
	@FieldDes(name="managementone",type="Float",memo="其中：绩效支出[省拨]")
	private Double managementone;

	/**
	 *  合作、协作研究与交流费[省拨]
	 */
	@FieldDes(name="collaborativeresearchone",type="Float",memo="合作、协作研究与交流费[省拨]")
	private Double collaborativeresearchone;

	/**
	 *  劳务费[省拨]
	 */
	@FieldDes(name="laborone",type="Float",memo="劳务费[省拨]")
	private Double laborone;

	/**
	 *  专家咨询费[省拨]
	 */
	@FieldDes(name="consultingone",type="Float",memo="专家咨询费[省拨]")
	private Double consultingone;

	/**
	 *  设备费[自筹]
	 */
	@FieldDes(name="equipmenttwo",type="Float",memo="设备费[自筹]")
	private Double equipmenttwo;

	/**
	 *  材料费[自筹]
	 */
	@FieldDes(name="materialtwo",type="Float",memo="材料费[自筹]")
	private Double materialtwo;

	/**
	 *  测试化验加工费[自筹]
	 */
	@FieldDes(name="processingtesttwo",type="Float",memo="测试化验加工费[自筹]")
	private Double processingtesttwo;

	/**
	 *  燃料动力费[自筹]
	 */
	@FieldDes(name="expendituretwo",type="Float",memo="燃料动力费[自筹]")
	private Double expendituretwo;

	/**
	 *  差旅/会议/国际合作与交流费[自筹]
	 */
	@FieldDes(name="inlandtraveltwo",type="Float",memo="差旅/会议/国际合作与交流费[自筹]")
	private Double inlandtraveltwo;

	/**
	 *  会议费[自筹]
	 */
	@FieldDes(name="conferencetwo",type="Float",memo="会议费[自筹]")
	private Double conferencetwo;

	/**
	 *  出版/文献/信息传播/知识产权事务费[自筹]
	 */
	@FieldDes(name="publishedtwo",type="Float",memo="出版/文献/信息传播/知识产权事务费[自筹]")
	private Double publishedtwo;

	/**
	 *  激励费[自筹]
	 */
	@FieldDes(name="incentivetwo",type="Float",memo="激励费[自筹]")
	private Double incentivetwo;

	/**
	 *  其中：绩效支出[自筹]
	 */
	@FieldDes(name="managementtwo",type="Float",memo="其中：绩效支出[自筹]")
	private Double managementtwo;

	/**
	 *  合作、协作研究与交流费[自筹]
	 */
	@FieldDes(name="collaborativeresearchtwo",type="Float",memo="合作、协作研究与交流费[自筹]")
	private Double collaborativeresearchtwo;

	/**
	 *  劳务费[自筹]
	 */
	@FieldDes(name="labortwo",type="Float",memo="劳务费[自筹]")
	private Double labortwo;

	/**
	 *  专家咨询费[自筹]
	 */
	@FieldDes(name="consultingtwo",type="Float",memo="专家咨询费[自筹]")
	private Double consultingtwo;

	/**
	 *  购置设备费[总预算]
	 */
	@FieldDes(name="buyequipmentsum",type="Float",memo="购置设备费[总预算]")
	private Double buyequipmentsum;

	/**
	 *  试制设备费[总预算]
	 */
	@FieldDes(name="testequipmentsum",type="Float",memo="试制设备费[总预算]")
	private Double testequipmentsum;

	/**
	 *  设备租赁费[总预算]
	 */
	@FieldDes(name="rentequipmentsum",type="Float",memo="设备租赁费[总预算]")
	private Double rentequipmentsum;

	/**
	 *  购置设备费[省拨]
	 */
	@FieldDes(name="buyequipmentkjt",type="Float",memo="购置设备费[省拨]")
	private Double buyequipmentkjt;

	/**
	 *  试制设备费[省拨]
	 */
	@FieldDes(name="testequipmentkjt",type="Float",memo="试制设备费[省拨]")
	private Double testequipmentkjt;

	/**
	 *  设备租赁费[省拨]
	 */
	@FieldDes(name="rentequipmentkjt",type="Float",memo="设备租赁费[省拨]")
	private Double rentequipmentkjt;

	/**
	 *  购置设备费[自筹]
	 */
	@FieldDes(name="buyequipmentself",type="Float",memo="购置设备费[自筹]")
	private Double buyequipmentself;

	/**
	 *  试制设备费[自筹]
	 */
	@FieldDes(name="testequipmentself",type="Float",memo="试制设备费[自筹]")
	private Double testequipmentself;

	/**
	 *  设备租赁费[自筹]
	 */
	@FieldDes(name="rentequipmentself",type="Float",memo="设备租赁费[自筹]")
	private Double rentequipmentself;

	/**
	 *  直接费用[总预算:总计]
	 */
	@FieldDes(name="directcostall",type="Float",memo="直接费用[总预算:总计]")
	private Double directcostall;

	/**
	 *  直接费用[省拨:总计]
	 */
	@FieldDes(name="directcostprovince",type="Float",memo="直接费用[省拨:总计]")
	private Double directcostprovince;

	/**
	 *  直接费用[自筹:总计]
	 */
	@FieldDes(name="directcostself",type="Float",memo="直接费用[自筹:总计]")
	private Double directcostself;

	/**
	 *  间接费用[总预算:总计]
	 */
	@FieldDes(name="indirectcostall",type="Float",memo="间接费用[总预算:总计]")
	private Double indirectcostall;

	/**
	 *  间接费用[省拨:总计]
	 */
	@FieldDes(name="indirectcostprovince",type="Float",memo="间接费用[省拨:总计]")
	private Double indirectcostprovince;

	/**
	 *  间接费用[自筹:总计]
	 */
	@FieldDes(name="indirectcostself",type="Float",memo="间接费用[自筹:总计]")
	private Double indirectcostself;

	/**
	 *  合计费用[总预算]
	 */
	@FieldDes(name="costall",type="Float",memo="合计费用[总预算]")
	private Double costall;

	/**
	 *  合计费用[省拨]
	 */
	@FieldDes(name="costprovince",type="Float",memo="合计费用[省拨]")
	private Double costprovince;

	/**
	 *  合计费用[自筹]
	 */
	@FieldDes(name="costself",type="Float",memo="合计费用[自筹]")
	private Double costself;

	/**
	 *  其他支出[总预算]
	 */
	@FieldDes(name="ysjfotherfee",type="Float",memo="其他支出[总预算]")
	private Double ysjfotherfee;

	/**
	 *  其他支出[省拨]
	 */
	@FieldDes(name="kjtotherfee",type="Float",memo="其他支出[省拨]")
	private Double kjtotherfee;

	/**
	 *  其他支出[自筹]
	 */
	@FieldDes(name="otherfeetwo",type="Float",memo="其他支出[自筹]")
	private Double otherfeetwo;

	/**
	 *  获国拨经费
	 */
	@FieldDes(name="costgb",type="Float",memo="获国拨经费")
	private Double costgb;

	/**
	 *  单位配套经费
	 */
	@FieldDes(name="costpt",type="Float",memo="单位配套经费")
	private Double costpt;

	/**
	 *  父表关联ID
	 */
	@FieldDes(name="sourceid",lenth="255",type="String",memo="父表关联ID")
	private String sourceid;

	/**
	 *  类型
	 */
	@FieldDes(name="type",lenth="255",type="String",memo="类型")
	private String type;

	/**
	 *  顺序
	 */
	@FieldDes(name="seq",type="Integer",memo="顺序")
	private Integer seq;

	/**
	 *  主键
	 */

	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		this.id=id;
	}


	/**
	 *  设备费[总预算]
	 */

	public Double getInstrumentsum (){
		return instrumentsum;
	}
	public void setInstrumentsum (Double instrumentsum){
		this.instrumentsum=instrumentsum;
	}


	/**
	 *  差旅/会议/国际合作与交流费[总预算]
	 */

	public Double getBusinesstripsum (){
		return businesstripsum;
	}
	public void setBusinesstripsum (Double businesstripsum){
		this.businesstripsum=businesstripsum;
	}


	/**
	 *  专家咨询费[总预算]
	 */

	public Double getAdviceforexpertsum (){
		return adviceforexpertsum;
	}
	public void setAdviceforexpertsum (Double adviceforexpertsum){
		this.adviceforexpertsum=adviceforexpertsum;
	}


	/**
	 *  材料费[总预算]
	 */

	public Double getMaterialexpense (){
		return materialexpense;
	}
	public void setMaterialexpense (Double materialexpense){
		this.materialexpense=materialexpense;
	}


	/**
	 *  劳务费[总预算]
	 */

	public Double getServicefee (){
		return servicefee;
	}
	public void setServicefee (Double servicefee){
		this.servicefee=servicefee;
	}


	/**
	 *  出版出版/文献/信息传播/知识产权事务费[总预算]
	 */

	public Double getAdminstrativeservicesum (){
		return adminstrativeservicesum;
	}
	public void setAdminstrativeservicesum (Double adminstrativeservicesum){
		this.adminstrativeservicesum=adminstrativeservicesum;
	}


	/**
	 *  测试化验加工费[总预算]
	 */

	public Double getProceedingscharges (){
		return proceedingscharges;
	}
	public void setProceedingscharges (Double proceedingscharges){
		this.proceedingscharges=proceedingscharges;
	}


	/**
	 *  会议费[总预算]
	 */

	public Double getCoferemce (){
		return coferemce;
	}
	public void setCoferemce (Double coferemce){
		this.coferemce=coferemce;
	}


	/**
	 *  合作、协作研究与交流费[总预算]
	 */

	public Double getCoorperationresearchsum (){
		return coorperationresearchsum;
	}
	public void setCoorperationresearchsum (Double coorperationresearchsum){
		this.coorperationresearchsum=coorperationresearchsum;
	}


	/**
	 *  燃料动力费[总预算]
	 */

	public Double getFuelandpower (){
		return fuelandpower;
	}
	public void setFuelandpower (Double fuelandpower){
		this.fuelandpower=fuelandpower;
	}


	/**
	 *  其中：绩效支出[总预算]
	 */

	public Double getManagesum (){
		return managesum;
	}
	public void setManagesum (Double managesum){
		this.managesum=managesum;
	}


	/**
	 *  主表关联id
	 */

	public String getMainid (){
		return mainid;
	}
	public void setMainid (String mainid){
		this.mainid=mainid;
	}


	/**
	 *  激励费[总预算]
	 */

	public Double getIncentivefee (){
		return incentivefee;
	}
	public void setIncentivefee (Double incentivefee){
		this.incentivefee=incentivefee;
	}


	/**
	 *  设备费[省拨]
	 */

	public Double getEquipmentone (){
		return equipmentone;
	}
	public void setEquipmentone (Double equipmentone){
		this.equipmentone=equipmentone;
	}


	/**
	 *  材料费[省拨]
	 */

	public Double getMaterialone (){
		return materialone;
	}
	public void setMaterialone (Double materialone){
		this.materialone=materialone;
	}


	/**
	 *  测试化验加工费[省拨]
	 */

	public Double getProcessingtestone (){
		return processingtestone;
	}
	public void setProcessingtestone (Double processingtestone){
		this.processingtestone=processingtestone;
	}


	/**
	 *  燃料动力费[省拨]
	 */

	public Double getExpenditureone (){
		return expenditureone;
	}
	public void setExpenditureone (Double expenditureone){
		this.expenditureone=expenditureone;
	}


	/**
	 *  差旅/会议/国际合作与交流费[省拨]
	 */

	public Double getInlandtravelone (){
		return inlandtravelone;
	}
	public void setInlandtravelone (Double inlandtravelone){
		this.inlandtravelone=inlandtravelone;
	}


	/**
	 *  会议费[省拨]
	 */

	public Double getConferenceone (){
		return conferenceone;
	}
	public void setConferenceone (Double conferenceone){
		this.conferenceone=conferenceone;
	}


	/**
	 *  出版/文献/信息传播/知识产权事务费[省拨]
	 */

	public Double getPublishedone (){
		return publishedone;
	}
	public void setPublishedone (Double publishedone){
		this.publishedone=publishedone;
	}


	/**
	 *  激励费[省拨]
	 */

	public Double getIncentiveone (){
		return incentiveone;
	}
	public void setIncentiveone (Double incentiveone){
		this.incentiveone=incentiveone;
	}


	/**
	 *  其中：绩效支出[省拨]
	 */

	public Double getManagementone (){
		return managementone;
	}
	public void setManagementone (Double managementone){
		this.managementone=managementone;
	}


	/**
	 *  合作、协作研究与交流费[省拨]
	 */

	public Double getCollaborativeresearchone (){
		return collaborativeresearchone;
	}
	public void setCollaborativeresearchone (Double collaborativeresearchone){
		this.collaborativeresearchone=collaborativeresearchone;
	}


	/**
	 *  劳务费[省拨]
	 */

	public Double getLaborone (){
		return laborone;
	}
	public void setLaborone (Double laborone){
		this.laborone=laborone;
	}


	/**
	 *  专家咨询费[省拨]
	 */

	public Double getConsultingone (){
		return consultingone;
	}
	public void setConsultingone (Double consultingone){
		this.consultingone=consultingone;
	}


	/**
	 *  设备费[自筹]
	 */

	public Double getEquipmenttwo (){
		return equipmenttwo;
	}
	public void setEquipmenttwo (Double equipmenttwo){
		this.equipmenttwo=equipmenttwo;
	}


	/**
	 *  材料费[自筹]
	 */

	public Double getMaterialtwo (){
		return materialtwo;
	}
	public void setMaterialtwo (Double materialtwo){
		this.materialtwo=materialtwo;
	}


	/**
	 *  测试化验加工费[自筹]
	 */

	public Double getProcessingtesttwo (){
		return processingtesttwo;
	}
	public void setProcessingtesttwo (Double processingtesttwo){
		this.processingtesttwo=processingtesttwo;
	}


	/**
	 *  燃料动力费[自筹]
	 */

	public Double getExpendituretwo (){
		return expendituretwo;
	}
	public void setExpendituretwo (Double expendituretwo){
		this.expendituretwo=expendituretwo;
	}


	/**
	 *  差旅/会议/国际合作与交流费[自筹]
	 */

	public Double getInlandtraveltwo (){
		return inlandtraveltwo;
	}
	public void setInlandtraveltwo (Double inlandtraveltwo){
		this.inlandtraveltwo=inlandtraveltwo;
	}


	/**
	 *  会议费[自筹]
	 */

	public Double getConferencetwo (){
		return conferencetwo;
	}
	public void setConferencetwo (Double conferencetwo){
		this.conferencetwo=conferencetwo;
	}


	/**
	 *  出版/文献/信息传播/知识产权事务费[自筹]
	 */

	public Double getPublishedtwo (){
		return publishedtwo;
	}
	public void setPublishedtwo (Double publishedtwo){
		this.publishedtwo=publishedtwo;
	}


	/**
	 *  激励费[自筹]
	 */

	public Double getIncentivetwo (){
		return incentivetwo;
	}
	public void setIncentivetwo (Double incentivetwo){
		this.incentivetwo=incentivetwo;
	}


	/**
	 *  其中：绩效支出[自筹]
	 */

	public Double getManagementtwo (){
		return managementtwo;
	}
	public void setManagementtwo (Double managementtwo){
		this.managementtwo=managementtwo;
	}


	/**
	 *  合作、协作研究与交流费[自筹]
	 */

	public Double getCollaborativeresearchtwo (){
		return collaborativeresearchtwo;
	}
	public void setCollaborativeresearchtwo (Double collaborativeresearchtwo){
		this.collaborativeresearchtwo=collaborativeresearchtwo;
	}


	/**
	 *  劳务费[自筹]
	 */

	public Double getLabortwo (){
		return labortwo;
	}
	public void setLabortwo (Double labortwo){
		this.labortwo=labortwo;
	}


	/**
	 *  专家咨询费[自筹]
	 */

	public Double getConsultingtwo (){
		return consultingtwo;
	}
	public void setConsultingtwo (Double consultingtwo){
		this.consultingtwo=consultingtwo;
	}


	/**
	 *  购置设备费[总预算]
	 */

	public Double getBuyequipmentsum (){
		return buyequipmentsum;
	}
	public void setBuyequipmentsum (Double buyequipmentsum){
		this.buyequipmentsum=buyequipmentsum;
	}


	/**
	 *  试制设备费[总预算]
	 */

	public Double getTestequipmentsum (){
		return testequipmentsum;
	}
	public void setTestequipmentsum (Double testequipmentsum){
		this.testequipmentsum=testequipmentsum;
	}


	/**
	 *  设备租赁费[总预算]
	 */

	public Double getRentequipmentsum (){
		return rentequipmentsum;
	}
	public void setRentequipmentsum (Double rentequipmentsum){
		this.rentequipmentsum=rentequipmentsum;
	}


	/**
	 *  购置设备费[省拨]
	 */

	public Double getBuyequipmentkjt (){
		return buyequipmentkjt;
	}
	public void setBuyequipmentkjt (Double buyequipmentkjt){
		this.buyequipmentkjt=buyequipmentkjt;
	}


	/**
	 *  试制设备费[省拨]
	 */

	public Double getTestequipmentkjt (){
		return testequipmentkjt;
	}
	public void setTestequipmentkjt (Double testequipmentkjt){
		this.testequipmentkjt=testequipmentkjt;
	}


	/**
	 *  设备租赁费[省拨]
	 */

	public Double getRentequipmentkjt (){
		return rentequipmentkjt;
	}
	public void setRentequipmentkjt (Double rentequipmentkjt){
		this.rentequipmentkjt=rentequipmentkjt;
	}


	/**
	 *  购置设备费[自筹]
	 */

	public Double getBuyequipmentself (){
		return buyequipmentself;
	}
	public void setBuyequipmentself (Double buyequipmentself){
		this.buyequipmentself=buyequipmentself;
	}


	/**
	 *  试制设备费[自筹]
	 */

	public Double getTestequipmentself (){
		return testequipmentself;
	}
	public void setTestequipmentself (Double testequipmentself){
		this.testequipmentself=testequipmentself;
	}


	/**
	 *  设备租赁费[自筹]
	 */

	public Double getRentequipmentself (){
		return rentequipmentself;
	}
	public void setRentequipmentself (Double rentequipmentself){
		this.rentequipmentself=rentequipmentself;
	}


	/**
	 *  直接费用[总预算:总计]
	 */

	public Double getDirectcostall (){
		return directcostall;
	}
	public void setDirectcostall (Double directcostall){
		this.directcostall=directcostall;
	}


	/**
	 *  直接费用[省拨:总计]
	 */

	public Double getDirectcostprovince (){
		return directcostprovince;
	}
	public void setDirectcostprovince (Double directcostprovince){
		this.directcostprovince=directcostprovince;
	}


	/**
	 *  直接费用[自筹:总计]
	 */

	public Double getDirectcostself (){
		return directcostself;
	}
	public void setDirectcostself (Double directcostself){
		this.directcostself=directcostself;
	}


	/**
	 *  间接费用[总预算:总计]
	 */

	public Double getIndirectcostall (){
		return indirectcostall;
	}
	public void setIndirectcostall (Double indirectcostall){
		this.indirectcostall=indirectcostall;
	}


	/**
	 *  间接费用[省拨:总计]
	 */

	public Double getIndirectcostprovince (){
		return indirectcostprovince;
	}
	public void setIndirectcostprovince (Double indirectcostprovince){
		this.indirectcostprovince=indirectcostprovince;
	}


	/**
	 *  间接费用[自筹:总计]
	 */

	public Double getIndirectcostself (){
		return indirectcostself;
	}
	public void setIndirectcostself (Double indirectcostself){
		this.indirectcostself=indirectcostself;
	}


	/**
	 *  合计费用[总预算]
	 */

	public Double getCostall (){
		return costall;
	}
	public void setCostall (Double costall){
		this.costall=costall;
	}


	/**
	 *  合计费用[省拨]
	 */

	public Double getCostprovince (){
		return costprovince;
	}
	public void setCostprovince (Double costprovince){
		this.costprovince=costprovince;
	}


	/**
	 *  合计费用[自筹]
	 */

	public Double getCostself (){
		return costself;
	}
	public void setCostself (Double costself){
		this.costself=costself;
	}


	/**
	 *  其他支出[总预算]
	 */

	public Double getYsjfotherfee (){
		return ysjfotherfee;
	}
	public void setYsjfotherfee (Double ysjfotherfee){
		this.ysjfotherfee=ysjfotherfee;
	}


	/**
	 *  其他支出[省拨]
	 */

	public Double getKjtotherfee (){
		return kjtotherfee;
	}
	public void setKjtotherfee (Double kjtotherfee){
		this.kjtotherfee=kjtotherfee;
	}


	/**
	 *  其他支出[自筹]
	 */

	public Double getOtherfeetwo (){
		return otherfeetwo;
	}
	public void setOtherfeetwo (Double otherfeetwo){
		this.otherfeetwo=otherfeetwo;
	}


	/**
	 *  获国拨经费
	 */

	public Double getCostgb (){
		return costgb;
	}
	public void setCostgb (Double costgb){
		this.costgb=costgb;
	}


	/**
	 *  单位配套经费
	 */

	public Double getCostpt (){
		return costpt;
	}
	public void setCostpt (Double costpt){
		this.costpt=costpt;
	}


	/**
	 *  父表关联ID
	 */

	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		this.sourceid=sourceid;
	}


	/**
	 *  类型
	 */

	public String getType (){
		return type;
	}
	public void setType (String type){
		this.type=type;
	}


	/**
	 *  顺序
	 */

	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		this.seq=seq;
	}
}