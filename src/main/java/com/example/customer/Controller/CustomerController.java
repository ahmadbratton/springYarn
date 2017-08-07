package com.example.customer.Controller;



import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/")
    public String getHome(){
        return "home";
    }


    @RequestMapping("/customer")
    public String getCustomers(){
        return "customers";
    }

    @RequestMapping("/add_customer")
    public String addCustomer(){
        return "add-customer";
    }



    @RequestMapping("/create-customer")
    public String createCustomer(@RequestParam(value = "firstname") String first, @RequestParam(value = "lastname") String last, @RequestParam(value = "email") String email, @RequestParam(value = "phone") String phone, Model model ){
        Customer customer = new Customer();
        customer.setFirstname(first);
        customer.setLastname(last);
        customer.setEmail(email);
        customer.setPhone(phone);

        customerService.add(customer);


        return "redirect:/customers";
    }

    @RequestMapping("/customers")
    public String viewCustomers(Model model){

        List<Customer> listOfCustomers = customerService.get();

        model.addAttribute("customers", listOfCustomers);
        return "customers";
    }

    @RequestMapping("/{id}")
    public String viewcustomer(@PathVariable int id, Model model){
        Customer customerById = customerService.getById(id);

        model.addAttribute("customer", customerById);
        return "one_customer";
    }



}

