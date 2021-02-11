package customer.model;

import lombok.Getter;

public enum CountriesEnum {

    Cameroon("237"),
    Ethiopia("251"),
    Morocco("212"),
    Mozambique("258"),
    Uganda("256");

//    Cameroon_237("Cameroon"),
//    Ethiopia_251("Ethiopia"),
//    Morocco_212("Morocco"),
//    Mozambique_258("Mozambique"),
//    Uganda_256("Uganda");

    @Getter
    private String countryCode;

    CountriesEnum(String countryCode) {
        this.countryCode = countryCode;
    }
}
