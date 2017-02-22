package com.lst.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class MstUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	private Integer id;

	private String account;
	@Expose
	private String password;

	private Integer type;
	@Expose
	private String nickname;

	private String name;
	@Expose
	private Boolean gender;  // 0：女-->false；1：男-->true
	@Expose
	private String mobileno;
	@Expose
	private BigDecimal weight;
	@Expose
	private Integer height;
	
	private String sex;
	@Expose
	private String birthday;
	@Expose
	private String email;
	@Expose
	private String portrait;

	private String qq;

	private String qqpwd;

	private String weixin;

	private String weixinpwd;

	private String weibo;

	private String weibopwd;
	@Expose
	private Integer totalnumber;
	@Expose
	private BigDecimal totalcalorie;
	@Expose
	private BigDecimal totalkilometer;
	@Expose
	private Integer totalstep;
	@Expose
	private Date totaldatatime;

	private Integer tenkmrank;

	private Integer friendrank;

	private Boolean isdeleted;
	@Expose
	private Integer reserved1;//用于时间统计

	private Integer reserved2;

	private BigDecimal reserved3;

	private BigDecimal reserved4;

	private Date reserved5;

	private Date reserved6;
	
	@Expose
	private Boolean reserved7;//init info ok

	private Boolean reserved8;
	@Expose
	private String reserved9;//icon url

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno == null ? null : mobileno.trim();
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait == null ? null : portrait.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public String getQqpwd() {
		return qqpwd;
	}

	public void setQqpwd(String qqpwd) {
		this.qqpwd = qqpwd == null ? null : qqpwd.trim();
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin == null ? null : weixin.trim();
	}

	public String getWeixinpwd() {
		return weixinpwd;
	}

	public void setWeixinpwd(String weixinpwd) {
		this.weixinpwd = weixinpwd == null ? null : weixinpwd.trim();
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo == null ? null : weibo.trim();
	}

	public String getWeibopwd() {
		return weibopwd;
	}

	public void setWeibopwd(String weibopwd) {
		this.weibopwd = weibopwd == null ? null : weibopwd.trim();
	}

	public Integer getTotalnumber() {
		return totalnumber;
	}

	public void setTotalnumber(Integer totalnumber) {
		this.totalnumber = totalnumber;
	}

	public BigDecimal getTotalcalorie() {
		return totalcalorie;
	}

	public void setTotalcalorie(BigDecimal totalcalorie) {
		this.totalcalorie = totalcalorie;
	}

	public BigDecimal getTotalkilometer() {
		return totalkilometer;
	}

	public void setTotalkilometer(BigDecimal totalkilometer) {
		this.totalkilometer = totalkilometer;
	}

	public Integer getTotalstep() {
		return totalstep;
	}

	public void setTotalstep(Integer totalstep) {
		this.totalstep = totalstep;
	}

	public Date getTotaldatatime() {
		return totaldatatime;
	}

	public void setTotaldatatime(Date totaldatatime) {
		this.totaldatatime = totaldatatime;
	}

	public Integer getTenkmrank() {
		return tenkmrank;
	}

	public void setTenkmrank(Integer tenkmrank) {
		this.tenkmrank = tenkmrank;
	}

	public Integer getFriendrank() {
		return friendrank;
	}

	public void setFriendrank(Integer friendrank) {
		this.friendrank = friendrank;
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