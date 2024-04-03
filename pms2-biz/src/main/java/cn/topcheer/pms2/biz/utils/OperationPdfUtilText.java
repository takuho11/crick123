package cn.topcheer.pms2.biz.utils;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

public class OperationPdfUtilText {

        /**
         * 生成新的PDF
         * @param pdfPath 要编辑的PDF路径
         * @param newPDFPath 生成新的PDF路径
         * @param imagePath　插入图片路径
         */
        //https://www.cnblogs.com/qinxu/p/8270310.html 参考地址
        public static void generatePDF(String pdfPath,String newPDFPath,String imagePath) {
            PdfReader reader = null;
            PdfStamper stamper = null;
            try {
                //创建一个pdf读入流
                reader = new PdfReader(pdfPath);
                //根据一个pdfreader创建一个pdfStamper.用来生成新的pdf.
                stamper = new PdfStamper(reader, new FileOutputStream(newPDFPath));
                //这个字体是itext-asian.jar中自带的 所以不用考虑操作系统环境问题.
                //BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                //form.addSubstitutionFont(com.lowagie.text.pdf.BaseFont.createFont("STSong-Light","UniGB-UCS2-H", com.lowagie.text.pdf.BaseFont.NOT_EMBEDDED));
                //baseFont不支持字体样式设定.但是font字体要求操作系统支持此字体会带来移植问题.
                /*Font font = new Font(bf, 10);
                font.setStyle(Font.BOLD);
                font.getBaseFont();*/
                PdfContentByte over;
                //页数是从1开始的
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    //获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上.
                    over = stamper.getOverContent(i);
                    //当前页的下层打印内容  按自己需求选择
                    //over = stamper.getUnderContent(i);
                    //用pdfreader获得当前页字典对象.包含了该页的一些数据.比如该页的坐标轴信息.
                    PdfDictionary p = reader.getPageN(i);
                    //拿到mediaBox 里面放着该页pdf的大小信息.
                    PdfObject po = p.get(new PdfName("MediaBox"));
                    //po是一个数组对象.里面包含了该页pdf的坐标轴范围.
                    PdfArray pa = (PdfArray) po;
                    /*//开始写入文本
                    over.beginText();
                    //设置字体和大小
                    //over.setFontAndSize(font.getBaseFont(), 90);
                    //设置字体颜色
                    over.setColorFill(new BaseColor(0, 110, 107, 100));
                    com.itextpdf.text.pdf.PdfGState gState = new PdfGState();
                    gState.setStrokeOpacity(0.1f);
                    over.setGState(gState);
                    //要输出的text          对齐方式          写的字        设置字体的输出位置  字体是否旋转
                    over.showTextAligned(0, "HELLO WORLD", 0, 100, 100);
                    over.endText();*/
                    //创建一个image对象.
                    Image image = Image.getInstance(imagePath);
                    //设置image对象的输出位置pa.getAsNumber(pa.size()-1).floatValue() 是该页pdf坐标轴的y轴的最大值  0, 0, 841.92, 595.32
                    image.setAbsolutePosition(450, pa.getAsNumber(pa.size()-1).floatValue()-130);// 左边距、底边距
                    //设置插入的图片大小
                    image.scaleToFit(120, 120);
                    over.addImage(image);
                    //画一个圈.
                    /*over.setRGBColorStroke(0xFF, 0x00, 0x00);
                    over.setLineWidth(5f);
                    over.ellipse(550, 450, 350, 550);*/
                    over.stroke();
                }
                stamper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static void setEwm(String pdfPath,String newPDFPath,String imagePath) {
        PdfReader reader = null;
        PdfStamper stamper = null;
        try {
            //创建一个pdf读入流
            reader = new PdfReader(pdfPath);
            //根据一个pdfreader创建一个pdfStamper.用来生成新的pdf.
            stamper = new PdfStamper(reader, new FileOutputStream(newPDFPath));
            AcroFields form = stamper.getAcroFields();
            //这个字体是itext-asian.jar中自带的 所以不用考虑操作系统环境问题.
            PdfContentByte over;
            //页数是从1开始的
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                //获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上.
                over = stamper.getOverContent(i);
                //当前页的下层打印内容  按自己需求选择
                //over = stamper.getUnderContent(i);
                //用pdfreader获得当前页字典对象.包含了该页的一些数据.比如该页的坐标轴信息.
                PdfDictionary p = reader.getPageN(i);
                //拿到mediaBox 里面放着该页pdf的大小信息.
                PdfObject po = p.get(new PdfName("MediaBox"));
                //po是一个数组对象.里面包含了该页pdf的坐标轴范围.
                PdfArray pa = (PdfArray) po;
                //创建一个image对象.
                Image image = Image.getInstance(imagePath);
                //Rectangle signRect =form
                /*Rectangle signRect = form.getFieldPositions(pdfPath).get(i).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();*/
                //设置image对象的输出位置pa.getAsNumber(pa.size()-1).floatValue() 是该页pdf坐标轴的y轴的最大值  0, 0, 841.92, 595.32
                //image.setAbsolutePosition(350, pa.getAsNumber(pa.size()-1).floatValue()-130);// 左边距、底边距

                PdfDocument document=over.getPdfDocument();
                float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
                float documentHeight = document.getPageSize().getHeight()-document.bottomMargin()-document.topMargin();//重新设置宽高
                //image.setAbsolutePosition(documentWidth,documentHeight);// 左边距、底边距
                //设置插入的图片大小
                //image.scaleToFit(120, 120);
                //System.out.print(pa.getAsNumber(0)+"第一"+pa.getAsNumber(1)+"第二"+pa.getAsNumber(2)+"第三"+pa.getAsNumber(3));
                image.setAbsolutePosition(pa.getAsNumber(2).floatValue()-75,pa.getAsNumber(3).floatValue()-75);// 左边距、底边距
                image.scaleToFit(60,60);
                over.addImage(image);
                over.stroke();
            }
            System.out.print("二维码插入成功");
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("二维码插入失败");
        }
    }
	
}
