package pl.espeo.sampleproject.service;

import org.springframework.stereotype.Service;

@Service("simpleMyServiceImpl")
public class SimpleMyServiceImpl implements MyService {

    @Override
    public String someMethod() {
        return "Simple Service!!!";
    }
}
