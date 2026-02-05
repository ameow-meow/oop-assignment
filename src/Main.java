import database.DatabaseConnection;
import daos.PetDAO;
import daos.AdopterDAO;
import daos.ShelterDAO;
import java.util.List;

public class Main {
//    public static void main(String[] args) {
//        if (!DatabaseConnection.testConnection()) {
//            System.out.println("Database connection failed!");
//            return;
//        }
//
//        System.out.println("=== Testing Shelters ===");
//        ShelterDAO shelterDAO = new ShelterDAO();
//        List<String> shelters = shelterDAO.getAllShelters();
//        for (String shelter : shelters) {
//            System.out.println(shelter);
//        }
//
//        System.out.println("\n=== Testing Adopters ===");
//        AdopterDAO adopterDAO = new AdopterDAO();
//        objects.Adopter newAdopter = new objects.Adopter("John Doe", "Astana");
//        adopterDAO.insertAdopter(newAdopter.getName(), newAdopter.getCity());
//
//        List<objects.Adopter> adopters = adopterDAO.getAllAdopters();
//        for (objects.Adopter adopter : adopters) {
//            System.out.println(adopter);
//        }
//
//        System.out.println("\n=== Testing Pets ===");
//        PetDAO petDAO = new PetDAO();
//        List<String> pets = petDAO.getAllPets();
//        for (String pet : pets) {
//            System.out.println(pet);
//        }
//    }
}