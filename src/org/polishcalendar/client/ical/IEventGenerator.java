package org.polishcalendar.client.ical;

import java.util.List;

import org.polishcalendar.shared.EventDTO;


public interface IEventGenerator {
	List<EventDTO> generateEvents(String fileName);
}
