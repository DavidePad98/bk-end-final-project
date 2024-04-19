package bkendfinalproject.finalpj.controllers;

import bkendfinalproject.finalpj.entities.Prenotations;
import bkendfinalproject.finalpj.exceptions.BadRequestException;
import bkendfinalproject.finalpj.payloads.PrenotazioniDTO;
import bkendfinalproject.finalpj.services.PrenotationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenot")
public class PrenotationsController {
    @Autowired
    private PrenotationsService ps;

    @GetMapping("/{id}")
    private Prenotations getPrenotationsById(@PathVariable long id) {
        return ps.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deletePrenotations(@PathVariable long id) {
        ps.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Prenotations saveNewPrenotations(@RequestBody @Validated PrenotazioniDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return ps.save(payload);
    }
}
