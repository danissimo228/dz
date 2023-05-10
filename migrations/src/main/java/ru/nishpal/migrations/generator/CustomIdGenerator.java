package ru.nishpal.migrations.generator;

import lombok.extern.log4j.Log4j2;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
            throws HibernateException {

        Connection connection = sharedSessionContractImplementor.connection();

        try {
            Statement statement=connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) AS Id FROM users");

            if(resultSet.next())
            {
                Long id = (long) (resultSet.getInt(1) + 1);
                log.info("Generated id: " + id);
                return id;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }
}
