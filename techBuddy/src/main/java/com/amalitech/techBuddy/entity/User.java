package com.amalitech.techBuddy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Role> role = new ArrayList<>();
    @OneToMany(mappedBy = "owner")
    private List<Asset> asset = new ArrayList<>();
}
