import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TileManager {
    private final int width;
    private final int heigth; // Il y a une petite faute de frappe ici, c'est probablement "height" plutôt que "heigth".

    private Image[][] tiles;
    private BufferedImage tileSheet;

    public TileManager(int width, int height) {
        this.width = width;
        this.heigth = height;
        setTiles(width, height, "././img/tileSetTest.png");
    }

    public TileManager(int width, int height, String fileName){
        this.width = width;
        this.heigth = height;
        setTiles(width, height, fileName);
    }

    // Méthode privée pour initialiser les tuiles à partir d'une feuille de tuiles
    private void setTiles(int width, int height, String fileName){
        try {
            tileSheet = ImageIO.read(new File(fileName)); // Lecture de la feuille de tuiles depuis le fichier
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialisation du tableau d'images pour stocker les tuiles
        tiles = new Image[tileSheet.getWidth() / width][tileSheet.getHeight() / height];

        // Boucle pour extraire chaque tuile de la feuille de tuiles et la stocker dans le tableau d'images
        for (int y = 0; y + heigth < tileSheet.getHeight(); y = y + height) {
            for (int x = 0; x + width < tileSheet.getWidth(); x = x + width) {
                tiles[x / width][y / height] = tileSheet.getSubimage(x, y, width, height); // Extraction de la tuile à la position (x, y) dans la feuille de tuiles
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    // Méthode pour obtenir une tuile spécifique à partir de la position (x, y) dans la feuille de tuiles
    public Image getTile(int x, int y){
        return tiles[x][y];
    }
}
