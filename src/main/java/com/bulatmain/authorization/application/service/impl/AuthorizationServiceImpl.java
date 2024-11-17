package com.bulatmain.authorization.application.service.impl;

import com.bulatmain.authorization.application.service.AuthorizationService;
import com.osohq.oso_cloud.Oso;
import com.osohq.oso_cloud.api.ApiException;
import com.osohq.oso_cloud.api.Value;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private Oso oso;

    public boolean authorizeActionOnConference(
            String userId,
            String action,
            String conferenceId
    ) throws IOException, ApiException {
        return oso.authorize(
                new Value("User", userId),
                action,
                new Value("Conference", conferenceId)
        );
    }

    public boolean authorizeEditConference(
            String userId,
            String conferenceId
    ) throws IOException, ApiException {
        return authorizeActionOnConference(userId, "edit", conferenceId);
    }


}
