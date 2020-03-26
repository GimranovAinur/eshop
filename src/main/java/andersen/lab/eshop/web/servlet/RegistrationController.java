package andersen.lab.eshop.web.servlet;


import andersen.lab.eshop.domain.User;
import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.exception.DatabaseException;
import andersen.lab.eshop.exception.EntityNotFoundException;
import andersen.lab.eshop.repository.jdbc.UserRepository;
import andersen.lab.eshop.service.UserService;
import andersen.lab.eshop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class RegistrationController extends HttpServlet {

    private UserService userService;

    public RegistrationController() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("current_user") != null){
            response.sendRedirect("/e_shop_war_exploded/catalog");
        }else{
            getServletContext()
                    .getRequestDispatcher("/views/registration.jsp")
                    .forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        try {
            userService.createUser(user);
            User currentUser = UserRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
            HttpSession session = request.getSession(true);
            session.setAttribute("current_user", currentUser);
            session.setMaxInactiveInterval(5*60);
            response.sendRedirect("/e_shop_war_exploded/catalog");
        } catch (DatabaseException e) {
            getServletContext()
                    .getRequestDispatcher("/views/registration.jsp")
                    .forward(request,response);
        }
    }
}
