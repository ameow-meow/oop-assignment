package petTypes;
import models.Pet;
public class Cat extends Pet {
    private int maxAdoptions;
    private boolean isIndoor;
    public Cat(String petCode, String petName, int age, int adoptionCount, int maxAdoptions, boolean isIndoor) {
        super(petCode, petName, age, adoptionCount, "Cat");
        this.maxAdoptions = maxAdoptions;
        this.isIndoor = isIndoor;
    }
    @Override
    public boolean isAdopted() {
        return getAdoptionCount() >= maxAdoptions;
    }
    @Override
    public void printInfo() {
        System.out.println(getPetName() + " (CAT), Indoor: " + isIndoor);
    }
}