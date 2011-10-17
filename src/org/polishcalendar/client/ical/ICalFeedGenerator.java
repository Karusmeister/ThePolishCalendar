package org.polishcalendar.client.ical;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.polishcalendar.shared.EventDTO;

public class ICalFeedGenerator implements IEventSaver {

	// Add tag writer
	@Override
	public void saveEvents(List<EventDTO> events, String filename) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		out.write("BEGIN:VCALENDAR\r\n");
		out.write("PRODID:-\r\n");//check this
		out.write("VERSION:2.0\r\n");
		out.write("CALSCALE:GREGORIAN\r\n");
		out.write("METHOD:PUBLISH\r\n");
		out.write("X-WR-CALNAME:Temp\r\n");
		out.write("X-WR-CALDESC:\r\n");
		for (EventDTO event : events) {
			out.write("BEGIN:VEVENT\r\n");
			out.write("DTSTART:" + parseDate(event.getStartDate()) + "\r\n");
			out.write("DTEND:" + parseDate(event.getEndDate()) + "\r\n");
			out.write("DESCRIPTION:\r\n");
			out.write("SUMMARY:" + event.getName() + "\r\n");
			out.write("LOCATION:" + event.getLocation() + "\r\n");
			out.write("END:VEVENT\r\n");
		}
		out.write("END:VCALENDAR\r\n");
		out.close();
	}

	@SuppressWarnings("deprecation")
	private String parseDate(Date date) {
		String s = new String();
		s = s + date.getYear();
		s = s + date.getMonth();
		s = s + date.getDate() + "T";
		s = s + date.getHours();
		s = s + date.getMinutes();
		s = s + date.getSeconds() + "Z";
		return s;
	}
}
