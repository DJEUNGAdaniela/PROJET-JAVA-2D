import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// Classe représentant le héros du jeu, qui est une sous-classe de DynamicThings
public final class Hero extends DynamicThings {

    private int health; // Nombre de vies du Héros
    private static volatile Hero instance = null; // Instance unique du héros (singleton)
    private Orientation orientation = Orientation.RIGHT; // Orientation du héros
    private boolean isWalking = false; // Indique si le héros est en mouvement

    private MainInterface mainInterface; // Référence vers l'interface principale

    // Constructeur prenant une référence vers l'interface principale en paramètre
    public Hero(MainInterface mainInterface) {
        super(120, 120, 48, 52); // Appelle le constructeur de la classe parent avec les dimensions du héros
        this.mainInterface = mainInterface;
        // Initialisation de la vie du héros
        this.health = 100;
        // ...
        try {
            this.setImage(ImageIO.read(new File("img/heroTileSheet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Constructeur privé sans paramètres utilisé pour mettre en œuvre le singleton
    private Hero() {
        super(120, 120, 48, 52);
        this.health = 100; // Ce que j'ai ajouté
        try {
            this.setImage(ImageIO.read(new File("img/heroTileSheet.png"))); // Charge l'image du héros depuis un fichier
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // Méthode pour décrémenter la santé du héros et vérifier s'il est mort
    public void decreaseHealth(int amount) {
        health -= amount;
        if (health <= 0 && mainInterface != null) {
            mainInterface.GameOver(); // Affiche l'écran de Game Over si la vie atteint zéro
        }
    }

    public final static Hero getInstance() {
        if (Hero.instance == null) {
            synchronized (Hero.class) {
                if (Hero.instance == null) {
                    Hero.instance = new Hero();
                }
            }
        }
        return Hero.instance;
    }

    // Méthode pour avoir l'orientation du héros
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public boolean isWalking() {
        return isWalking;
    } // Méthode pour savoir si le héros est en mouvement

    public Orientation getOrientation() {
        return orientation;
    } // Méthode pour obtenir l'orientation du héros

    // Méthode pour déplacer le héros s'il est possible
    public void moveIfPossible(double dx, double dy, Dungeon dungeon) {

        boolean movePossible = true;
        this.getHitBox().move(dx, dy);
        for (Things things : dungeon.getRenderList()) {
            if (things instanceof SolidThings) {
                if (((SolidThings) things).getHitBox().intersect(this.getHitBox())) {
                    movePossible = false;
                    break;
                }
            }
        }

        if (movePossible) {
            this.x = x + dx;
            this.y = y + dy;
        } else {
            openGameOverWindow();
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof MainInterface) {
                    window.dispose();
                }
            }
            this.getHitBox().move(-dx, -dy);
            this.setWalking(false); // Arrêter le mouvement du héros après le Game Over
        }
    }

    void resetHeroPosition() {
        // Réinitialisez la position du héros à une position initiale
        this.x = 120;
        this.y = 120;
    }

    private static void openGameOverWindow() {
        JFrame gameOverWindow = new JFrame("GameOver");

        JButton gameOverButton = new JButton("Reprendre le jeu ?");
        gameOverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fermer la fenêtre "GameOver"
                gameOverWindow.dispose();

                // Ouvrir la fenêtre "MainInterface"
                openMainInterface();
            }
        });

        gameOverWindow.getContentPane().add(gameOverButton);
        gameOverWindow.setSize(300, 200);
        gameOverWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverWindow.setVisible(true);
    }

    private static void openMainInterface() {
        MainInterface mainInterface = new MainInterface();
    }

    public void setWalking(boolean walking) {
        isWalking = walking;
    } // Méthode pour définir si le héros est en mouvement

    // Méthode pour dessiner le héros et sa barre de vie
    @Override
    public void draw(Graphics g) {
        int attitude = orientation.getI();
        int index = (int) ((System.currentTimeMillis() / 125) % 10);
        index = isWalking ? index : 0;
        g.drawImage(image, (int) x, (int) y, (int) x + 48, (int) y + 52, index * 96, 100 * attitude,
                (index + 1) * 96, 100 * (attitude + 1), null, null);

        // Dessiner la barre de vie
        int barWidth = 50; // Largeur de la barre de vie
        int barHeight = 10; // Hauteur de la barre de vie
        int barX = (int) x; // Position X de la barre de vie
        int barY = (int) y - 15; // Position Y de la barre de vie

        // Dessiner la barre de vie en rouge
        g.setColor(Color.RED);
        g.fillRect(barX, barY, barWidth, barHeight);

        // Dessiner la portion de la barre de vie en vert en fonction de la santé actuelle
        g.setColor(Color.GREEN);
        int currentBarWidth = (int) ((double) health / 100 * barWidth);
        g.fillRect(barX, barY, currentBarWidth, barHeight);
    }

    public MainInterface getMainInterface() {
        return mainInterface;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
