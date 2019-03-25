package com.app.digital.utils;

import org.junit.Before;
import org.junit.Test;

import com.app.digital.exceptions.DigitalAwayDayException;

public class ActivityReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void readActivitiesTestSuccess() throws DigitalAwayDayException {
		String fileName = "test_activities.txt";
		ActivitiesReader activityReader = new ActivitiesReader();
		
		activityReader.loadActivitiesFromFile(fileName);
		//No exception
	}

	@Test(expected =  DigitalAwayDayException.class)
	public void readInvalidActivitiesTestFail() throws DigitalAwayDayException {
		String fileName = "test_invalid_activities.txt";
		ActivitiesReader activityReader = new ActivitiesReader();
		
		activityReader.loadActivitiesFromFile(fileName);
		//No exception
	}
	
	@Test(expected =  DigitalAwayDayException.class)
	public void readWrongFileNameTestFail() throws DigitalAwayDayException {
		String fileName = "test_invalid_activities123.txt";
		ActivitiesReader activityReader = new ActivitiesReader();
		
		activityReader.loadActivitiesFromFile(fileName);
		//No exception
	}
}
