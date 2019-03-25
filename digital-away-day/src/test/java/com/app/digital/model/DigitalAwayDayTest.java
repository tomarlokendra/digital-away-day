package com.app.digital.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.app.digital.exceptions.DigitalAwayDayException;
import com.app.digital.models.Activity;
import com.app.digital.models.DigitalAwayDay;
import com.app.digital.models.EventSlot;

public class DigitalAwayDayTest {
	
	List<EventSlot> eventSlots;
	Integer extratime = 60;
	Integer numberOfEvents = 2;
	@Before
	public void setUp() throws Exception {
		extratime = 60;
		numberOfEvents = 2;
		eventSlots = new ArrayList<EventSlot>();
		eventSlots.add(new EventSlot(LocalTime.of(9, 00), LocalTime.of(12, 00)));
		eventSlots.add(new EventSlot(LocalTime.of(13, 00), LocalTime.of(16, 00)));
	}

	@Test
	public void addActivitiesSuccess() throws DigitalAwayDayException {
		DigitalAwayDay digitalAwayDay = new DigitalAwayDay(eventSlots, extratime, numberOfEvents);
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(new Activity("Duck Herding", 60));
		activities.add(new Activity("Archery ", 45));
		activities.add(new Activity("Magic", 40));
		activities.add(new Activity("Clay", 60));
		activities.add(new Activity("Football", 30));
		activities.add(new Activity("Driving", 30));
		activities.add(new Activity("Salsa", 15));
		activities.add(new Activity("Segways", 45));
		activities.add(new Activity("Axe", 60));
		activities.add(new Activity("Puzzle", 30));
		activities.add(new Activity("Graffiti", 60));
		activities.add(new Activity("Cricket", 60));
		activities.add(new Activity("Wine", 15));
		activities.add(new Activity("Arduino", 30));
		activities.add(new Activity("Tresure", 60));
		activities.add(new Activity("Enigma", 45));
		activities.add(new Activity("Haka", 30));
		activities.add(new Activity("Tracker", 15));
		
		digitalAwayDay.addActivities(activities);
		//Added successfully without exception
		
	}
	
	@Test(expected = DigitalAwayDayException.class )
	public void addExcessActivitiesFail() throws DigitalAwayDayException {
		DigitalAwayDay digitalAwayDay = new DigitalAwayDay(eventSlots, extratime, numberOfEvents);
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(new Activity("Duck Herding", 60));
		activities.add(new Activity("Archery ", 45));
		activities.add(new Activity("Magic", 40));
		activities.add(new Activity("Clay", 60));
		activities.add(new Activity("Football", 30));
		activities.add(new Activity("Driving", 30));
		activities.add(new Activity("Salsa", 15));
		activities.add(new Activity("Segways", 45));
		activities.add(new Activity("Axe", 60));
		activities.add(new Activity("Puzzle", 30));
		activities.add(new Activity("Graffiti", 60));
		activities.add(new Activity("Cricket", 60));
		activities.add(new Activity("Wine", 15));
		activities.add(new Activity("Arduino", 30));
		activities.add(new Activity("Tresure", 60));
		activities.add(new Activity("Enigma", 45));
		activities.add(new Activity("Haka", 30));
		activities.add(new Activity("Tracker", 15));
		activities.add(new Activity("Tresure1", 60));
		activities.add(new Activity("Tresure2", 60));
		
		digitalAwayDay.addActivities(activities);
		//DigitalAwayDayException
		
	}

}
