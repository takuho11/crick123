/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-3-5 11:23:05
 *
 */
package cn.topcheer.pms2.biz.zjk;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.zjk.ZjkBatchCns;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.zjk.ZjkBatchCnsDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ZjkBatchCns 服务类
 */
@Service(value="ZjkBatchCnsService")
public class ZjkBatchCnsService extends GenericService<ZjkBatchCns> {

	public ZjkBatchCnsDao getZjkBatchCnsDao() {
		return (ZjkBatchCnsDao) this.getGenericDao();
	}

	@Autowired
	DBHelper dbHelper;

	@Autowired
	public void setZjkBatchCnsDao(ZjkBatchCnsDao zjkBatchCnsDao) {

		this.setGenericDao(zjkBatchCnsDao);
	}



	public List<Map> findCnsList(JSONObject jsonObject) {
		String sql = "";
		JSONArray array = new JSONArray();

		String insql = " and 1=1 ";
		if(jsonObject!=null&&!"".equals(jsonObject)){
			insql = " and t.titlename like ?";
			array.add("%"+jsonObject.get("filter")+"%");
		}

		sql = "select t.* from zjk_batch_cns t where 1=1 "+insql+" order by t.creatdate desc";


		List<Map> list = dbHelper.getRows(sql,array.toArray());

		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				List<Map> batchlist = this.dbHelper.getRows("select batchid as id,batchname as name " +
						"from zjk_batch_cnsbatch where cnsid = ?",new Object[]{list.get(i).get("id")+""});

				if(batchlist.size()>0){
					list.get(i).put("batchlist",batchlist);
				}
			}
		}

		return list;
	}


	public Boolean saveCns(JSONObject jsonObject) throws SQLException {
		String id = jsonObject.getString("id");
		ZjkBatchCns zjkBatchCns = this.findById(id);
		if(zjkBatchCns==null){
			zjkBatchCns = new ZjkBatchCns();
		}

		Util.ApplyObject(zjkBatchCns, jsonObject);
		zjkBatchCns.setCreatdate(new Date());
		this.merge(zjkBatchCns);

		JSONArray array = jsonObject.getJSONArray("batchlist");
		dbHelper.runSql("delete from zjk_batch_cnsbatch where cnsid = ?",new Object[]{id});

		if(array.size()>0){
			for (int i = 0; i < array.size(); i++) {
				dbHelper.runSql("insert into zjk_batch_cnsbatch (id,cnsid,batchid,batchname) values (?,?,?,?)",
						new Object[]{Util.NewGuid(),id,array.getJSONObject(i).get("id")+"",array.getJSONObject(i).get("name")+""});
			}
		}

		return true;
	}


	public Boolean deleteCns(String id) {
		//如果有批次应用了，无法删除
		ZjkBatchCns zjkBatchCns = this.findById(id);

		if(Util.isEoN(zjkBatchCns.getBatchliststring())){
			this.deleteById(id);
			return true;
		}else{
			return false;
		}

	}


	public List<Map> findCnsListByBatchid(String batchid) {
		String sql = "select t.* from zjk_batch_cns t " +
				"left join zjk_batch_cnsbatch e on e.cnsid = t.id " +
				"where e.batchid = ?";
		List<Map> list = dbHelper.getRows(sql,new Object[]{batchid});

		return list;
	}
	
}
