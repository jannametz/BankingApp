package com.example.bankingapp.repository;

import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.entity.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Jana Metz on 22.01.24
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
  /*  List<Transaction> findByCreationDate(LocalDate creationDate);

    List<Transaction> findByType(TransactionType type);

    List<Transaction> findByTypeAndCreationDate(TransactionType type, LocalDate creationDate);

    List<Transaction> findAllOrderedDesc();

    List<Transaction> findByCreationDateOrderByCreationDateDesc(LocalDate creationDate);

    List<Transaction> findByTypeOrderByCreationDateDesc(TransactionType type);

    List<Transaction> findByTypeAndCreationDateOrderByCreationDateDesc(TransactionType type, LocalDate creationDate);

    List<Transaction> findAllOrderedAsc();

    List<Transaction> findByCreationDateOrderByCreationDateAsc(LocalDate dateTime);

    List<Transaction> findByTypeOrderByCreationDateAsc(TransactionType type);

    List<Transaction> findByTypeAndCreationDateOrderByCreationDateAsc(TransactionType type, LocalDate dateTime);
*/

}
