/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-1-8 13:09:44
 *
 */
package cn.topcheer.pms2.api.plan.entity;

import cn.topcheer.halberd.biz.modules.resource.entity.HalberdAttach;
import org.springblade.core.tool.utils.Func;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 *
 */
@Entity
@Table(name="PMS_FILEPRINT_RECORD")
public class PmsFilePrintRecord implements HalberdAttach {


	/**
	 *
	 */
	private String id;

	/*
	 * 后缀名
	 */
	private String extensions;


	public String getExtensions() {
		return extensions;
	}
	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}
	/**
	 *  名称
	 */
	private String name;


	/**
	 *  分类名称
	 */
	private String category;


	/**
	 *  创建时间
	 */
	private Date createtime;

	private Integer seq;


	/**
	 *  创建人
	 */
	private String creator;


	/**
	 *  源ID
	 */
	private String sourceid;


	/**
	 *  文件大小
	 */
	private String filesize;


	/**
	 *  超链接地址
	 */
	private String href;


	/**
	 *  备注
	 */
	private String memo;


	private Date judgetime;

	/**
	 *
	 */

	private String localfilepath;
	/**
	 *
	 */

	private String state;
	/**
	 *
	 */

	private String origid;

	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		this.id=id;
	}


	/**
	 *  名称
	 */

	public String getName (){
		return name;
	}


	public void setName (String name){
		this.name=name;
	}

	@Override
	@Transient
	public Long getFileSize() {
		if(Func.isNumeric(this.filesize)) {
			return Long.parseLong(this.filesize);
		}
		else
		{
			return null;
		}
	}

	@Override
	@Transient
	public String getLink() {
		return this.localfilepath;
	}

	@Override
	@Transient
	public String getDomainUrl() {
		return this.href;
	}

	@Override
	@Transient
	public String getOriginalName() {
		return this.memo;
	}

	@Override
	@Transient
	public String getExtension() {
		return this.extensions;
	}



	/**
	 *  分类名称
	 */

	public String getCategory (){
		return category;
	}

	@Override
	@Transient
	public String getOrgId() {
		return this.origid;
	}

	@Override
	@Transient
	public String getSourceId() {
		return this.sourceid;
	}

	public void setCategory (String category){
		this.category=category;
	}


	/**
	 *  创建时间
	 */

	public Date getCreatetime (){
		return createtime;
	}
	public void setCreatetime (Date createtime){
		this.createtime=createtime;
	}


	/**
	 *  创建人
	 */

	public String getCreator (){
		return creator;
	}
	public void setCreator (String creator){
		this.creator=creator;
	}


	/**
	 *  源ID
	 */

	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		this.sourceid=sourceid;
	}


	/**
	 *  文件大小
	 */

	public String getFilesize (){
		return filesize;
	}
	public void setFilesize (String filesize){
		this.filesize=filesize;
	}


	/**
	 *  超链接地址
	 */

	public String getHref (){
		return href;
	}
	public void setHref (String href){
		this.href=href;
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

	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}


	public String getLocalfilepath() {
		return localfilepath;
	}

	public void setLocalfilepath(String localfilepath) {
		this.localfilepath = localfilepath;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrigid() {
		return origid;
	}

	public void setOrigid(String origid) {
		this.origid = origid;
	}

	public Date getJudgetime() {
		return judgetime;
	}

	public void setJudgetime(Date judgetime) {
		this.judgetime = judgetime;
	}
}