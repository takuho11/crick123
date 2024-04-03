/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2019-5-3 15:43:17
 *
 */
package cn.topcheer.pms2.api.sys;

import cn.topcheer.pms2.api.annotation.FieldDes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="SYS_GUIDE")
public class SysGuide {


	/**
 	 *  唯一id
 	 */
	private String id;
	

	/**
 	 *  父级id
 	 */
	private String parentid;
	

	/**
 	 *  批次id
 	 */
	private String batchid;
	

	/**
 	 *  显示在页面的名称
 	 */
	private String name;
	

	/**
 	 *  类型
 	 */
	private String type;
	

	/**
 	 *  排序
 	 */
	private Integer seq;
	

	/**
 	 *  备注
 	 */
	private String memo;

	/**
	 *  处室
	 */
	private String belonglab;

	/**
	 *  处室id
	 */
	private String belonglabid;

	/**
	 *  项目主管
	 */
	private String xmzg;

	/**
	 *  项目主管id
	 */
	private String xmzgid;


	/**
	 *  支持方向
	 */
	@FieldDes(name="limitnum",lenth="255",type="String",memo="支持方向指标")
	private Integer limitnum;
	/**
	 * 申报开始时间
	 */
	private Date starttime;
	/**
	 * 申报结束时间
	 */
	private Date endtime;
	/**
	 *	依托单位审核截至时间
	 */
	private Date relyunitendtime;
	/**
	 *	推荐单位审核截止时间
	 */
	private Date recommendedunitendtime;

	/**
	 *  财政批复金额
	*/
	private Double czpfje;


	/**
 	 *  唯一id
 	 */
	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  父级id
 	 */
	
	public String getParentid (){
		return parentid;
	}
	public void setParentid (String parentid){
		  this.parentid=parentid;
	}


	/**
 	 *  显示在页面的名称
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}


	/**
 	 *  批次id
 	 */
	
	public String getBatchid (){
		return batchid;
	}
	public void setBatchid (String batchid){
		  this.batchid=batchid;
	}


	/**
 	 *  类型
 	 */
	
	public String getType (){return type;}
	public void setType (String type){this.type=type;}


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

	public String getBelonglabid() {return belonglabid;}
	public void setBelonglabid(String belonglabid) {this.belonglabid = belonglabid;}

	public String getBelonglab() {return belonglab;}
	public void setBelonglab(String belonglab) {this.belonglab = belonglab;}

	public String getXmzg() { return xmzg; }
	public void setXmzg(String xmzg) { this.xmzg = xmzg; }

	public String getXmzgid() { return xmzgid; }
	public void setXmzgid(String xmzgid) { this.xmzgid = xmzgid; }

	public Integer getLimitnum() {
		return limitnum;
	}

	public void setLimitnum(Integer limitnum) {
		this.limitnum = limitnum;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getRelyunitendtime() {
		return relyunitendtime;
	}

	public void setRelyunitendtime(Date relyunitendtime) {
		this.relyunitendtime = relyunitendtime;
	}

	public Date getRecommendedunitendtime() {
		return recommendedunitendtime;
	}

	public void setRecommendedunitendtime(Date recommendedunitendtime) {
		this.recommendedunitendtime = recommendedunitendtime;
	}

	public Double getCzpfje() {
		return czpfje;
	}

	public void setCzpfje(Double czpfje) {
		this.czpfje = czpfje;
	}
}