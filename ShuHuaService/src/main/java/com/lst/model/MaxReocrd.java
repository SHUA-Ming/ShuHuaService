package com.lst.model;

import com.google.gson.annotations.Expose;

public class MaxReocrd {
	@Expose
	private String maxTime;
	@Expose
	private String maxCalorie;
	@Expose
	private String maxKilometer;

	public String getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}

	public String getMaxCalorie() {
		return maxCalorie;
	}

	public void setMaxCalorie(String maxCalorie) {
		this.maxCalorie = maxCalorie;
	}

	public String getMaxKilometer() {
		return maxKilometer;
	}

	public void setMaxKilometer(String maxKilometer) {
		this.maxKilometer = maxKilometer;
	}
}