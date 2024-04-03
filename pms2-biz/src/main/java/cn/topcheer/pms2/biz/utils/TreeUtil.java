package cn.topcheer.pms2.biz.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeUtil {
    public List<Map> menuCommon;
    public List<Object> list = new ArrayList<Object>();

    /**
     * 根据 所有的层级集合获取 层级树结构
     * @param menu
     * @return
     */
    public List<Object> menuList(List<Map> menu){
        this.menuCommon = menu;
        for (Map x : menu) {
            if(Integer.parseInt(x.get("levelnum")+"")==1){
                x.put("data", menuChild(x.get("id")+""));
                list.add(x);
            }
        }
        return list;
    }

    /**
     * 查下一级
     * @param id
     * @return
     */
    public List<?> menuChild(String id){
        List<Object> lists = new ArrayList<Object>();
        for(Map a:menuCommon){
            if(!Util.isEoN(a.get("uplevelid"))&&a.get("uplevelid").equals(id)){
                a.put("data", menuChild(a.get("id")+""));
                lists.add(a);
            }
        }
        return lists;
    }
}