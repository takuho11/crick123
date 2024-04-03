package cn.topcheer.pms2.biz.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    private static final int  BUFFER_SIZE = 10 * 1024;

    /**
     * 将文件夹转为zip
     * @param srcDir 源目标文件夹
     * @param out 输出流
     * @param keepDirStruct  是否保留源文件夹的结构
     * @throws RuntimeException
     */
    public static void toZip(String srcDir , OutputStream out ,boolean keepDirStruct)
                                                            throws RuntimeException{
        ZipOutputStream zos =null;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile,zos,sourceFile.getName(),keepDirStruct);
        }catch (Exception e){
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally {
            if (zos!=null){
                try {
                    zos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static void compress(File sourceFile , ZipOutputStream zos ,String filename , boolean keepDirStruct) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()){
            //文件
            zos.putNextEntry(new ZipEntry(filename));

            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf))!=-1){
                zos.write(buf,0,len);
            }
            zos.closeEntry();
            in.close();
        }else{
            //文件夹
            File[] files = sourceFile.listFiles();
            if (null == files || 0==files.length){
                //空文件夹
                if (keepDirStruct){
                    //保留结构，空文件夹一并复制
                    zos.putNextEntry(new ZipEntry(filename+"/"));
                }
                zos.closeEntry();
            }else{
                //非空文件夹
                for (File file : files) {
                    if (keepDirStruct){
                        compress(file,zos,filename+"/"+file.getName(),true);
                    }else{
                        compress(file,zos,file.getName(),false);
                    }
                }
            }
        }
    }
}
