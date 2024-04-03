package cn.topcheer.pms2.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.topcheer.halberd.core.mq.CommonMqObject;
import cn.topcheer.halberd.core.mq.MqUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import liquibase.pro.packaged.J;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.topcheer.pms2.api.po.User;
import cn.topcheer.pms2.dao.jpa.UserDao;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/test")
@Api(value = "测试", tags = "测试")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {
    
    @Autowired
    UserDao userDao;
    @PostMapping("/jpa-list-all-users")
    public List<User> listAllUsers(){
            List<User> findAll = userDao.findAll();
            return findAll;
    }

    @PostMapping("/jpa-add-user")
    public void  addUser(@RequestBody User user) {
           user.setUpdateTime(new Date());
           System.out.println("test");
           userDao.save(user);
            
    }

    @PostMapping("/jpa-delete-user")
    public void  addUser(  String userId) {
           userDao.deleteById(userId);
            
    }


    @PostMapping(value = "push")
    public void push(HttpServletResponse response) throws IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        String buf="上证报中国证券网讯(记者 魏倩)记者10日获悉,微信支付已经完成与中国移动、中国电信、中国联通等三大通讯运营商旗下支付平台条码支付的互联互通。现在,用户通过中国移动“和";
        JSONObject jsonObject;
        for(int i=0;i<10;i++)
        {
            String str=buf.substring(i,i+1);
             jsonObject=new JSONObject();
            jsonObject.put("sentence_id",i+1);
            jsonObject.put("is_end",false);
            jsonObject.put("content",str);
            response.getWriter().write("data:"+jsonObject.toJSONString()+"\n\n");
            response.getWriter().flush();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        jsonObject = new JSONObject();
        jsonObject.put("sentence_id",buf.length());
        jsonObject.put("is_end",false);
        jsonObject.put("content","。");
        response.getWriter().write("data:"+jsonObject.toJSONString()+"\n\n");
        response.getWriter().flush();

    }



    @PostMapping("/mqTest")
    public void  mqTest(Integer num) {
        if(num!=null) {
            Long pc=System.currentTimeMillis();
            for (int i = 0; i < num; i=i+4)
            {
                CommonMqObject<JSONObject> commonMqObject=new CommonMqObject<>();
                JSONObject json=new JSONObject();
                json.put("timestamp",System.currentTimeMillis());
                json.put("i",i);
                json.put("pc",pc);
                commonMqObject.setData(json);
                MqUtil.sendCommonMq(commonMqObject);

                json=new JSONObject();
                json.put("timestamp",System.currentTimeMillis());
                json.put("i",i+1);
                json.put("pc",pc);
                commonMqObject.setData(json);
                MqUtil.sendCommonMq(commonMqObject);

                json=new JSONObject();
                json.put("timestamp",System.currentTimeMillis());
                json.put("i",i+2);
                json.put("pc",pc);
                commonMqObject.setData(json);
                MqUtil.sendCommonMq(commonMqObject);


                json=new JSONObject();
                json.put("timestamp",System.currentTimeMillis());
                json.put("i",i+3);
                json.put("pc",pc);
                commonMqObject.setData(json);
                MqUtil.sendCommonMq(commonMqObject);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
