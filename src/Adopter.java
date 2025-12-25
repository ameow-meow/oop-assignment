import java.util.Objects;
public class Adopter {
    private String name;
    private String city;
    public Adopter(String name, String city) {
        this.name = name;
        this.city = city;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return "Adopter{name='" + name + "', city='" + city + "'}";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Adopter adopter = (Adopter) obj;
        return name.equals(adopter.name) && city.equals(adopter.city);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }
}
