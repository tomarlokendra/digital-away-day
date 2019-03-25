package com.app.digital;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.app.digital.exceptions.DigitalAwayDayException;
import com.app.digital.models.Activity;
import com.app.digital.models.DigitalAwayDay;
import com.app.digital.models.EventSlot;
import com.app.digital.utils.ActivitiesReader;

/**
 * Entry point - read activities, add to Digital Away Day and print schedule.
 * 
 * @author Lokendra
 *
 */
public class StartDigitalAwayDay {

	public static void main(String[] args) {
		String INPUT = "activities.txt";
		ActivitiesReader fileUtils = new ActivitiesReader();
		List<Activity> activities = null;

		Integer extratime = 60;
		Integer numberOfEvents = 2;
		try {
			List<EventSlot> eventSlots = new ArrayList<EventSlot>();
			eventSlots.add(new EventSlot(LocalTime.of(9, 00), LocalTime.of(12, 00)));
			eventSlots.add(new EventSlot(LocalTime.of(13, 00), LocalTime.of(16, 00)));

			activities = fileUtils.loadActivitiesFromFile(INPUT);
			DigitalAwayDay digitalAwayDay = new DigitalAwayDay(eventSlots, extratime, numberOfEvents);
			digitalAwayDay.addActivities(activities);
			System.out.println("Deloitte Digital Away Day:");
			System.out.println(digitalAwayDay);
		} catch (DigitalAwayDayException e) {
			System.out.println("Problem while executing Appliation :" + e.getLocalizedMessage());
		}
	}

}
