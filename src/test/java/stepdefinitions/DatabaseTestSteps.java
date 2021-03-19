package stepdefinitions;

import database.CustomerDAO;
import database.Customer;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.sql.Connection;

public class DatabaseTestSteps {

    private final Connection connection;
    private Customer customer;
    private CustomerDAO customerDAO;


    public DatabaseTestSteps(Hooks hooks) {
        connection = hooks.returnConnection();
    }

    @Given("I create a new record in the customer table with first_name {string}, second_name {string}, email {string},phone {string}, address {string}, city {string}, state {string}, zipcode {string}")
    public void i_create_a_new_record_in_the_customer_table_with_first_name_second_name_email_phone_address_city_state_zipcode(String firstName, String lastName, String email,
                                                                                                                               String phone, String address, String city,
                                                                                                                               String state, String zipCode){
        customerDAO = new CustomerDAO(connection);
        customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setState(state);
        customer.setZipCode(zipCode);
        customerDAO.create(customer);
    }

    @Then("I validate the result")
    public void i_validate_the_result() {
        System.out.println(customerDAO.findById(customer.getID()));
        System.out.println("The customer details are" + customer);
    }
}
