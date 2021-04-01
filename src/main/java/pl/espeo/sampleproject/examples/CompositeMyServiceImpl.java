package pl.espeo.sampleproject.examples;

import org.springframework.stereotype.Service;

@Service("compositeMyServiceImpl")
public class CompositeMyServiceImpl implements MyService {

    @Override
    public String someMethod() {
        return "Composite service message!!!";
    }
}

