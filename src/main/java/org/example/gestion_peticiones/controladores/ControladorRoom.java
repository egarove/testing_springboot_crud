package org.example.gestion_peticiones.controladores;

import org.example.gestion_peticiones.modelo.dao.RoomDAO;
import org.example.gestion_peticiones.modelo.entidades.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/rooms"})
public class ControladorRoom {

    @Autowired
    private RoomDAO roomDAO;

    @GetMapping
    public List<Room> findAll() {
        return (List<Room>) roomDAO.findAll();
    }

    @GetMapping("/{idRoom}")
    public ResponseEntity<Room> findByIdRoom(@PathVariable Integer idRoom) {
        Optional<Room> room = roomDAO.findById(idRoom);

        if(room.isPresent()) {
            return ResponseEntity.ok(room.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Room> save(@RequestBody Room room) {
        Room u = roomDAO.save(room);
        return ResponseEntity.ok(u);
    }

    @DeleteMapping ("/{idRoom}")
    public ResponseEntity<Void> deleteByIdRoom(@PathVariable Integer idRoom) {

        if(roomDAO.findById(idRoom).isPresent()) {
            roomDAO.deleteById(idRoom);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping ("/{idRoom}")
    public ResponseEntity<Room> update(
            @PathVariable Integer idRoom, @RequestBody Room room) {

        if (!roomDAO.existsById(idRoom)) {
            return ResponseEntity.notFound().build();
        }

        room.setId(idRoom);
        return ResponseEntity.ok(roomDAO.save(room));
    }
}
