package org.polishcalendar.server.util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.polishcalendar.server.persistence.Event;
import org.polishcalendar.server.persistence.Organization;
import org.polishcalendar.server.persistence.User;
import org.polishcalendar.shared.EventDTO;
import org.polishcalendar.shared.OrganizationDTO;
import org.polishcalendar.shared.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappingUtils {
	
	private static final Logger logger 
		= LoggerFactory.getLogger(MappingUtils.class);

	public static void copyValues (Event to , EventDTO from) {
		//to.setId(from.getId());
		to.setLocation(from.getLocation());
		to.setName(from.getName());
		to.setAttendeesNumber(from.getAttendeesNumber());
		to.setStartDate(from.getStartDate());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Organization where name = '" + 
				from.getOrganizationName() + "'");
		Organization org_record = (Organization)q.uniqueResult();
		if (org_record != null) {
			to.setOrganization(org_record);
		}
	}
	
	public static void copyValues (EventDTO to , Event from) {
		to.setId(from.getId());
		to.setLocation(from.getLocation());
		to.setName(from.getName());
		to.setStartDate(from.getStartDate());
		to.setAttendeesNumber(from.getAttendeesNumber());
		
		Organization org_record = from.getOrganization();
		if (org_record != null) {
			to.setOrganizationName(org_record.getName());
		}
		else {
			logger.debug("Lazy loading of detached objects does not work, after all");
		}
		
	}
	
	public static void copyValues (OrganizationDTO to, Organization from) {
		to.setId(from.getId());
		to.setFollowersNumber(from.getFollowersNumber());
		to.setDescription(from.getDescription());
		to.setName(from.getName());
	}
	
	public static void copyValues(Organization to , OrganizationDTO from) {
		//to.setId(from.getId());
		to.setDescription(from.getDescription());
		to.setFollowersNumber(from.getFollowersNumber());
		to.setName(from.getName());
	}
	
	public static void copyValues(UserDTO to, User from) {
		to.setId(from.getId());
		to.setJoinedDate(from.getJoinedDate());
		to.setEmail(from.getEmail());
		to.setPassword(from.getPassword());
		to.setType(from.getType());
	}
	
	public static void copyValues(User to, UserDTO from) {
		//to.setId(from.getId());
		to.setJoinedDate(from.getJoinedDate());
		to.setEmail(from.getEmail());
		to.setPassword(from.getPassword());
		to.setType(from.getType());
	}
	
}
