package daos;

import objects.Adopter;
import java.util.List;

/**
 * Interface for Adopter Data Access Object
 * Defines all operations for managing adopters in the database
 */
public interface IAdopterDAO {

    /**
     * Insert a new adopter into the database
     * @param adopter The adopter to insert
     * @return true if successful, false otherwise
     */
    boolean insertAdopter(Adopter adopter);

    /**
     * Get all adopters from the database
     * @return List of all adopters
     */
    List<Adopter> getAllAdopters();

    /**
     * Get only verified adopters
     * @return List of verified adopters
     */
    List<Adopter> getVerifiedAdopters();

    /**
     * Get an adopter by their ID
     * @param id The adopter's ID
     * @return The adopter, or null if not found
     */
    Adopter getAdopterById(int id);

    /**
     * Get an adopter by their name
     * @param name The adopter's name
     * @return The adopter, or null if not found
     */
    Adopter getAdopterByName(String name);

    /**
     * Get all adopters from a specific city
     * @param city The city name
     * @return List of adopters from that city
     */
    List<Adopter> getAdoptersByCity(String city);

    /**
     * Update an existing adopter
     * @param adopter The adopter with updated information
     * @return true if successful, false otherwise
     */
    boolean updateAdopter(Adopter adopter);

    /**
     * Delete an adopter by ID
     * @param id The adopter's ID
     * @return true if successful, false otherwise
     */
    boolean deleteAdopter(int id);

    /**
     * Increment the adoption count for an adopter
     * @param adopterId The adopter's ID
     * @return true if successful, false otherwise
     */
    boolean incrementAdoptionCount(int adopterId);

    /**
     * Mark an adopter as verified
     * @param adopterId The adopter's ID
     * @return true if successful, false otherwise
     */
    boolean verifyAdopter(int adopterId);
}