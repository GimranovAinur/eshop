package andersen.lab.eshop.web.servlet;

import andersen.lab.eshop.domain.User;
import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.domain.cart.CartItem;
import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.repository.jdbc.CartRepository;
import andersen.lab.eshop.repository.jdbc.ProductRepository;
import andersen.lab.eshop.service.CartService;
import andersen.lab.eshop.service.impl.CartServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCatalogController extends HttpServlet {

    private final CartService cartService;

    public ProductCatalogController() {
        cartService = new CartServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        List<Product> products = ProductRepository.findAll();

        User user = (User) session.getAttribute("current_user");
        Cart cart = user.getCart();
        session.setAttribute("products", products);
        session.setAttribute("cart", cart);
        getServletContext().getRequestDispatcher("/views/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException{
        String action = req.getParameter("action");
        HttpSession session = req.getSession(true);
        if(action.equalsIgnoreCase("add to cart")) {
            Cart cart = (Cart) session.getAttribute("cart");
            Long productId = Long.parseLong(req.getParameter("product_id"));
            Product product = ProductRepository.findById(productId).orElse(null);
            String amountValue = req.getParameter("amount"+productId);
            int amount = StringUtils.isNumeric(amountValue) ? Integer.parseInt(amountValue) : 1;
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setAmount(amount);
            cartService.addToCart(cart, cartItem);
            getServletContext().getRequestDispatcher("/views/catalog.jsp").forward(req, resp);
        }
    }

}
