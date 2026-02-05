package models;
public class Shelter {
    private int id;
    private String shelterName;
    private String location;
    private int capacity;
    private int currentPets;
    public Shelter(String shelterName, String location, int capacity, int currentPets) {
        this.shelterName = shelterName;
        this.location = location;
        this.capacity = capacity;
        this.currentPets = currentPets;
    }
    public static void printInfo(Shelter s) {
        System.out.println("Shelter: " + s.getShelterName() + " | Capacity: " + s.getCapacity());
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getShelterName() { return shelterName; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public int getCurrentPets() { return currentPets; }
}