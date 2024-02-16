package com.example.bankingapp.repository;

import com.example.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * @author Jana Metz on 22.01.24
 */
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    //void updateAccountById(String email, String firstName, String lastName, String country, String city, @NonNull String id);

   // void updateAmountOfMoneyById(@NonNull BigDecimal amountOfMoney, @NonNull String id);

    List<Account> findByCityIn(Collection<String> cities);

    List<Account> findByCityInAndCreationDate(Collection<String> cities, LocalDate dateCreation);

    List<Account> findByCityInOrderByCreationDateDesc(Collection<String> cities);

    List<Account> findByCityInAndCreationDateOrderByCreationDateDesc(Collection<String> cities, LocalDate dateCreation);

    List<Account> findByCityInOrderByCreationDateAsc(Collection<String> cities);

    List<Account> findByCityInAndCreationDateOrderByCreationDateAsc(Collection<String> cities, LocalDate dateCreation);

    @Query("select a from Account a order by a.creationDate DESC")
    List<Account> findAllOrderedDesc();

   @Query("select a from Account a order by a.creationDate")
   List<Account> findAllOrderedAsc();

    List<Account> findByCreationDate(LocalDate dateCreation);

    List<Account> findByCreationDateOrderByCreationDateDesc(LocalDate dateCreation);

    List<Account> findByCreationDateOrderByCreationDateAsc(LocalDate dateCreation);

    Account findById(String id);
}
