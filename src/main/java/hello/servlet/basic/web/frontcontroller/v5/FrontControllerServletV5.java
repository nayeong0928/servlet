package hello.servlet.basic.web.frontcontroller.v5;

import hello.servlet.basic.web.frontcontroller.ModelView;
import hello.servlet.basic.web.frontcontroller.MyView;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    List<MyHandlerAdapter> adapters=new ArrayList<>();
    Map<String, Object> handlers=new HashMap<>();

    public FrontControllerServletV5() {
        initHandler();
        initAdapters();
    }

    private void initAdapters() {
        adapters.add(new ControllerV3HandlerAdapter());
        adapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandler() {
        handlers.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlers.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlers.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlers.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlers.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlers.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri=request.getRequestURI();
        Object handler=handlers.get(uri);

        if(handler==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);

        String viewNm=mv.getViewNm();
        MyView view = viewResolver(viewNm);
        view.render(mv.getModels(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : adapters) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalStateException("해당하는 핸들러가 없습니다.");
    }

    private static MyView viewResolver(String viewNm) {
        MyView view=new MyView("/WEB-INF/views/"+ viewNm +".jsp");
        return view;
    }

}
