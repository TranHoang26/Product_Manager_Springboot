package com.codegym.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ValueGenerationType;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
}