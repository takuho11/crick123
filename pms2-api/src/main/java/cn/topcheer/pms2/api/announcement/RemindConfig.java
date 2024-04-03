/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-7-25 16:10:05
 *
 */
package cn.topcheer.pms2.api.announcement;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  
 */
@Entity
@Table(name="REMIND_CONFIG")
public class RemindConfig {


	/**
 	 *  唯一标识
 	 */
	private String id;
	

	/**
 	 *  系统类型：kjxm,kjjl...
 	 */
	private String system;
	

	/**
 	 *  业务类型：sb,ht...
 	 */
	private String type;
	

	/**
 	 *  提醒内容
 	 */
	private String content;


	/**
	 *  点击数字后效果类型：跳转形式、弹框形式
	 */
	private String clicktype;


	/**
	 * 跳转形式：跳转的路由地址
	 */
	private String jumpurl;


	/**
	 * 弹框形式：点击显示哪个弹框列表
	 */
	private String gridtype;


	/**
	 *  是否读取脚本
	 */
	private Boolean readscript;


	/**
 	 *  特殊获取数据方法.do，非特殊情况，该字段为空
 	 */
	private String getdatamethod;


	/**
 	 *  sql脚本
 	 */
	private String script;
	

	/**
 	 *  备注
 	 */
	private String memo;
	

	/**
 	 *  自定义排序
 	 */
	private Integer seq;
	



	/**
 	 *  唯一标识
 	 */
	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  系统类型：kjxm,kjjl...
 	 */
	
	public String getSystem (){
		return system;
	}
	public void setSystem (String system){
		  this.system=system;
	}


	/**
 	 *  业务类型：sb,ht...
 	 */
	
	public String getType (){
		return type;
	}
	public void setType (String type){
		  this.type=type;
	}


	/**
 	 *  提醒内容
 	 */
	
	public String getContent (){
		return content;
	}
	public void setContent (String content){
		  this.content=content;
	}


	/**
 	 *  获取方法.do
 	 */
	
	public String getGetdatamethod (){
		return getdatamethod;
	}
	public void setGetdatamethod (String getdatamethod){
		  this.getdatamethod=getdatamethod;
	}


	/**
 	 *  是否读取脚本
 	 */
	
	public Boolean getReadscript(){
		return readscript;
	}
	public void setReadscript (Boolean readscript){
		  this.readscript=readscript;
	}


	/**
 	 *  sql脚本
 	 */
	
	public String getScript (){
		return script;
	}
	public void setScript (String script){
		  this.script=script;
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
 	 *  排序
 	 */
	
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		  this.seq=seq;
	}


	public String getGridtype() {
		return gridtype;
	}

	public void setGridtype(String gridtype) {
		this.gridtype = gridtype;
	}

	public String getClicktype() {
		return clicktype;
	}

	public void setClicktype(String clicktype) {
		this.clicktype = clicktype;
	}

	public String getJumpurl() {
		return jumpurl;
	}

	public void setJumpurl(String jumpurl) {
		this.jumpurl = jumpurl;
	}
}