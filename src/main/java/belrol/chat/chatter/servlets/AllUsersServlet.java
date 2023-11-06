package belrol.chat.chatter.servlets;

import belrol.chat.chatter.mvc.controller.UserController;
import belrol.chat.chatter.mvc.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "all_users", value = "/all_users")
public class AllUsersServlet extends HttpServlet {
    UserController userController = new UserController();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(User user:userController.getAllUsers()){
            resp.getWriter().println(user.getUsername());
            resp.getWriter().println(user.getPwd());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
