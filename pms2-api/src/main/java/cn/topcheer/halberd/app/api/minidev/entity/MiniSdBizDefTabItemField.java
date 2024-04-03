package cn.topcheer.halberd.app.api.minidev.entity;


import cn.topcheer.halberd.app.api.framework.entity.BasicEntity;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 标准业务分页子项字段表
 * </p>
 *
 * @author szs
 * @since 2023-08-28
 */
@Setter
@Getter
@TableName("mini_sd_biz_def_tab_item_field")
@ApiModel(value = "MiniSdBizDefTabItemField对象", description = "标准业务分页子项字段表")
public class MiniSdBizDefTabItemField extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "标准业务分页子项id")
    @TableField(value = "sd_biz_def_tab_item_id")
    private Long sdBizDefTabItemId;


    @ApiModelProperty(value = "默认显示名称")
    @TableField(value = "default_show_name", updateStrategy = FieldStrategy.IGNORED)
    private String defaultShowName;


    @ApiModelProperty(value = "显示名称")
    @TableField(value = "show_name", updateStrategy = FieldStrategy.IGNORED)
    private String showName;


    @ApiModelProperty(value = "字段名称")
    @TableField(value = "field_name", updateStrategy = FieldStrategy.IGNORED)
    private String fieldName;


    @ApiModelProperty(value = "组件类型")
    @TableField(value = "component_type", updateStrategy = FieldStrategy.IGNORED)
    private String componentType;


    @ApiModelProperty(value = "是否编辑（0否，1是）")
    @TableField(value = "is_edit", updateStrategy = FieldStrategy.IGNORED)
    private Boolean isEdit;


    @ApiModelProperty(value = "是否仅读（0否，1是）")
    @TableField(value = "is_readonly", updateStrategy = FieldStrategy.IGNORED)
    private Boolean isReadonly;


    @ApiModelProperty(value = "是否必填（0否，1是）")
    @TableField(value = "required", updateStrategy = FieldStrategy.IGNORED)
    private Boolean required;


    @ApiModelProperty(value = "输入提示")
    @TableField(value = "placeholder", updateStrategy = FieldStrategy.IGNORED)
    private String placeholder;


    @ApiModelProperty(value = "顺序")
    @TableField(value = "seq", updateStrategy = FieldStrategy.IGNORED)
    private Integer seq;


    @ApiModelProperty(value = "列占比（24栅格）")
    @TableField(value = "formspan", updateStrategy = FieldStrategy.IGNORED)
    private Integer formspan;


    @ApiModelProperty(value = "Label宽度（px）")
    @TableField(value = "label_width", updateStrategy = FieldStrategy.IGNORED)
    private String labelWidth;


    @ApiModelProperty(value = "校验类型（多个用 , 隔开 从规则维护列表中多选）")
    @TableField(value = "validate_type", updateStrategy = FieldStrategy.IGNORED)
    private String validateType;


    @ApiModelProperty(value = "自定义校验方法")
    @TableField(value = "validate_function", updateStrategy = FieldStrategy.IGNORED)
    private String validateFunction;


    @ApiModelProperty(value = "字段类型（ text,num,select,upload,date,switch）")
    @TableField(value = "type", updateStrategy = FieldStrategy.IGNORED)
    private String type;


    @ApiModelProperty(value = "最大字符长度（type=text）")
    @TableField(value = "max_length", updateStrategy = FieldStrategy.IGNORED)
    private Integer maxLength;


    @ApiModelProperty(value = "最小字符长度（type=text）")
    @TableField(value = "min_length", updateStrategy = FieldStrategy.IGNORED)
    private Integer minLength;


    @ApiModelProperty(value = "最大行数（type=text）")
    @TableField(value = "max_rows", updateStrategy = FieldStrategy.IGNORED)
    private Integer maxRows;


    @ApiModelProperty(value = "最小行数（type=text）")
    @TableField(value = "min_rows", updateStrategy = FieldStrategy.IGNORED)
    private Integer minRows;


    @ApiModelProperty(value = "最大值（type=num）")
    @TableField(value = "max_value", updateStrategy = FieldStrategy.IGNORED)
    private Long maxValue;


    @ApiModelProperty(value = "最小值（type=num）")
    @TableField(value = "min_value", updateStrategy = FieldStrategy.IGNORED)
    private Long minValue;


    @ApiModelProperty(value = "步数（type=num）")
    @TableField(value = "step", updateStrategy = FieldStrategy.IGNORED)
    private Integer step;


    @ApiModelProperty(value = "小数时保留位数（type=num）")
    @TableField(value = "decaimal_num", updateStrategy = FieldStrategy.IGNORED)
    private Integer decaimalNum;


    @ApiModelProperty(value = "下拉类型（req/dic/function/sample）")
    @TableField(value = "data_type", updateStrategy = FieldStrategy.IGNORED)
    private String dataType;


    @ApiModelProperty(value = "下拉字典名称（编码）、下拉方法")
    @TableField(value = "data_memember", updateStrategy = FieldStrategy.IGNORED)
    private String dataMemember;


    @ApiModelProperty(value = "连接器ID")
    @TableField(value = "req_connector_id", updateStrategy = FieldStrategy.IGNORED)
    private String reqConnectorId;


    @ApiModelProperty(value = "是否添加运行（0否，1是）")
    @TableField(value = "add_to_runtime", updateStrategy = FieldStrategy.IGNORED)
    private Boolean addToRuntime;


    @ApiModelProperty(value = "结果处理")
    @TableField(value = "data_script", updateStrategy = FieldStrategy.IGNORED)
    private String dataScript;


    @ApiModelProperty(value = "数据处理")
    @TableField(value = "fix_script", updateStrategy = FieldStrategy.IGNORED)
    private String fixScript;


    @ApiModelProperty(value = "请求前处理")
    @TableField(value = "before_req_script", updateStrategy = FieldStrategy.IGNORED)
    private String beforeReqScript;


    @ApiModelProperty(value = "字典数组（主要用于sample）")
    @TableField(value = "dic_data", updateStrategy = FieldStrategy.IGNORED)
    private String dicData;


    @ApiModelProperty(value = "级联属性（用于连接器）")
    @TableField(value = "cascader_prop", updateStrategy = FieldStrategy.IGNORED)
    private String cascaderProp;


    @ApiModelProperty(value = "级联时参数设置回调方法（用于连接器）")
    @TableField(value = "cascader_prop_param_function", updateStrategy = FieldStrategy.IGNORED)
    private String cascaderPropParamFunction;


    @ApiModelProperty(value = "字典标签字段名称")
    @TableField(value = "label_field", updateStrategy = FieldStrategy.IGNORED)
    private String labelField;


    @ApiModelProperty(value = "字典值字段名称")
    @TableField(value = "value_field", updateStrategy = FieldStrategy.IGNORED)
    private String valueField;


    @ApiModelProperty(value = "是否多选（0否，1是）")
    @TableField(value = "is_multiple", updateStrategy = FieldStrategy.IGNORED)
    private Boolean isMultiple;


    @ApiModelProperty(value = "模板下载URL")
    @TableField(value = "download_url", updateStrategy = FieldStrategy.IGNORED)
    private String downloadUrl;


    @ApiModelProperty(value = "上传类型，多个逗号隔开")
    @TableField(value = "upload_type", updateStrategy = FieldStrategy.IGNORED)
    private String uploadType;


    @ApiModelProperty(value = "上传个数，默认1")
    @TableField(value = "upload_limit", updateStrategy = FieldStrategy.IGNORED)
    private Integer uploadLimit;


    @ApiModelProperty(value = "上传大小（单位：M）")
    @TableField(value = "upload_size", updateStrategy = FieldStrategy.IGNORED)
    private Integer uploadSize;


    @ApiModelProperty(value = "日期类型（type=date）（year/month/date/dates/months/years/datetime/datetimerange/ daterange/monthrange）")
    @TableField(value = "date_type", updateStrategy = FieldStrategy.IGNORED)
    private String dateType;


    @ApiModelProperty(value = "开始提示（type=date）")
    @TableField(value = "start_placeholder", updateStrategy = FieldStrategy.IGNORED)
    private String startPlaceholder;


    @ApiModelProperty(value = "结束提示（type=date）")
    @TableField(value = "end_placeholder", updateStrategy = FieldStrategy.IGNORED)
    private String endPlaceholder;


    @ApiModelProperty(value = "指定输入框的格式（type=date）")
    @TableField(value = "format", updateStrategy = FieldStrategy.IGNORED)
    private String format;


    @ApiModelProperty(value = "指定绑定值的格式（type=date）")
    @TableField(value = "value_format", updateStrategy = FieldStrategy.IGNORED)
    private String valueFormat;


    @ApiModelProperty(value = "备注")
    @TableField(value = "memo", updateStrategy = FieldStrategy.IGNORED)
    private String memo;


    @ApiModelProperty(value = "默认数据")
    @TableField(value = "default_value", updateStrategy = FieldStrategy.IGNORED)
    private String defaultValue;


    @ApiModelProperty(value = "改变事件方法")
    @TableField(value = "change_event_function", updateStrategy = FieldStrategy.IGNORED)
    private String changeEventFunction;


    @ApiModelProperty(value = "switch 打开时的值")
    @TableField(value = "active_value", updateStrategy = FieldStrategy.IGNORED)
    private String activeValue;


    @ApiModelProperty(value = "switch 关闭时的值")
    @TableField(value = "inactive_value", updateStrategy = FieldStrategy.IGNORED)
    private String inactiveValue;


    @ApiModelProperty(value = "下拉请求参数方法")
    @TableField(value = "dropdown_req_param_function", updateStrategy = FieldStrategy.IGNORED)
    private String dropdownReqParamFunction;


    @ApiModelProperty(value = "自定义按钮")
    @TableField(value = "custom_button", updateStrategy = FieldStrategy.IGNORED)
    private String customButton;


    @ApiModelProperty(value = "是否显示动态方法")
    @TableField(value = "is_show_function", updateStrategy = FieldStrategy.IGNORED)
    private String isShowFunction;


    @ApiModelProperty(value = "是否必填动态方法")
    @TableField(value = "required_function", updateStrategy = FieldStrategy.IGNORED)
    private String requiredFunction;


    @ApiModelProperty(value = "是否编辑动态方法")
    @TableField(value = "is_edit_function", updateStrategy = FieldStrategy.IGNORED)
    private String isEditFunction;


    @ApiModelProperty(value = "是否显示（0否，1是）")
    @TableField(value = "is_show", updateStrategy = FieldStrategy.IGNORED)
    private Boolean isShow;


    @ApiModelProperty(value = "下拉后置方法")
    @TableField(value = "after_dropdown_data_function", updateStrategy = FieldStrategy.IGNORED)
    private String afterDropdownDataFunction;


    @ApiModelProperty(value = "禁止日期方法，设置禁用状态，参数为当前日期，要求返回 Boolean")
    @TableField(value = "disabled_date_function", updateStrategy = FieldStrategy.IGNORED)
    private String disabledDateFunction;


    @ApiModelProperty(value = "是否编辑行设置，数组字符串，暂时放弃")
    @TableField(value = "is_edit_row", updateStrategy = FieldStrategy.IGNORED)
    private String isEditRow;


    @ApiModelProperty(value = "公式脚本方法")
    @TableField(value = "formula_script", updateStrategy = FieldStrategy.IGNORED)
    private String formulaScript;


    @ApiModelProperty(value = "列表字段下拉属性")
    @TableField(value = "column_data_memember", updateStrategy = FieldStrategy.IGNORED)
    private String columnDataMemember;


    @ApiModelProperty(value = "列表字段下拉请求参数方法")
    @TableField(value = "column_dropdown_req_param_function", updateStrategy = FieldStrategy.IGNORED)
    private String columnDropdownReqParamFunction;


    @ApiModelProperty(value = "字段名称Label，主要适用于：crud组件、select组件、table组件、description组件等")
    @TableField(value = "field_label", updateStrategy = FieldStrategy.IGNORED)
    private String fieldLabel;


    @ApiModelProperty(value = "下拉方法")
    @TableField(value = "data_memember_function", updateStrategy = FieldStrategy.IGNORED)
    private String dataMememberFunction;


    @ApiModelProperty(value = "是否允许创建条目（0否，1是），用于下拉")
    @TableField(value = "is_allow_create", updateStrategy = FieldStrategy.IGNORED)
    private Boolean isAllowCreate;


    @ApiModelProperty(value = "是否列表显示（0否，1是），用于Crud的列表字段显示")
    @TableField(value = "is_list_show", updateStrategy = FieldStrategy.IGNORED)
    private Boolean isListShow;


    @ApiModelProperty(value = "是否显示快捷选项（0否，1是），用于日期")
    @TableField(value = "is_shortcut", updateStrategy = FieldStrategy.IGNORED)
    private Boolean isShortcut;


    @ApiModelProperty(value = "样例模板下载URL")
    @TableField(value = "sample_download_url", updateStrategy = FieldStrategy.IGNORED)
    private String sampleDownloadUrl;


}
