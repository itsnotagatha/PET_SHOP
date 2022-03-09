package com.agata.petshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@Entity
@Table(name = "purchase_details")
public class PurchaseDetails {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Purchase purchase;

    @ManyToOne
    private Item item;

    @Column(name = "amount")
    @NotNull
    @NotEmpty
    private int amount;

}
