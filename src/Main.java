public class Main {
    public static void main(String[] args) {

        Pet pet1 = new Pet("Aktos", 3);
        Pet pet2 = new Pet("Fiona", 1);

        pet1.setName("Rex");
        pet1.setAge(4);

        pet2.setName("Curie");
        pet2.setAge(2);

        Adopter adopter1 = new Adopter("Amina", "Karaganda");
        Shelter shelter1 = new Shelter("Happy Tails", "Astana");

        System.out.println("Pet 1: " + pet1.getName() + ", Age: " + pet1.getAge());
        System.out.println("Pet 2: " + pet2.getName() + ", Age: " + pet2.getAge());

        checkPetAge(pet1);
        checkPetAge(pet2);

        System.out.println("\nAdopter Information:");
        System.out.println(adopter1.getName() + " from " + adopter1.getCity());

        System.out.println("\nShelter Information:");
        System.out.println(shelter1.getName() + " located at " + shelter1.getAddress());
    }

    public static void checkPetAge(Pet pet) {
        if (pet.getAge() >= 3) {
            System.out.println(pet.getName() + " is an adult pet.");
        } else {
            System.out.println(pet.getName() + " is a young pet.");
        }
    }
}

