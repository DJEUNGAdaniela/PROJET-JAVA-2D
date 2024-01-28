

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Dungeon {
    // Les dimensions du donjon
    private final int height;
    private final int width;
    // Le gestionnaire de tuiles utilisé pour afficher le donjon
    private TileManager tileManager;

    // Liste des objets à rendre dans le donjon
    private ArrayList <Things> renderList = new ArrayList<>();

    // Carte représentant la disposition des éléments dans le donjon
    private char[][] map;



    // Constructeur pour créer un donjon avec des dimensions spécifiées
    public Dungeon(int height, int width) {
        this.height = height;
        this.width = width;
        this.tileManager = new TileManager(32,32);
        this.map = new char[width][height];
        // Initialise la carte avec des murs tout autour et l'intérieur vide
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                if ((x==0)||(x==width-1)||(y==0)||(y==height-1)){
                    this.map[x][y]='W';// 'W' représente un mur
                }
                else {
                    this.map[x][y]=' ';// Espace vide à l'intérieur du donjon
                }
            }
        }
        // Ajoute des objets initiaux à la liste des objets à rendre
        respawnListOfThings();
    }

    // Vérifie si la case aux coordonnées (x, y) est une porte
    public boolean isDoor(int x, int y) {
        return map[x][y] == 'D'; // 'D' représente une porte
    }

    public int getHeight() {
        return height;
    }// Obtient la hauteur du donjon

    public int getWidth() {
        return width;
    }// Obtient la largeur du donjon

    public Dungeon(int height, int width, TileManager tileManager) {
        this.height = height;
        this.width = width;
        this.tileManager = tileManager;
        this.map = new char[width][height];


        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                if ((x==0)||(x==width-1)||(y==0)||(y==height-1)){
                    this.map[x][y]='W';// 'W' représente un mur
                }
                else {
                    this.map[x][y]=' ';
                }
            }
        }

        respawnListOfThings();
    }

    // Constructeur pour créer un donjon à partir d'un fichier de configuration
    public Dungeon(String fileName, TileManager tileManager) {
        this.tileManager = tileManager;
        int height=0;
        int width=0;
        // Lecture du fichier pour déterminer la hauteur du donjon
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.readLine()!=null){
                height++;
            }
            br.close();
            // Lecture du fichier pour déterminer la largeur du donjon (en supposant que toutes les lignes ont la même longueur)
            br = new BufferedReader(new FileReader(fileName));
            String s = br.readLine();
            width = s.length();
            this.map = new char[width][height];
            // Remplissage de la carte avec les données du fichier
            for (int y=0;y<height;y++){
                for (int x=0;x<width;x++){
                    this.map[x][y]=s.toCharArray()[x];
                }
                s=br.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.height = height;
            this.width = width;
        }


        respawnListOfThings();
    }


    // Méthode pour mettre à jour la liste d'objets à rendre en fonction de la carte actuelle
    void respawnListOfThings(){
        renderList.clear();
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                // Ajout d'objets à la liste en fonction des caractères de la carte
                switch (this.map[x][y]){
                    case ' ' :  renderList.add(new Things(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(4,2)));
                                break;
                    case 'W' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(0,0)));
                                break;
                    case 'E' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(0,1)));
                                break;
                    case 'T' :  renderList.add(new Trap(x * tileManager.getWidth(), y * tileManager.getHeigth(), tileManager.getTile(11,13)));
                        break;
                }
            }
        }
    }


    // Classe interne représentant un piège dans le donjon
    public class Trap extends SolidThings {
        public Trap(int x, int y, Image image) {
            super(x, y, image);
        }
    }

    //Affiche le donjon dans la console (à des fins de débogage)
    public void displayDungeonInConsole(HitBox hero){
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                if (x==(hero.getX()/ tileManager.getWidth()) && y==(hero.getY()/ tileManager.getHeigth())) {
                    System.out.print("H");// Affiche 'H' à la position actuelle du héros
                }
                else {
                    System.out.print((map[x][y]));
                }
            }
            System.out.println();
        }

    }


    //Liste comportant des éléments statiques ou dynamiques
    public ArrayList<Things> getRenderList() {
        return renderList;
    }


}

