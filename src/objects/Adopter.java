package objects;
import printable.Printable;
public final class Adopter implements Printable {
    private int id;
    private String name;
    private String city;
    private int adoptionCount;
    private boolean isVerified;
    public Adopter() {}
    public Adopter(String name, String city, int adoptionCount, boolean isVerified) {
        this.name = name;
        this.city = city;
        this.adoptionCount = adoptionCount;
        this.isVerified = isVerified;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public int getAdoptionCount() { return adoptionCount; }
    public void setAdoptionCount(int count) { this.adoptionCount = count; }
    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }
    @Override
    public void printInfo() {
        System.out.println("Adopter: " + name + ", City: " + city);
    }
    @Override
    public String toString() {
        return String.format("Adopter[id=%d, name='%s', city='%s', adoptions=%d, verified=%b]",
                id, name, city, adoptionCount, isVerified);
    }
}