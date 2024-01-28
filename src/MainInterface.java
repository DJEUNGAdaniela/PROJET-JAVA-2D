import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainInterface extends JFrame implements KeyListener {

    TileManager tileManager = new TileManager(42, 42, "././img/tileSet.png");
    Dungeon dungeon = new Dungeon("././gameData/level1.txt", tileManager);
    Hero hero = Hero.getInstance();
    GameRender panel = new GameRender(dungeon, hero);

    public MainInterface() throws HeadlessException {
        super();

        Titre(); // Appelle la méthode pour afficher l'écran de titre

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setSize(new Dimension(dungeon.getWidth() * tileManager.getWidth(), dungeon.getHeight() * tileManager.getHeigth()));
        this.addKeyListener(this);

        ActionListener animationTimer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint(); // Redessine le panneau de rendu
                final int speed = 10;

                // Vérifie si le héros est en mouvement et effectue le déplacement
                if (hero.isWalking()) {
                    switch (hero.getOrientation()) {
                        case LEFT:
                            hero.moveIfPossible(-speed, 0, dungeon);
                            break;
                        case RIGHT:
                            hero.moveIfPossible(speed, 0, dungeon);
                            break;
                        case UP:
                            hero.moveIfPossible(0, -speed, dungeon);
                            break;
                        case DOWN:
                            hero.moveIfPossible(0, speed, dungeon);
                            break;
                    }
                }

                if (hero.getHealth() <= 0) { // Vérifie si la vie du héros est épuisée
                    GameOver(); // Appelle la méthode pour gérer l'événement de fin de jeu
                }
                repaint();
            }
        };

        Timer timer = new Timer(50, animationTimer);
        timer.start();
    }

    public static void main(String[] args) {
        MainInterface mainInterface = new MainInterface();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Cette méthode n'est pas utilisée dans cette implémentation
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Gestion des touches de déplacement du héros
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                hero.setOrientation(Orientation.LEFT);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setOrientation(Orientation.RIGHT);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_UP:
                hero.setOrientation(Orientation.UP);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_DOWN:
                hero.setOrientation(Orientation.DOWN);
                hero.setWalking(true);
                break;
        }
        this.repaint(); // Redessine le panneau après chaque pression de touche
    }

    // Implémentation de la méthode keyReleased de l'interface KeyListener
    @Override
    public void keyReleased(KeyEvent e) {
        hero.setWalking(false);
        if (hero.getHealth() <= 0) {
            GameOver(); // Appelle la méthode pour gérer l'événement de fin de jeu
        }
    }

    // Méthode pour afficher l'écran de titre et démarrer le jeu
    private void Titre() {
        String titleMessage = "Bienvenue dans Escape game";
        String startButtonLabel = "Démarrer le jeu"; // Correction de la faute de frappe

        int option = JOptionPane.showOptionDialog(
                this,
                titleMessage,
                "Écran Titre",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{startButtonLabel},
                startButtonLabel
        );
        if (option != 0) {
            System.exit(0);
        }
    }

    // Méthode pour gérer l'événement de fin de jeu
    void GameOver() {
        // Affichage d'une boîte de dialogue pour l'écran de Game Over
        int option = JOptionPane.showConfirmDialog(this,
                "Game Over! Voulez-vous recommencer?",
                "Game Over",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_NO_OPTION) {
            resetGame(); // Appelle la méthode pour réinitialiser le jeu
        } else {
            // Fermez l'application si l'utilisateur choisit de ne pas recommencer
            System.exit(0);
        }
    }

    // Méthode pour réinitialiser le jeu
    void resetGame() {
        MainInterface newGame = new MainInterface();
        hero.setHealth(100); // Réinitialise la vie du héros
        dungeon.respawnListOfThings(); // Réinitialisez la liste d'objets dans le donjon
        // Réinitialisez la position du héros
        hero.x = 120;
        hero.y = 120;
        dispose(); // Ferme la fenêtre actuelle
    }
}
