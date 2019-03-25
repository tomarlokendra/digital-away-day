package com.app.digital.models;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.app.digital.exceptions.DigitalAwayDayException;

/**
 * Class representing event
 * 
 * @author Lokendra
 *
 */
public class DayEvent {

	List<ActivitySlot> activitySlots = new ArrayList<ActivitySlot>();
	List<EventSlot> eventSlots;
	LocalTime morningStartTime;
	Integer extraTime;

	public DayEvent(List<EventSlot> eventSlots, Integer eveningExtraTime) throws DigitalAwayDayException {
		int i = 0;
		for (EventSlot eventSlot : eventSlots) {
			if (i < (eventSlots.size() - 1)) {
				this.validateSlot(eventSlot.getStartTime(), eventSlot.getEndTime());
				activitySlots
						.add(new ActivitySlot(getActivitySlotSize(eventSlot.getStartTime(), eventSlot.getEndTime()),
								eventSlot.getStartTime(), eventSlot.getEndTime()));
				i++;
			} else {
				activitySlots
						.add(new ExtraTimeSlot(getActivitySlotSize(eventSlot.getStartTime(), eventSlot.getEndTime()),
								eventSlot.getStartTime(), eventSlot.getEndTime(), eveningExtraTime));
			}
		}
		this.morningStartTime = eventSlots.get(0).getStartTime();
		this.extraTime = eveningExtraTime;
		this.eventSlots = eventSlots;
	}

	/**
	 * method to calculate slot duration
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws DigitalAwayDayException
	 */
	private Integer getActivitySlotSize(LocalTime startTime, LocalTime endTime) throws DigitalAwayDayException {
		Duration duration = Duration.between(startTime, endTime);
		if (duration.getSeconds() > 0) {
			return (int) (duration.getSeconds() / 60);
		}
		throw new DigitalAwayDayException("getActivitySlotSize : End time must be after start time");
	}

	/**
	 * validateSlot
	 * 
	 * @param slotStartTime
	 * @param slotEndTime
	 * @return
	 * @throws DigitalAwayDayException
	 */
	private boolean validateSlot(LocalTime slotStartTime, LocalTime slotEndTime) throws DigitalAwayDayException {
		if (slotStartTime != null && slotEndTime != null && slotEndTime.isAfter(slotStartTime)) {
			return true;
		} else {
			throw new DigitalAwayDayException("Problem creating day event, please provide valid slot details.");
		}
	}

	/**
	 * method to get min duration ie without extra time
	 * 
	 * @return
	 */
	public Integer getMinEventDuration() {
		Integer minEventDuration = 0;
		for (ActivitySlot activitySlot : activitySlots) {
			minEventDuration += activitySlot.getAvailableDuration();
		}
		return minEventDuration - this.extraTime;
	}

	/**
	 * method to get max duration ie including extra time
	 * 
	 * @return
	 */
	public Integer getMaxEventDuration() {
		Integer maxEventDuration = 0;
		for (ActivitySlot activitySlot : activitySlots) {
			maxEventDuration += activitySlot.getAvailableDuration();
		}
		return maxEventDuration;
	}

	/**
	 * Method to add activity in activity slot
	 * 
	 * @param activity
	 * @param activitySlot
	 * @return
	 */
	public boolean insertActivity(Activity activity, ActivitySlot activitySlot) {
		if (activity != null && activitySlot != null) {
			activity.setStartTime(activitySlot.getActivitySlotStartTime().plusMinutes(activitySlot.getUsedDuration()));
			return activitySlot.addActivity(activity);
		}
		return false;
	}

	public List<ActivitySlot> getActivitySlots() {
		return activitySlots;
	}

	public void setActivitySlots(List<ActivitySlot> activitySlots) {
		this.activitySlots = activitySlots;
	}

	public List<EventSlot> getEventSlots() {
		return eventSlots;
	}

	public void setEventSlots(List<EventSlot> eventSlots) {
		this.eventSlots = eventSlots;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		activitySlots.forEach(activitySlot -> buffer.append(activitySlot.toString()));
		return buffer.toString();
	}

}
