package cn.topcheer.pms2.biz.utils;

import com.suwell.ofd.custom.agent.ConvertException;
import com.suwell.ofd.custom.agent.HTTPAgent;
import com.suwell.ofd.custom.wrapper.PackException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 82608 on 2021/10/18.
 */
@Slf4j
public class Word2PdfSuwellUtil {

    private static String haurl = "http://112.94.68.114/";//tomcat下加convert-issuer

    public static boolean doc2pdf(String inPath, String outPath) {
        log.info("数科转换");
        HTTPAgent ha = new HTTPAgent(haurl);
        try {
            File in = new File(inPath);
            OutputStream out=new FileOutputStream(new File(outPath));
            ha.OFDToPDF(in, out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ConvertException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                ha.close();//注意：一定要记得关闭 ha
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean mergepdf(List<String> filesInFolder, String outPath) {
        log.info("数科拼接");
        HTTPAgent ha = new HTTPAgent(haurl);
        List<File> inList =new ArrayList<File>();
        OutputStream out=null;
        try {
            for (int i = 0; i < filesInFolder.size(); i++) {
                inList.add(new File(filesInFolder.get(i)));
            }
            out=new FileOutputStream(new File(outPath));
            ha.OFDToPDF(inList, out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ConvertException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                ha.close();//注意：一定要记得关闭 ha
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
