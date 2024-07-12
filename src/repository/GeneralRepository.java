package repository;

import model.User;

import java.util.Optional;

public interface GeneralRepository {
    public Optional<User> findByEmail(String email);
    public void create(User user);
    public void update(User user);
    public void delete(Long id);
}
