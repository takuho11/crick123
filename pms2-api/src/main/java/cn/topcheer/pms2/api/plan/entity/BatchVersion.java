package cn.topcheer.pms2.api.plan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BATCH_VERSION")
public class BatchVersion {
    private String id;//主id（小批次id）

    private String sb;//科技项目-申报

    private String ht;//科技项目-合同

    private String zqjc;//科技项目-中期检查

    private String ndbg;//科技项目-年度报告

    private String kjbg;//科技项目-科技报告

    private String ys;//科技项目-验收

    private String kc;//孵化器-备案

    private String cr;//孵化器-年度报告

    private String techawards;//科技奖励

    private String kjcxpt;//科技创新平台-备案

    private String kjcxpt_ndbg;//科技创新平台-年度报告

    private String kjcxpt_ht;//科技创新平台-任务书

    private String register_user;//注册人

    private String guide_collect;//指南征集

    private String msjx;//免申即享

    private String annual_inspect;//年度自查表

    private String warehouse;//数据仓

    private String cgdj;//成果登记

    private String bz;//补助

    private String xssc;//形式审查

    private String kjcxpt_check;//阶段性检查

    private String kjcxpt_acceptance;//平台验收

    private String rctd;//人才团队
    /*----------------------------------分割线----------------------------------*/




    @Id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getHt() {
        return ht;
    }

    public void setHt(String ht) {
        this.ht = ht;
    }

    public String getZqjc() {
        return zqjc;
    }

    public void setZqjc(String zqjc) {
        this.zqjc = zqjc;
    }

    public String getNdbg() {
        return ndbg;
    }

    public void setNdbg(String ndbg) {
        this.ndbg = ndbg;
    }

    public String getKjbg() {
        return kjbg;
    }

    public void setKjbg(String kjbg) {
        this.kjbg = kjbg;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    public String getKc() {
        return kc;
    }

    public void setKc(String kc) {
        this.kc = kc;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public String getTechawards() {
        return techawards;
    }

    public void setTechawards(String techawards) {
        this.techawards = techawards;
    }

    public String getKjcxpt() {
        return kjcxpt;
    }

    public void setKjcxpt(String kjcxpt) {
        this.kjcxpt = kjcxpt;
    }

    public String getKjcxpt_ndbg() {
        return kjcxpt_ndbg;
    }

    public void setKjcxpt_ndbg(String kjcxpt_ndbg) {
        this.kjcxpt_ndbg = kjcxpt_ndbg;
    }

    public String getKjcxpt_ht() {
        return kjcxpt_ht;
    }

    public void setKjcxpt_ht(String kjcxpt_ht) {
        this.kjcxpt_ht = kjcxpt_ht;
    }

    public String getRegister_user() {
        return register_user;
    }

    public void setRegister_user(String register_user) {
        this.register_user = register_user;
    }

    public String getGuide_collect() {
        return guide_collect;
    }

    public void setGuide_collect(String guide_collect) {
        this.guide_collect = guide_collect;
    }

    public String getMsjx() {
        return msjx;
    }

    public void setMsjx(String msjx) {
        this.msjx = msjx;
    }

    public String getAnnual_inspect() {
        return annual_inspect;
    }

    public void setAnnual_inspect(String annual_inspect) {
        this.annual_inspect = annual_inspect;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getCgdj() {
        return cgdj;
    }

    public void setCgdj(String cgdj) {
        this.cgdj = cgdj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getXssc() {
        return xssc;
    }

    public void setXssc(String xssc) {
        this.xssc = xssc;
    }

    public String getKjcxpt_check() {
        return kjcxpt_check;
    }

    public void setKjcxpt_check(String kjcxpt_check) {
        this.kjcxpt_check = kjcxpt_check;
    }

    public String getKjcxpt_acceptance() { return kjcxpt_acceptance; }

    public void setKjcxpt_acceptance(String kjcxpt_acceptance) { this.kjcxpt_acceptance = kjcxpt_acceptance; }

    public String getRctd() {
        return rctd;
    }

    public void setRctd(String rctd) {
        this.rctd = rctd;
    }
}
