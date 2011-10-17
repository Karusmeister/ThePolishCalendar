package org.polishcalendar.client.ical;

import java.io.IOException;
import java.util.List;

import org.polishcalendar.shared.EventDTO;


public interface IEventSaver {


	void saveEvents(List<EventDTO> events,
			String filename) throws IOException;
}
