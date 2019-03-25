# digital-away-day
digital-away-day is a maven project which reads a list of activities from a text file and schedule them based on available time on digital away day.

# Author
Lokendra Tomar

# Technologies
digital-away-day uses the following technologies:

- Java - 1.8
- Apache Maven - Build automation tool (version 3.6.0)
- junit - Library for testing (version 4.12)
- codehaus mojo plugin- Execute Java app plugin (version 1.6.0)


# Design 
Project is implemented in a way that it supports multiple breaks during event or multiple events running simultaneously.

- Activity          : Class to store activity with its start time and duration.

- ActivitySlot      : Class to store multiple activities, it has multiple activties, duration , a start time and an end time.

- ExtraTimeSlot     : Child class of ActivitySlot, holds extra time. Overrides getAvailableDuration method of ActivitySlot to provide                         extra time.

- EventSlot         : Class to store slot of an event. It has start time and end time. An even can have multiple slots.

- DayEvent          : This is an event of the day, there can be multiple events in the day which runs simultaneously.  Day event has                           multiple activity slots and extratime.

- DigitalAwayDay    : Class to represent digital away day, it can have multiple events.

- ActivitiesReader   : Reads input file of specific format.

- DigitalAwayDayException : Custom exception.

- StartDigitalAwayDay : Entry point of application , having main method.

- activities.txt      :  Input for application, present in src/main/resources folder.

# Mappings

DigitalAwayDay 1------>* DayEvent 1------->* AvtivitySlot(extended by ExtraTimeSlot) 1------->* Activity

# Instructions to run the application
 - digital-away-day requires java 1.8 and maven 3.x to run.
 - Input file activities.txt is present in src/main/resources folder
 
 Please use one of the below two ways to run the application
 - Way-1 :
 1. Checkout/download project.
 2. Unzip the folder if its a zip.
 3. Open a terminal and go to project folder ( digital-away-day)
 3. Execute below command to compile the code
    $ mvn clean package
 4. Execute below commnad to run the application
    $ mvn exec:java
 5. Output of application will be displayed in terminal.
 
 OR
 
 - Way 2:
 1. Checkout/download project.
 2. Unzip the folder if its a zip.
 3. Import project as maven project in eclipse or STS.
 4. Right click on class StartDigitalAwayDay.java and runs as java application.
 5. Output will be displayed in console.
 
 
 
 


