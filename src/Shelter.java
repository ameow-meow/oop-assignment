import java.util.Objects;
public class Shelter {
    private String name;
    private String address;
    public Shelter(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "Shelter{name='" + name + "', address='" + address + "'}";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Shelter shelter = (Shelter) obj;
        return name.equals(shelter.name) && address.equals(shelter.address);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
