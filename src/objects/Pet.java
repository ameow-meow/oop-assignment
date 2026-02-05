package models;
public class Pet {
    private int id;
    private String petCode;
    private String petName;
    private int age;
    private int adoptionCount;
    private String petType;
    public Pet(String petCode, String petName) {
        this.petCode = petCode;
        this.petName = petName;
        this.age = 0;
        this.petType = "General";
    }
    public Pet(String petCode, String petName, int age, int adoptionCount, String petType) {
        this.petCode = petCode;
        this.petName = petName;
        this.age = age;
        this.adoptionCount = adoptionCount;
        this.petType = petType;
    }
    public boolean isAdopted() {
        return false;
    }
    public void printInfo() {
        System.out.println("Pet: " + petName);
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPetCode() { return petCode; }
    public String getPetName() { return petName; }
    public int getAge() { return age; }
    public int getAdoptionCount() { return adoptionCount; }
    public String getPetType() { return petType; }
}