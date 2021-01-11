package pl.anicieja.ksb2.controller.currencygame;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.anicieja.ksb2.model.currency.Message;
import pl.anicieja.ksb2.service.CurrencyGameService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CurrencyGameController {

    CurrencyGameService currencyGameService;

    @Autowired
    public CurrencyGameController(CurrencyGameService currencyGameService) {
        this.currencyGameService = currencyGameService;
    }

    @GetMapping("/currency-game-java")
    public Double getName() {
        return currencyGameService.getCurrencyValue();
    }

    @PostMapping("/currency-game-java")
    public Message compareProvided(@RequestBody Double providedValue) {
        return this.currencyGameService.getComparisonResult(providedValue);
    }

}
