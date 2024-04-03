/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-12-14 9:51:06
 *
 */
package cn.topcheer.pms2.api.pml.entity;

import cn.topcheer.halberd.annotation.FieldDes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  列表数据导出配置表
 */
@Entity
@Table(name="PML_GRID_EXPORT")
public class PmlGridExport {


	/**
     *  主键ID
     */
    @FieldDes(name="id",lenth="255",type="String",memo="主键ID")
	private String id;

	/**
     *  导出名称
     */
    @FieldDes(name="name",lenth="255",type="String",memo="导出名称")
	private String name;

	/**
     *  类型(公共业务,默认业务详情,列表定制)
     */
    @FieldDes(name="type",lenth="255",type="String",memo="类型(公共业务_public,默认业务详情_default,列表定制_grid)")
	private String type;

	/**
     *  参数(公共业务业务类型，默认业务业务类型，列表ids)
     */
    @FieldDes(name="value",lenth="4000",type="String",memo="参数(公共业务业务类型,默认业务业务类型,列表ids)")
	private String value;

	/**
     *  备注
     */
    @FieldDes(name="memo",lenth="255",type="String",memo="备注")
	private String memo;

	/**
     *  SQL脚本
     */
    @FieldDes(name="sql_script",type="CLOB",memo="SQL脚本")
	private String sql_script;

	/**
     *  字段映射
     */
    @FieldDes(name="field_reflect",type="CLOB",memo="字段映射")
	private String field_reflect;



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
 	 *  导出名称
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}


	/**
 	 *  类型(公共业务_public,默认业务详情_default,列表定制_grid)
 	 */
	
	public String getType (){
		return type;
	}
	public void setType (String type){
		  this.type=type;
	}


	/**
 	 *  参数(公共业务业务类型，默认业务业务类型，列表ids)
 	 */
	
	public String getValue (){
		return value;
	}
	public void setValue (String value){
		  this.value=value;
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
 	 *  SQL脚本
 	 */
	
	public String getSql_script (){
		return sql_script;
	}
	public void setSql_script (String sql_script){
		  this.sql_script=sql_script;
	}


	/**
 	 *  字段映射
 	 */
	
	public String getField_reflect (){
		return field_reflect;
	}
	public void setField_reflect (String field_reflect){
		  this.field_reflect=field_reflect;
	}




}