package customer.controller;


import customer.dto.CustomerDTO;
import customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers(){
        return new ResponseEntity<>(customerService.getCustomers(), new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/filter")
    public ResponseEntity<List<CustomerDTO>> getFilteredCustomers(@RequestParam(name = "pageNo") Integer pageNo,
                                                          @RequestParam(name = "pageSize") Integer pageSize){
        return new ResponseEntity<>(customerService.getFilteredCustomers(pageNo,pageSize), new HttpHeaders(), HttpStatus.OK);
    }
}
