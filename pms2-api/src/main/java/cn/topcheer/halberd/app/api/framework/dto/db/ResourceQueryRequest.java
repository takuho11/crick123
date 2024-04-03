package cn.topcheer.halberd.app.api.framework.dto.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResourceQueryRequest {

    private Long dataResourceId;

    private List<ColumnOperate> columnOperateList;

    private List<Order> orderList = new ArrayList<>();

    private String filterItem;

    private String filterItemContent;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean paging = true;

    /**
     * 从1开始
     */
    private int page = 1;
    private int size = 100;



    @Getter
    @Setter
    public static class ColumnOperate{

        private String columnName;

        /**
         * group, count, count_star, sum, avg
         */
        private String operate;

        public String getOperateChineseLabel(){
            switch (operate){
                case "count": return "总数";
                case "sum": return "总和";
                case "avg": return "均值";
                default: return null;
            }
        }

    }


    @Getter
    @Setter
    public static class Order{

        /**
         * 需要进行排序的字段
         */
        private String column;
        /**
         * 是否正序排列，默认 true
         */
        private boolean asc = true;

        public Order() {
        }

        public Order(String column) {
            this.column = column;
        }

        public Order(String column, boolean asc) {
            this.column = column;
            this.asc = asc;
        }
    }

}
