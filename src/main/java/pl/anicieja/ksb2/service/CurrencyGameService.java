package pl.anicieja.ksb2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.anicieja.ksb2.model.currency.Currency;
import pl.anicieja.ksb2.model.currency.Message;
import pl.anicieja.ksb2.model.currency.Rates;

@Service
@Getter
public class CurrencyGameService {

    private Double currencyPLN;

    public CurrencyGameService() {
        setCurrencyPLN();
    }

    public void setCurrencyPLN() {
        RestTemplate restTemplate = new RestTemplate();
        Currency currency = restTemplate.getForObject("https://api.exchangeratesapi.io/latest", Currency.class);
        currencyPLN = currency.getRates().getPLN();
    }

    public Message getComparisonResult(Double providedValue) throws JsonProcessingException, ParseException {
        Message message = new Message();
        if (providedValue > this.currencyPLN) {
            message.setMessage("Your value is too high");
        } else if (providedValue < this.currencyPLN) {
            message.setMessage("Your value is too low");
        } else {
            message.setMessage("Brilliant! It's exactly " + providedValue);
        }
        return message;
    }
}
