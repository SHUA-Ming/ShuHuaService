package com.lst.model;

import java.math.BigDecimal;
import java.util.Date;

public class UserMedal {
    private Integer id;

    private Integer userid;

    private Integer medalid;

    private Boolean isdeleted;

    private Integer reserved1;

    private Integer reserved2;

    private BigDecimal reserved3;

    private BigDecimal reserved4;

    private Date reserved5;

    private Date reserved6;

    private Boolean reserved7;

    private Boolean reserved8;

    private String reserved9;

    private String reserved10;

    private String reserved11;

    private String reserved12;

    private String remark;

    private Date createdate;

    private Integer createuser;

    private Date updatedate;

    private Integer updateuser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMedalid() {
        return medalid;
    }

    public void setMedalid(Integer medalid) {
        this.medalid = medalid;
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

    public BigDecimal getReserved3() {
        return reserved3;
    }

    public void setReserved3(BigDecimal reserved3) {
        this.reserved3 = reserved3;
    }

    public BigDecimal getReserved4() {
        return reserved4;
    }

    public void setReserved4(BigDecimal reserved4) {
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

    public Boolean getReserved7() {
        return reserved7;
    }

    public void setReserved7(Boolean reserved7) {
        this.reserved7 = reserved7;
    }

    public Boolean getReserved8() {
        return reserved8;
    }

    public void setReserved8(Boolean reserved8) {
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
}