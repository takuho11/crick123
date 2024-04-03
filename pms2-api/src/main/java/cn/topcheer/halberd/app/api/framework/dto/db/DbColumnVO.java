package cn.topcheer.halberd.app.api.framework.dto.db;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbColumnVO {

    @ExcelProperty("列名")
    private String columnName;

    @ExcelProperty("中文名")
    private String dcName;

    @ExcelProperty("其它备注")
    private String dcMemo;
}
