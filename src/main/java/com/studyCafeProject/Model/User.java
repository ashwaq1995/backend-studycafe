package com.studyCafeProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class User implements UserDetails {

    @Id
    private String id = UUID.randomUUID().toString().toUpperCase();

    @NotEmpty(message = "username is required")
//    @Length(min = 6,max = 20,message = "username have to be between 6 and 20 length range")
    private String username;

    @NotEmpty(message = "Password is required !")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
//            ,message = "Your password must be strong")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"
//            ,message = "Your password must be minimum eight characters, at least one letter and one number")
    private String password;

    @NotEmpty(message = "email is required")
    @Email(message = "You email is invalid")
    private String email;

    @NotEmpty(message = "phoneNo is required")
//    @Pattern(regexp ="^(009665|9665|\\+9665|05|5)(503649187)([0-9]{7})$", message = "invalid phone Number")
    private String phoneNo;

    @NotEmpty(message = "Role is required")
    @Pattern(regexp = "(ADMIN|USER)",message = "Role must be in (ADMIN|USER)")
    private String role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Booking> bookings;

//    @ManyToMany
//    private Set<Cafe> cafe;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "User",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Contact> contacts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
