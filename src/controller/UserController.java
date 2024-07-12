package controller;

import model.User;
import repository.GeneralRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserController implements GeneralRepository {
    private List<User> users;

    public UserController() {
        this.users = new ArrayList<>();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = this.users.stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
        if (!user.isPresent()) {
            System.out.println("Usuário não localizado");
        };

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }
}
