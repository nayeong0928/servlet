package hello.servlet.basic.web.frontcontroller.v1;

import hello.servlet.basic.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.basic.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.basic.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerSevletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerSevletV1 extends HttpServlet {

    Map<String, ControllerV1> controllers=new HashMap<>();

    public FrontControllerSevletV1(){
        controllers.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllers.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllers.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url= request.getRequestURI();
        System.out.println("url = " + url);

        ControllerV1 controller=controllers.get(url);

        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
    }
}
