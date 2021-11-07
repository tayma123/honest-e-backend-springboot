package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String object;
    private String subject;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "email")
    private String email ;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="email", insertable = false, updatable = false)
    private User user;
    public Complaint() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
