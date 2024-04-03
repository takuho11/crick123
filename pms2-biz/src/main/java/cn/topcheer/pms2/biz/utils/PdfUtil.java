package cn.topcheer.pms2.biz.utils;


import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * Created by 82608 on 2021/11/3.
 */
public class PdfUtil {

    public static String addPageNum (String orgPdfPath, String outputPdfPath,String pageFormat) {

        try (
           // 输出文件 流
           FileOutputStream fos = new FileOutputStream(outputPdfPath);){

            // 读取 源PDF文件，进行一页一页复制，才能触发 添加页码的  页面监听事件
            PdfReader reader = new PdfReader(orgPdfPath);
            // 获取 源文件总页数
            int num = reader.getNumberOfPages();
            System.out.println("总页数：" + num);
            // 新建文档，默认A4大小
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            // 设置页面监听事件，必须在open方法前
            writer.setPageEvent(new PageNumPdfPageEvent(num,pageFormat));
            document.open();

            // PDF内容体
            PdfContentByte pdfContent = writer.getDirectContent();
            // 页面数是从1开始的
            for (int i = 1; i <= num; i++) {
                if (reader.getPageSize(i).getWidth()>reader.getPageSize(i).getHeight()) {
                    setPageSizeHen(document);
                }else{
                    setPageSizeShu(document);
                }
                document.newPage();
                // 设置空页码进行展示
                writer.setPageEmpty(false);
                PdfImportedPage page = writer.getImportedPage(reader, i);
                // 复制好的页面，添加到内容去，触发事件监听
                pdfContent.addTemplate(page, 0, 0);
            }

            document.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputPdfPath ;
    }

    /**
     * @Author j
     * @Description //TODO 设置页面横向
     * @Date  2021-11-3 18:04:35
     * @return
     **/
    public static  void setPageSizeHen(Document document){
        //横向
        Rectangle pageSize = new Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth());
        pageSize.rotate();
        document.setPageSize(pageSize);
    }
    /**
     * @Author j
     * @Description //TODO 设置页面竖向
     * @Date  2021-11-3 18:04:46
     * @return
     **/
    public static  void setPageSizeShu(Document document){
        //竖向
        Rectangle pageSize = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        pageSize.rotate();
        document.setPageSize(pageSize);
    }
    private static float getWidthScale(float width) {
        float scale = PageSize.A4.getWidth() / width;
        return scale;
    }

    private static float getHeightScale(float height) {
        float scale = PageSize.A4.getHeight() / height;
        return scale;
    }

    public static String addPageNumNew (String orgPdfPath, String outputPdfPath,String pageFormat) {

        try {
            // 读取 源PDF文件，进行一页一页复制，才能触发 添加页码的  页面监听事件
            PdfReader reader = new PdfReader(orgPdfPath);
            // 新建文档，默认A4大小
            Document document = new Document();
            // 输出文件 流
            FileOutputStream fos = new FileOutputStream(outputPdfPath);
            PdfWriter writer = PdfWriter.getInstance(document, fos);

            // 获取 源文件总页数
            int num = reader.getNumberOfPages();
            System.out.println("总页数：" + num);
            // 设置页面监听事件，必须在open方法前
            writer.setPageEvent(new PageNumPdfPageEvent(num,pageFormat));
            document.open();

            // PDF内容体
            PdfContentByte pdfContent = writer.getDirectContent();

            for (int i = 1; i <=num ; i++) {
                PdfImportedPage page = writer.getImportedPage(reader, i);
                float width = page.getWidth();
                float height = page.getHeight();
                if(height > width) {
                    //横向
                    document.setPageSize(PageSize.A4);
                    document.newPage();
                    //计算比例
                    float widthScale = getWidthScale(width);
                    float heightScale = getHeightScale(height);
                    // 设置空页码进行展示
                    writer.setPageEmpty(false);
                    //addTemplate方法中有6个float类型的参数，是通过二维图像仿射变换得到的
                    //cb.addTemplate(page, new AffineTransform(widthScale, 0, 0, heightScale,0,0));
                    //二维图像仿射变换:https://www.cnblogs.com/v2m_/archive/2013/05/09/3070187.html
                    pdfContent.addTemplate(page, widthScale, 0, 0, heightScale,0,0);
                } else {
                    //纵向
                    document.setPageSize(new Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth()));
                    document.newPage();
                    float widthScale = getWidthScale(height);
                    float heightScale = getHeightScale(width);
                    // 设置空页码进行展示
                    writer.setPageEmpty(false);
                    pdfContent.addTemplate(page, widthScale, 0, 0, heightScale,0,0);
                }
            }

            document.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputPdfPath ;
    }
}
