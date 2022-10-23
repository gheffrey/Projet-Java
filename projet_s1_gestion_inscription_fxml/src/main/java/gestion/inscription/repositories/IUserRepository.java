package gestion.inscription.repositories;

import gestion.inscription.entities.User;

public interface IUserRepository {
    public User findUserByLoginAndPassword(String login,String password);
}
