package com.in28minutes.rest.webservices.restfulwebservices.User;

import com.in28minutes.rest.webservices.restfulwebservices.ExceptionHandling.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static Integer userCount = 0;

    static {
        users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(26)));
        users.add(new User(++userCount, "Eve", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "John", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User addUser(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public void deleteUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return;
            }
        }
        throw new UserNotFoundException("User not found");
    }

}
