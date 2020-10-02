package org.lmcgee.technicaltest.controller;

import org.junit.jupiter.api.Test;
import org.lmcgee.technicaltest.service.GetUsersService;
import org.lmcgee.technicaltest.service.GetUsersServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetUsersControllerTests {

    @Mock
    GetUsersServiceImpl getUsersService;

    @InjectMocks
    private GetUsersController getUsersController;

    @Test
    public void testGetUsers() {
        when(getUsersService.getUsers()).thenReturn("TestUsersString");
        getUsersController.getUsers();
        assertTrue(true, "The controller should have been called with no errors");
    }
}
