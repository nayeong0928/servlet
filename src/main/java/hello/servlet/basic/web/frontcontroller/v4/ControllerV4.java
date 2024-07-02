package hello.servlet.basic.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    public String process(Map<String, String> params, Map<String, Object> models);
}
