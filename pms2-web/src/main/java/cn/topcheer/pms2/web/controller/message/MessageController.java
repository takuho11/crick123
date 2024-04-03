package cn.topcheer.pms2.web.controller.message;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.message.PmsMessagerecord;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping({"/MessageController" })
public class MessageController extends GenericController<PmsMessagerecord> {

    @Autowired
    private MessageService messageService;
    /**
     * 发送短信
     */
    @PostMapping(value = "/sendMessage",produces="text/plain;charset=utf-8")
    public @ResponseBody
    ReturnToJs sendMessage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ReturnToJs returnToJs = null;
        messageService.sendMessage("","");
        return returnToJs;
    }
}
