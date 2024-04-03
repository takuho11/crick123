package cn.topcheer.pms2.api.pml.vo;

/**
 * Created by j on 2020/11/02.
 */
public class ReturnToExcelMerge {


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
     * 高度
     */
    private Integer heightinpoints;

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

    public Integer getHeightinpoints() {
        return heightinpoints;
    }

    public void setHeightinpoints(Integer heightinpoints) {
        this.heightinpoints = heightinpoints;
    }

    @Override
    public String toString() {
        return "{" +
                " firstrow=" + firstrow +
                ", lastrow='" + lastrow + '\'' +
                ", firstcol='" + firstcol + '\'' +
                ", lastcol='" + lastcol + '\'' +
                ", heightinpoints='" + heightinpoints + '\'' +
                '}';
    }
}
