package andersen.lab.eshop.util;

import andersen.lab.eshop.domain.cart.Cart;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

@UtilityClass
public class CartSerializationUtil {

    public void writeToFile(Cart cart) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL url = classLoader.getResource("");
        try {
            File parentDirectory = new File(new URI(url.toString()));
            File file = new File(parentDirectory,  "temp" + cart.getId() + ".tmp");
            if(!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(cart);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Cart> readFromFile(Long id) {
        URL url = CartSerializationUtil.class.getResource("/storage");
        try {
            File parentDirectory = new File(new URI(url.toString()));
            File file = new File(parentDirectory,  "temp" + id + ".tmp");
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            return Optional.ofNullable((Cart)inputStream.readObject());
        } catch (URISyntaxException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
