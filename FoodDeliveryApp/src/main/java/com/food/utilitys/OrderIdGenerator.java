package com.food.utilitys;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class OrderIdGenerator {

	
	
	public static String getId() {
		LocalDate date = LocalDate.now();
		int dayOfYear = date.getDayOfYear();
		LocalTime time = LocalTime.now();
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();
		return ""+dayOfYear+hour+minute+second+"";
		
		
	}
}
