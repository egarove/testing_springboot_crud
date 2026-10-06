package org.example.gestion_peticiones.modelo.dao;
import org.example.gestion_peticiones.modelo.entidades.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDAO extends CrudRepository<Booking,Integer> {
}
