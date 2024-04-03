/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-1-25 17:15:07
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
 *  进度表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ClassInfo(name = "进度表", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@Entity
@Table(name="PMS_PROJECTSCHEDULE")
public class PmsProjectschedule {

	/**
	 *  主键
	 */
	@FieldDes(name="id",lenth="255",type="String",memo="主键")
	private String id;

	/**
	 *  进度名称
	 */
	@FieldDes(name="schedulename",lenth="500",type="String",memo="进度名称")
	private String schedulename;

	/**
	 *  开始时间
	 */
	@FieldDes(name="schedulestartdate",lenth="255",type="String",memo="开始时间")
	private String schedulestartdate;

	/**
	 *  结束时间
	 */
	@FieldDes(name="scheduleenddate",lenth="255",type="String",memo="结束时间")
	private String scheduleenddate;

	/**
	 *  进度成果
	 */
	@FieldDes(name="scheduleachievement",lenth="4000",type="String",memo="进度成果")
	private String scheduleachievement;

	/**
	 *  主表关联id
	 */
	@FieldDes(name="mainid",lenth="255",type="String",memo="主表关联id")
	private String mainid;

	/**
	 *  投入经费
	 */
	@FieldDes(name="money",type="Integer",memo="投入经费")
	private Integer money;

	/**
	 *  排序
	 */
	@FieldDes(name="seq",type="Integer",memo="排序")
	private Integer seq;

	/**
	 *  备注
	 */
	@FieldDes(name="memo",lenth="4000",type="String",memo="备注")
	private String memo;

	/**
	 *  主要工作内容
	 */
	@FieldDes(name="workcontents",lenth="4000",type="String",memo="主要工作内容")
	private String workcontents;

	/**
	 *  成果形式
	 */
	@FieldDes(name="resultfrom",lenth="4000",type="String",memo="成果形式")
	private String resultfrom;

	/**
	 *  年份
	 */
	@FieldDes(name="year",lenth="255",type="String",memo="年份")
	private String year;

	/**
	 *  父表关联id
	 */
	@FieldDes(name="sourceid",lenth="255",type="String",memo="父表关联id")
	private String sourceid;

	/**
	 *  类型
	 */
	@FieldDes(name="type",lenth="255",type="String",memo="类型")
	private String type;

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
	 *  进度名称
	 */

	public String getSchedulename (){
		return schedulename;
	}
	public void setSchedulename (String schedulename){
		this.schedulename=schedulename;
	}


	/**
	 *  开始时间
	 */

	public String getSchedulestartdate (){
		return schedulestartdate;
	}
	public void setSchedulestartdate (String schedulestartdate){
		this.schedulestartdate=schedulestartdate;
	}


	/**
	 *  结束时间
	 */

	public String getScheduleenddate (){
		return scheduleenddate;
	}
	public void setScheduleenddate (String scheduleenddate){
		this.scheduleenddate=scheduleenddate;
	}


	/**
	 *  进度成果
	 */

	public String getScheduleachievement (){
		return scheduleachievement;
	}
	public void setScheduleachievement (String scheduleachievement){
		this.scheduleachievement=scheduleachievement;
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
	 *  投入经费
	 */

	public Integer getMoney (){
		return money;
	}
	public void setMoney (Integer money){
		this.money=money;
	}


	/**
	 *  排序
	 */

	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		this.seq=seq;
	}


	/**
	 *  备注
	 */

	public String getMemo (){
		return memo;
	}
	public void setMemo (String memo){
		this.memo=memo;
	}


	/**
	 *  主要工作内容
	 */

	public String getWorkcontents (){
		return workcontents;
	}
	public void setWorkcontents (String workcontents){
		this.workcontents=workcontents;
	}


	/**
	 *  成果形式
	 */

	public String getResultfrom (){
		return resultfrom;
	}
	public void setResultfrom (String resultfrom){
		this.resultfrom=resultfrom;
	}


	/**
	 *  年份
	 */

	public String getYear (){
		return year;
	}
	public void setYear (String year){
		this.year=year;
	}


	/**
	 *  父表关联id
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

}