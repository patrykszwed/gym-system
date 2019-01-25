package com.botq.gymsystem;

import com.botq.gymsystem.models.User;
import com.botq.gymsystem.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GymSystemApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {


    }

    @Test
    public void testUserDate(){
        LocalDate expected = Calendar.getInstance().getTime().toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        User u = new User("johnysmith@gmail.com","John","Smith");
        userService.saveOrUpdateUser(u);
        User u2 = userService.findUserByEmail("johnysmith@gmail.com");
        userService.deleteUser("johnysmith@gmail.com");
        Assert.assertNotNull(u2);
        Assert.assertEquals(expected,u2.getRegistrationDate());
    }

}

