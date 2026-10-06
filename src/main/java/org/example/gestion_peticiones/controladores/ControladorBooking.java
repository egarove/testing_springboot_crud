package org.example.gestion_peticiones.controladores;

import org.example.gestion_peticiones.modelo.dao.BookingDAO;
import org.example.gestion_peticiones.modelo.entidades.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/booking"})
public class ControladorBooking {

    @Autowired
    private BookingDAO bookingDAO;

    @GetMapping
    public List<Booking> findAll() {
        return (List<Booking>) bookingDAO.findAll();
    }

    @GetMapping("/{idBooking}")
    public ResponseEntity<Booking> findByIdBooking(@PathVariable Integer idBooking) {
        Optional<Booking> booking = bookingDAO.findById(idBooking);

        if(booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Booking> save(@RequestBody Booking booking) {
        Booking u = bookingDAO.save(booking);
        return ResponseEntity.ok(u);
    }

    @DeleteMapping ("/{idBooking}")
    public ResponseEntity<Void> deleteByIdBooking(@PathVariable Integer idBooking) {

        if(bookingDAO.findById(idBooking).isPresent()) {
            bookingDAO.deleteById(idBooking);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping ("/{idBooking}")
    public ResponseEntity<Booking> update(
            @PathVariable Integer idBooking, @RequestBody Booking booking) {

        if (!bookingDAO.existsById(idBooking)) {
            return ResponseEntity.notFound().build();
        }

        booking.setId(idBooking);
        return ResponseEntity.ok(bookingDAO.save(booking));
    }
}
