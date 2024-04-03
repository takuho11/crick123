/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2019-3-3 15:54:32
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
@Table(name="PML_GRID_COLUMN")
public class PmlGridColumn {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  列表表id
 	 */
	private String gridid;
	

	/**
 	 *  列名表id
 	 */
	private String columnid;

	/**
	 * 排序
	 */
	private Integer seq;

	



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
 	 *  列表表id
 	 */
	
	public String getGridid (){
		return gridid;
	}
	public void setGridid (String gridid){
		  this.gridid=gridid;
	}


	/**
 	 *  列名表id
 	 */
	
	public String getColumnid (){
		return columnid;
	}
	public void setColumnid (String columnid){
		  this.columnid=columnid;
	}

	/**
	 * 排序
     */
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}