package hello.servlet.basic.web.frontcontroller.v3;

import hello.servlet.basic.web.frontcontroller.ModelView;
import hello.servlet.basic.web.frontcontroller.MyView;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    Map<String, ControllerV3> controllers=new HashMap<>();

    public FrontControllerServletV3() {
        controllers.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllers.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllers.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri=request.getRequestURI();
        ControllerV3 controller=controllers.get(uri);
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> map = createParamMap(request);
        ModelView mv= controller.process(map);

        String viewNm=mv.getViewNm();
        MyView view = viewResolver(viewNm);
        view.render(mv.getModels(), request, response);
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
