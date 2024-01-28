import javax.swing.*;
import java.awt.*;

// La classe SolidThings étend la classe abstraite Things
public class SolidThings extends Things {
    // Attribut représentant la boîte de collision (hitBox) de l'objet solide
    protected HitBox hitBox;

    // Constructeur prenant les coordonnées (x, y) ainsi que la largeur et la hauteur
    public SolidThings(int x, int y, int width, int height) {
        // Appel du constructeur de la classe parent Things avec les paramètres appropriés
        super(x, y, width, height);
        // Initialisation de la boîte de collision avec les mêmes paramètres
        this.hitBox = new HitBox(x, y, width, height);
    }

    // Constructeur prenant les coordonnées (x, y) et une image pour représenter l'objet solide
    public SolidThings(int x, int y, Image image) {
        // Appel du constructeur de la classe parent Things avec les paramètres appropriés
        super(x, y, image);
        // Initialisation de la boîte de collision avec les dimensions de l'image
        this.hitBox = new HitBox(x, y, image.getWidth(null), image.getHeight(null));
    }

    // Méthode permettant d'obtenir la boîte de collision de l'objet solide
    public HitBox getHitBox() {
        return hitBox;
    }
}
