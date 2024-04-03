package cn.topcheer.pms2.dao.pml;

import cn.topcheer.halberd.app.dao.jpa.GenericDao;
import cn.topcheer.pms2.api.pml.entity.FlAuthorityGrid;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by peanut.huang on 2020/3/18.
 */
@Repository
public class FlAuthorityGridDao extends GenericDao<FlAuthorityGrid> {

    public FlAuthorityGrid findByTypeAndGridtypeAndRoleid(String type,String gridtype,String roleids) {
        try{
            String[] roleArray = roleids.split("@");
            Query query = this.getQuery("select t from FlAuthorityGrid t where t.type = ?0 and t.gridtype = ?1 and t.roleid in (:roleid) ");
            query.setParameter(0,type);
            query.setParameter(1,gridtype);
            query.setParameterList("roleid",roleArray);
            return (FlAuthorityGrid)query.uniqueResult();
        }catch (Exception e) {
            return null;
        }
    }
}
