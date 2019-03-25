package com.app.digital.models;

import java.time.LocalTime;

/**
 * ExtraTimeSlot
 * 
 * @author Lokendra
 *
 */
public class ExtraTimeSlot extends ActivitySlot {

	private Integer extraTime;
	private LocalTime startTime;
	private Integer extraTimeSlotDuration;

	public ExtraTimeSlot(Integer extraTimeSlotDuration, LocalTime startTime, LocalTime endTime, Integer extraTime) {
		super(extraTimeSlotDuration, startTime, endTime);
		this.extraTime = extraTime;
		this.startTime = startTime;
		this.extraTimeSlotDuration = extraTimeSlotDuration;
	}

	/**
	 * Method to retrieve the available size
	 * 
	 * @return available size
	 */
	@Override
	public Integer getAvailableDuration() {
		return super.getAvailableDuration() + this.extraTime;
	}

	public Integer getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(Integer extraTime) {
		this.extraTime = extraTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public Integer getExtraTimeSlotDuration() {
		return extraTimeSlotDuration;
	}

	public void setExtraTimeSlotDuration(Integer extraTimeSlotDuration) {
		this.extraTimeSlotDuration = extraTimeSlotDuration;
	}

}
