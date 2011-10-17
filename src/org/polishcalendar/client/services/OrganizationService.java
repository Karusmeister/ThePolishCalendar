package org.polishcalendar.client.services;

import org.polishcalendar.shared.OrganizationDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("organization")
public interface OrganizationService extends RemoteService  {

	OrganizationDTO addOrganization(OrganizationDTO organization);
	OrganizationDTO removeOrganization(OrganizationDTO organization);
	OrganizationDTO updateOrganization(OrganizationDTO organization);
	OrganizationDTO fetchOrganization(OrganizationDTO organization);
}
