package com.klu.model;

import java.util.List;

import javax.ejb.Remote;

import com.klu.entity.User;

@Remote
public interface UserActions {
public String register(User u);
public String login(User u);
public List<User> getAllUsers();
}
