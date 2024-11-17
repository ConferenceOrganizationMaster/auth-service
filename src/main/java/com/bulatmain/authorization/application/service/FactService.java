package com.bulatmain.authorization.application.service;

import com.osohq.oso_cloud.api.ApiException;

import java.io.IOException;

public interface FactService {
    void setOrganizerRole(String userId, String conferenceId) throws IOException, ApiException;

    void setConferenceRole(String userId, String role, String conferenceId) throws IOException, ApiException;
}
