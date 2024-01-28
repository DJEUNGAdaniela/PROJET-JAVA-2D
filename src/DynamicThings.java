import javax.swing.*;
import java.awt.*;

// La classe DynamicThings étend la classe AnimatedThings
public class DynamicThings extends AnimatedThings {

    // Constructeur pour initialiser les propriétés de position et de taille
    public DynamicThings(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    // Constructeur pour initialiser les propriétés de position et utiliser une image
    public DynamicThings(int x, int y, Image image) {
        super(x, y, image);
    }

    // Méthode pour déplacer l'objet dynamique en fonction des valeurs spécifiées
    public void move(double moveX, double moveY){
        this.x = this.x + moveX;
        this.y = this.y + moveY;
    }

    // Méthode pour définir l'image associée à l'objet dynamique
    public void setImage(Image image){
        this.image = image;
    }
}
