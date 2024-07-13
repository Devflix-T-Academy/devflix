package controller.user;

import model.user.User;
import repository.GeneralRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserController implements GeneralRepository<User, String> {
    private List<User> users;

    public UserController() {
        this.users = new ArrayList<>();
    }

    public User authenticate(String email, String password) {
        User user = this.findUser(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Usuário autenticado com sucesso!");
        } else {
            System.out.println("E-mail ou senha inválidos");
        }

        return user;
    }

    private User findUser(String email) {
        Optional<User> user = this.users
                .stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
        return user.orElse(null);
    }

    @Override
    public void create(User user) {
        if (this.findUser(user.getEmail()) != null) {
            System.out.println("E-mail informado já cadastrado!");
            return;
        }
        this.users.add(user);
    }

    @Override
    public void update(User user) {
        this.users = this.users
                .stream()
                .map(curUser -> curUser.getEmail().equalsIgnoreCase(user.getEmail()) ? user : curUser)
                .toList();
    }


    @Override
    public void delete(String email) {
        if (this.users.remove(this.findUser(email))) {
            System.out.println("Usuário excluido com sucesso!");
            return;
        };

        System.out.println("Erro ao excluir usuário");
    }
}
