/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2023-11-14 13:52:23
 *
 */
package cn.topcheer.pms2.api.project.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *  
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="SJHJ_METADATA")
public class SjhjMetadata {


	/**
     *  主键
     */
    @FieldDes(name="id",lenth="255",type="String",memo="主键")
	private String id;

	/**
     *  
     */
    @FieldDes(name="mainid",lenth="255",type="String",memo="")
	private String mainid;

	/**
     *  
     */
    @FieldDes(name="type",lenth="255",type="String",memo="")
	private String type;

	/**
     *  
     */
    @FieldDes(name="seq",type="Integer",memo="")
	private Integer seq;

	/**
     *  
     */
    @FieldDes(name="sourceid",lenth="255",type="String",memo="")
	private String sourceid;

	/**
     *  
     */
    @FieldDes(name="updatelasttime",type="Date",memo="")
	private Date updatelasttime;

	/**
     *  
     */
    @FieldDes(name="savedate",type="Date",memo="")
	private Date savedate;

	/**
     *  标识符
     */
    @FieldDes(name="datpid",lenth="1000",type="String",memo="标识符")
	private String datpid;

	/**
     *  标题
     */
    @FieldDes(name="datnam",lenth="500",type="String",memo="标题")
	private String datnam;

	/**
     *  摘要
     */
    @FieldDes(name="databs",lenth="4000",type="String",memo="摘要")
	private String databs;

	/**
     *  关键词
     */
    @FieldDes(name="datkeyword",lenth="255",type="String",memo="关键词")
	private String datkeyword;

	/**
     *  开始时间
     */
    @FieldDes(name="dattimstart",type="Date",memo="开始时间")
	private Date dattimstart;

	/**
     *  结束时间
     */
    @FieldDes(name="dattimend",type="Date",memo="结束时间")
	private Date dattimend;

	/**
     *  空间范围
     */
    @FieldDes(name="datgeosco",lenth="255",type="String",memo="空间范围")
	private String datgeosco;

	/**
     *  语种
     */
    @FieldDes(name="language",lenth="255",type="String",memo="语种")
	private String language;

	/**
     *  文件内容
     */
    @FieldDes(name="file",lenth="500",type="String",memo="文件内容")
	private String file;

	/**
     *  数据量
     */
    @FieldDes(name="datvol",lenth="255",type="String",memo="数据量")
	private String datvol;

	/**
     *  数据格式
     */
    @FieldDes(name="datfor",lenth="255",type="String",memo="数据格式")
	private String datfor;

	/**
     *  发布日期
     */
    @FieldDes(name="datpubdate",type="Date",memo="发布日期")
	private Date datpubdate;

	/**
     *  出版期刊
     */
    @FieldDes(name="journal",lenth="500",type="String",memo="出版期刊")
	private String journal;

	/**
     *  版本信息
     */
    @FieldDes(name="apppro",lenth="500",type="String",memo="版本信息")
	private String apppro;

	/**
     *  数据集引用格式
     */
    @FieldDes(name="datrefform",lenth="500",type="String",memo="数据集引用格式")
	private String datrefform;

	/**
     *  数据集共享许可协议
     */
    @FieldDes(name="datlic",lenth="2000",type="String",memo="数据集共享许可协议")
	private String datlic;

	/**
     *  数据集使用声明
     */
    @FieldDes(name="datsta",lenth="2000",type="String",memo="数据集使用声明")
	private String datsta;

	/**
     *  数据集下载地址
     */
    @FieldDes(name="datdownurl",lenth="255",type="String",memo="数据集下载地址")
	private String datdownurl;

	/**
     *  数据论文访问地址
     */
    @FieldDes(name="datpapaccurl",lenth="255",type="String",memo="数据论文访问地址")
	private String datpapaccurl;

	/**
     *  数据论文内容标识符
     */
    @FieldDes(name="datpappid",lenth="1000",type="String",memo="数据论文内容标识符")
	private String datpappid;

	/**
     *  标题
     */
    @FieldDes(name="datpaptitl",lenth="500",type="String",memo="标题")
	private String datpaptitl;

