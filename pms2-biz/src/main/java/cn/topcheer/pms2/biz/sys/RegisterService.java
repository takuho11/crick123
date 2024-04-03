package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RegisterService extends GenericService<SysUser> {
    @Autowired
    private DBHelper dbHelper;

    /**
     * 判断当前注册用户
     * 手机验证码和邮箱验证码是否通过
     *
     * @param json
     * @return
     */
    public JSONObject judgeValidCode(JSONObject json) throws ParseException {
        JSONObject resJson = judgeValidCode(json, "mobile,email");
        return resJson;
    }

    public JSONObject judgeValidCode(JSONObject json, String type) throws ParseException {
        JSONObject resJson = new JSONObject();
        if (type.contains("mobile")) {
            String judgeMobileCode = judgeMobileCode(json);
            if (!Util.isEoN(judgeMobileCode)) {
                resJson.put("flag", false);
                resJson.put("reason", judgeMobileCode);
                return resJson;
            }
        }
        if (type.contains("email")) {
            String judgeEmailCode = judgeEmailCode(json);
            if (!Util.isEoN(judgeEmailCode)) {
                resJson.put("flag", false);
                resJson.put("reason", judgeEmailCode);
                return resJson;
            }
        }
        resJson.put("flag", true);
        resJson.put("reason", "验证通过");
        return resJson;
    }

    public String judgeMobileCode(JSONObject json) throws ParseException {
        Date date = DateUtils.addMinutes(new Date(), -30);
        String mobile = json.getString("mobile");
        String validCode = json.getString("validCode");
        String sql = "select * from PMS_VALIDCODERECORD where mobile = ? order by createdate DESC limit 1";
        List<Map> rows = dbHelper.getRows(sql, new Object[]{mobile});
        if (rows.size() == 0) {
            return "请检查：手机验证码是否正确.";
        }
        String mobiledate = (String) rows.get(0).get("createdate");
        Date mobiledateD = DateUtils.parseDate(mobiledate.substring(0, mobiledate.length() - 2), "yyyy-MM-dd HH:mm:ss");
        if (mobiledateD.getTime() < date.getTime()) {
            return "请检查：手机验证码已超期。";
        } else {
            String content = (String) rows.get(0).get("content");
            if (!content.contains("[" + validCode + "]")) {
                return "请检查：手机验证码是否正确。";
            }
        }
        return "";
    }

    public String judgeEmailCode(JSONObject json) throws ParseException {
        Date date = DateUtils.addMinutes(new Date(), -30);
        String email = json.getString("email");
        String validmailcode = json.getString("validemailcode");
        String sql = "select * from PMS_VALMAILCODERECORD where email = ? order by createdate DESC limit 1";
        List<Map> rows = dbHelper.getRows(sql, new Object[]{email});
        if (rows.size() == 0) {
            return "请检查：邮箱验证码是否正确.";
        }
        String emaildate = (String) rows.get(0).get("createdate");
        Date emaildateD = DateUtils.parseDate(emaildate.substring(0, emaildate.length() - 2), "yyyy-MM-dd HH:mm:ss");
        if (emaildateD.getTime() < date.getTime()) {
            return "请检查：邮箱验证码已超期。";
        } else {
            String content = (String) rows.get(0).get("content");
            if (!content.contains("[" + validmailcode + "]")) {
                return "请检查：邮箱验证码是否正确。";
            }
        }
        return "";
    }
}
