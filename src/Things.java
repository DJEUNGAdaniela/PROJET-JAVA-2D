import java.awt.*;

// La classe Things représente des objets génériques avec une position et une image
public class Things {
    // Coordonnée x de l'objet
    protected double x;

    // Coordonnée y de l'objet
    protected double y;

    // Largeur de l'objet
    protected int width;

    // Hauteur de l'objet
    protected int height;

    // Image associée à l'objet
    protected Image image;

    // Constructeur pour un objet avec dimensions définies
    public Things(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Constructeur pour un objet avec une image
    public Things(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    // Méthode pour dessiner l'objet sur un contexte graphique
    public void draw(Graphics g){
        // Dessine l'image de l'objet aux coordonnées spécifiées
        g.drawImage(image, (int) x, (int) y, null);
    }
}
