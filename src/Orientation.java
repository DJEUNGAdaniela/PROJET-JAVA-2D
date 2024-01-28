// Définition d'une énumération pour représenter les orientations
public enum Orientation {
    UP(2),      // Haut avec une valeur associée de 2
    DOWN(0),    // Bas avec une valeur associée de 0
    LEFT(1),    // Gauche avec une valeur associée de 1
    RIGHT(3);   // Droite avec une valeur associée de 3

    // Variable privée pour stocker la valeur associée à chaque orientation
    private final int i;

    // Constructeur privé pour initialiser la valeur associée à chaque orientation
    Orientation(int i) {
        this.i = i;
    }

    // Méthode publique pour obtenir la valeur associée à une orientation
    public int getI() {
        return i;
    }
}
