package com.agata.petshop.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    @NotNull
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    public Item(String name, String color, double price, Category category) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;

        return id == item.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
