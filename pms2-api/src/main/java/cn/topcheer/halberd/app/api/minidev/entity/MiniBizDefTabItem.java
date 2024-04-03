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
 * 业务分页子项表
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Setter
@Getter
@TableName("mini_biz_def_tab_item")
@ApiModel(value = "MiniBizDefTabItem对象", description = "业务分页子项表")
public class MiniBizDefTabItem extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "子项编号")
    @TableField(value = "tab_item_code")
    private String tabItemCode;


    @ApiModelProperty(value = "业务分页id")
    @TableField(value = "biz_def_tab_id")
    private Long bizDefTabId;


    @ApiModelProperty(value = "标准业务分页子项id")
    @TableField(value = "sd_biz_def_tab_item_id")
    private Long sdBizDefTabItemId;


    @ApiModelProperty(value = "模型ID")
    @TableField(value = "model_id")
    private String modelId;


    @ApiModelProperty(value = "模型名称")
    @TableField(value = "model_name")
    private String modelName;


    @ApiModelProperty(value = "子项名称")
    @TableField(value = "show_name")
    private String showName;


    @ApiModelProperty(value = "子项Prop")
    @TableField(value = "data_member")
    private String dataMember;


    @ApiModelProperty(value = "顺序")
    @TableField(value = "seq")
    private Integer seq;


    @ApiModelProperty(value = "数据源名称")
    @TableField(value = "table_name")
    private String tableName;


    @ApiModelProperty(value = "是否含有说明（0否，1是）")
    @TableField(value = "if_nodes")
    private Boolean ifNodes;


    @ApiModelProperty(value = "前置说明")
    @TableField(value = "before_notes", updateStrategy = FieldStrategy.IGNORED)
    private String beforeNotes;


    @ApiModelProperty(value = "后置说明")
    @TableField(value = "after_notes", updateStrategy = FieldStrategy.IGNORED)
    private String afterNotes;


    @ApiModelProperty(value = "数据类型(1 表单、2 附件、3 列表)")
    @TableField(value = "data_type")
    private Integer dataType;


    @ApiModelProperty(value = "编辑对应组件类型")
    @TableField(value = "component_type_edit")
    private String componentTypeEdit;


    @ApiModelProperty(value = "查看对应组件类型")
    @TableField(value = "component_type_view")
    private String componentTypeView;


    @ApiModelProperty(value = "Label宽度（px）")
    @TableField(value = "label_width")
    private String labelWidth;


    @ApiModelProperty(value = "Label位置（左对齐：left，右对齐：right，顶部对齐：top）")
    @TableField(value = "label_position")
    private String labelPosition;


    @ApiModelProperty(value = "最多条数")
    @TableField(value = "list_max_num", updateStrategy = FieldStrategy.IGNORED)
    private Integer listMaxNum;


    @ApiModelProperty(value = "最少条数")
    @TableField(value = "list_min_num", updateStrategy = FieldStrategy.IGNORED)
    private Integer listMinNum;


    @ApiModelProperty(value = "上级id（上级子项，用于嵌套）")
    @TableField(value = "parent_id", updateStrategy = FieldStrategy.IGNORED)
    private Long parentId;


    @ApiModelProperty(value = "自定义按钮")
    @TableField(value = "custom_button", updateStrategy = FieldStrategy.IGNORED)
    private String customButton;


    @ApiModelProperty(value = "自定义分页")
    @TableField(value = "custom_pagination", updateStrategy = FieldStrategy.IGNORED)
    private String customPagination;


    @ApiModelProperty(value = "是否显示（0否，1是）")
    @TableField(value = "is_show", updateStrategy = FieldStrategy.IGNORED)
    private Boolean isShow;


    @ApiModelProperty(value = "是否显示动态方法")
    @TableField(value = "is_show_function", updateStrategy = FieldStrategy.IGNORED)
    private String isShowFunction;


    @ApiModelProperty(value = "固定行配置，比如财务经费")
    @TableField(value = "fixed_row", updateStrategy = FieldStrategy.IGNORED)
    private String fixedRow;


    @ApiModelProperty(value = "编辑字段配置，数组")
    @TableField(value = "edit_field_row", updateStrategy = FieldStrategy.IGNORED)
    private String editFieldRow;


    @ApiModelProperty(value = "默认表单数据，表单存JSON对象")
    @TableField(value = "default_form_value", updateStrategy = FieldStrategy.IGNORED)
    private String defaultFormValue;


    @ApiModelProperty(value = "默认表格数据，数组存JSON数组")
    @TableField(value = "default_table_value", updateStrategy = FieldStrategy.IGNORED)
    private String defaultTableValue;


    @ApiModelProperty(value = "数据校验方法")
    @TableField(value = "data_validate_function", updateStrategy = FieldStrategy.IGNORED)
    private String dataValidateFunction;


    @ApiModelProperty(value = "子项类型")
    @TableField(value = "item_type", updateStrategy = FieldStrategy.IGNORED)
    private String itemType;


    @ApiModelProperty(value = "是否编辑（0否，1是）")
    @TableField(value = "is_edit", updateStrategy = FieldStrategy.IGNORED)
    private Boolean isEdit;


    @ApiModelProperty(value = "是否编辑动态方法")
    @TableField(value = "is_edit_function", updateStrategy = FieldStrategy.IGNORED)
    private String isEditFunction;


    @ApiModelProperty(value = "复制业务定义分页子项id")
    @TableField(exist = false)
    private Long copyBizDefTabItemId;


}