	/**
     *  摘要
     */
    @FieldDes(name="datpapabs",lenth="4000",type="String",memo="摘要")
	private String datpapabs;

	/**
     *  关键词
     */
    @FieldDes(name="datpapkeywor",lenth="255",type="String",memo="关键词")
	private String datpapkeywor;

	/**
     *  收稿日期
     */
    @FieldDes(name="datpaprecdate",type="Date",memo="收稿日期")
	private Date datpaprecdate;

	/**
     *  同评日期
     */
    @FieldDes(name="datpaprevdate",type="Date",memo="同评日期")
	private Date datpaprevdate;

	/**
     *  录用日期
     */
    @FieldDes(name="datpapaccdate",type="Date",memo="录用日期")
	private Date datpapaccdate;

	/**
     *  出版日期
     */
    @FieldDes(name="datpappubdate",type="Date",memo="出版日期")
	private Date datpappubdate;

	/**
     *  版本信息
     */
    @FieldDes(name="datpapver",lenth="255",type="String",memo="版本信息")
	private String datpapver;

	/**
     *  出版期刊
     */
    @FieldDes(name="pubjou",lenth="1000",type="String",memo="出版期刊")
	private String pubjou;

	/**
     *  数据论文引用格式
     */
    @FieldDes(name="datpapreffor",lenth="500",type="String",memo="数据论文引用格式")
	private String datpapreffor;

	/**
     *  数据论文下载地址
     */
    @FieldDes(name="datpapdownurl",lenth="255",type="String",memo="数据论文下载地址")
	private String datpapdownurl;

	/**
     *  数据论文共享许可协议
     */
    @FieldDes(name="datpaplicense",lenth="2000",type="String",memo="数据论文共享许可协议")
	private String datpaplicense;

	/**
     *  数据集访问地址
     */
    @FieldDes(name="dataccurl",lenth="255",type="String",memo="数据集访问地址")
	private String dataccurl;

	/**
     *  引言
     */
    @FieldDes(name="intro",lenth="4000",type="CLOB",memo="引言")
	private String intro;

	/**
     *  数据采集和处理方法
     */
    @FieldDes(name="meth",lenth="4000",type="CLOB",memo="数据采集和处理方法")
	private String meth;

	/**
     *  数据样本描述
     */
    @FieldDes(name="datrec",lenth="4000",type="CLOB",memo="数据样本描述")
	private String datrec;

	/**
     *  数据质量控制和评估
     */
    @FieldDes(name="qcneva",lenth="4000",type="CLOB",memo="数据质量控制和评估")
	private String qcneva;

	/**
     *  数据使用方法和建议
     */
    @FieldDes(name="usannote",lenth="4000",type="CLOB",memo="数据使用方法和建议")
	private String usannote;

	/**
     *  参考文献
     */
    @FieldDes(name="ref",lenth="4000",type="CLOB",memo="参考文献")
	private String ref;

	/**
     *  致谢
     */
    @FieldDes(name="ack",lenth="4000",type="CLOB",memo="致谢")
	private String ack;

	/**
     *  人员信息拼接
     */
    @FieldDes(name="xmryinfo",lenth="4000",type="CLOB",memo="人员信息拼接")
	private String xmryinfo;



	/**
 	 *  主键
 	 */
	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  
 	 */
	
	public String getMainid (){
		return mainid;
	}
	public void setMainid (String mainid){
		  this.mainid=mainid;
	}


	/**
 	 *  
 	 */
	
	public String getType (){
		return type;
	}
	public void setType (String type){
		  this.type=type;
	}


