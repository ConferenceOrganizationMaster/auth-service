package com.bulatmain.authorization.infrastructure.controller;

import com.bulatmain.authorization.application.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorityController {
    private AuthorizationService authorizer;

    @GetMapping(path = "/edit")
    public ResponseEntity<Boolean> authorizeEditConference(
            @RequestParam("userId") String userId,
            @RequestParam("conferenceId") String conferenceId) {
        try {
            boolean isAuthorized = authorizer.authorizeEditConference(
                    userId,
                    conferenceId
            );
            return new ResponseEntity<>(isAuthorized, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
