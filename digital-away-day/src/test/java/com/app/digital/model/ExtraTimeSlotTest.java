package com.app.digital.model;

import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import com.app.digital.models.Activity;
import com.app.digital.models.ExtraTimeSlot;

public class ExtraTimeSlotTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getAvailableDurationTestSuccess() {
		ExtraTimeSlot activitySlot = new ExtraTimeSlot(120, LocalTime.of(9, 00), LocalTime.of(10, 00),30);
		Integer result =activitySlot.getAvailableDuration();
		
		assertTrue("Result must be 150", result == 150);
	}
	
	@Test
	public void getAvailableDurationAfterAddingActivityTestSuccess() {
		ExtraTimeSlot activitySlot = new ExtraTimeSlot(120, LocalTime.of(9, 00), LocalTime.of(10, 00),30);
		activitySlot.addActivity(new Activity("test", 40));
		Integer result =activitySlot.getAvailableDuration();
		
		assertTrue("Result must be 110", result == 110);
	}
	
	@Test
	public void getAvailableDurationAfterAddingFullActivityTestSuccess() {
		ExtraTimeSlot activitySlot = new ExtraTimeSlot(120, LocalTime.of(9, 00), LocalTime.of(10, 00),30);
		activitySlot.addActivity(new Activity("test", 150));
		Integer result =activitySlot.getAvailableDuration();
		
		assertTrue("Result must be 0", result == 0);
	}
	
}
