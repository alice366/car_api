package pl.anicieja.ksb2.controller.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductApi {

    //parameter
//    @GetMapping
////    @RequestMapping(method = RequestMethod.GET)
//    public String getProducts(@RequestParam String name, @RequestParam(required = false, defaultValue = "") String surname) {
//        return "GET text " + name + " " + surname;
//    }

    //path
//    @GetMapping("/{name}")
//    public String getProducts(@PathVariable String name) {
//        return "GET text " + name ;
//    }

    //header
//    @GetMapping
//    public String getProducts(@RequestHeader String name) {
//        return "GET text " + name ;
//    }

    //body
    @GetMapping
    public String getProducts(@RequestBody String name) {
        return "GET text " + name ;
    }

    public ResponseEntity<String> getName() {
        return new ResponseEntity("GET text ", HttpStatus.OK);
    }

    @PostMapping
    public String addProduct() {
        return "POST text";
    }

    @PutMapping
    public String modifyProduct() {
        return "PUT text";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "DELETE text";
    }
}

