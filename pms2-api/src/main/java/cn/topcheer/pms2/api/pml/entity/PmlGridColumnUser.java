/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-12-30 13:41:35
 *
 */
package cn.topcheer.pms2.api.pml.entity;


import cn.topcheer.halberd.annotation.FieldDes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  用户自定义列表列名
 */
@Entity
@Table(name="PML_GRID_COLUMN_USER")
public class PmlGridColumnUser {


	/**
     *  主键ID
     */
    @FieldDes(name="id",lenth="255",type="String",memo="主键ID")
	private String id;

	/**
     *  列表ID
     */
    @FieldDes(name="gridid",lenth="255",type="String",memo="列表ID")
	private String gridid;

	/**
     *  列名ID
     */
    @FieldDes(name="columnid",lenth="255",type="String",memo="列名ID")
	private String columnid;

	/**
     *  用户ID
     */
    @FieldDes(name="userid",lenth="255",type="String",memo="用户ID")
	private String userid;

	/**
     *  排序
     */
    @FieldDes(name="seq",type="Integer",memo="排序")
	private Integer seq;

	/**
	 *  是否选中
	 */
	@FieldDes(name="ischeck",lenth="255",type="String",memo="是否选中")
	private String ischeck;


	/**
 	 *  主键ID
 	 */
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  列表ID
 	 */
	
	public String getGridid (){
		return gridid;
	}
	public void setGridid (String gridid){
		  this.gridid=gridid;
	}


	/**
 	 *  列名ID
 	 */
	
	public String getColumnid (){
		return columnid;
	}
	public void setColumnid (String columnid){
		  this.columnid=columnid;
	}


	/**
 	 *  用户ID
 	 */
	
	public String getUserid (){
		return userid;
	}
	public void setUserid (String userid){
		  this.userid=userid;
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


	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}
}