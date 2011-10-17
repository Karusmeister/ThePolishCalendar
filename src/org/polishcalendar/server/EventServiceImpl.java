package org.polishcalendar.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.polishcalendar.client.services.EventService;
import org.polishcalendar.server.persistence.Event;
import org.polishcalendar.server.util.HibernateUtil;
import org.polishcalendar.server.util.MappingUtils;
import org.polishcalendar.shared.EventDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EventServiceImpl extends RemoteServiceServlet implements
		EventService {

	private static final long serialVersionUID = 2084965182039303410L;
	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
	
	public EventServiceImpl() {}

	@Override
	public EventDTO addEvent(EventDTO event_dto) {
		logger.debug("Executing event add");
		logger.debug(event_dto.toString());
		
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
		// creating Event
		Event event_record = new Event();
		MappingUtils.copyValues(event_record, event_dto);
		Calendar today = Calendar.getInstance(); 
		event_record.setCreationDate(today.getTime());
        
        session.save(event_record);
        session.getTransaction().commit();
        
        event_dto.setId(event_record.getId());
		return event_dto;
	}

	@Override
	public void deleteEvent(EventDTO event_dto) {
		logger.debug("Executing event delete");
		logger.debug(event_dto.toString());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Event where EVENT_ID = '" + 
				event_dto.getId() + "'");
		Event event_record = (Event)q.uniqueResult();
		if (event_record != null) {
			session.delete(event_record);
		}
        session.getTransaction().commit();
	}

	@Override
	public EventDTO updateEvent(EventDTO event_dto) {
		logger.debug("Executing event update");
		logger.debug(event_dto.toString());
		
		// Fetching Event object
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event event_record = (Event)session.load(Event.class, event_dto.getId());
		
		if (event_record != null) {
			// Updating detached object
			MappingUtils.copyValues(event_record , event_dto);
			session.update(event_record);
		}
		
		session.getTransaction().commit();
		return event_dto;
	}

	@Override
	public List<EventDTO> fetchEvent() {
		logger.debug("Executing event fetch");
		List<EventDTO> output = new ArrayList<EventDTO>();
		
		// Fetching Event object
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query q = session.createQuery("from Event");
		@SuppressWarnings("unchecked")
		List<Event> events = (List<Event>)q.list();
		
		for (Event event_record : events) {
			EventDTO event_dto = new EventDTO();
			MappingUtils.copyValues(event_dto, event_record);
			output.add(event_dto);
		}
		
		session.getTransaction().commit();
		
		
		return output;
	}

}
