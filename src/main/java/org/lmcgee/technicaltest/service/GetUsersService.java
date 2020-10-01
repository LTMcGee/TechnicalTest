package org.lmcgee.technicaltest.service;

import org.lmcgee.technicaltest.model.User;

import java.util.List;

public interface GetUsersService {
    String getUsers();
    String convertJsonMessage(List<User> userList);
}
