package com.example.backback.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "password"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        }),
        @UniqueConstraint(columnNames = {
                "firstname"
        }),
        @UniqueConstraint(columnNames = {
                "lastname"
        }),
        @UniqueConstraint(columnNames = {
                "city"
        }),


})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    @JsonIgnore
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstname;
    @NotBlank
    @Size(min = 3, max = 50)
    private String lastname;
    @NotBlank
    @Size(min = 3, max = 50)
    private String city;
    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @Lob
    private String image;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Long id, String username, String password,String firstname, String lastname, String city, String email, String image, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.email = email;
        this.image = image;
        this.roles = roles;
    }

    public User(@NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String encode, @NotBlank @Size(min = 3, max = 50) String firstname, @NotBlank @Size(min = 3, max = 50) String lastname, @NotBlank @Size(min = 3, max = 50) String city, @NotBlank @Size(max = 50) @Email String email) {
        this.username = username;
        this.password = encode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.email = email;
    }

    public User(Long id, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password, @NotBlank @Size(min = 3, max = 50) String firstname, @NotBlank @Size(min = 3, max = 50) String lastname, @NotBlank @Size(min = 3, max = 50) String city, @NotBlank @Size(max = 50) @Email String email, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.email = email;
        this.roles = roles;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
