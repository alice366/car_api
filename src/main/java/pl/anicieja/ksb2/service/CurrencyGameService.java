package pl.anicieja.ksb2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.anicieja.ksb2.model.currency.Currency;
import pl.anicieja.ksb2.model.currency.Message;

@Service
public class CurrencyGameService {

    private Double currencyPLN;

    public CurrencyGameService() {
        setCurrencyPLN();
    }

    private void setCurrencyPLN() {
        RestTemplate restTemplate = new RestTemplate();
        Currency currency = restTemplate.getForObject("https://api.exchangeratesapi.io/latest", Currency.class);
        assert currency != null;
        currencyPLN = currency.getRates().getPLN();
    }

    public Double getCurrencyValue() {
        return this.currencyPLN;
    }

    public Message getComparisonResult(Double providedValue) {
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
