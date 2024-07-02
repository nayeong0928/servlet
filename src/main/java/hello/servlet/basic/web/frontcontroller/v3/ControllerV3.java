package hello.servlet.basic.web.frontcontroller.v3;

import hello.servlet.basic.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    public ModelView process(Map<String, String> map);
}
