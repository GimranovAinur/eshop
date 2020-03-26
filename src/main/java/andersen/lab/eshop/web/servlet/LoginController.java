package andersen.lab.eshop.web.servlet;

import andersen.lab.eshop.domain.User;
import andersen.lab.eshop.repository.jdbc.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("current_user") == null) {
            getServletContext().getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            User user = new User();
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));
            session.setAttribute("current_user", user);
            session.setMaxInactiveInterval(60);
            resp.sendRedirect("/e_shop_war_exploded/catalog");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        Optional<User> userContainer = UserRepository.findByEmail(email);
        if(userContainer.isPresent()) {
            User user = userContainer.get();
            if(pass.equals(user.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("current_user", user);
                session.setMaxInactiveInterval(5*60);
                resp.sendRedirect("/e_shop_war_exploded/catalog");
            }
        } else {
            getServletContext().getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

}
