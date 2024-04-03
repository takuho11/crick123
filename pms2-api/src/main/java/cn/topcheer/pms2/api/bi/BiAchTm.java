/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 15:56:31
 */
package cn.topcheer.pms2.api.bi;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  数据仓-论文专著表
 */
@Entity
@Table(name = "BI_ACH_TM")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiAchTm {

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "")
    private String id;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "")
    private Integer seq;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("THESISNAME")
    @Column(columnDefinition = "THESISNAME")
    @FieldDes(name = "thesisname", lenth = "1000", type = "String", memo = "")
    private String thesisname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("FIRSTAUTHOR")
    @Column(columnDefinition = "FIRSTAUTHOR")
    @FieldDes(name = "firstauthor", lenth = "255", type = "String", memo = "")
    private String firstauthor;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CORRESPONDINGAUTHOR")
    @Column(columnDefinition = "CORRESPONDINGAUTHOR")
    @FieldDes(name = "correspondingauthor", lenth = "255", type = "String", memo = "")
    private String correspondingauthor;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PUBLICATIONNAME")
    @Column(columnDefinition = "PUBLICATIONNAME")
    @FieldDes(name = "publicationname", lenth = "2000", type = "String", memo = "")
    private String publicationname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PAGE")
    @Column(columnDefinition = "PAGE")
    @FieldDes(name = "page", lenth = "255", type = "String", memo = "")
    private String page;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("INFLUENCE")
    @Column(columnDefinition = "INFLUENCE")
    private String influence;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PUBLISHDATE")
    @Column(columnDefinition = "PUBLISHDATE")
    @FieldDes(name = "publishdate", lenth = "255", type = "String", memo = "")
    private String publishdate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SCINUM")
    @Column(columnDefinition = "SCINUM")
    @FieldDes(name = "scinum", type = "Integer", memo = "")
    private Integer scinum;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("EINUM")
    @Column(columnDefinition = "EINUM")
    @FieldDes(name = "einum", type = "Integer", memo = "")
    private Integer einum;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("THESISQUOTENUM")
    @Column(columnDefinition = "THESISQUOTENUM")
    @FieldDes(name = "thesisquotenum", type = "Integer", memo = "")
    private Integer thesisquotenum;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AUTHORRANK")
    @Column(columnDefinition = "AUTHORRANK")
    @FieldDes(name = "authorrank", type = "Integer", memo = "")
    private Integer authorrank;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("MEMO")
    @Column(columnDefinition = "MEMO")
    @FieldDes(name = "memo", lenth = "255", type = "String", memo = "")
    private String memo;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("MAINID")
    @Column(columnDefinition = "MAINID")
    @FieldDes(name = "mainid", lenth = "255", type = "String", memo = "")
    private String mainid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SOURCEID")
    @Column(columnDefinition = "SOURCEID")
    @FieldDes(name = "sourceid", lenth = "255", type = "String", memo = "")
    private String sourceid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "255", type = "String", memo = "")
    private String type;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PUBLISHINGHOUSE")
    @Column(columnDefinition = "PUBLISHINGHOUSE")
    @FieldDes(name = "publishinghouse", lenth = "255", type = "String", memo = "")
    private String publishinghouse;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("KEYWORDS")
    @Column(columnDefinition = "KEYWORDS")
    @FieldDes(name = "keywords", lenth = "3000", type = "String", memo = "")
    private String keywords;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISSHOW")
    @Column(columnDefinition = "ISSHOW")
    @FieldDes(name = "isshow", lenth = "255", type = "String", memo = "")
    private String isshow;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SCIOREI")
    @Column(columnDefinition = "SCIOREI")
    @FieldDes(name = "sciorei", lenth = "255", type = "String", memo = "")
    private String sciorei;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AUTHOR")
    @Column(columnDefinition = "AUTHOR")
    @FieldDes(name = "author", lenth = "2000", type = "String", memo = "")
    private String author;

    /**
     *  保存时间
     */
    @ApiModelProperty("保存时间")
    @TableField("SAVEDATE")
    @Column(columnDefinition = "SAVEDATE")
    @FieldDes(name = "savedate", type = "Date", memo = "保存时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date savedate;

    /**
     *  最后一次更新时间
     */
    @ApiModelProperty("最后一次更新时间")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "最后一次更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updatelasttime;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("DRBJ")
    @Column(columnDefinition = "DRBJ")
    @FieldDes(name = "drbj", lenth = "255", type = "String", memo = "")
    private String drbj;


    /**
    *  
    */
        @Id
    public String getId (){
        return id;
    }
    public void setId (String id){
        this.id = id;
    }

    /**
    *  
    */
    public Integer getSeq (){
        return seq;
    }
    public void setSeq (Integer seq){
        this.seq = seq;
    }

    /**
    *  
    */
    public String getThesisname (){
        return thesisname;
    }
    public void setThesisname (String thesisname){
        this.thesisname = thesisname;
    }

    /**
    *  
    */
    public String getFirstauthor (){
        return firstauthor;
    }
    public void setFirstauthor (String firstauthor){
        this.firstauthor = firstauthor;
    }

    /**
    *  
    */
    public String getCorrespondingauthor (){
        return correspondingauthor;
    }
    public void setCorrespondingauthor (String correspondingauthor){
        this.correspondingauthor = correspondingauthor;
    }

    /**
    *  
    */
    public String getPublicationname (){
        return publicationname;
    }
    public void setPublicationname (String publicationname){
        this.publicationname = publicationname;
    }

    /**
    *  
    */
    public String getPage (){
        return page;
    }
    public void setPage (String page){
        this.page = page;
    }

    /**
    *  
    */
    public String getInfluence (){
        return influence;
    }
    public void setInfluence (String influence){
        this.influence = influence;
    }

    /**
    *  
    */
    public String getPublishdate (){
        return publishdate;
    }
    public void setPublishdate (String publishdate){
        this.publishdate = publishdate;
    }

    /**
    *  
    */
    public Integer getScinum (){
        return scinum;
    }
    public void setScinum (Integer scinum){
        this.scinum = scinum;
    }

    /**
    *  
    */
    public Integer getEinum (){
        return einum;
    }
    public void setEinum (Integer einum){
        this.einum = einum;
    }

    /**
    *  
    */
    public Integer getThesisquotenum (){
        return thesisquotenum;
    }
    public void setThesisquotenum (Integer thesisquotenum){
        this.thesisquotenum = thesisquotenum;
    }

    /**
    *  
    */
    public Integer getAuthorrank (){
        return authorrank;
    }
    public void setAuthorrank (Integer authorrank){
        this.authorrank = authorrank;
    }

    /**
    *  
    */
    public String getMemo (){
        return memo;
    }
    public void setMemo (String memo){
        this.memo = memo;
    }

    /**
    *  
    */
    public String getMainid (){
        return mainid;
    }
    public void setMainid (String mainid){
        this.mainid = mainid;
    }

    /**
    *  
    */
    public String getSourceid (){
        return sourceid;
    }
    public void setSourceid (String sourceid){
        this.sourceid = sourceid;
    }

    /**
    *  
    */
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
    }

    /**
    *  
    */
    public String getPublishinghouse (){
        return publishinghouse;
    }
    public void setPublishinghouse (String publishinghouse){
        this.publishinghouse = publishinghouse;
    }

    /**
    *  
    */
    public String getKeywords (){
        return keywords;
    }
    public void setKeywords (String keywords){
        this.keywords = keywords;
    }

    /**
    *  
    */
    public String getIsshow (){
        return isshow;
    }
    public void setIsshow (String isshow){
        this.isshow = isshow;
    }

    /**
    *  
    */
    public String getSciorei (){
        return sciorei;
    }
    public void setSciorei (String sciorei){
        this.sciorei = sciorei;
    }

    /**
    *  
    */
    public String getAuthor (){
        return author;
    }
    public void setAuthor (String author){
        this.author = author;
    }

    /**
    *  保存时间
    */
    public Date getSavedate (){
        return savedate;
    }
    public void setSavedate (Date savedate){
        this.savedate = savedate;
    }

    /**
    *  最后一次更新时间
    */
    public Date getUpdatelasttime (){
        return updatelasttime;
    }
    public void setUpdatelasttime (Date updatelasttime){
        this.updatelasttime = updatelasttime;
    }

    /**
    *  
    */
    public String getDrbj (){
        return drbj;
    }
    public void setDrbj (String drbj){
        this.drbj = drbj;
    }

}