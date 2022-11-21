package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void save(User user);
    void delete(int id);
    void update(int id, User user);
    User findOne(int id);
}
