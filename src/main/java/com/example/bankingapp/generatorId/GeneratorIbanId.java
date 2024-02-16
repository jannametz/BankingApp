package com.example.bankingapp.generatorId;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.iban4j.Iban;

import java.io.Serializable;

/**
 * @author Jana Metz on 21.01.24
 */
@AllArgsConstructor
public class GeneratorIbanId implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return Iban.random().toFormattedString();
    }
}
