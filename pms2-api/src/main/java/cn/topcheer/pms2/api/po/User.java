package cn.topcheer.pms2.api.po;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
 
@Entity
@Table(name = "sf_user", uniqueConstraints = {@UniqueConstraint(columnNames="account")})
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Date updateTime;
    /**
     * 用户名
     */
    private String name;


    /**
     * 显示的用户姓名（一般中文）
     */
    private String realName;

/**
     * 显示的用户姓名（一般中文）
     */
    private String account;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;


    
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


     
   
 
   
    public void setId (String id) {
        this.id = id;
    }

    public String getId () {
        return id;
    }
 
 

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     

      
  

 
}

