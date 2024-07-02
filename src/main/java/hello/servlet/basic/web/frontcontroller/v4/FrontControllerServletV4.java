package hello.servlet.basic.web.frontcontroller.v4;

import hello.servlet.basic.web.frontcontroller.MyView;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    Map<String, ControllerV4> controllers=new HashMap<>();

    public FrontControllerServletV4() {
        controllers.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllers.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllers.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri=request.getRequestURI();
        ControllerV4 controller=controllers.get(uri);
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> params = createParamMap(request);
        Map<String, Object> models=new HashMap<>();
        String viewNm= controller.process(params, models);

        MyView view = viewResolver(viewNm);
        view.render(models, request, response);
    }

    private static MyView viewResolver(String viewNm) {
        MyView view=new MyView("/WEB-INF/views/"+ viewNm +".jsp");
        return view;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> map=new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(param->map.put(param, request.getParameter(param)));
        return map;
    }

}
