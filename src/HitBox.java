public class HitBox {
    // Hauteur et largeur constantes de la boîte de collision
    private final double height;
    private final double width;

    // Coordonnées (x, y) de la boîte de collision
    private double x;
    private double y;

    // Nom de la boîte de collision (peut être utilisé pour l'identification, facultatif)
    private String name;

    // Constructeur pour initialiser la boîte de collision avec des coordonnées, une largeur et une hauteur spécifiques
    public HitBox(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    // Méthode pour vérifier si cette boîte de collision intersecte avec une autre boîte de collision
    public boolean intersect(HitBox anotherHitBox){
        // Sélectionne la boîte avec la coordonnée y la plus basse comme "upper" et la boîte avec la coordonnée x la plus basse comme "leftest"
        HitBox upper = (this.y < anotherHitBox.y) ? this : anotherHitBox;
        HitBox leftest = (this.x < anotherHitBox.x) ? this : anotherHitBox;

        // Vérifie s'il y a un chevauchement en termes de coordonnées x
        boolean xOverlap = Math.abs(x - anotherHitBox.x) < leftest.width;

        // Vérifie s'il y a un chevauchement en termes de coordonnées y
        boolean yOverlap = Math.abs(y - anotherHitBox.y) < upper.height;

        // Renvoie vrai si les chevauchements existent à la fois en x et y
        return xOverlap && yOverlap;
    }

    // Getter pour obtenir la coordonnée x de la boîte de collision
    public double getX() {
        return x;
    }

    // Getter pour obtenir la coordonnée y de la boîte de collision
    public double getY() {
        return y;
    }

    // Méthode pour déplacer la boîte de collision de manière relative
    public void move(double dx, double dy){
        // Ajoute les valeurs de déplacement aux coordonnées actuelles
        x = x + dx;
        y = y + dy;
    }
}
