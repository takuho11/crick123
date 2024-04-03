/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-12-31 11:39:20
 *
 */
package cn.topcheer.pms2.api.pml.entity;

import cn.topcheer.halberd.annotation.FieldDes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  列表导出配置用户或角色
 */
@Entity
@Table(name="PML_GRID_EXPORT_CONFIG_USER")
public class PmlGridExportConfigUser {


	/**
     *  主键ID
     */
    @FieldDes(name="id",lenth="255",type="String",memo="主键ID")
	private String id;

	/**
     *  配置表ID
     */
    @FieldDes(name="configid",lenth="255",type="String",memo="配置表ID")
	private String configid;

	/**
     *  type
     */
    @FieldDes(name="type",lenth="255",type="String",memo="type")
	private String type;

	/**
     *  来源ID
     */
    @FieldDes(name="sourceid",lenth="255",type="String",memo="来源ID")
	private String sourceid;

	/**
	 *  排序
	 */
	@FieldDes(name="seq",type="Integer",memo="排序")
	private Integer seq;



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
 	 *  配置表ID
 	 */
	
	public String getConfigid (){
		return configid;
	}
	public void setConfigid (String configid){
		  this.configid=configid;
	}


	/**
 	 *  类型
 	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
 	 *  来源ID
 	 */
	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	/**
	 *  排序
	 */
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}