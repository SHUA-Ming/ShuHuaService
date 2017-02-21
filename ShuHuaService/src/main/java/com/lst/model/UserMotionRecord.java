package com.lst.model;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class UserMotionRecord {
	//id userId machineId userPlanId startDate endDate longTime calorie kilometer
	//step heartRate pace averageVelocity averageSpeed averageStride averageGrade createDate mark
	private Integer id;
	private Integer userId;
	private Integer machineId;
	private Integer userPlanId;
	private Long startDate;
	private Long endDate;
	private Integer longTime;
	private float calorie;
	private float kilometer;
	private Integer step;
	private Integer heartRate;
	private float pace;
	private float averageVelocity;
	private Integer averageSpeed;
	private Integer averageStride;
	private float averageGrade;
	private Date createDate;
	private String mark;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMachineId() {
		return machineId;
	}
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}
	public Integer getUserPlanId() {
		return userPlanId;
	}
	public void setUserPlanId(Integer userPlanId) {
		this.userPlanId = userPlanId;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	public Integer getLongTime() {
		return longTime;
	}
	public void setLongTime(Integer longTime) {
		this.longTime = longTime;
	}
	public float getCalorie() {
		return calorie;
	}
	public void setCalorie(float calorie) {
		this.calorie = calorie;
	}
	public float getKilometer() {
		return kilometer;
	}
	public void setKilometer(float kilometer) {
		this.kilometer = kilometer;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public Integer getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}
	public float getPace() {
		return pace;
	}
	public void setPace(float pace) {
		this.pace = pace;
	}
	public float getAverageVelocity() {
		return averageVelocity;
	}
	public void setAverageVelocity(float averageVelocity) {
		this.averageVelocity = averageVelocity;
	}
	public Integer getAverageSpeed() {
		return averageSpeed;
	}
	public void setAverageSpeed(Integer averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	public Integer getAverageStride() {
		return averageStride;
	}
	public void setAverageStride(Integer averageStride) {
		this.averageStride = averageStride;
	}
	public float getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(float averageGrade) {
		this.averageGrade = averageGrade;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
}