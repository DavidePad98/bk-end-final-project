package bkendfinalproject.finalpj.controllers;

import bkendfinalproject.finalpj.entities.Events;
import bkendfinalproject.finalpj.exceptions.BadRequestException;
import bkendfinalproject.finalpj.payloads.EventsDTO;
import bkendfinalproject.finalpj.services.EventsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsServices es;

    @GetMapping
    private Page<Events> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sort) {
        return es.getEvents(page, size, sort);
    }

    @GetMapping("/{id}")
    private Events getEventsById(@PathVariable long id) {
        return es.findById(id);
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ORGANIZATOR')")
    @ResponseStatus(HttpStatus.CREATED)
    private Events saveNewEvents(@RequestBody @Validated EventsDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return es.save(payload);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZATOR')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteEvents(@PathVariable long id) {
        es.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZATOR')")
    private Events updateEvents(@PathVariable long id, @RequestBody @Validated EventsDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return es.findByIdAndUpdate(id, payload);
    }
//    @PutMapping("{id}/users/{userId}")
//    @PreAuthorize("hasAuthority('EVENT_MANAGER')")
//    public Events addUserToEvent(@PathVariable long id, @PathVariable long userId) {
//        return es.addUserToEvent(id, userId);
//    }
}
