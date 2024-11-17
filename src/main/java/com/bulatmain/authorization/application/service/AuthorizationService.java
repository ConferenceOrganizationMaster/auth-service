package com.bulatmain.authorization.application.service;

import com.osohq.oso_cloud.api.ApiException;

import java.io.IOException;

public interface AuthorizationService {

    boolean authorizeActionOnConference(
            String userId,
            String action,
            String conferenceId
    ) throws IOException, ApiException;

    boolean authorizeEditConference(
            String userId,
            String conferenceId
    ) throws IOException, ApiException;


}
