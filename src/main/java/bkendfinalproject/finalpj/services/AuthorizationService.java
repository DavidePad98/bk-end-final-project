package bkendfinalproject.finalpj.services;

import bkendfinalproject.finalpj.entities.User;
import bkendfinalproject.finalpj.exceptions.UnauthorizedException;
import bkendfinalproject.finalpj.payloads.newUserLog;
import bkendfinalproject.finalpj.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private UserServices us;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUserAndGenerateToken(newUserLog payload) {
        User user = this.us.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }
    }
}
