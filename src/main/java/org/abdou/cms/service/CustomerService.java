package org.abdou.cms.service;
import net.bytebuddy.implementation.bytecode.Throw;
import org.abdou.cms.dao.CustomerDAO;
import org.abdou.cms.exception.CustomerNotFoundException;
import org.abdou.cms.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    private int customerIdCount = 1 ;
    private List<Customer> customerList =  new CopyOnWriteArrayList<>();
   public Customer addCustomer(Customer customer){

       /*  customer.setCustomerId(customerIdCount);
         customerList.add(customer);
         customerIdCount++;
         return customer;*/
       return customerDAO.save(customer);
    }
    public List<Customer> getCustomers(){
       return customerDAO.findAll();
      //  return customerList;
    }
    public Customer getCustomer(int customerId) {

        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);
      if (!optionalCustomer.isPresent())
          throw new CustomerNotFoundException("Customer Record is not found");

       /* return customerList
               .stream()
               .filter(c -> c.getCustomerId() == customerId )
               .findFirst()
               .get();*/

       //return customerDAO.findById(customerId).get();
          return optionalCustomer.get();
    }

    public Customer updateCustomer(int customerId, Customer updatecustomer) {
        updatecustomer.setCustomerId(customerId);
       /*    customerList
                .stream()
                .forEach(c -> {
                    if (c.getCustomerId() == customerId) {
                        c.setCustomerFirstName(updatecustomer.getCustomerFirstName());
                        c.setCustomerLastName(updatecustomer.getCustomerLastName());
                        c.setCustomerEmail(updatecustomer.getCustomerEmail());
                    }
                });
        return customerList
                .stream()
                .filter(c -> c.getCustomerId() == customerId )
                .findFirst()
                .get();*/
        return customerDAO.save(updatecustomer);
    }

    public void deleteCustomer(int customerId) {
      /*  customerList
                .stream()
                .forEach( c -> {
                        if (c.getCustomerId() == customerId ) {
                    customerList.remove(c);
        }
    }); */
customerDAO.deleteById(customerId);
    }
}
