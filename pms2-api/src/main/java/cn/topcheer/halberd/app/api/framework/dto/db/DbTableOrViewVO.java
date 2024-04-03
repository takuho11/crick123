package cn.topcheer.halberd.app.api.framework.dto.db;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbTableOrViewVO {

    @ExcelProperty("表或视图名")
    private String name;

    @ExcelProperty("数据分层")
    private String dataLevel;

    @ExcelProperty("业务领域")
    private String businessDomain;

    @ExcelProperty("其它说明")
    private String memo;

    private List<DbColumnVO> columnList;
}
