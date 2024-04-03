package cn.topcheer.pms2.biz.pml.service.impl.enumUtil;

import cn.topcheer.pms2.api.pml.entity.FlAuthorityGrid;

/**
 * Created by peanut.huang on 2020/3/22.
 */
public enum FiltSqlEnum {
    flAuthorityFpService("flAuthorityFpService") {
        @Override
        public Object[] getParams(String type, String gridtype, String roleid, String userid, FlAuthorityGrid flAuthorityGrid) {
            return new Object[]{type,roleid};
        }
    },
    flitFlowPointGridService("flitFlowPointGridService"){
        @Override
        public Object[] getParams(String type, String gridtype, String roleid, String userid,FlAuthorityGrid flAuthorityGrid) {
            return new Object[]{type,roleid,flAuthorityGrid.getFlowpoints()};
        }
    },
    filtTeamnameService("filtTeamnameService"){
        @Override
        public Object[] getParams(String type, String gridtype, String roleid, String userid,FlAuthorityGrid flAuthorityGrid) {
            return new Object[]{type};
        }
    },
    filtBigbatchService("filtBigbatchService"){
        @Override
        public Object[] getParams(String type, String gridtype, String roleid, String userid,FlAuthorityGrid flAuthorityGrid) {
            return new Object[]{type};
        }
    };

    private String filtservice ;

    FiltSqlEnum(String filtservice) {
        this.filtservice = filtservice;
    }

    public abstract Object[] getParams(String type, String gridtype, String roleid, String userid, FlAuthorityGrid flAuthorityGrid);
}
