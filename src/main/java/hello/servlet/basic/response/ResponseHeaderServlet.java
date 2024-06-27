package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        content(response);
        cookie(response);
        redirect(response);
    }

    private void redirect(HttpServletResponse response) throws IOException {

//        response.setStatus(HttpServletResponse.SC_FOUND);
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/htllo-form.html");
    }

    private void content(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-stroe, must-revalidate");
        response.setHeader("my-header", "hello");
        PrintWriter writer=response.getWriter();
        writer.println("안녕하세요.");
    }

    private void cookie(HttpServletResponse response) {
        Cookie cookie=new Cookie("myCookie", "good");
        cookie.setMaxAge(60);
        response.addCookie(cookie);
    }
}
