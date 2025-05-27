// Interface definition
interface Playable {
    void play();  // Abstract method to be implemented
}

// Implementing class: Guitar
class Guitar implements Playable {
    @Override
    public void play() {
        System.out.println("Strumming the guitar.");
    }
}

// Implementing class: Piano
class Piano implements Playable {
    @Override
    public void play() {
        System.out.println("Playing the piano.");
    }
}

// Main class
public class InterfaceImplementation {
    public static void main(String[] args) {
        // Create objects of Guitar and Piano
        Playable myGuitar = new Guitar();
        Playable myPiano = new Piano();

        // Call the play() method on each
        myGuitar.play();  // Output: Strumming the guitar.
        myPiano.play();   // Output: Playing the piano.
    }
}
