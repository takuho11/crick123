package cn.topcheer.halberd.app.api.framework.entity.dolphin;


import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.dto.DbColumnDTO;
import cn.topcheer.halberd.app.api.framework.dto.db.dataintegration.DataIntegrationMySqlColumn;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("column_transfer_info")
// 列转换关系信息，在列信息上补充了源列信息
public class ColumnTransferInfo extends DbColumnDTO {

    // 来源字段的信息
    private String sourceName;
    private String sourceChineseName;
    private String sourceType;
    private String sourceComment;
    //来源字段长度
    private long sourceLength;
    // 数据精度
    private int sourceNumericPrecision;
    //来源字端小数位数
    private int sourceDecimalPlaces;

    // 关联的数据集成工作流id
    @Deprecated
    private String flowDefId;

    // 关联的数据集成任务id
    private String taskDefId;


    public static ColumnTransferInfo getColumnTransferInfo(DataIntegrationMySqlColumn dataIntegrationMySqlColumn, DataStoreType.Type type, boolean multiVarcharLength) {
        ColumnTransferInfo columnTransferInfo = new ColumnTransferInfo();
        columnTransferInfo.setSourceName(dataIntegrationMySqlColumn.getName());
        columnTransferInfo.setSourceChineseName(dataIntegrationMySqlColumn.getChineseName());
        columnTransferInfo.setSourceType(dataIntegrationMySqlColumn.getType());
        columnTransferInfo.setSourceLength(dataIntegrationMySqlColumn.getCharMaxLength());
        columnTransferInfo.setSourceNumericPrecision(dataIntegrationMySqlColumn.getNumericPrecision());
        columnTransferInfo.setSourceDecimalPlaces(dataIntegrationMySqlColumn.getNumericScale());
        columnTransferInfo.setSourceComment(dataIntegrationMySqlColumn.getComment());

        columnTransferInfo.setName(dataIntegrationMySqlColumn.getName());
        columnTransferInfo.setChineseName(dataIntegrationMySqlColumn.getChineseName());
        columnTransferInfo.setType(dataIntegrationMySqlColumn.getType());
        long charMaxLength = multiVarcharLength ?
                (dataIntegrationMySqlColumn.getCharMaxLength() * 4 > 60000 ? 60000 : dataIntegrationMySqlColumn.getCharMaxLength() * 4)
                : dataIntegrationMySqlColumn.getCharMaxLength();
        columnTransferInfo.setDestLength(charMaxLength);
        columnTransferInfo.setDestNumericPrecision(dataIntegrationMySqlColumn.getNumericPrecision());
        columnTransferInfo.setDestDecimalPlaces(dataIntegrationMySqlColumn.getNumericScale());
        columnTransferInfo.setComment(dataIntegrationMySqlColumn.getComment());

        columnTransferInfo.setPrimaryKey(dataIntegrationMySqlColumn.getPrimaryKey());
        columnTransferInfo.setKeyBtree(dataIntegrationMySqlColumn.getKeyBtree());
        columnTransferInfo.setNullable(dataIntegrationMySqlColumn.getNullable());
        return columnTransferInfo;

    }

}
