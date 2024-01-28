

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRender extends JPanel {
    private Dungeon dungeon;
    private Hero hero;

    // Méthode appelée automatiquement pour dessiner les composants graphiques
    public GameRender(Dungeon dungeon, DynamicThings hero) {
        this.dungeon = dungeon;
        this.hero = Hero.getInstance();
    }
    private int currentLevel;

    // Méthode pour définir le niveau actuel
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner les objets du donjon
        for (Things t : dungeon.getRenderList()){
            t.draw(g);
        }
        // Dessiner le héros
        hero.draw(g);


    }
}
