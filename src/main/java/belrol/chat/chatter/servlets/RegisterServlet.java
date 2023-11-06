package belrol.chat.chatter.servlets;

import belrol.chat.chatter.mvc.controller.UserController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "register", value = "/register_user")
public class RegisterServlet extends HttpServlet {
    UserController userController = new UserController();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("register.html");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        response.sendRedirect("register.html?msg="+userController.registerUser(username, request.getParameter("pwd")));
    }
}
