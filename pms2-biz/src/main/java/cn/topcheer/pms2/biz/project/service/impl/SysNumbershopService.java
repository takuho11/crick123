/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-3-23 10:18:54
 *
 */
package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.*;

import java.util.ArrayList;

/**
 * SysNumbershop 服务类
 */
@Service(value="SysNumbershopService")
public class SysNumbershopService extends GenericService<SysNumberShop> {

    public SysNumberShopDao getSysNumberShopDao() {
        return (SysNumberShopDao) this.getGenericDao();
    }

    @Autowired
    public void setSysNumberShopDao(SysNumberShopDao sysNumberShopDao) {

        this.setGenericDao(sysNumberShopDao);
    }

    private Session session;
    public SysNumberShopImp DoLock(String numberName)
    {

        return this.getSysNumberShopDao().DoLock(numberName);
    }

    public void DoUnlock(SysNumberShopImp numbershop)
    {
        this.getSysNumberShopDao().DoUnlock(numbershop);
    }

    public Integer GetSingleFlowID(String numbershopname)
    {
        //this.Peek(1, numbershopname);

        ArrayList al = this.GetMutiFlowID(1,numbershopname);
        //al = this.GetMutiFlowID(1, numbershopname);
        if (al.size()  > 0)
            return Integer.valueOf(al.get(0).toString());
        else
            return 0;
    }

    public synchronized ArrayList GetMutiFlowID(int NumLen,String numbershopname)
    {
        ArrayList rv;
        try
        {
            SysNumberShopImp numbershop= DoLock(numbershopname);
            rv = numbershop.getNumberCallBacks().GetNumber(NumLen, false);
            DoUnlock(numbershop);
            return rv;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;


    }

    /**
     * 添加前缀 控制长度
     * @param numbershopname
     * @param pre
     * @param len
     * @return
     */
    public String GetSingleFlowID(String numbershopname,String pre,int len)
    {
        StringBuilder ids = new StringBuilder(pre);
        ArrayList al = this.GetMutiFlowID(1,numbershopname);
        int num = 0;
        if (al.size()  > 0) {
            num = Integer.valueOf(al.get(0).toString());
        } else {
            num = 1;
        }
        int ll = String.valueOf(num).length();
        if (ll <= len) {
            for (int i = 0; i < len - ll; i ++) {
                ids.append("0");
            }
            ids.append(String.valueOf(num));
        }

        return ids.toString();
    }

    public void AddCallBack(Object Number,String numbershopname)
    {
        try
        {
            SysNumberShopImp numbershop= DoLock(numbershopname);
            numbershop.CallBack(Number);

            DoUnlock(numbershop);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void AddSkip(Object Number,String numbershopname)
    {
        try
        {
            SysNumberShopImp numbershop= DoLock(numbershopname);
            numbershop.Skip(Number);
            DoUnlock(numbershop);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public int PeekSingle(String numbershopname)
    {
        ArrayList al = Peek(1,numbershopname);
        if (al.size() > 0)
            return Integer.valueOf(al.get(0).toString());
        else
            return 0;
    }

    public ArrayList Peek(int NumLen,String numbershopname)
    {
        ArrayList rv;
        try
        {
            SysNumberShopImp numbershop= DoLock(numbershopname);
            rv = numbershop.getNumberCallBacks().GetNumber(NumLen, true);
            DoUnlock(numbershop);
            return rv;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

}
