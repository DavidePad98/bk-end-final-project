package bkendfinalproject.finalpj.controllers;

import bkendfinalproject.finalpj.entities.Events;
import bkendfinalproject.finalpj.exceptions.BadRequestException;
import bkendfinalproject.finalpj.payloads.*;
import bkendfinalproject.finalpj.services.AuthorizationService;
import bkendfinalproject.finalpj.services.EventsServices;
import bkendfinalproject.finalpj.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorService;

    @Autowired
    private UserServices us;

    @Autowired
    private EventsServices es;

    @PostMapping("/login")
    public UserLogResponseDTO login(@RequestBody newUserLog payload){
        return new UserLogResponseDTO(this.authorService.authenticateUserAndGenerateToken(payload));
    }
//    @PostMapping("/login")
//    public UserLogResponseDTO login(@RequestBody newUserLog payload){
//        return authorService.authenticateUserAndGenerateToken(payload);
//    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private newUserResponseDTO saveEmployee(@RequestBody @Validated UserDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return new newUserResponseDTO(this.us.save(payload).getId());
    }

    @PostMapping("events/register")
    @ResponseStatus(HttpStatus.CREATED)
    private Events saveNewEvents(@RequestBody @Validated EventsDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return es.save(payload);
    }
}
