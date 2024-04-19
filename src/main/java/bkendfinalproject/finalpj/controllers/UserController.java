package bkendfinalproject.finalpj.controllers;

import bkendfinalproject.finalpj.entities.Events;
import bkendfinalproject.finalpj.entities.User;
import bkendfinalproject.finalpj.exceptions.BadRequestException;
import bkendfinalproject.finalpj.payloads.EventsDTO;
import bkendfinalproject.finalpj.payloads.UserDTO;
import bkendfinalproject.finalpj.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices us;

    @GetMapping
    private Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sort) {
        return us.getUsers(page, size, sort);
    }

    @GetMapping("/{id}")
    private User getUserById(@PathVariable long id) {
        return us.findById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteUser(@PathVariable long id) {
        us.findByIdAndDelete(id);
    }


    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser){
        return currentAuthenticatedUser;
    }


    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedUser){
        this.us.findByIdAndDelete(currentAuthenticatedUser.getId());
    }
}
