/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2019-3-3 15:54:33
 */
package cn.topcheer.pms2.api.pml.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
@Table(name = "PML_COLUMN")
public class PmlColumn implements Serializable {


    /**
     *
     */
    @Id
    private String id;


    /**
     *  列表中文名，用来显示
     */
    private String columnnameCh;


    /**
     *  列表英文名，用来和select语句匹配，要有特殊标识，如col_name，select语句也要取别名
     */
    private String columnnameEn;


    /**
     *  是否有跳转方法，有:1，无:0
     */
    private Boolean havejump;


    /**
     *  跳转的方法，一般是js方法，有个js文件专门存放一些跳转方法
     */
    private String jumpmethod;

    /**
     * 有跳转的列，鼠标悬浮有提示语句
     */
    private String jumptips;


    /**
     *  创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date createdate;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 列名宽度
     */
    private Integer columnwidth;

    /**
     * 备注：主要用来区分列名相同的不同列，方便列表选择
     */
    private String remarks;

    /**
     * 业务类型
     */
    private String businesstype;

    /**
     * 手机端用到
     */
    private String fittype;

    /**
     * 主动排序类型
     */
    private String sorttype;


}