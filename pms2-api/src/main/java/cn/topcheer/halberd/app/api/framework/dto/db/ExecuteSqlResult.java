package cn.topcheer.halberd.app.api.framework.dto.db;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class ExecuteSqlResult implements SqlResult{

    @Override
    public String getResultType() {
        return "select";
    }


    private List<GridColumn> columnList;

    private List<Map<String, Object>> data;

    private long total;

    public ExecuteSqlResult(long total, List<Map<String, Object>> data){
        this.total = total;
        this.data = data;
        if(data == null || data.isEmpty()){
            this.columnList = new ArrayList<>();
        }else {
            Map<String, Object> first = data.get(0);
            this.columnList = first.keySet().stream().map(s -> GridColumn.builder().label(s).prop(s).build()).collect(Collectors.toList());
        }
    }


    public ExecuteSqlResult(long total, List<GridColumn> columnList, List<Map<String, Object>> data){
        this.total = total;
        this.data = data;
        this.columnList = columnList;
    }


    @Getter
    @Setter
    @Builder
    public static class GridColumn{
        private String label;
        private String prop;
    }

}
