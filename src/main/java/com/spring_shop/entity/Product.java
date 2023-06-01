package com.spring_shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.File;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int price;
    private String description;
    private String imgPath;

    @ManyToOne
    private Category category;
}
