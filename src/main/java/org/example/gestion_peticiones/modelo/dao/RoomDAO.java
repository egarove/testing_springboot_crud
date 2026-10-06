package org.example.gestion_peticiones.modelo.dao;
import org.example.gestion_peticiones.modelo.entidades.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDAO extends CrudRepository<Room,Integer> {
}
