package cn.topcheer.halberd.gateway.api.po;

import java.io.Serializable;

import org.springblade.core.mp.base.BaseUuidEntity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("sf_gw_subsystem")
@Data
public class SubSystem extends  BaseUuidEntity implements Serializable {
    
    private String name;
    private String gatewayPath;
 private String realUrl;
 private int authMode;
 private String authBean;
 private String resType;
 private String skipUrl;
}
