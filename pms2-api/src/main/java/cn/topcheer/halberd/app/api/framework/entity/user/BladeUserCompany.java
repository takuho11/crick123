package cn.topcheer.halberd.app.api.framework.entity.user;


import cn.topcheer.halberd.app.api.framework.entity.BasicEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* <p>
* 用户企业信息表
* </p>
*
* @author szs
* @since 2023-09-06
*/
@Setter
@Getter
@TableName("sf_user_company")
@ApiModel(value="BladeUserCompany对象", description="用户企业信息表")
    public class BladeUserCompany extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "用户id")
    @TableField(value = "user_id")
    private String userId;


    @ApiModelProperty(value = "企业名称")
    @TableField(value = "name")
    private String name;


    @ApiModelProperty(value = "统一社会信用代码")
    @TableField(value = "code")
    private String code;


    @ApiModelProperty(value = "通讯地址")
    @TableField(value = "address")
    private String address;


    @ApiModelProperty(value = "行政区代号")
    @TableField(value = "district_code")
    private String districtCode;


    @ApiModelProperty(value = "企业登记注册类型")
    @TableField(value = "register_type")
    private String registerType;


    @ApiModelProperty(value = "企业法人")
    @TableField(value = "legal_person")
    private String legalPerson;


    @ApiModelProperty(value = "填表人")
    @TableField(value = "fill_form_person")
    private String fillFormPerson;


    @ApiModelProperty(value = "手机")
    @TableField(value = "phone")
    private String phone;


    @ApiModelProperty(value = "上市代码")
    @TableField(value = "listing_code")
    private String listingCode;


}
