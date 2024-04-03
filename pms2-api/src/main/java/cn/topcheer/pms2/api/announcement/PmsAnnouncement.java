/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2019-5-20 16:18:55
 *
 */
package cn.topcheer.pms2.api.announcement;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="PMS_ANNOUNCEMENT")
public class 、、PmsAnnouncement {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  标题
 	 */
	private String title;
	

	/**
 	 *  内容
 	 */
	private String content;
	

	/**
 	 *  是否显示
 	 */
	private String isshow;
	

	/**
 	 *  是否发布
 	 */
	private String isrelease;
	

	/**
 	 *  保存时间
 	 */
	private Date savedate;
	

	/**
 	 *  发布人
 	 */
	private String releaseuser;
	

	/**
 	 *  发布时间
 	 */
	private Date releasetime;

	/**
	 * 类型，项目申报\公示公告
	 */
	private String type;


	/**
	 * 来自哪个系统
	 */
	private String system;


	/**
	 * 图片地址
	 */
	private String imgurl;

	/**
	 * 图片Base64
	 */
	private String imgsource;


	/**
	 * 跳转地址
	 */
	private String otherurl;




	/**
 	 *  
 	 */
	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  
 	 */
	
	public String getTitle (){
		return title;
	}
	public void setTitle (String title){
		  this.title=title;
	}


	/**
 	 *  
 	 */
	
	public String getContent (){
		return content;
	}
	public void setContent (String content){
		  this.content=content;
	}


	/**
 	 *  
 	 */
	
	public String getIsshow (){
		return isshow;
	}
	public void setIsshow (String isshow){
		  this.isshow=isshow;
	}


	/**
 	 *  
 	 */
	
	public String getIsrelease (){
		return isrelease;
	}
	public void setIsrelease (String isrelease){
		  this.isrelease=isrelease;
	}


	/**
 	 *  
 	 */
	
	public Date getSavedate (){
		return savedate;
	}
	public void setSavedate (Date savedate){
		  this.savedate=savedate;
	}


	/**
 	 *  
 	 */
	
	public String getReleaseuser (){
		return releaseuser;
	}
	public void setReleaseuser (String releaseuser){
		  this.releaseuser=releaseuser;
	}


	/**
 	 *  
 	 */
	
	public Date getReleasetime (){
		return releasetime;
	}
	public void setReleasetime (Date releasetime){
		  this.releasetime=releasetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getImgsource() {
		return imgsource;
	}

	public void setImgsource(String imgsource) {
		this.imgsource = imgsource;
	}


	public String getOtherurl() {
		return otherurl;
	}

	public void setOtherurl(String otherurl) {
		this.otherurl = otherurl;
	}
}