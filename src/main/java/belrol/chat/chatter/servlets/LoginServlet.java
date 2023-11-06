package belrol.chat.chatter.servlets;

import belrol.chat.chatter.mvc.controller.UserController;
import belrol.chat.chatter.mvc.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    UserController userController = new UserController();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        User user = userController.login(username, req.getParameter("pwd"));
        String msg;
        if(user != null){
            resp.addCookie(new Cookie("username", user.getUsername()));
            msg = "Welcome "+user.getUsername();
            resp.sendRedirect("index.jsp?msg="+msg);
        }
        else {
            msg = "Failed to log in! Wrong credential(s)";
            resp.sendRedirect("login.html?msg="+msg);
        }

    }
}
