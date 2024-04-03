package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.PmsProjectexpense;
import cn.topcheer.pms2.dao.project.PmsProjectExpenseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="PmsProjectexpenseService")
public class PmsProjectExpenseService extends GenericService<PmsProjectexpense> {


	public PmsProjectExpenseDao getPmsProjectExpenseDao() {
		return (PmsProjectExpenseDao)this.getGenericDao();
	}

	@Autowired
	DBHelper dbHelper;
	@Autowired
	public void setPmsProjectExpenseDao(PmsProjectExpenseDao pmsProjectExpenseDao) {
		this.setGenericDao(pmsProjectExpenseDao);
	}

	public PmsProjectexpense findByxmyssbid(String infoid) {
		// TODO Auto-generated method stub
		String hql = "from cn.topcheer.pms2.api.project.entity.PmsProjectexpense p where p.xmsbsid = ?";
		List<PmsProjectexpense> lists = this.getPmsProjectExpenseDao().findByHql(hql, new Object[]{infoid});
		if(lists!=null&&lists.size()>0) {
			return lists.get(0);
		} else {
			return null;
		}
	}

	public PmsProjectexpense findexpenseBybaseinfoid(String pid) {
		// TODO Auto-generated method stub
		String hql = "from cn.topcheer.pms2.api.project.entity.PmsProjectexpense e where e.xmsbsid = ?";
		List<PmsProjectexpense> lists = this.getPmsProjectExpenseDao().findByHql(hql, new Object[]{pid});
		if(lists!=null&&lists.size()>0) {
			return lists.get(0);
		} else {
			return null;
		}
	}
	
	public List<PmsProjectexpense> findByJxid(String jxid) {
		// TODO Auto-generated method stub
		String hql = "select e from cn.topcheer.pms2.api.project.entity.PmsProjectexpense e left join e.pmsProjectbase base where base.id = ?";
		List<PmsProjectexpense> lists = this.getPmsProjectExpenseDao().findByHql(hql, new Object[]{jxid});
		return lists;
	}
	
	public List<Map> findByJxid2(String jxid){
		String sql="select * from  pms_projectexpense  where projectbaseid=? ";
		List<Map> list = dbHelper.getRows(sql, new Object[]{jxid});
		return list;
	}
    public PmsProjectexpense findexpenseBybaseid(String pid) {
        // TODO Auto-generated method stub
        String hql = "select e from cn.topcheer.pms2.api.project.entity.PmsProjectexpense e left join e.pmsProjectbase base where base.id = ?";
        List<PmsProjectexpense> lists = this.getPmsProjectExpenseDao().findByHql(hql, new Object[]{pid});
        if(lists!=null&&lists.size()>0) {
			return lists.get(0);
		} else {
			return null;
		}
    }
	
	
}
