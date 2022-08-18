package by.htp.ex.util.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtil {
	private DateUtil() {
	}

	public static java.sql.Date getDate() {
		Date current = Date.from(Instant.now());
		java.sql.Date date = new java.sql.Date(current.getTime());
		return date;
	}

	public static LocalDate convertStrToDate(String dateStr) {
		dateStr="2022-11-01";
		return LocalDate.parse(dateStr);
	}

	public static String convertDateToStr(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return  localDate.format(formatter);
		
	}

}
