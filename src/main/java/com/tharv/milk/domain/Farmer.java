package com.tharv.milk.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "farmer")
public class Farmer {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

}
