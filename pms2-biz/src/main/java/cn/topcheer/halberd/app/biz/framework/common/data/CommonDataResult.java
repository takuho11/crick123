package cn.topcheer.halberd.app.biz.framework.common.data;

import cn.topcheer.halberd.biz.modules.system.vo.ResDataListVO;
import cn.topcheer.halberd.core.result.Result;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CommonDataResult {

    private JSONObject params=new JSONObject();
    private Result<List<ResDataListVO>> result=Result.data(new ArrayList<>());
    public void data(Result<List<ResDataListVO>> listResult)
    {
        this.result= listResult;
    }

    public CommonDataResult(JSONObject params)
    {
        if(params!=null) {
            this.params = params;
        }
    }


    public Object getObject(String name)
    {
        return this.params.get(name);
    }

    public String getString(String name)
    {
        return this.params.getString(name);
    }

    public Long getLong(String name)
    {
        return this.params.getLong(name);
    }

    public Date getDate(String name)
    {
        return this.params.getDate(name);
    }

    public void put(String name,Object value)
    {
        this.params.put(name,value);
    }


}
