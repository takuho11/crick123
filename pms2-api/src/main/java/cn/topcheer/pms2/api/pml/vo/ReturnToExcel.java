package cn.topcheer.pms2.api.pml.vo;

import java.util.List;

import cn.topcheer.halberd.core.result.Result;
/**
 * Created by j on 2020/11/02.
 */
public class ReturnToExcel<T> extends Result<T> {

    /**
     * 后台返回的数据
     */
    private T data;

    /**
     * 后台返回的数据--合并单元格数组
     */
    private List<ReturnToExcelMerge> mergeArray;

    /**
     * 后台返回的数据--样式数组
     */
    private List<ReturnToExcelStyle> styleArray;




    public List<ReturnToExcelMerge> getMergeArray() {
        return mergeArray;
    }

    public void setMergeArray(List<ReturnToExcelMerge> mergeArray) {
        this.mergeArray = mergeArray;
    }

    public List<ReturnToExcelStyle> getStyleArray() {
        return styleArray;
    }

    public void setStyleArray(List<ReturnToExcelStyle> styleArray) {
        this.styleArray = styleArray;
    }


    public String getErrMsg() {
        return this.getMsg();
    }

    public void setErrMsg(String errMsg) {
        this.setMsg(errMsg);
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                ", mergeArray=" + mergeArray +
                ", styleArray=" + styleArray +
                ", success=" + this.isSuccess() +
                ", errMsg='" + this.getErrMsg() + '\'' +
                '}';
    }
}
