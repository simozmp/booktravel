package logic.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static String parseDate(LocalDate date) {
		String parsedDate = date.format(dtf);
		return parsedDate;
	}
	
	public static LocalDate parseDate(String date) {		
		LocalDate parsedDate = LocalDate.parse(date, dtf);
		return parsedDate;
	}

}
