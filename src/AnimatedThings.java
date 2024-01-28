
import javax.swing.*;
import java.awt.*;
// La classe AnimatedThings étend SolidThings, indiquant qu'elle partage certaines caractéristiques avec des objets solides
public class AnimatedThings extends SolidThings {
    // Constructeur prenant les coordonnées (x, y), la largeur et la hauteur comme paramètres
    public AnimatedThings(int x, int y, int width, int height) {
        // Appelle le constructeur de la classe mère (SolidThings) avec les paramètres appropriés
        super(x, y, width, height);
    }

    // Constructeur prenant les coordonnées (x, y) et une image comme paramètres
    public AnimatedThings(int x, int y, Image image) {
        super(x, y, image);
    }


}


