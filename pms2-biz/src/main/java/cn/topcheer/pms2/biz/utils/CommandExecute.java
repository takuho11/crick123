package cn.topcheer.pms2.biz.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 82608 on 2021/6/11.
 */
@Slf4j
public class CommandExecute {

//    public static void main(String[] args) {
//        CommandExecute obj = new CommandExecute();
//        String domainName = "www.baidu.com";
//        //in mac oxs
//        String command = "ping " + domainName;
//        //in windows
//        //String command = "ping -n 3 " + domainName;
//        String output = obj.executeCommand(command);
//        System.out.println(output);
//    }

    public static String executeCommand(String command) {
        StringBuffer output = new StringBuffer();
        Process p;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            inputStreamReader = new InputStreamReader(p.getInputStream(), "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            //p.destroy();//这个通常不须要
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(inputStreamReader);
        }
        System.out.println(output.toString());
        return output.toString();

    }
    /**
     * 执行command指令
     * @param command
     * @return
     */
    public static boolean executeLibreOfficeCommand(String command) {
        log.info("开始进行转化.......");
        Process process;// Process可以控制该子进程的执行或获取该子进程的信息
        try {
            log.debug("convertOffice2PDF cmd : {}"+command );
            process = Runtime.getRuntime().exec(command);// exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
            // 下面两个可以获取输入输出流
//            InputStream errorStream = process.getErrorStream();
//            InputStream inputStream = process.getInputStream();
        } catch (IOException e) {
            log.error(" convertOffice2PDF {} error", e);
            return false;
        }
        int exitStatus = 0;
        try {
            exitStatus = process.waitFor();// 等待子进程完成再往下执行，返回值是子线程执行完毕的返回值,返回0表示正常结束
            // 第二种接受返回值的方法
            int i = process.exitValue(); // 接收执行完毕的返回值
            log.debug("i----" + i);
        } catch (InterruptedException e) {
            log.error("InterruptedException  convertOffice2PDF {}"+command, e);
            return false;
        }
        if (exitStatus != 0) {
            log.error("convertOffice2PDF cmd exitStatus {}"+exitStatus);
        } else {
            log.debug("convertOffice2PDF cmd exitStatus {}"+exitStatus);
        }
        process.destroy(); // 销毁子进程
        log.info("转化结束.......");
        return true;
    }

    /**
     * 利用libreOffice将office文档转换成pdf
     * @param inputFile  目标文件地址
     * @param pdfFile    输出文件夹
     * @return
     */
    public static boolean convertOffice2PDF(String inputFile, String pdfFile){
        long start = System.currentTimeMillis();
        String command;
        boolean flag;
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows")) {
            //command = "cmd /c start soffice --headless --invisible --convert-to pdf:writer_pdf_Export " + inputFile + " --outdir " + pdfFile;
            command = "soffice --convert-to pdf  -outdir " + pdfFile + " " + inputFile;
        }else {
            command = "libreoffice --headless --invisible --convert-to pdf:writer_pdf_Export " + inputFile + " --outdir " + pdfFile;
        }
        flag = executeLibreOfficeCommand(command);
        long end = System.currentTimeMillis();
        log.debug("用时:{"+(end - start)+"} ms");
        return flag;
    }

}
