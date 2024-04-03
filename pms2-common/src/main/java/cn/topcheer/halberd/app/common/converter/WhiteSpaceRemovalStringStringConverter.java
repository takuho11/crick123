package cn.topcheer.halberd.app.common.converter;

import com.alibaba.excel.converters.string.StringStringConverter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import org.apache.commons.lang3.StringUtils;

public class WhiteSpaceRemovalStringStringConverter extends StringStringConverter {

    @Override
    public String convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {
        String result = super.convertToJavaData(cellData, contentProperty, globalConfiguration);
        if(StringUtils.isBlank(result)){
            return null;
        }
        return StringUtils.trim(result);
    }
}
