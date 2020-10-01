package org.lmcgee.technicaltest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.lmcgee.technicaltest.config.PropertiesConfig;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import org.lmcgee.technicaltest.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetUsersServiceImplTests {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    PropertiesConfig propertiesConfig;

    @Test
    public void testGetUsersResult() {
        String expectedResult = this.getExpectedUsersString();
        GetUsersService getUsersService = new GetUsersServiceImpl(propertiesConfig, objectMapper, restTemplate);

        String actualResult = getUsersService.getUsers();
        assertEquals(expectedResult, actualResult, "The actual result should match the expected string");
    }

    @Test
    public void testGetUsersCalls() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        ObjectMapper objectMapperMock = Mockito.mock(ObjectMapper.class);
        PropertiesConfig propertiesConfigMock = Mockito.mock(PropertiesConfig.class);

        String endpoint = "https://bpdts-test-app-v3.herokuapp.com/users";
        User[] usersList = getExpectedUsersArray();
        String usersString = getExpectedUsersString();

        GetUsersService getUsersService = new GetUsersServiceImpl(propertiesConfigMock, objectMapperMock, restTemplateMock);

        try {
            when(propertiesConfigMock.getAllUsersEndpoint()).thenReturn(endpoint);
            when(restTemplateMock.getForObject(eq(endpoint), any())).thenReturn(usersList);
            when(objectMapperMock.writeValueAsString(any())).thenReturn(usersString);

            getUsersService.getUsers();

            verify(propertiesConfigMock, times(1)).getAllUsersEndpoint();
            verify(restTemplateMock, times(1)).getForObject(eq(endpoint), any());
            verify(objectMapperMock, times(1)).writeValueAsString(any());
        } catch (JsonProcessingException e) {
            fail("An exception should not have been thrown");
        }
    }

    @Test
    public void testConvertJsonMessage() {
        String expectedResult = this.getExpectedUsersString();
        List<User> usersList = this.getExpectedUsersList();
        GetUsersService getUsersService = new GetUsersServiceImpl(propertiesConfig, objectMapper, restTemplate);

        String actualResult = getUsersService.convertJsonMessage(usersList);
        assertEquals(expectedResult, actualResult, "The actual result should match the expected string");
    }

    private String getExpectedUsersString() {
        return "[{\"id\":266,\"firstName\":null,\"lastName\":null,\"email\":\"agarnsworthy7d@seattletimes.com\",\"ipAddress\":null,\"latitude\":51.6553959,\"longitude\":0.0572553},{\"id\":322,\"firstName\":null,\"lastName\":null,\"email\":\"hlynd8x@merriam-webster.com\",\"ipAddress\":null,\"latitude\":51.6710832,\"longitude\":0.8078532},{\"id\":554,\"firstName\":null,\"lastName\":null,\"email\":\"phebbsfd@umn.edu\",\"ipAddress\":null,\"latitude\":51.5489435,\"longitude\":0.3860497}]";
    }

    private User[] getExpectedUsersArray() {
        User[] usersList = new User[3];

        try {
            usersList = objectMapper.readValue(getExpectedUsersString(), User[].class);
        } catch (JsonProcessingException e) {
            fail("An exception should not have been thrown");
        }
        return usersList;
    }

    private List<User> getExpectedUsersList() {
        List<User> usersList = new ArrayList<>();

        usersList = Arrays.asList(this.getExpectedUsersArray());
        return usersList;
    }
}
