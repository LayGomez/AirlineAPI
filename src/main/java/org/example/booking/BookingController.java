package org.example.booking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/bookings")
public class BookingController {
    private final BookingServices services;

    public BookingController(BookingServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse booking = services.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }


    @GetMapping("/my-reservations")
    public ResponseEntity<List<BookingResponse>> getMyReservations() {
        List<BookingResponse> bookings = services.getAllBookingsForUser();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings (){
        return ResponseEntity.ok(services.getAllBookings());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable Long id, @RequestBody BookingRequest request) {
        BookingResponse updatedBooking = services.updateBooking(id, request);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteMyBooking(@PathVariable Long id){
        services.deleteMyBooking(id);
    }
}
