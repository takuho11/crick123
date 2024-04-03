package cn.topcheer.pms2.api.map;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 创新地图资源表
 * @author zk/byf
 * @since 2024-03-08
 */
@Data
@TableName("MAP_RESOURCE")
@ApiModel(value = "MapResource(创新地图资源表)")
public class MapResource{

    @NotBlank
    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("保存时间")
    private Date savedate;

    @ApiModelProperty("资源类型")
    private String resourcetype;

    @ApiModelProperty("平台名称")
    private String projectbasename;

    @ApiModelProperty("依托单位名称")
    private String mainorganizers;

    @ApiModelProperty("统一社会信用代码")
    private String creditcode;

    @ApiModelProperty("层级")
    private String level;

    @ApiModelProperty("联系人")
    private String linkman;

    @ApiModelProperty("联系方式")
    private String linkphone;

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("县/区")
    private String county;

    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("简介（300字以内）")
    private String introduction;

    @ApiModelProperty("经度")
    private String longitude;

    @ApiModelProperty("纬度")
    private String latitude;

}
