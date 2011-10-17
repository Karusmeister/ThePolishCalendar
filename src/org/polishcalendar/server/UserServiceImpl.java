package org.polishcalendar.server;

import java.util.Calendar;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.hibernate.Session;
import org.polishcalendar.client.services.UserService;
import org.polishcalendar.server.persistence.User;
import org.polishcalendar.server.util.HibernateUtil;
import org.polishcalendar.server.util.MappingUtils;
import org.polishcalendar.shared.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl extends RemoteServiceServlet implements 
							 UserService {

	private static final long serialVersionUID = 4854895607913385725L;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public UserDTO addUser(UserDTO user_dto) {
		logger.debug("Executing user_dto add");
		logger.debug(user_dto.toString());
		
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
		// Creating user object
		User user_record = new User();
		MappingUtils.copyValues(user_record, user_dto);
		Calendar today = Calendar.getInstance(); 
		user_record.setJoinedDate(today.getTime());
        
        session.save(user_record);
        session.getTransaction().commit();
        
        user_dto.setId(user_record.getId());
		return user_dto;
	}

	@Override
	public UserDTO removeUser(UserDTO user_dto) {
		logger.debug("Executing user remove");
		logger.debug(user_dto.toString());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user_record = (User)session.load(User.class, user_dto.getId());
		if (user_record != null) {
			session.delete(user_record);
		}
        session.getTransaction().commit();
		
		return user_dto;
	}

	@Override
	public UserDTO updateUser(UserDTO user_dto) {
		logger.debug("Executing user remove");
		logger.debug(user_dto.toString());
		
		// Fetching User object
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user_record = (User)session.load(User.class, user_dto.getId());
		
		if (user_record != null) {
			// Updating detached object
			MappingUtils.copyValues(user_record , user_dto);
			session.update(user_record);
		}
		
		session.getTransaction().commit();
		return user_dto;
	}

	@Override
	public UserDTO fetchUser(UserDTO user_dto) {
		logger.debug("Executing user fetch");
		logger.debug(user_dto.toString());
		
		// Fetching User object
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user_record = (User)session.load(User.class, user_dto.getId());
		
		if (user_record != null) {
			MappingUtils.copyValues(user_dto, user_record);
		}
		session.getTransaction().commit();
		
		return user_dto;
	}

}
