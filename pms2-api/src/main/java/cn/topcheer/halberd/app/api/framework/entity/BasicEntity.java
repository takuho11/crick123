package cn.topcheer.halberd.app.api.framework.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseBigIntEntity;

/**
 * 基础实体类
 *
 * @author Bruce Song
 * @version 1.0
 * @since 2023/7/24 9:34
 */
@Getter
@Setter
public class BasicEntity extends BaseBigIntEntity {

    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("主键id")
    @TableId(
            value = "id",
            type = IdType.ASSIGN_ID
    )
    private Long id;


    @Override
    public boolean isIdNull() {
        return id==null;
    }

    @Override
    public void setStatus(Integer status) {

    }
}
