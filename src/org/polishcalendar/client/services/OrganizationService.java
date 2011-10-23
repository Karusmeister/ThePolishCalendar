package org.polishcalendar.client.services;

import java.util.List;

import org.polishcalendar.shared.OrganizationDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("organization")
public interface OrganizationService extends RemoteService  {

	OrganizationDTO addOrganization(OrganizationDTO organization);
	void removeOrganization(OrganizationDTO organization);
	OrganizationDTO updateOrganization(OrganizationDTO organization);
	List<OrganizationDTO> fetchOrganization();
}
