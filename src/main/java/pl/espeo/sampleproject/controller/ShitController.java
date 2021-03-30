package pl.espeo.sampleproject.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.espeo.sampleproject.service.MyService;

@RestController
@RequestMapping("/api/v1")
public class ShitController {

    private final MyService myService;

    public ShitController(@Qualifier("compositeMyServiceImpl") MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/shit")
    public ResponseEntity<String> getPostById() {
        return new ResponseEntity<>(myService.someMethod(), HttpStatus.OK);
    }
}
