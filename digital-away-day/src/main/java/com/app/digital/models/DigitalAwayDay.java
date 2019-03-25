package com.app.digital.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.app.digital.exceptions.DigitalAwayDayException;
import com.app.digital.utils.Constants;

/**
 * DigitalAwayDay - can have multiple events
 * 
 * @author Lokendra
 *
 */
public class DigitalAwayDay {

	List<DayEvent> dayEvents = new ArrayList<DayEvent>();

	public DigitalAwayDay(List<EventSlot> eventSlots, Integer eveningExtraTime, Integer numberOfEvents)
			throws DigitalAwayDayException {
		for (int i = 0; i < numberOfEvents; i++) {
			dayEvents.add(new DayEvent(eventSlots, eveningExtraTime));
		}
	}

	/**
	 * addActivities
	 * 
	 * @param activities
	 * @throws DigitalAwayDayException
	 */
	public void addActivities(List<Activity> activities) throws DigitalAwayDayException {
		this.validateActivities(activities);

		// List<Activity> orderedActivities = activities.stream().sorted((a1, a2) ->
		// a2.getDuration() - a1.getDuration()).collect(Collectors.toList());
		for (Activity activity : activities) {
			boolean isActivityAdded = this.addActivity(activity);
			if (!isActivityAdded) {
				throw new DigitalAwayDayException(
						"addActivities : There was a problem inserting activity (" + activity.getName() + ")");
			}
		}
	}

	/**
	 * addActivity
	 * 
	 * @param activity
	 * @return
	 */
	private boolean addActivity(Activity activity) {
		boolean isAdded = false;
		Iterator<DayEvent> iterator = dayEvents.iterator();
		while (isAdded != true && iterator.hasNext()) {
			DayEvent currEvent = iterator.next();
			for (int i = 0; i < currEvent.getActivitySlots().size(); i++) {
				isAdded = currEvent.insertActivity(activity, currEvent.getActivitySlots().get(i));
				if (isAdded) {
					break;
				}

			}
		}
		return isAdded;
	}

	/**
	 * validateActivities
	 * 
	 * @param activities
	 * @throws DigitalAwayDayException
	 */
	private void validateActivities(List<Activity> activities) throws DigitalAwayDayException {

		Integer activitiesTime = activities.stream().mapToInt(activity -> activity.getDuration()).sum();
		// Integer minEventTime = dayEvents.stream().mapToInt(dayEvent ->
		// dayEvent.getMinEventDuration()).sum();
		Integer maxEventTime = dayEvents.stream().mapToInt(dayEvent -> dayEvent.getMaxEventDuration()).sum();
		if (activitiesTime > maxEventTime) {
			throw new DigitalAwayDayException("validateActivities : List of activities has unmanageable durations.");
		}
	}

	public List<DayEvent> getDayEvents() {
		return dayEvents;
	}

	public void setDayEvents(List<DayEvent> dayEvents) {
		this.dayEvents = dayEvents;
	}

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer("");
		Integer count = 1;
		for (DayEvent dayEvent : dayEvents) {
			buffer.append("Team " + count + ":").append(System.getProperty(Constants.LINE_SEPARATOR)).append(dayEvent)
					.append(System.getProperty(Constants.LINE_SEPARATOR));
			count++;
		}
		return buffer.append(System.getProperty(Constants.LINE_SEPARATOR)).toString();
	}

}
