package by.htp.ex.util.date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class Date {
	private Date() {
	}

	public static String getDate() {
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("GMT+3"));
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = dateTimeFormatter.format(zonedDateTime);
		return date;
	}

}
