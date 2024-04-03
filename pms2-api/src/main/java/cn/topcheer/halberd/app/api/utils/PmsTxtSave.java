package cn.topcheer.halberd.app.api.utils;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by peanut.huang on 2018/6/14.
 */
@Component
public class PmsTxtSave {

    public void saveTxt(String projectbaseid, JSONObject jsonObject, String target, String method) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FileWriter fileWriter = null;
        String folder = "D:/PmsTxtAll_Folder/" + projectbaseid + "/";
        File tmpFolderPathFile = new File(Util.GetFileRealPath(folder));
        if (!tmpFolderPathFile.exists()) {
            tmpFolderPathFile.mkdirs();
        }
        try {
            fileWriter = new FileWriter(Util.GetFileRealPath(folder) + "单独保存" + projectbaseid + ".txt", true);

            fileWriter.write("\r\n\r\n\r\n\r\n\r\n\r\n\r\n" + "调用的对象:" + target + ";调用的方法" + method + jsonObject.toString() + "\r\n" + df.format(new Date()).toString() + "------------------------------------------------------");

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
