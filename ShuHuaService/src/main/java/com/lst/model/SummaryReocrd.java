package com.lst.model;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

public class SummaryReocrd {
	@Expose
	private String starttime;
	@Expose
	private BigDecimal kilometer;

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public BigDecimal getKilometer() {
		return kilometer;
	}

	public void setKilometer(BigDecimal kilometer) {
		this.kilometer = kilometer;
	}
}