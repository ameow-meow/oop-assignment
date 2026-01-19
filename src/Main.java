import daos.AdopterDAO;
import daos.PetDAO;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Dog pet1 = new Dog("Archi", 3);
        Pet pet2 = new Cat("Fiona", 1);
        Pet pet3 = new Dog("Rex", 2);
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

        pet1.setAge(5, "months");

        System.out.println("\n========== DATABASE ==========\n");

        PetDAO petDAO = new PetDAO();
        AdopterDAO adopterDAO = new AdopterDAO();

        System.out.println("--- INSERT Operations ---");
        petDAO.insertPet("Archi", 3, "Dog");
        petDAO.insertPet("Fiona", 1, "Cat");
        petDAO.insertPet("Rex", 2, "Dog");

        System.out.println("\n--- READ Operations ---");
        System.out.println("All Pets from Database:");
        for (String pet : petDAO.getAllPets()) {
            System.out.println("  " + pet);
        }

        System.out.println("\n--- UPDATE Operations ---");
        petDAO.updatePet(1, "Archi Updated", 4);

        System.out.println("\n--- DELETE Operations ---");
        petDAO.deletePet(3);

        System.out.println("\n--- Adopters ---");
        adopterDAO.insertAdopter("Amina", "Karaganda");
        adopterDAO.insertAdopter("Bolat", "Astana");

        System.out.println("\nAll Adopters from Database:");
        for (String adopter : adopterDAO.getAllAdopters()) {
            System.out.println("  " + adopter);
        }

        DatabaseConnection.closeConnection();
        System.out.println("\n========== END ==========");
    }
}