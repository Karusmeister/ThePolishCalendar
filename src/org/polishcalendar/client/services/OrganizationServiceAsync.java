package org.polishcalendar.client.services;

import org.polishcalendar.shared.OrganizationDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OrganizationServiceAsync {

	void addOrganization(OrganizationDTO organization,
			AsyncCallback<OrganizationDTO> callback);

	void removeOrganization(OrganizationDTO organization,
			AsyncCallback<OrganizationDTO> callback);

}
