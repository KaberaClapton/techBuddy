package com.amalitech.techBuddy.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tbl_assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;
}
