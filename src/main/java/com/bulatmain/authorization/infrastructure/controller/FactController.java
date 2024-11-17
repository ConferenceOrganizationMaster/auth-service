package com.bulatmain.authorization.infrastructure.controller;

import com.bulatmain.authorization.application.service.FactService;
import com.bulatmain.authorization.infrastructure.port.AssignConfOrganizerRoleReq;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/fact")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FactController {
    private FactService factService;

    @PostMapping("/assign-org-role")
    public ResponseEntity<Object> assignConfOrganizerRole(@RequestBody AssignConfOrganizerRoleReq request) {
        try {
            factService.setOrganizerRole(
                    request.getUserId(),
                    request.getConferenceId()
            );
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
