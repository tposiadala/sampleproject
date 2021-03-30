package pl.espeo.sampleproject.service;

import org.springframework.stereotype.Service;

@Service("compositeMyServiceImpl")
public class CompositeMyServiceImpl implements MyService {

    @Override
    public String someMethod() {
        return "Composite Service!!!";
    }
}
