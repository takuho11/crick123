package cn.topcheer.halberd.app.biz.framework.common.data;

import cn.hutool.script.ScriptUtil;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.biz.common.utils.Util;
import cn.topcheer.halberd.biz.modules.system.entity.DataListConfig;
import cn.topcheer.halberd.biz.modules.system.entity.DictBiz;
import cn.topcheer.halberd.biz.modules.system.service.IDataListConfigService;
import cn.topcheer.halberd.biz.modules.system.service.IDictBizService;
import cn.topcheer.halberd.biz.modules.system.vo.ResDataListVO;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.biz.sys.SysUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommonDataService {

    @Autowired
    private IDataListConfigService dataListConfigService;
    @Autowired
    private IDictBizService dictBizService;

    @Autowired
    private DropdownListDataServer dropdownListDataServer;

    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private DBHelper dbHelper;



    public Result<List<ResDataListVO>> getDropdownList(String code, JSONObject params){
        DataListConfig dataListConfig=  dataListConfigService.getOne(new LambdaQueryWrapper<DataListConfig>().eq(DataListConfig::getCode,code).eq(DataListConfig::getEnabled,1));
        List<ResDataListVO> result=new ArrayList<>();
        if(dataListConfig!=null)
        {
            switch(dataListConfig.getSourceType())
            {
                case "dict":  //字典类型
                    List<DictBiz> list= dictBizService.getList(code);
                    if( Func.isNotEmpty(list))
                    {
                        result = list.stream().map(dictBiz -> {
                            ResDataListVO resDataListVO=new ResDataListVO();
                            resDataListVO.setName(dictBiz.getDictValue());
                            resDataListVO.setValue(dictBiz.getDictKey());
                            resDataListVO.setRemark(dictBiz.getRemark());
                            JSONObject jsonObject=new JSONObject();
                            jsonObject.put("code",dictBiz.getCode());
                            resDataListVO.setExtJson(jsonObject);
                            return resDataListVO;
                        }).collect(Collectors.toList());
                    }
                    break;
                case "sql":
                    if(Func.isNotEmpty(dataListConfig.getDropdownSql()))
                    {
                        //params.put("code",code);
                        List<Object> paramValues=new ArrayList<>();
                        List paramList = Util.regexMatchList(dataListConfig.getDropdownSql(),"\\$\\{(.*?)\\}");
                        if (code.equals("ds_bigBatchArr")){
                            String enterPriseId = sysUserService.getById(AuthUtil.getUserId()).getEnterPriseId();
                            params.put("enterpriseId", enterPriseId);
                        }
                        paramList.forEach((key)->{
                            paramValues.add(params.get(key));
                        });
                        List<Map> sqlResultlist=new ArrayList<>();
                        String sql = Util.regexMatchReplace(dataListConfig.getDropdownSql(),"(\\$\\{.*?\\})","?");
                        sqlResultlist = dbHelper.getRows (sql,paramValues.toArray());
                        if (sqlResultlist != null) {
                            result =
                                    sqlResultlist.stream()
                                            .map(
                                                    m -> {
                                                        ResDataListVO resDataListVO = new ResDataListVO();
                                                        resDataListVO.setName((String) m.get("name"));
                                                        resDataListVO.setValue(m.get("value"));
                                                        resDataListVO.setRemark((String) m.get("remark"));
                                                        JSONObject jsonObject = new JSONObject();
                                                        for (Object extKey : m.keySet()) {
                                                            if (!"name,value,remark,".contains(extKey.toString().toLowerCase() + ",")) {
                                                                jsonObject.put(extKey.toString(), m.get(extKey));
                                                            }
                                                        }
                                                        resDataListVO.setExtJson(jsonObject);
                                                        return resDataListVO;
                                                    })
                                            .collect(Collectors.toList());
                        }
                    }
                    break;
                case "interface":

                    break;
                case "script":
                    if(Func.isNotEmpty(dataListConfig.getDropdownSql())) {
                        CommonDataResult commonDataResult = new CommonDataResult(params);
                        ScriptEngine scriptEngine = ScriptUtil.getJsEngine();
                        scriptEngine.put("server", dropdownListDataServer);
                        scriptEngine.put("me", commonDataResult);
                        try {
                            scriptEngine.eval(dataListConfig.getDropdownSql());
                            result=commonDataResult.getResult().getData();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
        else { //未找到则从字典中获取
            List<DictBiz> list= dictBizService.getList(code);
            if(Func.isNotEmpty(list))
            {
                result = list.stream().map(dictBiz -> {
                    ResDataListVO resDataListVO=new ResDataListVO();
                    resDataListVO.setName(dictBiz.getDictValue());
                    resDataListVO.setValue(dictBiz.getDictKey());
                    resDataListVO.setRemark(dictBiz.getRemark());
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("code",dictBiz.getCode());
                    resDataListVO.setExtJson(jsonObject);
                    return resDataListVO;
                }).collect(Collectors.toList());
            }
        }
        return Result.data(result);

    }

    /**
     * 通用下拉获取(开放)
     */
    public Result<List<ResDataListVO>> getDropdownListOpen(String code, JSONObject params) {
        Result<List<ResDataListVO>> result = new Result<>();
        switch (code){
            //大批次数据ds_PlanProject，业务处室ds_Department
            case "ds_PlanProject":
            case "ds_Department":
                return this.getDropdownList(code, params);
            //单位类型ds_Unittype,一级归口单位ds_OneEnterprise,二级归口单位ds_TwoEnterprise
            case "ds_Unittype":
            case "ds_OneEnterprise":
            case "ds_TwoEnterprise":
                result = this.getDropdownList(code, params);
                break;
            default:
                result.setMsg("请登录后重试");
                return result;
        }
        if (result.getData() == null || result.getData().size() == 0){
            List<ResDataListVO> list = new ArrayList<>();
            ResDataListVO resDataListVO = new ResDataListVO();
            resDataListVO.setName("无");
            resDataListVO.setValue("无");
            resDataListVO.setExtJson(new JSONObject());
            list.add(resDataListVO);
            result.setData(list);
        }
        return result;
    }

}
