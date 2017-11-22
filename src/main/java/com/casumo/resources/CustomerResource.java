package com.casumo.resources;

import com.casumo.models.Customer;
import com.casumo.repositories.CustomerRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
	private final CustomerRepository customerRepository;

	public CustomerResource(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

    @GET
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @GET
    @Path("/{customerId}")
    public Customer getById(@PathParam("customerId") int customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new WebApplicationException("Can not find customer with id:" + customerId,404));
    }

    @POST
    public Customer create(Customer customer) {
        return customerRepository.insert(customer);
    }

    @PUT
    @Path("/{customerId}")
    public Customer update(@PathParam("customerId") int customerId, Customer customer) {
        customer.setId(customerId);
        return customerRepository.update(customer);
    }

    @DELETE
    @Path("/{customerId}")
    public Customer remove(@PathParam("customerId") int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new WebApplicationException("Can not find customer with id:" + customerId,404));
        return customerRepository.remove(customer);
    }
}
