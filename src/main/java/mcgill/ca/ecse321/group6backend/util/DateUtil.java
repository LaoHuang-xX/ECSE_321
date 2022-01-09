package mcgill.ca.ecse321.group6backend.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date StringToDate(String dateString) {
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-hh-mm");
	    Date date = null;
	    try {
			date = ft.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	 }
	 
	 public static String DateToDateString(Date date) {
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	    String dateString = null;
		dateString = ft.format(date);
		return dateString;
	 }
	 
	 public static String DateToTimeString(Date date) {
	    SimpleDateFormat ft = new SimpleDateFormat ("hh:mm");
	    String dateString = null;
		dateString = ft.format(date);
		return dateString;
	 }
}
