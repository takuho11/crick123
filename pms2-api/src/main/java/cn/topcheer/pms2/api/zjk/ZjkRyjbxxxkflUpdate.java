/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2017-1-23 9:49:28
 *
 */
package cn.topcheer.pms2.api.zjk;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  学科
 */
@Entity
@Table(name="ZJK_RYJBXXXKFL_UPDATE")
public class ZjkRyjbxxxkflUpdate {


	/**
 	 *  
 	 */
	@Id
	private String id;
	

	/**
 	 *  
 	 */
	private String firstleveldiscipline;
	

	/**
 	 *  
 	 */
	private String subordinatediscipline;
	

	/**
 	 *  
 	 */
	private String thirdleveldiscipline;
	

	/**
 	 *  
 	 */
	private String person_id;//zjk_ryjbxx表id
	

	/**
 	 *  
 	 */
	private String project_id;
	

	/**
 	 *  
 	 */
	private Integer seq;
	

	/**
 	 *  
 	 */
	private String xmzlfl;//国标学科：gbxk；基金学科：jjxk；csly：从事领域
	

	/**
 	 *  
 	 */
	private String discipline;//123学科取最后不为空的学科
	

	/**
 	 *  
 	 */
	private String cdxmlx;
	

	/**
 	 *  
 	 */
	private String panels;
	

	/**
 	 *  
 	 */
	private String managedomain;
	
	private String zylb;

	private String jjxktype;//自然基金学科ABC


	private String jjfirstleveldiscipline;//自然基金的一级（A\B\C\D\E\...）

	/**
	 * 学科类型
	 */
	private String subjecttype;

	/**
	 * 一级学科编码
	 */
	private String subjectonecode;

	/**
	 * 一级学科名称
	 */
	private String subjectonename;

	/**
	 * 二级学科编码
	 */
	private String subjecttwocode;

	/**
	 * 二级学科名称
	 */
	private String subjecttwoname;

	/**
	 * 三级学科编码
	 */
	private String subjectthreecode;

	/**
	 * 三级学科名称
	 */
	private String subjectthreename;

	/**
	 * 四级学科编码
	 */
	private String subjectfourcode;

	/**
	 * 四级学科名称
	 */
	private String subjectfourname;

	/**
	 * 最终学科编码
	 */
	private String subjectendcode;

	/**
	 * 最终学科名称
	 */
	private String subjectendname;


	public String getZylb() {
		return zylb;
	}
	public void setZylb(String zylb) {
		this.zylb = zylb;
	}
	/**
 	 *  
 	 */
	
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  
 	 */
	
	public String getFirstleveldiscipline (){
		return firstleveldiscipline;
	}
	public void setFirstleveldiscipline (String firstleveldiscipline){
		  this.firstleveldiscipline=firstleveldiscipline;
	}


	/**
 	 *  
 	 */
	
	public String getSubordinatediscipline (){
		return subordinatediscipline;
	}
	public void setSubordinatediscipline (String subordinatediscipline){
		  this.subordinatediscipline=subordinatediscipline;
	}


	/**
 	 *  
 	 */
	
	public String getThirdleveldiscipline (){
		return thirdleveldiscipline;
	}
	public void setThirdleveldiscipline (String thirdleveldiscipline){
		  this.thirdleveldiscipline=thirdleveldiscipline;
	}


	/**
 	 *  
 	 */
	
	public String getPerson_id (){
		return person_id;
	}
	public void setPerson_id (String person_id){
		  this.person_id=person_id;
	}


	/**
 	 *  
 	 */
	
	public String getProject_id (){
		return project_id;
	}
	public void setProject_id (String project_id){
		  this.project_id=project_id;
	}


	/**
 	 *  
 	 */
	
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		  this.seq=seq;
	}


	/**
 	 *  
 	 */
	
	public String getXmzlfl (){
		return xmzlfl;
	}
	public void setXmzlfl (String xmzlfl){
		  this.xmzlfl=xmzlfl;
	}


	/**
 	 *  
 	 */
	
	public String getDiscipline (){
		return discipline;
	}
	public void setDiscipline (String discipline){
		  this.discipline=discipline;
	}


	/**
 	 *  
 	 */
	
	public String getCdxmlx (){
		return cdxmlx;
	}
	public void setCdxmlx (String cdxmlx){
		  this.cdxmlx=cdxmlx;
	}


	/**
 	 *  
 	 */
	
	public String getPanels (){
		return panels;
	}
	public void setPanels (String panels){
		  this.panels=panels;
	}


	/**
 	 *  
 	 */
	
	public String getManagedomain (){
		return managedomain;
	}
	public void setManagedomain (String managedomain){
		  this.managedomain=managedomain;
	}


	public String getJjxktype() {
		return jjxktype;
	}

	public void setJjxktype(String jjxktype) {
		this.jjxktype = jjxktype;
	}


	public String getJjfirstleveldiscipline() {
		return jjfirstleveldiscipline;
	}

	public void setJjfirstleveldiscipline(String jjfirstleveldiscipline) {
		this.jjfirstleveldiscipline = jjfirstleveldiscipline;
	}

	public String getSubjecttype() {
		return subjecttype;
	}

	public void setSubjecttype(String subjecttype) {
		this.subjecttype = subjecttype;
	}

	public String getSubjectonecode() {
		return subjectonecode;
	}

	public void setSubjectonecode(String subjectonecode) {
		this.subjectonecode = subjectonecode;
	}

	public String getSubjectonename() {
		return subjectonename;
	}

	public void setSubjectonename(String subjectonename) {
		this.subjectonename = subjectonename;
	}

	public String getSubjecttwocode() {
		return subjecttwocode;
	}

	public void setSubjecttwocode(String subjecttwocode) {
		this.subjecttwocode = subjecttwocode;
	}

	public String getSubjecttwoname() {
		return subjecttwoname;
	}

	public void setSubjecttwoname(String subjecttwoname) {
		this.subjecttwoname = subjecttwoname;
	}

	public String getSubjectthreecode() {
		return subjectthreecode;
	}

	public void setSubjectthreecode(String subjectthreecode) {
		this.subjectthreecode = subjectthreecode;
	}

	public String getSubjectthreename() {
		return subjectthreename;
	}

	public void setSubjectthreename(String subjectthreename) {
		this.subjectthreename = subjectthreename;
	}

	public String getSubjectfourcode() {
		return subjectfourcode;
	}

	public void setSubjectfourcode(String subjectfourcode) {
		this.subjectfourcode = subjectfourcode;
	}

	public String getSubjectfourname() {
		return subjectfourname;
	}

	public void setSubjectfourname(String subjectfourname) {
		this.subjectfourname = subjectfourname;
	}

	public String getSubjectendcode() {
		return subjectendcode;
	}

	public void setSubjectendcode(String subjectendcode) {
		this.subjectendcode = subjectendcode;
	}

	public String getSubjectendname() {
		return subjectendname;
	}

	public void setSubjectendname(String subjectendname) {
		this.subjectendname = subjectendname;
	}
}