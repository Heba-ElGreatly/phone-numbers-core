package customer.service;

import customer.dto.CustomerDTO;
import customer.model.Customer;
import customer.repository.CustomerRepository;
import customer.util.ValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServicesTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ValidationService validationService;


    @Test
    public void getAllCustomersTest(){
        Integer pageSize = 5;
        Integer currentpage = 3;
        customerService.getCustomers();
    }

    @Test
    public void getCutomersDtoTest(){
        Integer pageSize = 5;
        Integer currentpage = 3;
        CustomerDTO dto = new CustomerDTO("JACKSON NELLY","(256) 775069443","valid","Uganda");
        CustomerDTO dto2 = new CustomerDTO("Kiwanuka Budallah","(256) 7503O6263","not valid","Uganda");
        List<CustomerDTO> list = new ArrayList<>();
        list.add(dto);
        list.add(dto2);
        when(customerRepository.findAll().stream().map(customerService.mapEntityToDTO()).collect(Collectors.toList())).thenReturn(list);
        assertEquals(41,customerService.getFilteredCustomers(pageSize,currentpage).size());
    }

    @Test
    public void validateNumbers(){
        // test with different patterns
        // this test will fail
        String patterns = "\\(237\\)\\ ?[236]\\d{7,8}$"
                            + "\\(251\\)\\ ?[1-5]\\d{8}$"
                            + "\\(212\\)\\ ?[5-9]\\d{8}$"
                            + "\\(258\\)\\ ?[28]\\d{7,8}$"
                            + "\\(256\\)\\ ?\\d{9}$";

        List<CustomerDTO> list = customerService.getCustomers();
        List<String> phoneNumbers = list.stream().map(x -> x.getPhoneNumber()).collect(Collectors.toList());

        Pattern pattern = Pattern.compile(patterns);
        for(String number : phoneNumbers) {
            Matcher matcher = pattern.matcher(number);
            assertTrue(matcher.matches());
        }
    }

    @Test
    public void matchedPatternsTest(){
        List<Customer> list = customerRepository.findAll();

        Map<String, List<Customer>> customerMap = list.stream().collect(Collectors.groupingBy(x -> x.getPhoneNumber().substring(1, 4)));
        customerMap.entrySet()
                .stream()
                .forEach(c -> c.getValue().stream().forEach(x -> {
                            boolean matches = validationService.matchCriteria(c.getKey(), x.getPhoneNumber());
                        })
                );
    }
}
