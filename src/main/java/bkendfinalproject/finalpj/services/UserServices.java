package bkendfinalproject.finalpj.services;

import bkendfinalproject.finalpj.dao.UserDAO;
import bkendfinalproject.finalpj.entities.User;
import bkendfinalproject.finalpj.entities.UserRole;
import bkendfinalproject.finalpj.exceptions.BadRequestException;
import bkendfinalproject.finalpj.exceptions.NotFoundException;
import bkendfinalproject.finalpj.payloads.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserDAO uDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    public User save(UserDTO user){
        this.uDAO.findByEmail(user.email()).ifPresent(
                userloyee -> {throw new BadRequestException("L'email " + user.email() + " è già in uso!");
                }
        );
        User newUser = new User(
                user.username(),
                user.email(),
                bcrypt.encode(user.password()),
                user.role());
        return uDAO.save(newUser);
    }

    public Page<User> getUsers(int page, int size, String sort) {
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return uDAO.findAll(pageable);
    }

    public User findById(long id) {
        return uDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        User found = this.findById(id);
        uDAO.delete(found);
    }


    public User findByEmail(String email){
        return uDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Dipendente non trovato"));
    }

}
