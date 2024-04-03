package cn.topcheer.pms2.dao.project;

import java.util.ArrayList;
import java.util.List;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericDao;
import cn.topcheer.pms2.api.project.entity.SysNumberShop;
import cn.topcheer.pms2.api.project.entity.SysNumberShopImp;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class SysNumberShopDao extends GenericDao<SysNumberShop> {
    public void Refresh(Object SysNumberShop,LockMode lockmode)
    {
        this.getHibernateTemplate().refresh(SysNumberShop, lockmode);
    }

    public List<SysNumberShopImp.NumberCallBack> findbyname(String name) {
        // TODO Auto-generated method stub
        // String hql = "from SysNumberShop where name like '%" + name + "%'";


        List<SysNumberShop> list =(List<SysNumberShop>) this.findByProperty("name", name);
        ArrayList<SysNumberShopImp.NumberCallBack> rvList = new ArrayList<SysNumberShopImp.NumberCallBack>();
        if (list.size() > 0) {
            SysNumberShopImp ns =new SysNumberShopImp(list.get(0));

            SysNumberShopImp.NumberCallBack nc[] = ns.getNumberCallBacks().ToArray();
            for (int i = 0; i < nc.length; i++) {
                rvList.add(nc[i]);
            }
        }

        return rvList;
    }

    private Session session;
    public SysNumberShopImp DoLock(String numberName) {
        SysNumberShop SysNumberShop;
        session = this .getHibernateTemplate().getSessionFactory().openSession();
        List<SysNumberShop> list = (List<SysNumberShop>) this.findByProperty("name",
                numberName);
        if (list.size() == 0) {
            SysNumberShop = new SysNumberShop();
            SysNumberShop.setId(Util.NewGuid());
            SysNumberShop.setName(numberName);
            SysNumberShop.setNumbercallbackstr("1," + SysNumberShopImp.MaxNumber);
            session.beginTransaction();
            session.save (SysNumberShop);
            session.getTransaction().commit();
        } else
            SysNumberShop = list.get(0);
        session.beginTransaction();
        SysNumberShop=(SysNumberShop) session.load(SysNumberShop.class ,SysNumberShop.getId(), LockMode.UPGRADE);
        return new SysNumberShopImp(SysNumberShop);
    }

    public void DoUnlock(SysNumberShopImp sysNumberShop) {
        String s = "";
        if (sysNumberShop.getNumberCallBacks() != null) {
            SysNumberShopImp.NumberCallBack[] ncs = sysNumberShop.getNumberCallBacks().ToArray();
            for (int i = 0; i < ncs.length; i++)// foreach (NumberCallBack n in
            // mNumberCallBacks)
            {
                SysNumberShopImp.NumberCallBack nc = ncs[i];
                s += nc.getNumberStart() + "," + nc.getNumberEnd() + ";";
            }
            sysNumberShop.setNumbercallbackstr(s);
        } else
            sysNumberShop.setNumbercallbackstr("1," + sysNumberShop.MaxNumber);
        session.saveOrUpdate(sysNumberShop.getNumberShop());
        session.getTransaction().commit();
        session.close();
    }



    public int GetSingleFlowID(String numbershopname) {

        ArrayList al = this.GetMutiFlowID(1, numbershopname);
        al = this.GetMutiFlowID(1, numbershopname);
        if (al.size() > 0)
            return Integer.valueOf(al.get(0).toString());
        else
            return 0;
    }

    public ArrayList GetMutiFlowID(int NumLen, String numbershopname) {
        ArrayList rv;
        try {
            SysNumberShopImp SysNumberShop = DoLock(numbershopname);
            rv = SysNumberShop.getNumberCallBacks().GetNumber(NumLen, false);
            DoUnlock(SysNumberShop);
            return rv;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;

    }

    // 回收
    public void AddCallBack(Object Number, String numbershopname) {
        try {
            SysNumberShopImp SysNumberShop = DoLock(numbershopname);
            SysNumberShop.CallBack(Number);

            DoUnlock(SysNumberShop);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    // 1-5;10-20; 1 1;2;3;4;5;6 [1,2,3,4,5,6] 预留
    public void AddSkip(Object Number, String numbershopname) {
        try {
            SysNumberShopImp SysNumberShop = DoLock(numbershopname);
            SysNumberShop.Skip(Number);
            DoUnlock(SysNumberShop);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public int PeekSingle(String numbershopname) {
        ArrayList al = Peek(1, numbershopname);
        if (al.size() > 0)
            return Integer.valueOf(al.get(0).toString());
        else
            return 0;
    }

    public ArrayList Peek(int NumLen, String numbershopname) {
        ArrayList rv;
        try {
            SysNumberShopImp SysNumberShop = DoLock(numbershopname);
            rv = SysNumberShop.getNumberCallBacks().GetNumber(NumLen, true);
            Boolean b=true;
            while(b)
            {
                Thread.sleep(20);
            }
            //DoUnlock(SysNumberShop);
            return rv;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
}
