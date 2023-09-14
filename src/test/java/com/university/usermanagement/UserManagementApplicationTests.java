package com.university.usermanagement;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.university.usermanagement.integration.BaseIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.university.usermanagement.aggregates.user.User;
import com.university.usermanagement.controllers.RoleController;
import com.university.usermanagement.controllers.UserController;
import com.university.usermanagement.utilities.DefaultObject;

@SpringBootTest
class UserManagementApplicationTests extends BaseIntegrationTest {

    @Autowired
    private RoleController roleController;
    @Autowired
    private UserController userController;
    @Autowired
    private DefaultObject defaultObject;

    @Test
    void contextLoads() {
    }

    @Test
    public void readAllRolesTest() {
        Assertions.assertInstanceOf(List.class, roleController.readAllRoles());
    }

    @Test
    public void readAllUsersTest() {
        Assertions.assertInstanceOf(List.class, userController.readAllUsers());
    }

    @Test
    public void createUserTest() {
        User newUser = defaultObject.user();

        User createdAndRetrievedUser = userController.createUser(newUser);

        Assertions.assertEquals(newUser, createdAndRetrievedUser);
        assertThat(createdAndRetrievedUser.getUuid()).isNotBlank();

        userController.deleteUser(newUser.getSocialSecurityNumber());
    }

}
