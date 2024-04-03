package cn.topcheer.halberd.app.common.converter;


import com.alibaba.excel.converters.booleanconverter.BooleanStringConverter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class ChineseBooleanStringConverter extends BooleanStringConverter {

    private static List<String> TRUE_LABELS = Arrays.asList("1", "active", "true", "enabled", "yes", "y", "是", "能", "可以", "行", "好");
    private static List<String> FALSE_LABELS = Arrays.asList("0", "inactive", "false", "disabled", "no", "n", "否", "不能", "不可以", "不行", "不", "差");

    @Override
    public Boolean convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) {
        String str = StringUtils.trimToEmpty(cellData.getStringValue()).toLowerCase();
        if (TRUE_LABELS.contains(str)){
            return Boolean.TRUE;
        } else if(FALSE_LABELS.contains(str)){
            return Boolean.FALSE;
        }
        return null;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Boolean value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) {
        String str = "";
        if(Boolean.TRUE.equals(value)){
            str = "是";
        } else if (Boolean.FALSE.equals(value)) {
            str = "否";
        }
        return new WriteCellData<>(str);
    }
}
