package cn.topcheer.pms2.api.pml.vo;

import java.util.List;

/**
 * Created by ZK on 2019/3/4.
 * 分页类，用于对分页进行封装
 */
public class Page<T> implements java.io.Serializable {


    private List<T> data;

    private Integer total;

    public Page(){

    }

    public Page(List<T> data, Integer total){
        this.data = data;
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Page{" +
                "data=" + data +
                ", total=" + total +
                '}';
    }
}
