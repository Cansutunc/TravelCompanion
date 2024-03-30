// Import required packages
package com.energyxchange.EnergyXChange.model;

import java.util.Collection;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Define the buyer entity
@Entity
@Getter @Setter @NoArgsConstructor
@Data
@Builder
@AllArgsConstructor


// Map the entity to a table named "buyers"
@Table(name = "users")
public class User implements UserDetails {

    // Define the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String email;
    private String name;
    private String hobbie1;
    private String hobbie2;
    private String hobbie3;
    private String hobbie4;

    private String password;

    // Define the "role" attribute as an enumerated type
    @Enumerated(EnumType.STRING)
    private Role role;

    // Implement the UserDetails interface methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
