<%@ page import="andersen.lab.eshop.domain.product.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Айнур
  Date: 26.03.2020
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>
</head>
<body>

    <form name="catalog" action="http://localhost:8080/e_shop_war_exploded/catalog" method="post">
        <%
            List<Product> productList = (List<Product>) session.getAttribute("products");
            if(productList != null) {
        %>
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Price</td>
                <td>Amount</td>
                <td>Add to Cart</td>
            </tr>
            <%
                for (Product product : productList) {
            %>
            <tr>
                <td><%= product.getId()%></td>
                <td><%= product.getName()%></td>
                <td><%= product.getPrice()%></td>
                <td><input type="text" name="amount<%= product.getId()%>"/></td>
                <td>
                    <input type="submit" name="action" value="add to cart"/>
                    <input type="hidden" name="product_id" value="<%= product.getId()%>">
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        %>
        <input type="submit" name="action" value="go to cart">
        <input type="hidden" name="page" value="catalog">
    </form>

</body>
</html>
