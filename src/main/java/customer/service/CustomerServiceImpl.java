package customer.service;

import customer.dto.CustomerDTO;
import customer.model.Customer;
import customer.repository.CustomerRepository;
import customer.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ValidationService validationService;

    public Map<String, String> countries = new HashMap<>();

    @PostConstruct
    public void getAllCountries() {
        countries.put("237", "Cameroon");
        countries.put("251", "Ethiopia");
        countries.put("212", "Morocco");
        countries.put("258", "Mozambique");
        countries.put("256", "Uganda");
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return mapCustomers(customers);
    }

    @Override
    public List<CustomerDTO> getFilteredCustomers(Integer pageNo, Integer pageSize) {

        Pageable page = PageRequest.of(pageNo-1, pageSize);
        Page<Customer> customerList = customerRepository.findAll(page);
        List<CustomerDTO> list = mapCustomers(customerList.getContent());
        return list;
    }

    @Override
    public Function<Customer, CustomerDTO> mapEntityToDTO() {
        return entity -> {
            return CustomerDTO.builder()
                    .name(entity.getName())
                    .phoneNumber(entity.getPhoneNumber())
                    .build();
        };
    }

    private List<CustomerDTO> mapCustomers(List<Customer> customerList) {
        List<CustomerDTO> list = new ArrayList<>();
        Map<String, List<Customer>> customerMap = customerList.stream().collect(Collectors.groupingBy(x -> x.getPhoneNumber().substring(1, 4)));
        customerMap.entrySet()
                .stream()
                .forEach(c -> c.getValue().stream().forEach(x -> {
                            boolean matches = validationService.matchCriteria(c.getKey(), x.getPhoneNumber());
                            if (matches) {
                                CustomerDTO customerDto = new CustomerDTO(x.getName(), x.getPhoneNumber(), "valid", countries.get(c.getKey()));
                                list.add(customerDto);
                            } else {
                                CustomerDTO customerDto = new CustomerDTO(x.getName(), x.getPhoneNumber(), "not valid", countries.get(c.getKey()));
                                list.add(customerDto);
                            }
                        })
                );
        return list;
    }

}
