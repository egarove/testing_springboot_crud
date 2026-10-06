package org.example.gestion_peticiones.controladores;

import org.example.gestion_peticiones.modelo.dao.UserDAO;
import org.example.gestion_peticiones.modelo.entidades.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/users"})
public class ControladorUser {

    @Autowired
    private UserDAO userDAO;

    @GetMapping
    public List<User> findAll() {
        return (List<User>) userDAO.findAll();
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<User> findByIdUser(@PathVariable Integer idUser) {
        Optional<User> user = userDAO.findById(idUser);

        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User u = userDAO.save(user);
        return ResponseEntity.ok(u);
    }

    @DeleteMapping ("/{idUser}")
    public ResponseEntity<Void> deleteByIdUser(@PathVariable Integer idUser) {

        if(userDAO.findById(idUser).isPresent()) {
            userDAO.deleteById(idUser);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping ("/{idUser}")
    public ResponseEntity<User> update(
            @PathVariable Integer idUser, @RequestBody User user) {

        if (!userDAO.existsById(idUser)) {
            return ResponseEntity.notFound().build();
        }

        user.setId(idUser);
        return ResponseEntity.ok(userDAO.save(user));
    }
}
