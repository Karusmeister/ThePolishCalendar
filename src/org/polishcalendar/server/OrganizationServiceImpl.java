package org.polishcalendar.server;

import java.util.Calendar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.polishcalendar.client.services.OrganizationService;
import org.polishcalendar.server.persistence.Organization;
import org.polishcalendar.server.util.HibernateUtil;
import org.polishcalendar.server.util.MappingUtils;
import org.polishcalendar.shared.OrganizationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class OrganizationServiceImpl extends RemoteServiceServlet 
									 implements OrganizationService  {

	private static final long serialVersionUID = -4663612999121644331L;
	private static final Logger logger 
		= LoggerFactory.getLogger(OrganizationServiceImpl.class);
	
	@Override
	public OrganizationDTO addOrganization(OrganizationDTO org_dto) {
		logger.debug("Executing organization add");
		logger.debug(org_dto.toString());
		
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
		// creating Organization
		Organization org_record = new Organization();
		MappingUtils.copyValues(org_record, org_dto);
		Calendar today = Calendar.getInstance(); 
		org_record.setCreationDate(today.getTime());
        
        session.save(org_record);
        session.getTransaction().commit();
        
        org_dto.setId(org_record.getId());
		return org_dto;
	}
	@Override
	public OrganizationDTO removeOrganization(OrganizationDTO org_dto) {
		logger.debug("Executing organization remove");
		logger.debug(org_dto.toString());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Organization where ORGANIZATION_ID = '" + 
				org_dto.getId() + "'");
		Organization org_record = (Organization)q.uniqueResult();
		if (org_record != null) {
			session.delete(org_record);
		}
        session.getTransaction().commit();
		return org_dto;
	}
	@Override
	public OrganizationDTO updateOrganization(OrganizationDTO org_dto) {
		logger.debug("Executing organization update");
		logger.debug(org_dto.toString());
		
		// Fetching Organization object
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Organization org_record = (Organization)session.load(Organization.class, org_dto.getId());
		
		if  (org_record != null) {
			// Updating detached object
			MappingUtils.copyValues(org_record , org_dto);
			session.update(org_record);
		}
		
		session.getTransaction().commit();
		
		return org_dto;
	}
	@Override
	public OrganizationDTO fetchOrganization(OrganizationDTO org_dto) {
		logger.debug("Executing organization fetch");
		logger.debug(org_dto.toString());

		// Fetching Organization object
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Organization org_record = (Organization)session.load(Organization.class, org_dto.getId());
		MappingUtils.copyValues(org_dto, org_record);
		session.getTransaction().commit();
		
		return org_dto;
	}

}
