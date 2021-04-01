package pl.espeo.sampleproject.examples;

import org.springframework.stereotype.Service;

@Service("simpleMyServiceImpl")
public class SimpleMyServiceImpl implements MyService {

    @Override
    public String someMethod() {
        return "Simple service message!!!";
    }
}
