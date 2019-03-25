package com.app.digital.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.app.digital.exceptions.DigitalAwayDayException;
import com.app.digital.models.Activity;
import com.app.digital.models.ActivitySlot;
import com.app.digital.models.DayEvent;
import com.app.digital.models.EventSlot;

public class DayEventTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void insertActivityTestSuccess() throws DigitalAwayDayException {
		List<EventSlot >eventSlots = new ArrayList<EventSlot>();
		Integer extraTime = 60;
		eventSlots.add(new EventSlot(LocalTime.of(9, 00), LocalTime.of(12, 00)));
		eventSlots.add(new EventSlot(LocalTime.of(13, 00), LocalTime.of(16, 00)));
		DayEvent dayEvent = new DayEvent(eventSlots, extraTime);
		
		ActivitySlot activitySlot = new ActivitySlot(180, LocalTime.of(9, 00), LocalTime.of(12, 00));
		Activity activity = new Activity("Test", 30);
		boolean result = dayEvent.insertActivity(activity, activitySlot);
		
		assertTrue("Result must be true", result);
	}
	
	@Test
	public void insertBiggerActivityTestFail() throws DigitalAwayDayException {
		List<EventSlot >eventSlots = new ArrayList<EventSlot>();
		Integer extraTime = 60;
		eventSlots.add(new EventSlot(LocalTime.of(9, 00), LocalTime.of(12, 00)));
		eventSlots.add(new EventSlot(LocalTime.of(13, 00), LocalTime.of(16, 00)));
		DayEvent dayEvent = new DayEvent(eventSlots, extraTime);
		
		ActivitySlot activitySlot = new ActivitySlot(180, LocalTime.of(9, 00), LocalTime.of(12, 00));
		Activity activity = new Activity("Test", 190);
		boolean result = dayEvent.insertActivity(activity, activitySlot);
		
		assertFalse("Result must be true", result);
	}
	
	@Test
	public void insertNullActivityTestFail() throws DigitalAwayDayException {
		List<EventSlot >eventSlots = new ArrayList<EventSlot>();
		Integer extraTime = 60;
		eventSlots.add(new EventSlot(LocalTime.of(9, 00), LocalTime.of(12, 00)));
		eventSlots.add(new EventSlot(LocalTime.of(13, 00), LocalTime.of(16, 00)));
		DayEvent dayEvent = new DayEvent(eventSlots, extraTime);
		
		ActivitySlot activitySlot = new ActivitySlot(180, LocalTime.of(9, 00), LocalTime.of(12, 00));
		boolean result = dayEvent.insertActivity(null, activitySlot);
		
		assertFalse("Result must be true", result);
	}
	
	@Test
	public void getMinEventDurationTestSuccess() throws DigitalAwayDayException {
		List<EventSlot >eventSlots = new ArrayList<EventSlot>();
		Integer extraTime = 60;
		eventSlots.add(new EventSlot(LocalTime.of(9, 00), LocalTime.of(10, 00)));
		eventSlots.add(new EventSlot(LocalTime.of(13, 00), LocalTime.of(14, 00)));
		DayEvent dayEvent = new DayEvent(eventSlots, extraTime);
		
		Integer result = dayEvent.getMinEventDuration();
		assertTrue("Result must be 120", result == 120);
	}
	
	@Test
	public void getMaxEventDurationTestSuccess() throws DigitalAwayDayException {
		List<EventSlot >eventSlots = new ArrayList<EventSlot>();
		Integer extraTime = 60;
		eventSlots.add(new EventSlot(LocalTime.of(9, 00), LocalTime.of(10, 00)));
		eventSlots.add(new EventSlot(LocalTime.of(13, 00), LocalTime.of(14, 00)));
		DayEvent dayEvent = new DayEvent(eventSlots, extraTime);
		
		
		Integer result = dayEvent.getMaxEventDuration();
		assertTrue("Result must be 180", result == 180);
	}

}
