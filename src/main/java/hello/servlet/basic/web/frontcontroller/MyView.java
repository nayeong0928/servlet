package hello.servlet.basic.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MyView {

    private String uri;

    public MyView(String uri){
        this.uri=uri;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> models, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        modelToRequest(models, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
        dispatcher.forward(request, response);
    }

    private static void modelToRequest(Map<String, Object> models, HttpServletRequest request) {
        models.entrySet()
                .iterator()
                .forEachRemaining((param)-> request.setAttribute(param.getKey(), param.getValue()));
    }
}
