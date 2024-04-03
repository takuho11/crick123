package cn.topcheer.halberd.app.controller.minidev;


import cn.topcheer.halberd.app.api.minidev.dto.ExportDataDTO;
import cn.topcheer.halberd.app.api.minidev.dto.ExportTemplateDTO;
import com.alibaba.excel.EasyExcel;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * ExcelControler
 *
 * @author szs
 * @date 2023-10-18
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/excel")
@Validated
@Api(value = "EXCEL处理相关", tags = "EXCEL处理相关")
public class ExcelControler {


    /**
     * 导入数据
     *
     * @author szs
     * @date 2023-10-18
     */
    @PostMapping("/importData")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "导入数据", notes = "传入excel")
    public R<HashMap<String, Object>> importData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new ServiceException("请选择导入Excel文件");
        }

        // 读取Excel中的数据
        List<Object> read = ExcelUtil.read(file, 0, 0, null);


        // 返回对象
        HashMap<String, Object> object = new HashMap<>(2);
        object.put("head", read.get(0));
        object.put("data", read.subList(1, read.size()));

        return R.data(object);
    }


    /**
     * 导出数据
     *
     * @author szs
     * @date 2023-10-18
     */
    @PostMapping("/exportData")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "导出数据")
    public void exportData(@Valid @RequestBody ExportDataDTO dto, HttpServletResponse response) {

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding(Charsets.UTF_8.name());
            String fileName = URLEncoder.encode(dto.getFileName(), Charsets.UTF_8.name());
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), null).sheet(dto.getSheetName()).head(dto.getHeadList()).doWrite(dto.getDataList());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new ServiceException(throwable.getMessage());
        }

    }


    /**
     * 导出模板
     *
     * @author szs
     * @date 2023-10-18
     */
    @PostMapping("/exportTemplate")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "导出模板")
    public void exportTemplate(@Valid @RequestBody ExportTemplateDTO dto, HttpServletResponse response) {

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding(Charsets.UTF_8.name());
            String fileName = URLEncoder.encode(dto.getFileName(), Charsets.UTF_8.name());
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), null).sheet(dto.getSheetName()).head(dto.getHeadList()).doWrite(new ArrayList<>());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new ServiceException(throwable.getMessage());
        }

    }


}
