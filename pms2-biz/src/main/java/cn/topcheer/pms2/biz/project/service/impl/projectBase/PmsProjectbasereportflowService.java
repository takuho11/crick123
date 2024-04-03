package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericService;

import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbasereportflow;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbasereportflowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by boboo on 2017/12/11.
 */
@Service(value="PmsProjectbasereportflowService")
public class PmsProjectbasereportflowService extends GenericService<PmsProjectbasereportflow> {

    public PmsProjectbasereportflowDao getProjectbaseReportFlowDao() {
        return (PmsProjectbasereportflowDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsProjectbasereportflowDao(PmsProjectbasereportflowDao PmsProjectbasereportflowDao) {

        this.setGenericDao(PmsProjectbasereportflowDao);
    }

    @Autowired
	DBHelper dbHelper;

    // 保存或者更新 业务流程关系
    // 如果是下面的管理部门，则不设计有其他管理部门，如果是到了科技厅，则考虑到部门处理
    // 部门的ID事先配置好传入的？？
    // 如何判断下一个状态的处理角色所在的单位
    // 部门字段预留

    /**
     *
     * @param pid
     * @param usereid
     * @param editEid
     * @param treeIndex
     * @param btype
     */
    public void saveOrupdateBussiness(String pid,String usereid,String editEid,String treeIndex,String btype){
        List<PmsProjectbasereportflow> pprfs = this.findByHql("from PmsProjectbasereportflow ppr where ppr.bussinessid=? and " +
                " ppr.bussinessType=?", pid,btype);
        PmsProjectbasereportflow pprf =null;
        String eids = usereid;
        if(pprfs!=null&&pprfs.size()>0) {
            pprf = pprfs.get(0);
        }else {
            pprf = new PmsProjectbasereportflow();
            pprf.setId(Util.NewGuid());
        }
        if(!Util.isEoN(editEid)){
            eids = "";
            eids = usereid+"@"+editEid;
            if(Util.isEoN(treeIndex)||"1".equals(treeIndex)){
                String getUpEid  = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{editEid});
                while(!Util.isEoN(getUpEid)){
                    eids += "@"+getUpEid;
                    getUpEid = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{getUpEid});
                }
            }else{
                String getUpEid  = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{editEid});
                while(!Util.isEoN(getUpEid)){
                    eids += "@"+getUpEid;
                    getUpEid = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{getUpEid});
                }
            }
        }else{
            if(Util.isEoN(treeIndex)||"1".equals(treeIndex)){
                String getUpEid  = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{usereid});
                while(!Util.isEoN(getUpEid)){
                    eids += "@"+getUpEid;
                    getUpEid = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{getUpEid});
                }
            }else{
                String getUpEid  = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{usereid});
                while(!Util.isEoN(getUpEid)){
                    eids += "@"+getUpEid;
                    getUpEid = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{getUpEid});
                }
            }
        }
        pprf.setBussinessid(pid);
        pprf.setBussinesstype(btype);
        if(eids!=null&&eids.startsWith("@")) {
            pprf.setReportenterpriseflow(eids.substring(1, eids.length()));
        }else {
            pprf.setReportenterpriseflow(eids);
        }
        this.getProjectbaseReportFlowDao().merge(pprf);
    }
    /**
     * 保存流程环节层级
     * @param 
     * @param usereid 当前单位
     * @param editEid
     * @param treeIndex
     */
    public void saveOrupdateBussiness_role(String type,String id,String usereid,String editEid,String treeIndex){
    	if("".equals(type)||"".equals(usereid)||"".equals(id)){
    		//没有对应的表，或者ID，或者企业id，不做处理
    		
    	}else{
    		//专家库的，只需要单位id和流程节点，不需要处室和主管id
    		if("zjk_ryjbxx".equals(type)){
    			//更新企业id
    			String sql_run2="update "+type+" t set t.enterpriseorusersid=? where t.id=?";
    			try {
    				dbHelper.runSql(sql_run2,new Object[]{usereid,id});
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			String eids = usereid;
    			// 省外：科技厅;省内：本单位-二级-一级-科技厅(专家信息入库)
    			String sql="select region from "+type+" where id =?";
    			String region=dbHelper.getOnlyStringValue(sql, new Object[]{id});
    			if("1".equals(region)){//省内
    				if(Util.isEoN(treeIndex)||"1".equals(treeIndex)){
    					String getUpEid  = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{usereid});
    					while(!Util.isEoN(getUpEid)){
    						eids += "@"+getUpEid;
    						getUpEid = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{getUpEid});
    					}
    				}else{
    					String getUpEid  = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{usereid});
    					while(!Util.isEoN(getUpEid)){
    						eids += "@"+getUpEid;
    						getUpEid = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{getUpEid});
    					}
    				}
    				
    				if(eids!=null&&eids.startsWith("@")) {
    					String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    					try {
    						dbHelper.runSql(sql_up,new Object[]{eids.substring(1, eids.length()),id});
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else {
    					String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    					try {
    						dbHelper.runSql(sql_up,new Object[]{eids,id});
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}
    			}else{
    				eids="98C57F262CB74E12AAC31C9D37533A17";
    				String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    				try {
    					dbHelper.runSql(sql_up,new Object[]{eids,id});
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    		}else{
    			//更新处室和主管id到对应主表
    			String sql_bid="select t.belonglabid from "+type+" t where t.id =? ";
    			String bid=dbHelper.getOnlyStringValue(sql_bid, new Object[]{id});
    			if(bid==null||"".equals(bid)){
    				String sql_run1="update "+type+" t set t.belonglabid=(select b.belonglabid from pms_projectbase b where b.id =t.projectbaseid ) where t.id =? ";
    				if("pms_contractchange".equals(type)){
    					sql_run1="update "+type+" t set t.belonglabid=(select b.belonglabid from pms_projectbase b left join crt_contract_jbxx j on j.projectbaseid=b.id where j.id =t.contractid ) where t.id =? ";
    				}
    				try {
    					dbHelper.runSql(sql_run1,new Object[]{id});
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    			String sql_xmzgid="select t.xmzgid from "+type+" t where t.id =? ";
    			String xmzgid=dbHelper.getOnlyStringValue(sql_xmzgid, new Object[]{id});
    			if(xmzgid==null||"".equals(xmzgid)){
    				String sql_run1="update "+type+" t set t.xmzgid=(select b.xmzgid from pms_projectbase b where b.id =t.projectbaseid ) where t.id =? ";
    				if("pms_contractchange".equals(type)){
    					sql_run1="update "+type+" t set t.xmzgid=(select b.xmzgid from pms_projectbase b left join crt_contract_jbxx j on j.projectbaseid=b.id where j.id =t.contractid ) where t.id =? ";
    				}
    				try {
    					dbHelper.runSql(sql_run1,new Object[]{id});
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    			//更新企业id
    			String sql_run2="update "+type+" t set t.enterpriseorusersid=? where t.id=?";
    			try {
    				dbHelper.runSql(sql_run2,new Object[]{usereid,id});
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    			String eids = usereid;
    			//合同之后的流程目前不存在这自己选择归口的情况?
    			if("pms_acceptapplication".equals(type)){
    				//（公益：本单位-二级或者一级;其他：本单位-二级或者一级-科技厅）验收申请
    				if(Util.isEoN(treeIndex)||"1".equals(treeIndex)){
    					String getUpEid  = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{usereid});
    					while(!Util.isEoN(getUpEid)){
    						eids += "@"+getUpEid;
    						//判断当前归口是否有高管，如果有则跳出循环，然后再加上科技厅
    						String sql1="select t.roleid from sys_identity t where t.purvieworganizeid =? and t.roleid='2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487'";
    						String roleid=dbHelper.getOnlyStringValue(sql1, new Object[]{getUpEid});
    						if(!"".equals(roleid)&&roleid!=null){
    							eids+="@98C57F262CB74E12AAC31C9D37533A17";
    							break;//跳出循环
    						}
    						getUpEid = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{getUpEid});
    					}
    				}else{
    					String getUpEid  = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{usereid});
    					while(!Util.isEoN(getUpEid)){
    						eids += "@"+getUpEid;
    						//判断当前归口是否有高管，如果有则跳出循环，然后再加上科技厅
    						String sql1="select t.roleid from sys_identity t where t.purvieworganizeid =? and t.roleid='2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487'";
    						String roleid=dbHelper.getOnlyStringValue(sql1, new Object[]{getUpEid});
    						if(!"".equals(roleid)&&roleid!=null){
    							eids+="@98C57F262CB74E12AAC31C9D37533A17";
    							break;//跳出循环
    						}
    						getUpEid = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{getUpEid});
    					}
    				}
    				
    				if(eids!=null&&eids.startsWith("@")) {
    					String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    					try {
    						dbHelper.runSql(sql_up,new Object[]{eids.substring(1, eids.length()),id});
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else {
    					String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    					try {
    						dbHelper.runSql(sql_up,new Object[]{eids,id});
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}
    			}else if("pms_acceptancenotice".equals(type)){
    				//（公益：科技厅-二级或者一级;其他：科技厅）验收公示
    				String pczid = this.dbHelper.getOnlyStringValue("select h.planprojectid from crt_contract_jbxx e left join pms_projectbase b on b.id =e.projectbaseid "
    						+ " left join pms_planprojectbatch h on h.id =b.planprojectbatchid "
    						+ "  where e.id=? ",new Object[]{id});
    				if("48310CA7-7921-4C39-AE3E-1F84C0AC18B9".equals(pczid)||"E227B8B0-56CA-41D6-9CF1-B9571A64E4A2".equals(pczid)||"5F7E926D-6A8E-419C-B471-51EF84E6594D".equals(pczid)
    						||"D5F7D170-FFE1-474D-A1B5-E2A45BA15EF2".equals(pczid)||"3LXT4D8D-A52E-4776-B3C4-3019AC29E3B8".equals(pczid)//公益类项目 不进行处室审核
    						||"00LXT4D8D-A52E-4776-B3C4-3019AC29E3B8".equals(pczid))//钱江人才
    				{
    					//公益类项目
    					eids="98C57F262CB74E12AAC31C9D37533A17";
    					if(Util.isEoN(treeIndex)||"1".equals(treeIndex)){
    						String getUpEid  = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{usereid});
    						while(!Util.isEoN(getUpEid)){
    							eids += "@"+getUpEid;
    							//判断当前归口是否有高管，如果有则跳出循环，然后再加上科技厅
    							String sql1="select t.roleid from sys_identity t where t.purvieworganizeid =? and t.roleid='2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487'";
    							String roleid=dbHelper.getOnlyStringValue(sql1, new Object[]{getUpEid});
    							if(!"".equals(roleid)&&roleid!=null){
    								break;//跳出循环
    							}
    							getUpEid = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{getUpEid});
    						}
    					}else{
    						String getUpEid  = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{usereid});
    						while(!Util.isEoN(getUpEid)){
    							eids += "@"+getUpEid;
    							//判断当前归口是否有高管，如果有则跳出循环，然后再加上科技厅
    							String sql1="select t.roleid from sys_identity t where t.purvieworganizeid =? and t.roleid='2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487'";
    							String roleid=dbHelper.getOnlyStringValue(sql1, new Object[]{getUpEid});
    							if(!"".equals(roleid)&&roleid!=null){
    								break;//跳出循环
    							}
    							getUpEid = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{getUpEid});
    						}
    					}
    					
    					if(eids!=null&&eids.startsWith("@")) {
    						String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    						try {
    							dbHelper.runSql(sql_up,new Object[]{eids.substring(1, eids.length()),id});
    						} catch (SQLException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
    					}else {
    						String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    						try {
    							dbHelper.runSql(sql_up,new Object[]{eids,id});
    						} catch (SQLException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
    					}
    				}else{
    					eids="98C57F262CB74E12AAC31C9D37533A17";
    					String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    					try {
    						dbHelper.runSql(sql_up,new Object[]{eids,id});
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}
    				
    			}else if("pms_contractchange".equals(type)){
    				//不需要需要增加本单位
    				eids="";
    				// 二级-一级-科技厅(项目变更)
    				if(Util.isEoN(treeIndex)||"1".equals(treeIndex)){
    					String getUpEid  = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{usereid});
    					while(!Util.isEoN(getUpEid)){
    						eids += "@"+getUpEid;
    						getUpEid = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{getUpEid});
    					}
    				}else{
    					String getUpEid  = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{usereid});
    					while(!Util.isEoN(getUpEid)){
    						eids += "@"+getUpEid;
    						getUpEid = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{getUpEid});
    					}
    				}
    				
    				if(eids!=null&&eids.startsWith("@")) {
    					String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    					try {
    						dbHelper.runSql(sql_up,new Object[]{eids.substring(1, eids.length()),id});
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else {
    					String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    					try {
    						dbHelper.runSql(sql_up,new Object[]{eids,id});
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}
    			}else {
    				//本单位-二级-一级-科技厅(默认情况下：合同，合同终止，用户中期检查等)
    				if(!Util.isEoN(editEid)){
    					eids="";
    					eids = usereid+"@"+editEid;//需要增加本单位
    					if(Util.isEoN(treeIndex)||"1".equals(treeIndex)){
    						String getUpEid  = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{editEid});
    						while(!Util.isEoN(getUpEid)){
    							eids += "@"+getUpEid;
    							getUpEid = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{getUpEid});
    						}
    					}else{
    						String getUpEid  = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{editEid});
    						while(!Util.isEoN(getUpEid)){
    							eids += "@"+getUpEid;
    							getUpEid = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{getUpEid});
    						}
    					}
    				}else{
    					if(Util.isEoN(treeIndex)||"1".equals(treeIndex)){
    						String getUpEid  = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{usereid});
    						while(!Util.isEoN(getUpEid)){
    							eids += "@"+getUpEid;
    							getUpEid = dbHelper.getValue("select parentid from pms_enterprise e where id = ?", "parentid",new Object[]{getUpEid});
    						}
    					}else{
    						String getUpEid  = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{usereid});
    						while(!Util.isEoN(getUpEid)){
    							eids += "@"+getUpEid;
    							getUpEid = dbHelper.getValue("select parentid"+treeIndex+" from pms_enterprise e where id = ?", "parentid"+treeIndex,new Object[]{getUpEid});
    						}
    					}
    				}
    				if(eids!=null&&eids.startsWith("@")) {
    					String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    					try {
    						dbHelper.runSql(sql_up,new Object[]{eids.substring(1, eids.length()),id});
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else {
    					String sql_up="update "+type+" t set t.reportenterpriseflow=? where t.id=?";
    					try {
    						dbHelper.runSql(sql_up,new Object[]{eids,id});
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}
    			}
    		}
    	}
    	
    }
    /**
     * 存入现有的流程
     * @param pid
     * @param flowEid
     * @param ueid
     * @param flowTypd
     */
    public  void saveCurrentHasFlow(String pid,String flowEid,String ueid,String flowTypd){
        PmsProjectbasereportflow PmsProjectbasereportflow = new PmsProjectbasereportflow();
        PmsProjectbasereportflow.setId(Util.NewGuid());
        PmsProjectbasereportflow.setReportenterpriseflow(flowEid);
        PmsProjectbasereportflow.setBussinesstype(flowTypd);
        PmsProjectbasereportflow.setBussinessid(pid);
        PmsProjectbasereportflow.setCurrententerprise(ueid);
        this.getProjectbaseReportFlowDao().save(PmsProjectbasereportflow);
    }

    // 找到业务流程

    /**
     *
     * @param pid
     * @param btype
     * @return
     */
    public PmsProjectbasereportflow getPmsProjectbaseReprortFlow(String pid, String btype){
        List<PmsProjectbasereportflow> pprfs = this.findByHql("from PmsProjectbasereportflow ppr where ppr.bussinessid=? and " +
                " ppr.bussinessType=?", pid,btype);
        if(pprfs!=null&&pprfs.size()>0) {
           return pprfs.get(0);
        }else {
            pprfs = this.findByHql("from PmsProjectbasereportflow ppr where ppr.bussinessid=? ", pid);
            if(pprfs!=null&&pprfs.size()>0) {
				return pprfs.get(0);
			} else {
				return null;
			}
        }
    }

}
