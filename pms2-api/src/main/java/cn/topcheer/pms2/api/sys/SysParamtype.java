/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-1-8 10:23:54
 *
 */
package cn.topcheer.pms2.api.sys;

import javax.persistence.*;
import java.util.List;

/**
 *  
 */
@Entity
@Table(name="SYS_PARAMTYPE")
public class SysParamtype {

	private String id;
	private String name;
	private String code;
	private String paramlevel;
	private String memo;
	//private String planprojectid;
	

	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}

	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}

	public String getCode (){
		return code;
	}
	public void setCode (String code){
		  this.code=code;
	}
	
	public String getParamlevel (){
		return paramlevel;
	}
	public void setParamlevel (String paramlevel){
		  this.paramlevel=paramlevel;
	}

	public String getMemo (){
		return memo;
	}
	public void setMemo (String memo){
		  this.memo=memo;
	}


	private List<SysParamvalue> sysParamvalues;
	
	@OneToMany(mappedBy= "sysParamtype",cascade={CascadeType.ALL})
	public List<SysParamvalue> getSysParamvalues(){
		return sysParamvalues;
	}
	
	public void setSysParamvalues (List<SysParamvalue> sysParamvalues){
		  this.sysParamvalues=sysParamvalues;
	}	
	
		


}