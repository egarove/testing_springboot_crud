package org.example.gestion_peticiones.modelo.dao;

import org.example.gestion_peticiones.modelo.entidades.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {

}
