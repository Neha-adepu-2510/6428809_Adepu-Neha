// Base class
class Animal {
    // Method to be overridden
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

// Subclass
class Dog extends Animal {
    // Overriding the makeSound method
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

// Main class
public class InheritanceExample {
    public static void main(String[] args) {
        // Creating an Animal object
        Animal genericAnimal = new Animal();
        genericAnimal.makeSound();  // Output: Some generic animal sound

        // Creating a Dog object
        Dog dog = new Dog();
        dog.makeSound();  // Output: Bark
    }
}
