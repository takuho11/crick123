/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-1-8 10:23:54
 *
 */
package cn.topcheer.pms2.api.sys;

import javax.persistence.*;

/**
 *  
 */
@Entity
@Table(name="SYS_PARAMVALUE")
public class SysParamvalue {


	private String id;
	private String value;
	private SysParamtype sysParamtype;
	private String code;
	private String name;
	private String showname;
	private String memo;
    private Integer seq;
	
	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}

	
	public String getValue (){
		return value;
	}
	public void setValue (String value){
		  this.value=value;
	}
	
	@ManyToOne
	@JoinColumn(name="PARAMID")
	public SysParamtype getSysParamtype (){
		return sysParamtype;
	}
	public void setSysParamtype (SysParamtype sysParamtype){
		  this.sysParamtype=sysParamtype;
	}

	
	public String getCode (){
		return code;
	}
	public void setCode (String code){
		  this.code=code;
	}
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}

	
	public String getShowname (){
		return showname;
	}
	public void setShowname (String showname){
		  this.showname=showname;
	}

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getMemo (){
		return memo;
	}
	public void setMemo (String memo){
		  this.memo=memo;
	}




}