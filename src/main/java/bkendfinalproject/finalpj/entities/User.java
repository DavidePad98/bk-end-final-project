package bkendfinalproject.finalpj.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"password", "role", "authorities", "accountNonExpired", "credentialsNonExpired", "accountNonLocked", "enabled"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToMany(mappedBy = "utente_id")
    private List<Prenotations> prenotazioni;

    public User(String username, String password, String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}