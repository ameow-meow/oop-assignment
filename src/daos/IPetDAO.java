package daos;
import models.Pet;
import java.util.List;
public interface IPetDAO {
    List<Pet> getAllPets();
    boolean addPet(Pet pet);
    boolean deletePet(String code);
}