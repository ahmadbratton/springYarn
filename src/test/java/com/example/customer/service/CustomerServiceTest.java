package com.example.customer.service;

import com.example.customer.Repository.CustomerRepository;
import com.example.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;
    @Test
    public void testAddGet()  {
        String firstname = Long.toString(System.currentTimeMillis());
        String lastname = Long.toString(System.currentTimeMillis());
        String email = "work@yahoo.com";
        String phone = "558-765-8948";

        Customer customer1 = new Customer();
        customer1.setFirstname(firstname);
        customer1.setLastname(lastname);
        customer1.setEmail(email);
        customer1.setPhone(phone);
        customerService.add(customer1);

        List<Customer> customers = customerService.get();

        Customer customer2 = findInList(customers, firstname, lastname, email, phone);
        System.out.println(customer2+ "this is customer 2");
        Assert.assertNotNull(customer2);

        Customer customer3 = customerService.getById(customer2.getId());
        Assert.assertNotNull(customer3);
        Assert.assertEquals(firstname, customer3.getFirstname());
        Assert.assertEquals(lastname, customer3.getLastname());
    }

    @Test
    public void testUpdate()  {
        String firstname = Long.toString(System.currentTimeMillis());
        String lastname = Long.toString(System.currentTimeMillis());
        String email = "york@yahoo.com";
        String phone = "558-765-8948";




        Customer customer1 = new Customer();

        customer1.setFirstname(firstname);
        customer1.setLastname(lastname);
        customer1.setPhone(phone);
        customer1.setEmail(email);


        customerService.add(customer1);

        List<Customer> customers = customerService.get();

        Customer customer2 = findInList(customers, customer1.getFirstname(), customer1.getLastname(), customer1.getEmail(), customer1.getPhone());
        Assert.assertNotNull(customer2);

        String updateFirstName = Long.toString(System.currentTimeMillis());
        String updateLastName = Long.toString(System.currentTimeMillis());
        String updatedEmail = "work@yahoo.com";
        String updatedPhone = "558-765-8948";

        customer2.setFirstname(updateFirstName);
        customer2.setLastname(updateLastName);
        customer2.setEmail(updatedEmail);
        customer2.setPhone(updatedPhone);
        customerService.update(customer2);

        customers = customerService.get();

        Customer customer3 = findInList(customers, updateFirstName, updateLastName, updatedEmail, updatedPhone);
        Assert.assertNotNull(customer3);
        Assert.assertEquals(customer2.getId(), customer3.getId());
    }

    @Test
    public void testDelete() {
        String firstname = Long.toString(System.currentTimeMillis());
        String lastname = Long.toString(System.currentTimeMillis());
        String email = "york@yahoo.com";
        String phone = "558-765-8948";




        Customer customer1 = new Customer();

        customer1.setFirstname(firstname);
        customer1.setLastname(lastname);
        customer1.setPhone(phone);
        customer1.setEmail(email);
        customerService.add(customer1);

        List<Customer> customers = customerService.get();

       Customer customer2 = findInList(customers, customer1.getFirstname(), customer1.getLastname(), customer1.getEmail(), customer1.getPhone());


        Assert.assertNotNull(customer2);

       customerService.delete(customer2.getId());

       customers = customerService.get();

       Customer shouldBeNull = findInList(customers, customer2.getFirstname(), customer2.getLastname(), customer2.getEmail(), customer2.getPhone() );

       Assert.assertNull(shouldBeNull);


    }

    private static Customer findInList(List<Customer> customers, String first, String last, String email, String phone) {
        // Find the new person in the list
        boolean found = false;
        for (Customer customer : customers) {
            System.out.println(customer.getFirstname() + customer.getLastname() + customer.getPhone() + customer.getEmail());
            System.out.println(first + last + email + phone);
            if (customer.getFirstname().equals(first) && customer.getLastname().equals(last) && customer.getEmail().equals(email) && customer.getPhone().equals(phone)) {
//            if (customer.getFirstname().equals(first) && customer.getLastname().equals(last) && customer.getEmail().equals(email) && customer.getPhone().equals(phone)) {
                System.out.println("+1");
                return customer;
            }
        }
        return null;
    }
}
