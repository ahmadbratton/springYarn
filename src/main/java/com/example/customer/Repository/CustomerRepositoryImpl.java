package com.example.customer.Repository;

import com.example.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_SQL = "INSERT INTO customer (firstname, lastname, phone, email) VALUES (?, ?, ?, ?)";
    @Override
    public void add(Customer customer) {
        jdbcTemplate.update(INSERT_SQL, customer.getFirstname(), customer.getLastname(), customer.getPhone(),
                customer.getEmail());
    }

    private final String UPDATE_SQL = "update customer set firstname=?, lastname=?, phone=?, email=? where id=?";
    @Override
    public void update(Customer customer) {
        jdbcTemplate.update(UPDATE_SQL, customer.getFirstname(), customer.getLastname(), customer.getPhone(), customer.getEmail(), customer.getId());
    }
    private final String SELECT_BY_ID_SQL = "select * from customer where id =?";
    @Override
    public Customer getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new customerMapper(), id);
    }
    private final String SELECT_SQL = "select * from customer";
    @Override
    public List<Customer> get() {
        return jdbcTemplate.query(SELECT_SQL, new customerMapper());
    }
    private final String DELETE_SQL = "delete from customer where id=?";
    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    private static class customerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setFirstname(resultSet.getString("firstname"));
            customer.setLastname(resultSet.getString("lastname"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPhone(resultSet.getString("phone"));
            return customer;
        }

    }



}
