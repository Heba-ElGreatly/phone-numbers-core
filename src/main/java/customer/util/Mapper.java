package customer.util;

import customer.dto.CustomerDTO;
import customer.model.Customer;

import java.util.function.Function;

@FunctionalInterface
public interface Mapper<Entity,DTO> {

    Function<Entity,DTO> mapEntityToDTO();
}
