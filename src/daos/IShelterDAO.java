package daos;
import models.Shelter;
import java.util.List;
public interface IShelterDAO {
    List<Shelter> getAllShelters();
    boolean addShelter(Shelter shelter);
}