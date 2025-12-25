import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Pet pet1 = new Pet("Aktos", 3);
        Pet pet2 = new Pet("Fiona", 1);
        Pet pet3 = new Pet("Rex", 2);

        List<Pet> pets = new ArrayList<>();
        pets.add(pet1);
        pets.add(pet2);
        pets.add(pet3);

        for (int i = 0; i < pets.size() - 1; i++) {
            for (int j = i + 1; j < pets.size(); j++) {
                if (pets.get(i).getAge() > pets.get(j).getAge()) {
                    Pet temp = pets.get(i);
                    pets.set(i, pets.get(j));
                    pets.set(j, temp);
                }
            }
        }
        List<Pet> youngPets = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getAge() <= 2) {
                youngPets.add(pet);
            }
        }
        System.out.println("All Pets:");
        for (Pet pet : pets) {
            System.out.println(pet);
        }
        System.out.println("\nYoung Pets:");
        for (Pet pet : youngPets) {
            System.out.println(pet);
        }
        Adopter adopter1 = new Adopter("Amina", "Karaganda");
        Shelter shelter1 = new Shelter("Happy Tails", "Astana");

        System.out.println("\nAdopter Information:");
        System.out.println(adopter1);

        System.out.println("\nShelter Information:");
        System.out.println(shelter1);

        Dog dog = new Dog("Sharik", 4);
        Cat cat = new Cat("Murka", 2);

        System.out.println("\nSounds:");
        dog.makeSound();
        cat.makeSound();

        System.out.println("\nComparing Pets:");
        System.out.println(pet1.equals(pet2));
        System.out.println(pet1.equals(pet3));
    }
}
