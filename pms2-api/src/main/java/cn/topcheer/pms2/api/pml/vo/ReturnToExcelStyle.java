package cn.topcheer.pms2.api.pml.vo;

/**
 * Created by j on 2020/11/02.
 */
public class ReturnToExcelStyle {


    /**
     * 首行
     */
    private Integer firstrow;


    /**
     * 最后一行
     */
    private Integer lastrow;


    /**
     * 首列
     */
    private Integer firstcol;


    /**
     * 最后一列
     */
    private Integer lastcol;


    /**
     * 样式id
     */
    private String styleid;

    public Integer getFirstrow() {
        return firstrow;
    }

    public void setFirstrow(Integer firstrow) {
        this.firstrow = firstrow;
    }

    public Integer getLastrow() {
        return lastrow;
    }

    public void setLastrow(Integer lastrow) {
        this.lastrow = lastrow;
    }

    public Integer getFirstcol() {
        return firstcol;
    }

    public void setFirstcol(Integer firstcol) {
        this.firstcol = firstcol;
    }

    public Integer getLastcol() {
        return lastcol;
    }

    public void setLastcol(Integer lastcol) {
        this.lastcol = lastcol;
    }

    public String getStyleid() {
        return styleid;
    }

    public void setStyleid(String styleid) {
        this.styleid = styleid;
    }

    @Override
    public String toString() {
        return "{" +
                " firstrow=" + firstrow +
                ", lastrow='" + lastrow + '\'' +
                ", firstcol='" + firstcol + '\'' +
                ", lastcol='" + lastcol + '\'' +
                ", styleid='" + styleid + '\'' +
                '}';
    }
}
