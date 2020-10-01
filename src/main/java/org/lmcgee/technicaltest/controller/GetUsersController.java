package org.lmcgee.technicaltest.controller;

import lombok.extern.slf4j.Slf4j;
import org.lmcgee.technicaltest.service.GetUsersServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/getUsers")
@Slf4j
public class GetUsersController {

    private GetUsersServiceImpl getUsersService;

    public GetUsersController(GetUsersServiceImpl usersService) {
        this.getUsersService = usersService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUsers() {
        return getUsersService.getUsers();
    }
}
