package com.app.digital.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class having information of activity slots.
 * 
 * @author Lokendra
 *
 */
public class ActivitySlot {

	private List<Activity> activities;
	private Integer activitySlotSize;
	private LocalTime activitySlotStartTime;
	private LocalTime activitySlotEndTime;

	public ActivitySlot(Integer activitySlotSize, LocalTime activitySlotStartTime, LocalTime activitySlotEndTime) {
		this.activitySlotSize = activitySlotSize;
		this.activitySlotStartTime = activitySlotStartTime;
		this.activitySlotEndTime = activitySlotEndTime;
		this.activities = new ArrayList<Activity>();
	}

	/**
	 * Method to add activity if there is available time in current slot
	 * 
	 * @param activity
	 * @return
	 */
	public boolean addActivity(Activity activity) {

		if (activity != null && activity.getDuration() <= this.getAvailableDuration()) {
			this.activities.add(activity);
			return true;
		}
		return false;
	}

	/**
	 * method to get available time
	 * 
	 * @return
	 */
	public Integer getAvailableDuration() {
		return this.activitySlotSize - activities.stream().mapToInt(activity -> activity.getDuration()).sum();
	}

	/**
	 * method to get used size
	 * 
	 * @return
	 */
	public Integer getUsedDuration() {
		return activities.stream().mapToInt(activity -> activity.getDuration()).sum();
	}

	public LocalTime getActivitySlotStartTime() {
		return activitySlotStartTime;
	}

	public void setActivitySlotStartTime(LocalTime activitySlotStartTime) {
		this.activitySlotStartTime = activitySlotStartTime;
	}

	public LocalTime getActivitySlotEndTime() {
		return activitySlotEndTime;
	}

	public void setActivitySlotEndTime(LocalTime activitySlotEndTime) {
		this.activitySlotEndTime = activitySlotEndTime;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		activities.forEach(actvity -> buffer.append(actvity).append(System.getProperty("line.separator")));
		return buffer.toString();
	}
}
