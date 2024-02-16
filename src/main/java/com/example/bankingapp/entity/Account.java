package com.example.bankingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.LAZY;

/**
 * @author Jana Metz on 21.01.24
 */
@AllArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Transactional
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @NotBlank
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
  //  @GenericGenerator(name = "iban", strategy = "com.example.bankingApp.generator.GeneratorIbanId")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "date_creation")
    private LocalDate creationDate;

    @NotBlank(message = "First name shouldn't be blank")
    @Column(name = "f_Name")
    private String fName;

    @NotBlank(message = "Last name shouldn't be blank")
    @Column(name = "l_Name")
    private String lName;

    @NotBlank(message = "Country shouldn't be blank")
    @Column(name = "country")
    private String country;

    @NotBlank(message = "City shouldn't be blank")
    @Column(name = "city")
    private String city;

    @Column(name = "money_amount")
    private BigDecimal moneyAmount;

    @ToString.Exclude
    @ManyToMany(cascade = MERGE,fetch = LAZY)
    private Set<com.example.bankingapp.entity.Transaction> transactions = new LinkedHashSet<>();

    public void addTransaction(com.example.bankingapp.entity.Transaction tr) {

        transactions.add(tr);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email);
    }

}
