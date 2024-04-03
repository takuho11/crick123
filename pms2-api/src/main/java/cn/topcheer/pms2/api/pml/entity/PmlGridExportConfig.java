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
import java.util.Date;

/**
 *  列表导出配置
 */
@Entity
@Table(name="PML_GRID_EXPORT_CONFIG")
public class PmlGridExportConfig {


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
     *  业务类型
     */
    @FieldDes(name="businesstype",lenth="255",type="String",memo="业务类型")
	private String businesstype;

	/**
     *  列表ID
     */
    @FieldDes(name="gridid",lenth="255",type="String",memo="列表ID")
	private String gridid;

	/**
     *  版本ID
     */
    @FieldDes(name="versionid",lenth="255",type="String",memo="版本ID")
	private String versionid;

	/**
     *  创建人
     */
    @FieldDes(name="operator",lenth="255",type="String",memo="创建人")
	private String operator;

	/**
     *  保存时间
     */
	@FieldDes(name="savedate",type="Date",memo="保存时间")
	private Date savedate;

	/**
     *  备注
     */
    @FieldDes(name="memo",lenth="255",type="String",memo="备注")
	private String memo;

	/**
     *  导出字段
     */
    @FieldDes(name="export_feild",type="Clob",memo="导出字段")
	private String export_feild;

	/**
     *  配置字段
     */
    @FieldDes(name="config_feild",type="Clob",memo="配置字段")
	private String config_feild;

	/**
	 *  是否导出唯一性标识字段
	 */
	@FieldDes(name="enable_unique",lenth="255",type="String",memo="是否导出唯一性标识字段")
	private String enable_unique;

	/**
	 *  定制化字段
	 */
	@FieldDes(name="customid",lenth="255",type="String",memo="定制化字段")
	private String customid;


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
 	 *  业务类型
 	 */

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
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
 	 *  版本ID
 	 */
	
	public String getVersionid (){
		return versionid;
	}
	public void setVersionid (String versionid){
		  this.versionid=versionid;
	}


	/**
 	 *  创建人
 	 */
	
	public String getOperator (){
		return operator;
	}
	public void setOperator (String operator){
		  this.operator=operator;
	}


	/**
 	 *  保存时间
 	 */
	
	public Date getSavedate (){
		return savedate;
	}
	public void setSavedate (Date savedate){
		  this.savedate=savedate;
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
 	 *  导出字段
 	 */
	
	public String getExport_feild (){
		return export_feild;
	}
	public void setExport_feild (String export_feild){
		  this.export_feild=export_feild;
	}


	/**
 	 *  配置字段
 	 */
	
	public String getConfig_feild (){
		return config_feild;
	}
	public void setConfig_feild (String config_feild){
		  this.config_feild=config_feild;
	}

	/**
	 * 是否导出唯一性标识字段
	 * @return
	 */
	public String getEnable_unique() {
		return enable_unique;
	}

	public void setEnable_unique(String enable_unique) {
		this.enable_unique = enable_unique;
	}

	public String getCustomid() {
		return customid;
	}

	public void setCustomid(String customid) {
		this.customid = customid;
	}
}