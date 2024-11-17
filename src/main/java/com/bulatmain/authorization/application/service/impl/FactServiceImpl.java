package com.bulatmain.authorization.application.service.impl;

import com.bulatmain.authorization.application.service.FactService;
import com.osohq.oso_cloud.Oso;
import com.osohq.oso_cloud.api.ApiException;
import com.osohq.oso_cloud.api.Value;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class FactServiceImpl implements FactService {
    private Oso oso;


    public void setOrganizerRole(String userId, String conferenceId) throws IOException, ApiException {
        setConferenceRole(userId, "organizer", conferenceId);
    }

    public void setConferenceRole(String userId, String role, String conferenceId) throws IOException, ApiException {
        oso.tell("has_role",
                new Value("User", userId),
                new Value(role),
                new Value("Conference", conferenceId)
        );
    }
}
