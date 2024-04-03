package cn.topcheer.pms2.biz.utils;

import com.aspose.words.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 82608 on 2021/10/18.
 */
@Slf4j
public class Word2PdfAsposeUtil {

    
    public static boolean getLicense() {
    boolean result = false;
    InputStream is = null;
    try {
        Resource resource = new ClassPathResource("license.xml");
        is = resource.getInputStream();
        //InputStream is = Word2PdfAsposeUtil.class.getClassLoader().getResourceAsStream("license.xml"); // license.xml应放在..\WebRoot\WEB-INF\classes路径下
        License aposeLic = new License();
        //FontSettings.setFontsFolder("/usr/share/fonts/chinese", false);//设置aspose取字体从哪个文件夹下取
        aposeLic.setLicense(is);
        result = true;
    } catch (Exception e) {
        e.printStackTrace();
    }finally {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return result;
}

    public static boolean doc2pdf(String inPath, String outPath) {
//        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
//            return false;
//        }
        log.info("aspose转换");
        FileOutputStream os = null;
        try {
            long old = System.currentTimeMillis();
            /*搞个新的*/
            String osName = System.getProperty("os.name");
            if (osName.contains("Windows")) {//
                FontSettings.getDefaultInstance().setFontsFolders(new String[]{"C:/Windows/Fonts","C:/Users/82608/AppData/Local/Microsoft/Windows/Fonts"}, true);//设置aspose取字体从哪个文件夹下取
                //FontSettings.setFontsFolder("C:/Windows/Fonts",true);
            } else {
                FontSettings.getDefaultInstance().setFontsFolders(new String[]{"/usr/share/fonts/","/usr/share/fonts/winfont"}, true);//设置aspose取字体从哪个文件夹下取
            }
            File file = new File(outPath);
            os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            Document document = new Document();//新建一个空白pdf文档
            document.removeAllChildren();
            document.appendDocument(doc, ImportFormatMode.KEEP_SOURCE_FORMATTING);//保留样式
            document.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            /*--原来的生成，可能有样式问题
            File file = new File(outPath); // 新建一个空白pdf文档
            os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,*/
            // EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            log.info("pdf转换成功，共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /*public static void main(String[] arg){
        String docPath = "D:\\PdfTmpFile\\tempdocCg.doc";
        String pdfPath = "D:\\PdfTmpFile\\交通态势日报-2021-01-10.pdf";
        Word2PdfAsposeUtil.doc2pdf(docPath,pdfPath);
    }*/
}
