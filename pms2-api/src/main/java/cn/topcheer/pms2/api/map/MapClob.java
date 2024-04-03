package cn.topcheer.pms2.api.map;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

/**
 * 创新地图资源大字段表
 * @author zk
 * @since 2024-03-09
 */
@Data
@TableName("MAP_CLOB")
@ApiModel(value = "MapClob(创新地图资源大字段表)")
public class MapClob{

    @NotBlank
    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("数据")
    private String source;

    @ApiModelProperty("最后更新时间")
    private Date lastupdate;
}
