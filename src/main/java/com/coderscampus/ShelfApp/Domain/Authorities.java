package com.coderscampus.ShelfApp.Domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Authorities implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Authorities(String authority, User user) {
        super();
        this.authority = authority;
        this.user = user;
    }

    public Authorities() {

    }

    @Override
    public String getAuthority() {
        return authority;
    }


    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
