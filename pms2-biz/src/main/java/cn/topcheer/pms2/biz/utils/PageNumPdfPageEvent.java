package cn.topcheer.pms2.biz.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;

/**
 * Created by 82608 on 2021/10/18.
 */
public class PageNumPdfPageEvent extends PdfPageEventHelper{

    // 总页数
    private int totalPageNums ;
    // 页码样式
    private String pageFormat ;

    public PageNumPdfPageEvent() {
        super();
    }

    /**
     * 传入总页码
     * @param pageNums 总页码
     */
    public PageNumPdfPageEvent(int pageNums, String pageFormat) {
        super();
        this.totalPageNums = pageNums ;
        this.pageFormat = pageFormat ;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        try {
            // PDF文档内容
            PdfContentByte pdfContent = writer.getDirectContent();

            pdfContent.saveState();
            pdfContent.beginText();

            int footerFontSize = 14 ;

            // 解决页码中文无法显示 或者 显示为乱码的问题
            // 但是必须引入jar包 itext-asian-5.2.0.jar
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
            Font fontDetail = new Font(baseFont, footerFontSize, Font.NORMAL);

            pdfContent.setFontAndSize(baseFont, footerFontSize);

            // 页脚的页码 展示
            String footerNum;
            if (Util.isEoN(totalPageNums)) {
                footerNum = String.format(pageFormat, writer.getPageNumber());
            } else {
                footerNum = String.format(pageFormat, writer.getPageNumber(),totalPageNums);
            }
            Phrase phrase = new Phrase(footerNum, fontDetail);

            // 页码的 横轴 坐标 居中
            float x = ( document.left() + document.right() ) / 2 ;
            // 页码的 纵轴 坐标
            float y = document.bottom(-10) ;
            // 添加文本内容，进行展示页码
            ColumnText.showTextAligned(pdfContent, Element.ALIGN_CENTER, phrase, x, y, 0);

            pdfContent.endText();
            pdfContent.restoreState();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
