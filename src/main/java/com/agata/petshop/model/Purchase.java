package com.agata.petshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User user;

    @OneToMany
    List<PurchaseDetails> purchaseDetails;

    @Column(name = "price")
    @NotNull
    @NotEmpty
    private double price;
}
