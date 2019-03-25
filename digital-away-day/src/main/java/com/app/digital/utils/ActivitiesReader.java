package com.app.digital.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.app.digital.exceptions.DigitalAwayDayException;
import com.app.digital.models.Activity;

/**
 * Class to read activities from file
 * 
 * @author Lokendra
 *
 */
public class ActivitiesReader {

	static String REGEX = "^[a-zA-Z0-9][a-zA-Z0-9& -]* (([0-9]+min)|sprint)$";
	static String SPRINT = "sprint";

	/**
	 * method to load activities from file
	 * 
	 * @param fileName
	 * @return
	 * @throws DigitalAwayDayException
	 */
	public List<Activity> loadActivitiesFromFile(String fileName) throws DigitalAwayDayException {
		List<Activity> activities = new ArrayList<>();
		try {
			Path path = Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI());
			Stream<String> lines;
			lines = Files.lines(path);
			Iterator<String> iterator = lines.iterator();
			while (iterator.hasNext()) {
				String line = iterator.next();
				if (Pattern.matches(REGEX, line)) {
					Integer duration = this.getMinutes(line.substring(line.lastIndexOf(" ") + 1));
					activities.add(new Activity(line.substring(0, line.lastIndexOf(" ")), duration));
				} else {
					lines.close();
					throw new DigitalAwayDayException(
							"ActivitiesReader - loadActivitiesFromFile: File line " + line + " has not a valid format");
				}
			}
			lines.close();
		} catch (Exception ex) {
			throw new DigitalAwayDayException(
					"ActivitiesReader - loadActivitiesFromFile: there was a problem loading activities from file:"
							+ ex.getLocalizedMessage());
		}
		return activities;
	}

	/**
	 * Method to get activity duration - minute's part
	 * 
	 * @param value
	 * @return
	 * @throws DigitalAwayDayException
	 */
	private Integer getMinutes(String value) throws DigitalAwayDayException {
		Integer result = 0;
		if (SPRINT.equals(value))
			result = 15;
		else
			result = Integer.valueOf(value.substring(0, value.length() - 3));

		if (result <= 0) {
			throw new DigitalAwayDayException("ActivitiesReader - getMinutes: invalid activity duration: " + result);
		}
		return result;
	}

}
