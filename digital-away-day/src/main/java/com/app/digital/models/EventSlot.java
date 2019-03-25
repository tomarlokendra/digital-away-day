package com.app.digital.models;

import java.time.LocalTime;

/**
 * Slot of event
 * 
 * @author Lokendra
 *
 */
public class EventSlot {

	private LocalTime startTime;

	private LocalTime endTime;

	public EventSlot(LocalTime startTime, LocalTime endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

}
