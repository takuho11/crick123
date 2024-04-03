package cn.topcheer.halberd.app.api.framework.dto.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExecuteSqlRequest {
    private String dataSourceId;
    private String sql;

    private boolean paging = false;

    /**
     * 从1开始
     */
    private int page = 1;
    private int size = 50;
}
