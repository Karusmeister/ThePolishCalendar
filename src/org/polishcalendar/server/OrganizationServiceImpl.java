package org.polishcalendar.server;

import org.polishcalendar.client.services.OrganizationService;
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
	public OrganizationDTO addOrganization(OrganizationDTO organization) {
		logger.debug(organization.toString());
		return organization;
	}
	@Override
	public OrganizationDTO removeOrganization(OrganizationDTO organization) {
		logger.debug(organization.toString());
		return organization;
	}

}
