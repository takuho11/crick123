/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-2-22 14:53:22
 *
 */
package cn.topcheer.pms2.api.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="PMS_ENTERPRISELIMIT")
public class PmsEnterpriselimit {


	private String id;

	/**
 	 *  是否有效 0 表示无效 1 表示有效
 	 */
	private Integer isable;

	/**
	 *  单位id
	 */
	private String enterpriseid;

	/**
	 *  excel单位名称
	 */
	private String excelenterprisename;

	/**
	 *  系统单位名称
	 */
	private String enterprisename;

	/**
	 *  单位类型
	 */
	private String enterprisetype;

	/**
	 *  批次名称
	 */
	private String batchname;

	/**
	 *  批次id
	 */
	private String batchid;

	/**
	 *  可推荐数量
	 */
	private Integer limitnum;

	/**
	 *  已推荐数量
	 */
	private Integer recommendnum;

	/**
	 *  是否导入成功
	 */
	private String importstate;

    /**
     *  导入失败原因
     */
    private String failreason;

    /**
	 * 导入时间
	 */
    private Date importdate;


	private Integer tjdwseq;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 方向
	 */
	private String directionid;

	/**
	 * 子方向
	 */
	private String childdirectionid;

	/**
	 * 创建者
	 */
	@ApiModelProperty("创建者")
	@TableField("CREATE_USER")
	@Column(columnDefinition = "CREATE_USER")
	private String createUser;



	/**
	 * 组织单位截止审核时间
	 */
	private Date endtime;

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getRecommendnum() {
		return recommendnum;
	}
	public void setRecommendnum(Integer recommendnum) {
		this.recommendnum = recommendnum;
	}

	public String getBatchname() {
		return batchname;
	}
	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}

	public String getEnterpriseid() {
		return enterpriseid;
	}
	public void setEnterpriseid(String enterpriseid) {
		this.enterpriseid = enterpriseid;
	}

	public String getBatchid() {
		return batchid;
	}
	public void setBatchid(String batchid) { this.batchid = batchid; }

	public String getEnterprisetype() {
		return enterprisetype;
	}
	public void setEnterprisetype(String enterprisetype) {
		this.enterprisetype = enterprisetype;
	}

	public String getEnterprisename (){
		return enterprisename;
	}
	public void setEnterprisename (String enterprisename){
		  this.enterprisename = enterprisename;
	}

	public String getExcelenterprisename (){
		return excelenterprisename;
	}
	public void setExcelenterprisename (String excelenterprisename){
		this.excelenterprisename = excelenterprisename;
	}
	
	public Integer getLimitnum (){
		return limitnum;
	}
	public void setLimitnum (Integer limitnum){
		  this.limitnum = limitnum;
	}
	
	public Integer getIsable (){
		return isable;
	}
	public void setIsable (Integer isable){
		  this.isable = isable;
	}

	public String getImportstate (){ return importstate; }
	public void setImportstate (String importstate){
		this.importstate = importstate;
	}

    public String getFailreason() {
        return failreason;
    }
    public void setFailreason(String failreason) {
        this.failreason = failreason;
    }

    public Date getImportdate() {
        return importdate;
    }
    public void setImportdate(Date importdate) {
        this.importdate = importdate;
    }

	public Integer getTjdwseq() {return tjdwseq;}
	public void setTjdwseq(Integer tjdwseq) {this.tjdwseq = tjdwseq;}

	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getType() { return type; }
	public void setType(String type) { this.type = type; }



	public String getDirectionid() {
		return directionid;
	}

	public void setDirectionid(String directionid) {
		this.directionid = directionid;
	}

	public String getChilddirectionid() {
		return childdirectionid;
	}

	public void setChilddirectionid(String childdirectionid) {
		this.childdirectionid = childdirectionid;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
}