package org.polishcalendar.client.ical;

import java.util.List;

import org.polishcalendar.shared.EventDTO;


public interface ICalParser {
	List<EventDTO> generateEvents(String pathname);
	
	void generateICalFeed(List<EventDTO> events, String filename);
	}
