package com.lst.model;

import java.util.Date;

public class OpRecord {
    private Integer id;

    private String actiontype;

    private String actionfunction;

    private Boolean isdeleted;

    private Integer reserved1;

    private Integer reserved2;

    private Integer reserved3;

    private Integer reserved4;

    private Date reserved5;

    private Date reserved6;

    private Date reserved7;

    private Date reserved8;

    private String reserved9;

    private String reserved10;

    private String reserved11;

    private String reserved12;

    private String remark;

    private Date createdate;

    private Integer createuser;

    private Date updatedate;

    private Integer updateuser;

    private String actionparam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActiontype() {
        return actiontype;
    }

    public void setActiontype(String actiontype) {
        this.actiontype = actiontype == null ? null : actiontype.trim();
    }

    public String getActionfunction() {
        return actionfunction;
    }

    public void setActionfunction(String actionfunction) {
        this.actionfunction = actionfunction == null ? null : actionfunction.trim();
    }

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Integer getReserved1() {
        return reserved1;
    }

    public void setReserved1(Integer reserved1) {
        this.reserved1 = reserved1;
    }

    public Integer getReserved2() {
        return reserved2;
    }

    public void setReserved2(Integer reserved2) {
        this.reserved2 = reserved2;
    }

    public Integer getReserved3() {
        return reserved3;
    }

    public void setReserved3(Integer reserved3) {
        this.reserved3 = reserved3;
    }

    public Integer getReserved4() {
        return reserved4;
    }

    public void setReserved4(Integer reserved4) {
        this.reserved4 = reserved4;
    }

    public Date getReserved5() {
        return reserved5;
    }

    public void setReserved5(Date reserved5) {
        this.reserved5 = reserved5;
    }

    public Date getReserved6() {
        return reserved6;
    }

    public void setReserved6(Date reserved6) {
        this.reserved6 = reserved6;
    }

    public Date getReserved7() {
        return reserved7;
    }

    public void setReserved7(Date reserved7) {
        this.reserved7 = reserved7;
    }

    public Date getReserved8() {
        return reserved8;
    }

    public void setReserved8(Date reserved8) {
        this.reserved8 = reserved8;
    }

    public String getReserved9() {
        return reserved9;
    }

    public void setReserved9(String reserved9) {
        this.reserved9 = reserved9 == null ? null : reserved9.trim();
    }

    public String getReserved10() {
        return reserved10;
    }

    public void setReserved10(String reserved10) {
        this.reserved10 = reserved10 == null ? null : reserved10.trim();
    }

    public String getReserved11() {
        return reserved11;
    }

    public void setReserved11(String reserved11) {
        this.reserved11 = reserved11 == null ? null : reserved11.trim();
    }

    public String getReserved12() {
        return reserved12;
    }

    public void setReserved12(String reserved12) {
        this.reserved12 = reserved12 == null ? null : reserved12.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Integer createuser) {
        this.createuser = createuser;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(Integer updateuser) {
        this.updateuser = updateuser;
    }

    public String getActionparam() {
        return actionparam;
    }

    public void setActionparam(String actionparam) {
        this.actionparam = actionparam == null ? null : actionparam.trim();
    }
}