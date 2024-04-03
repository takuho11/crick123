package cn.topcheer.pms2.api.sys.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@ApiModel(value = "SysGuideVO对象")
@JsonInclude(JsonInclude.Include.NON_NULL)//如果属性值为Null，不返回给前端
public class SysGuideVO {

    @ApiModelProperty("主键id--方向id")
    private String directionid;

    @ApiModelProperty("主键id--子方向id")
    private String childdirectionid;

    @ApiModelProperty("主键id--项目id")
    private String projectid;

    @ApiModelProperty("主键id--内容id")
    private String zcnrid;

    @ApiModelProperty("父级id")
    private String parentid;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("备注")
    private String memo;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("排序")
    private Integer seq;

    @ApiModelProperty("是否有强制指标")
    private String isqzzb;

    @ApiModelProperty("处室")
    private String belonglab;

    @ApiModelProperty("处室id")
    private String belonglabid;

    @ApiModelProperty("项目主管")
    private String xmzg;

    @ApiModelProperty("项目主管id")
    private String xmzgid;

    @ApiModelProperty("支持方向指标")
    private Integer limitnum;

    @ApiModelProperty("申报开始时间")
    private String starttime;

    @ApiModelProperty("申报结束时间")
    private String endtime;

    @ApiModelProperty("依托单位审核截至时间")
    private String relyunitendtime;

    @ApiModelProperty("推荐单位审核截止时间")
    private String recommendedunitendtime;

    @ApiModelProperty("主键id--关联sys_guide_config表的id")
    private String id;

    @ApiModelProperty("批次id")
    private String batchid;

    @ApiModelProperty("批次名称")
    private String batchname;

    @ApiModelProperty("专题名称")
    private String topicname;

    @ApiModelProperty("支持方向")
    private String supportdirection;

    @ApiModelProperty("支持子方向")
    private String supportdirectionchild;

    @ApiModelProperty("研究内容")
    private String researchcontent;

    @ApiModelProperty("参考指标")
    private String refertarget;

    @ApiModelProperty("强制指标")
    private String obligedtarget;

    @ApiModelProperty("需求脚本")
    private String demand;

    @ApiModelProperty("需求中文")
    private String requirement;

    @ApiModelProperty("支持强度-每个项目财政经费定额")
    private Double fund;

    @ApiModelProperty("支持方向类型")
    private String supporttype;

    @ApiModelProperty("支持子方向id")
    private String supportdirectionchildid;

    @ApiModelProperty("关联id")
    private String sourceid;

    @ApiModelProperty("关联层次type")
    private String sourcetype;

    @ApiModelProperty("经费内容")
    private String fund_content;

    @ApiModelProperty("方向集合")
    private List<SysGuideVO> directionList;

    @ApiModelProperty("子方向集合")
    private List<SysGuideVO> childdirectionList;



}
