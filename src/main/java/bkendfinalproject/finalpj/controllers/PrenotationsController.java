package bkendfinalproject.finalpj.controllers;

import bkendfinalproject.finalpj.entities.Events;
import bkendfinalproject.finalpj.entities.Prenotations;
import bkendfinalproject.finalpj.exceptions.BadRequestException;
import bkendfinalproject.finalpj.payloads.EventsDTO;
import bkendfinalproject.finalpj.services.EventsServices;
import bkendfinalproject.finalpj.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenot")
public class PrenotationsController {

    @Autowired
    private EventsServices es;

    @Autowired
    private UserServices us;

    @GetMapping
    private Page<Prenotations> getAllPrenotations(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sort) {
        return es.(page, size, sort);
    }

    @GetMapping("/{id}")
    private Prenotations getPrenotationsById(@PathVariable long id) {
        return es.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZATOR')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deletePrenotations(@PathVariable long id) {
        es.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZATOR')")
    private Prenotations updatePrenotations(@PathVariable long id, @RequestBody @Validated EventsDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return es.findByIdAndUpdate(id, payload);
    }
}
