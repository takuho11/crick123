package cn.topcheer.pms2.api.sys;

import cn.topcheer.pms2.api.annotation.FieldDes;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@TableName("SYS_GUIDE_CONFIG")
public class SysGuideConfig {

	/**
 	 *  主键
 	 */
    @FieldDes(name="id",lenth="255",type="String",memo="主键")
    @NotBlank
	private String id;

    /**
     *  批次id
     */
    @FieldDes(name="batchid",lenth="255",type="String",memo="批次id")
    private String batchid;

    /**
     *  批次名称
     */
    @FieldDes(name="batchname",lenth="255",type="String",memo="批次名称")
    private String batchname;

	/**
 	 *  处室id
 	 */
    @FieldDes(name="belonglabid",lenth="4000",type="String",memo="处室id")
	private String belonglabid;

    /**
     *  处室
     */
    @FieldDes(name="belonglab",lenth="4000",type="String",memo="处室")
    private String belonglab;

    /**
     *  专题名称
     */
    @FieldDes(name="topicname",lenth="255",type="String",memo="专题名称")
    private String topicname;

    /**
     *  支持方向
     */
    @FieldDes(name="supportdirection",lenth="255",type="String",memo="支持方向")
    private String supportdirection;

    /**
     *  支持子方向
     */
    @FieldDes(name="supportdirectionchild",lenth="255",type="String",memo="支持子方向")
    private String supportdirectionchild;

    /**
     *  研究内容
     */
    @FieldDes(name="researchcontent",lenth="2000",type="String",memo="研究内容")
    private String researchcontent;

    /**
     *  参考指标
     */
    @FieldDes(name="refertarget",lenth="2000",type="String",memo="参考指标")
    private String refertarget;

    /**
     *  强制指标
     */
    @FieldDes(name="obligedtarget",lenth="2000",type="String",memo="强制指标")
    private String obligedtarget;

    /**
     *  需求脚本
     */
    @FieldDes(name="demand",lenth="4000",type="String",memo="需求脚本")
    private String demand;

    /**
     *  需求中文
     */
    @FieldDes(name="requirement",lenth="4000",type="String",memo="需求中文")
    private String requirement;

    /**
     *  支持强度-每个项目财政经费定额
     */
    @FieldDes(name="fund",type="Double",memo="支持强度-每个项目财政经费定额")
    private Double fund;

    /**
     *  支持方向
     */
    @FieldDes(name="supporttype",lenth="255",type="String",memo="支持方向")
    private String supporttype;

    /**
     *  排序
     */
    @FieldDes(name="seq",type="Integer",memo="排序")
    private Integer seq;

    /**
     *  支持子方向id
     */
    @FieldDes(name="supportdirectionchildid",lenth="255",type="String",memo="支持子方向id")
    private String supportdirectionchildid;

    /**
     *  关联id
     */
    @FieldDes(name="sourceid",lenth="255",type="String",memo="关联id")
    private String sourceid;

    /**
     *  关联层次type
     */
    @FieldDes(name="sourcetype",lenth="255",type="String",memo="关联层次type")
    private String sourcetype;

    /**
     *  经费内容
     */
    @FieldDes(name="fund_content",lenth="255",type="String",memo="经费内容")
    private String fund_content;

}