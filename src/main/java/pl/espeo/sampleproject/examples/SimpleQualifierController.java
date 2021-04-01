package pl.espeo.sampleproject.examples;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SimpleQualifierController {

    private final MyService myService;

    public SimpleQualifierController(@Qualifier("compositeMyServiceImpl") MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/message")
    public ResponseEntity<String> getQualifierMessage() {
        return new ResponseEntity<>(myService.someMethod(), HttpStatus.OK);
    }
}
