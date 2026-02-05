package petTypes;
import models.Pet;
public class Dog extends Pet {
    private int maxAdoptions;
    public Dog(String petCode, String petName, int age, int adoptionCount, int maxAdoptions) {
        super(petCode, petName, age, adoptionCount, "Dog");
        this.maxAdoptions = maxAdoptions;
    }
    @Override
    public boolean isAdopted() {
        return getAdoptionCount() >= maxAdoptions;
    }
    @Override
    public void printInfo() {
        System.out.println(getPetName() + " (DOG)");
    }
}