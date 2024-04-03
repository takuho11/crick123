package cn.topcheer.halberd.app.api.framework.dto.db;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AggregateSqlResult extends ExecuteSqlResult{

    @Override
    public String getResultType() {
        return "aggregate";
    }

    private List<String> groupByFields;


    public AggregateSqlResult(long total, List<GridColumn> columnList, List<Map<String, Object>> data, List<String> groupByFields) {
        super(total, columnList, data);
        this.groupByFields = groupByFields;
    }
}
