package cn.topcheer.halberd.app.biz.framework.common.data;

import cn.topcheer.halberd.biz.modules.system.vo.ResDataListVO;
import cn.topcheer.halberd.core.result.Result;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉数据处理
 */
@Service
public class DropdownListDataServer {
    /**
     * 获取年份方法
     * @param startYear
     * @param endYear
     * @return
     */
    public Result<List<ResDataListVO>> getYarArrayOne(Integer startYear, Integer endYear)
    {
        if(startYear==null)
        {
            startYear=2020;
        }
        if(Func.isEmpty(endYear))
        {
            endYear= new LocalDateTime().getYear()+1;
        }
        List<ResDataListVO> yearList=new ArrayList<>();
        for(int i=endYear;i>=startYear;i--)
        {
            ResDataListVO resDataListVO=new ResDataListVO();
            resDataListVO.setName(Func.toStr(i)+"年");
            resDataListVO.setValue(Func.toStr(i));
            yearList.add(resDataListVO);
        }
        return Result.data(yearList);
    }

}
