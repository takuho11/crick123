package cn.topcheer.pms2.web.controller.sys;

import cn.topcheer.halberd.biz.modules.auth.utils.TokenUtil;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.PmsAcceptanceSave;
import cn.topcheer.pms2.biz.sys.ZwwRemoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/zwwRemote")
@Api(value = "政务网",tags = "政务网相关方法")
public class ZwwRemoteController {
    @Autowired
    private ZwwRemoteService zwwRemoteService;


    /**
     * 调用政务网受理保存
     * @param type 类型
     * @return
     */
    @PostMapping("/saveAcceptance")
    public ReturnToJs saveAcceptance(String type){
        return zwwRemoteService.saveAcceptance(type,"");
    }


    /**
     * 测试md5生成
     * @return
     */
    @PostMapping("/getMD5")
    public ReturnToJs getMD5(String type){
        Map md5 = zwwRemoteService.getMD5(type);
        ReturnToJs<Object> returnToJs = new ReturnToJs<>();
        returnToJs.setData(md5);
        return returnToJs;
    }

    /**
     * 政务网接口调用
     */
    @PostMapping("/zwwRemote")
    @ApiOperation(value ="政务网接口调用",notes = "政务网接口调用")
    @ResponseBody
    public ReturnToJs zwwRemote(@RequestParam String type,@RequestParam String json){
        return zwwRemoteService.zwwRemote(type,json,"");
    }
}
