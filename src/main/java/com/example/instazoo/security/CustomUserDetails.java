package com.example.instazoo.security;

import com.example.instazoo.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = user.getRoleUsers().stream()
                            .map(roleUser -> new SimpleGrantedAuthority(roleUser.getAuthority()))
                            .collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
