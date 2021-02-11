package customer.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationService {

    public boolean matchCriteria(String code, String phoneNumber) {
        Pattern pattern;
        Matcher matcher;
        boolean match = false;

        switch (code) {
            case "237":
                pattern = Pattern.compile("\\(237\\)\\ ?[2368]\\d{7,8}$");
                matcher = pattern.matcher(phoneNumber);
                if (matcher.matches()) {
                    match = true;
                }
                break;
            case "251":
                pattern = Pattern.compile("\\(251\\)\\ ?[1-59]\\d{8}$");
                matcher = pattern.matcher(phoneNumber);
                if (matcher.matches()) {
                    match = true;
                }
                break;
            case "212":
                pattern = Pattern.compile("\\(212\\)\\ ?[5-9]\\d{8}$");
                matcher = pattern.matcher(phoneNumber);
                if (matcher.matches()) {
                    match = true;
                }
                break;
            case "258":
                pattern = Pattern.compile("\\(258\\)\\ ?[28]\\d{7,8}$");
                matcher = pattern.matcher(phoneNumber);
                if (matcher.matches()) {
                    match = true;
                }
                break;
            case "256":
                pattern = Pattern.compile("\\(256\\)\\ ?\\d{9}$");
                matcher = pattern.matcher(phoneNumber);
                if (matcher.matches()) {
                    match = true;
                }
                break;
        }

        return match;
    }

}
