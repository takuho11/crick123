/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2019-4-19 11:02:54
 *
 */
package cn.topcheer.pms2.api.pml.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  
 */
@Entity
@Table(name="PML_GRIDTABS_GRID")
public class PmlGridtabsGrid {


	/**
 	 *  唯一主键id
 	 */
	private String id;
	

	/**
 	 *  pms_gridtabs表的id
 	 */
	private String gridtabsid;
	

	/**
 	 *  pmd_grid表的id
 	 */
	private String gridid;
	

	/**
 	 *  pmd_grid表的gridtype
 	 */
	private String gridtype;

	/**
	 * pmd_grid表的gridname
	 */
	private String gridname;
	

	/**
 	 *  排序
 	 */
	private Integer seq;
	



	/**
 	 *  唯一主键id
 	 */
	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  pms_gridtabs表的id
 	 */
	
	public String getGridtabsid (){
		return gridtabsid;
	}
	public void setGridtabsid (String gridtabsid){
		  this.gridtabsid=gridtabsid;
	}


	/**
 	 *  pmd_grid表的id
 	 */
	
	public String getGridid (){
		return gridid;
	}
	public void setGridid (String gridid){
		  this.gridid=gridid;
	}


	/**
 	 *  pmd_grid表的gridtype
 	 */
	
	public String getGridtype (){
		return gridtype;
	}
	public void setGridtype (String gridtype){
		  this.gridtype=gridtype;
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

	public String getGridname() {
		return gridname;
	}

	public void setGridname(String gridname) {
		this.gridname = gridname;
	}
}