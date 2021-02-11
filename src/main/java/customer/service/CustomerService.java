package customer.service;

import customer.dto.CustomerDTO;
import customer.model.Customer;
import customer.util.Mapper;

import java.util.List;
import java.util.function.Function;

public interface CustomerService extends Mapper {

    List<CustomerDTO> getCustomers();
    List<CustomerDTO> getFilteredCustomers(Integer pageNo,Integer pageSize);
}
