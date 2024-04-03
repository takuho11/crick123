package cn.topcheer.pms2.web.controller.bi;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.pms2.biz.bi.DataStatisticService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @Description 大屏数据展示
 * @Author shenxian
 * @Date 2024-03-12
*/

@Slf4j
@Controller
@RequestMapping({"/DataStatistic" })
public class DataStatisticController {

    @Autowired
    private DataStatisticService dataStatisticService;


    /**
     * 【企业地点】
     * @throws Exception
     */
    @RequestMapping("/findStatisticForQYLocation")
    public @ResponseBody R findStatisticForQYLocation() {
        return R.data(this.dataStatisticService.findStatisticForQYLocation());
    }

    /**
     * 【企业注册资本统计】
     * @throws Exception
     */
    @RequestMapping("/findStatisticForQYRegisterCaptial")
    public @ResponseBody R findStatisticForQYRegisterCaptial() {
        return R.data(this.dataStatisticService.findStatisticForQYRegisterCaptial());
    }

    /**
     * 【用户统计】
     * @throws Exception
     */
    @RequestMapping("/findStatisticForYH")
    public @ResponseBody R findStatisticForYH() {
        return R.data(this.dataStatisticService.findStatisticForYH());
    }

    /**
     * 【企业统计】
     * @throws Exception
     */
    @RequestMapping("/findStatisticForQY")
    public @ResponseBody R findStatisticForQY() {
        return R.data(this.dataStatisticService.findStatisticForQY());
    }

    /**
     * 【企业行业领域统计】
     * @throws Exception
     */
    @RequestMapping("/findStatisticForQYHYLY")
    public @ResponseBody R findStatisticForQYHYLY() {
        return R.data(this.dataStatisticService.findStatisticForQYHYLY());
    }

    /**
     * 【企业成立时间】
     * @throws Exception
     */
    @RequestMapping("/findStatisticForQYEstablishDate")
    public @ResponseBody R findStatisticForQYEstablishDate() {
        return R.data(this.dataStatisticService.findStatisticForQYEstablishDate());
    }

    /**
     * 专家分类数量查询---市---前9学科
     * @throws Exception
     */
    @RequestMapping("/findExpertXkslByCity")
    public @ResponseBody R findExpertXkslByCity(@RequestBody JSONObject json) {
        return R.data(JSONArray.fromObject(this.dataStatisticService.findExpertXkslByCity(json)));
    }

    /**
     * 专家统计学历
     */
    @RequestMapping("/findExpertEducation")
    public @ResponseBody R findExpertEducation() {
        return R.data(this.dataStatisticService.findExpertEducation());
    }

    /**
     * 专家统计职称
     */
    @RequestMapping("/findExpertTitle")
    public @ResponseBody R findExpertTitle() {
        return R.data(this.dataStatisticService.findExpertTitle());
    }

    /**
     * 专家统计人才称号
     */
    @RequestMapping("/findExpertRcch")
    public @ResponseBody R findExpertRcch() {
        return R.data(this.dataStatisticService.findExpertRcch());
    }

    /**
     * 专家统计人才称号
     */
    @RequestMapping("/findExpertRcchDetail")
    public @ResponseBody R findExpertRcchDetail(){
        return R.data(this.dataStatisticService.findExpertRcchDetail());
    }

    @RequestMapping("/findExpertTypeList")
    public @ResponseBody R findExpertTypeList(@RequestBody JSONObject jsonObject){
        return R.data(this.dataStatisticService.findExpertTypeList(jsonObject));
    }

    /**
     *  专家分类数量查询
     */
    @RequestMapping("/findExpertStatistic")
    public @ResponseBody R findExpertStatistic(@RequestBody JSONObject jsonObject){
        return R.data(this.dataStatisticService.findExpertStatistic(jsonObject.getString("province")));
    }

    /**
     * 【专家工作单位数统计】
     * @throws Exception
     */
    @RequestMapping("/findStatisticForExpertUnitType")
    public @ResponseBody R findStatisticForExpertUnitType() {
        return R.data(this.dataStatisticService.findStatisticForExpertUnitType());
    }
}
