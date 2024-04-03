package cn.topcheer.halberd.app.api.framework.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DBGrid implements DBResult {

    private List<GridColumn> columns;

    private List<GridRow> rows;

    @Override
    public String getType() {
        return "grid";
    }

}