	/**
 	 *  
 	 */
	
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		  this.seq=seq;
	}


	/**
 	 *  
 	 */
	
	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		  this.sourceid=sourceid;
	}


	/**
 	 *  
 	 */
	
	public Date getUpdatelasttime (){
		return updatelasttime;
	}
	public void setUpdatelasttime (Date updatelasttime){
		  this.updatelasttime=updatelasttime;
	}


	/**
 	 *  
 	 */
	
	public Date getSavedate (){
		return savedate;
	}
	public void setSavedate (Date savedate){
		  this.savedate=savedate;
	}


	/**
 	 *  标识符
 	 */
	
	public String getDatpid (){
		return datpid;
	}
	public void setDatpid (String datpid){
		  this.datpid=datpid;
	}


	/**
 	 *  标题
 	 */
	
	public String getDatnam (){
		return datnam;
	}
	public void setDatnam (String datnam){
		  this.datnam=datnam;
	}


	/**
 	 *  摘要
 	 */
	
	public String getDatabs (){
		return databs;
	}
	public void setDatabs (String databs){
		  this.databs=databs;
	}


	/**
 	 *  关键词
 	 */
	
	public String getDatkeyword (){
		return datkeyword;
	}
	public void setDatkeyword (String datkeyword){
		  this.datkeyword=datkeyword;
	}


	/**
 	 *  开始时间
 	 */
	
	public Date getDattimstart (){
		return dattimstart;
	}
	public void setDattimstart (Date dattimstart){
		  this.dattimstart=dattimstart;
	}


	/**
 	 *  结束时间
 	 */
	
	public Date getDattimend (){
		return dattimend;
	}
	public void setDattimend (Date dattimend){
		  this.dattimend=dattimend;
	}


	/**
 	 *  空间范围
 	 */
	
	public String getDatgeosco (){
		return datgeosco;
	}
	public void setDatgeosco (String datgeosco){
		  this.datgeosco=datgeosco;
	}


	/**
 	 *  语种
 	 */
	
	public String getLanguage (){
		return language;
	}
	public void setLanguage (String language){
		  this.language=language;
	}


	/**
 	 *  文件内容
 	 */
	
	public String getFile (){
		return file;
	}
	public void setFile (String file){
		  this.file=file;
	}


	/**
 	 *  数据量
 	 */
	
	public String getDatvol (){
		return datvol;
	}
	public void setDatvol (String datvol){
		  this.datvol=datvol;
	}


	/**
 	 *  数据格式
 	 */
	
	public String getDatfor (){
		return datfor;
	}
	public void setDatfor (String datfor){
		  this.datfor=datfor;
	}


	/**
 	 *  发布日期
 	 */
	
	public Date getDatpubdate (){
		return datpubdate;
	}
	public void setDatpubdate (Date datpubdate){
		  this.datpubdate=datpubdate;
	}


	/**
 	 *  出版期刊
 	 */
	
	public String getJournal (){
		return journal;
	}
	public void setJournal (String journal){
		  this.journal=journal;
	}


	/**
 	 *  版本信息
 	 */
	
	public String getApppro (){
		return apppro;
	}
	public void setApppro (String apppro){
		  this.apppro=apppro;
	}


	/**
 	 *  数据集引用格式
 	 */
	
	public String getDatrefform (){
		return datrefform;
	}
	public void setDatrefform (String datrefform){
		  this.datrefform=datrefform;
	}


	/**
 	 *  数据集共享许可协议
 	 */
	
	public String getDatlic (){
		return datlic;
	}
	public void setDatlic (String datlic){
		  this.datlic=datlic;
	}


	/**
 	 *  数据集使用声明
 	 */
	
	public String getDatsta (){
		return datsta;
	}
	public void setDatsta (String datsta){
		  this.datsta=datsta;
	}


	/**
 	 *  数据集下载地址
 	 */
	
	public String getDatdownurl (){
		return datdownurl;
	}
	public void setDatdownurl (String datdownurl){
		  this.datdownurl=datdownurl;
	}


	/**
 	 *  数据论文访问地址
 	 */
	
	public String getDatpapaccurl (){
		return datpapaccurl;
	}
	public void setDatpapaccurl (String datpapaccurl){
		  this.datpapaccurl=datpapaccurl;
	}


	/**
 	 *  数据论文内容标识符
 	 */
	
	public String getDatpappid (){
		return datpappid;
	}
	public void setDatpappid (String datpappid){
		  this.datpappid=datpappid;
	}


	/**
 	 *  标题
 	 */
	
	public String getDatpaptitl (){
		return datpaptitl;
	}
	public void setDatpaptitl (String datpaptitl){
		  this.datpaptitl=datpaptitl;
	}


	/**
 	 *  摘要
 	 */
	
	public String getDatpapabs (){
		return datpapabs;
	}
	public void setDatpapabs (String datpapabs){
		  this.datpapabs=datpapabs;
	}


	/**
 	 *  关键词
 	 */
	
	public String getDatpapkeywor (){
		return datpapkeywor;
	}
	public void setDatpapkeywor (String datpapkeywor){
		  this.datpapkeywor=datpapkeywor;
	}


	/**
 	 *  收稿日期
 	 */
	
	public Date getDatpaprecdate (){
		return datpaprecdate;
	}
	public void setDatpaprecdate (Date datpaprecdate){
		  this.datpaprecdate=datpaprecdate;
	}


	/**
 	 *  同评日期
 	 */
	
	public Date getDatpaprevdate (){
		return datpaprevdate;
	}
	public void setDatpaprevdate (Date datpaprevdate){
		  this.datpaprevdate=datpaprevdate;
	}


	/**
 	 *  录用日期
 	 */
	
	public Date getDatpapaccdate (){
		return datpapaccdate;
	}
	public void setDatpapaccdate (Date datpapaccdate){
		  this.datpapaccdate=datpapaccdate;
	}


	/**
 	 *  出版日期
 	 */
	
	public Date getDatpappubdate (){
		return datpappubdate;
	}
	public void setDatpappubdate (Date datpappubdate){
		  this.datpappubdate=datpappubdate;
	}


	/**
 	 *  版本信息
 	 */
	
	public String getDatpapver (){
		return datpapver;
	}
	public void setDatpapver (String datpapver){
		  this.datpapver=datpapver;
	}


	/**
 	 *  出版期刊
 	 */
	
	public String getPubjou (){
		return pubjou;
	}
	public void setPubjou (String pubjou){
		  this.pubjou=pubjou;
	}


	/**
 	 *  数据论文引用格式
 	 */
	
	public String getDatpapreffor (){
		return datpapreffor;
	}
	public void setDatpapreffor (String datpapreffor){
		  this.datpapreffor=datpapreffor;
	}


	/**
 	 *  数据论文下载地址
 	 */
	
	public String getDatpapdownurl (){
		return datpapdownurl;
	}
	public void setDatpapdownurl (String datpapdownurl){
		  this.datpapdownurl=datpapdownurl;
	}


	/**
 	 *  数据论文共享许可协议
 	 */
	
	public String getDatpaplicense (){
		return datpaplicense;
	}
	public void setDatpaplicense (String datpaplicense){
		  this.datpaplicense=datpaplicense;
	}


	/**
 	 *  数据集访问地址
 	 */
	
	public String getDataccurl (){
		return dataccurl;
	}
	public void setDataccurl (String dataccurl){
		  this.dataccurl=dataccurl;
	}


	/**
 	 *  引言
 	 */
	
	public String getIntro (){
		return intro;
	}
	public void setIntro (String intro){
		  this.intro=intro;
	}


	/**
 	 *  数据采集和处理方法
 	 */
	
	public String getMeth (){
		return meth;
	}
	public void setMeth (String meth){
		  this.meth=meth;
	}


	/**
 	 *  数据样本描述
 	 */
	
	public String getDatrec (){
		return datrec;
	}
	public void setDatrec (String datrec){
		  this.datrec=datrec;
	}


	/**
 	 *  数据质量控制和评估
 	 */
	
	public String getQcneva (){
		return qcneva;
	}
	public void setQcneva (String qcneva){
		  this.qcneva=qcneva;
	}


	/**
 	 *  数据使用方法和建议
 	 */
	
	public String getUsannote (){
		return usannote;
	}
	public void setUsannote (String usannote){
		  this.usannote=usannote;
	}


	/**
 	 *  参考文献
 	 */
	
	public String getRef (){
		return ref;
	}
	public void setRef (String ref){
		  this.ref=ref;
	}


	/**
 	 *  致谢
 	 */
	
	public String getAck (){
		return ack;
	}
	public void setAck (String ack){
		  this.ack=ack;
	}


	/**
 	 *  人员信息拼接
 	 */
	
	public String getXmryinfo (){
		return xmryinfo;
	}
	public void setXmryinfo (String xmryinfo){
		  this.xmryinfo=xmryinfo;
	}




}