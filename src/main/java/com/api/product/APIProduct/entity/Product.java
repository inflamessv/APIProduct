package com.api.product.APIProduct.entity;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class Product{
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private Double precio;
    @Column
    @NotNull
    private Integer quantity;

    public Product(Long id, String name, Double precio, Integer quantity) {
        this.id = id;
        this.name = name;
        this.precio = precio;
        this.quantity = quantity;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}