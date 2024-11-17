package com.bulatmain.authorization.infrastructure.port;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeEditConferenceReq {
    private String userId;
    private String conferenceId;
}
