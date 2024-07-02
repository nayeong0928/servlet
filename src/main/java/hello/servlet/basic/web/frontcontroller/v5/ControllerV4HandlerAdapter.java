package hello.servlet.basic.web.frontcontroller.v5;

import hello.servlet.basic.web.frontcontroller.ModelView;
import hello.servlet.basic.web.frontcontroller.v4.ControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        if(handler instanceof ControllerV4) return true;
        return false;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV4 controller=(ControllerV4) handler;

        Map<String, String> params=createParamMap(request);
        Map<String, Object> models = new HashMap<>();

        String viewNm = controller.process(params, models);
        ModelView mv=new ModelView(viewNm);
        mv.setModels(models);
        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> map=new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(param->map.put(param, request.getParameter(param)));
        return map;
    }
}
