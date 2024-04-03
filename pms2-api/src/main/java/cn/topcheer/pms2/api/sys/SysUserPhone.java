/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-1-25 13:39:30
 *
 */
package cn.topcheer.pms2.api.sys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="SYS_USER_PHONE")
public class SysUserPhone {


	private String id;

	private String name;

	private String userid;

	private String mobile;

	private String status;

	private String type;

	private Date savedate;

	@Id
	public String getId() {return id;}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getSavedate() {return savedate;}

	public void setSavedate(Date savedate) {this.savedate = savedate;}
}