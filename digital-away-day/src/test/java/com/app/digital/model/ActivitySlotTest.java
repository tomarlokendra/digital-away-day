package com.app.digital.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import com.app.digital.models.Activity;
import com.app.digital.models.ActivitySlot;

public class ActivitySlotTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void addActivitySuccess() {
		Activity activity = new Activity("Test", 30);
		
		ActivitySlot activitySlot = new ActivitySlot(60, LocalTime.of(9, 00), LocalTime.of(10, 00));
		boolean result = activitySlot.addActivity(activity);
		
		assertTrue(result);
	}
	
	@Test
	public void addBiggerActivityFail() {
		Activity activity = new Activity("Test", 70);
		
		ActivitySlot activitySlot = new ActivitySlot(60, LocalTime.of(9, 00), LocalTime.of(10, 00));
		boolean result  = activitySlot.addActivity(activity);
		
		assertFalse(result);
	}
	
	@Test
	public void addNullActivityFail() {
		ActivitySlot activitySlot = new ActivitySlot(60, LocalTime.of(9, 00), LocalTime.of(10, 00));
		boolean result =activitySlot.addActivity(null);
		assertFalse(result);
	}
	
	@Test
	public void getAvailableDurationTestSuccess() {
		ActivitySlot activitySlot = new ActivitySlot(60, LocalTime.of(9, 00), LocalTime.of(10, 00));
		Integer result =activitySlot.getAvailableDuration();
		
		assertTrue("Result must be 60", result == 60);
	}
	
	@Test
	public void getAvailableDurationAfterAddingActivityTestSuccess() {
		ActivitySlot activitySlot = new ActivitySlot(60, LocalTime.of(9, 00), LocalTime.of(10, 00));
		activitySlot.addActivity(new Activity("test", 40));
		Integer result =activitySlot.getAvailableDuration();
		
		assertTrue("Result must be 20", result == 20);
	}
	
	@Test
	public void getAvailableDurationAfterAddingFullActivityTestSuccess() {
		ActivitySlot activitySlot = new ActivitySlot(60, LocalTime.of(9, 00), LocalTime.of(10, 00));
		activitySlot.addActivity(new Activity("test", 60));
		Integer result =activitySlot.getAvailableDuration();
		
		assertTrue("Result must be 0", result == 0);
	}
	
	@Test
	public void getUsedDurationTestSuccess() {
		ActivitySlot activitySlot = new ActivitySlot(60, LocalTime.of(9, 00), LocalTime.of(10, 00));
		Integer result =activitySlot.getUsedDuration();
		
		assertTrue("Result must be 0", result == 0);
	}
	
	@Test
	public void getUsedDurationAfterAddingActivityTestSuccess() {
		ActivitySlot activitySlot = new ActivitySlot(60, LocalTime.of(9, 00), LocalTime.of(10, 00));
		activitySlot.addActivity(new Activity("test", 40));
		Integer result =activitySlot.getUsedDuration();
		
		assertTrue("Result must be 40", result == 40);
	}
	

}
