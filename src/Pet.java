import java.util.Objects;
public abstract class Pet implements Soundable {
    private String name;
    private int age;
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setAge(int age, String unit) {
        if (unit.equals("months")) {
            this.age = age / 12;
        } else {
            this.age = age;
        }
    }
    @Override
    public String toString() {
        return "Pet{name='" + name + "', age=" + age + "}";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pet pet = (Pet) obj;
        return age == pet.age && name.equals(pet.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    @Override
    public abstract void makeSound();

    public final void printInfo() {
        System.out.println("This is a final method. It cannot be overridden.");
    }
}
