package com.app.digital.models;

import java.time.LocalTime;

public class Activity {
	static String SPRINT = "sprint";

	private String name;

	private LocalTime startTime;

	private Integer duration;

	public Activity(String name, Integer duration) {
		this.name = name;
		this.duration = duration;
	}

	public static String getSPRINT() {
		return SPRINT;
	}

	public static void setSPRINT(String sPRINT) {
		SPRINT = sPRINT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		String startTimeStr;
		StringBuffer buffer = new StringBuffer("");
		if (this.getStartTime().getHour() >= 12) {
			startTimeStr = this.startTime.minusHours(12).toString() + " pm";
		} else {
			startTimeStr = this.startTime.toString() + " am";
		}
		buffer.append(startTimeStr).append(" : " + this.getName() + " ")
				.append((this.duration == 15 ? SPRINT : this.duration + "min"));
		return buffer.toString();

	}
}
