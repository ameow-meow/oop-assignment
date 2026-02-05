package daos;

import database.DatabaseConnection;
import objects.Adopter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdopterDAO {

    /**
     * Insert a new adopter into the database
     */
    public boolean insertAdopter(Adopter adopter) {
        String sql = "INSERT INTO adopters (name, city, adoption_count, is_verified) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, adopter.getName());
            pstmt.setString(2, adopter.getCity());
            pstmt.setInt(3, adopter.getAdoptionCount());
            pstmt.setBoolean(4, adopter.isVerified());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Get the generated ID and set it in the adopter object
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        adopter.setId(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Adopter inserted successfully: " + adopter.getName());
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Error inserting adopter: " + e.getMessage());
            return false;
        }
    }

    /**
     * Convenience method for backward compatibility
     */
    public boolean insertAdopter(String name, String city) {
        Adopter adopter = new Adopter(name, city, 0, false);
        return insertAdopter(adopter);
    }

    /**
     * Get an adopter by name
     */
    public Adopter getAdopterByName(String name) {
        String sql = "SELECT * FROM adopters WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return createAdopterFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error reading adopter by name: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get all adopters from the database
     */
    public List<Adopter> getAllAdopters() {
        List<Adopter> adopters = new ArrayList<>();
        String sql = "SELECT * FROM adopters ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                adopters.add(createAdopterFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error reading adopters: " + e.getMessage());
        }

        return adopters;
    }

    /**
     * Get an adopter by ID
     */
    public Adopter getAdopterById(int id) {
        String sql = "SELECT * FROM adopters WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return createAdopterFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error reading adopter by ID: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get all adopters from a specific city
     */
    public List<Adopter> getAdoptersByCity(String city) {
        List<Adopter> adopters = new ArrayList<>();
        String sql = "SELECT * FROM adopters WHERE city = ? ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, city);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                adopters.add(createAdopterFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error reading adopters by city: " + e.getMessage());
        }

        return adopters;
    }

    /**
     * Get only verified adopters
     */
    public List<Adopter> getVerifiedAdopters() {
        List<Adopter> adopters = new ArrayList<>();
        String sql = "SELECT * FROM adopters WHERE is_verified = true ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                adopters.add(createAdopterFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error reading verified adopters: " + e.getMessage());
        }

        return adopters;
    }

    /**
     * Update an adopter in the database
     */
    public boolean updateAdopter(Adopter adopter) {
        String sql = "UPDATE adopters SET name = ?, city = ?, adoption_count = ?, is_verified = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, adopter.getName());
            pstmt.setString(2, adopter.getCity());
            pstmt.setInt(3, adopter.getAdoptionCount());
            pstmt.setBoolean(4, adopter.isVerified());
            pstmt.setInt(5, adopter.getId());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Adopter updated successfully: " + adopter.getName());
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Error updating adopter: " + e.getMessage());
            return false;
        }
    }

    /**
     * Convenience method for backward compatibility
     */
    public boolean updateAdopter(String name, String city) {
        Adopter adopter = getAdopterByName(name);
        if (adopter != null) {
            adopter.setCity(city);
            return updateAdopter(adopter);
        }
        return false;
    }

    /**
     * Delete an adopter by ID
     */
    public boolean deleteAdopter(int id) {
        String sql = "DELETE FROM adopters WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Adopter deleted successfully!");
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Error deleting adopter: " + e.getMessage());
            return false;
        }
    }

    /**
     * Increment adoption count for an adopter
     */
    public boolean incrementAdoptionCount(int adopterId) {
        String sql = "UPDATE adopters SET adoption_count = adoption_count + 1 WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, adopterId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Adoption count incremented for adopter ID: " + adopterId);
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Error incrementing adoption count: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verify an adopter
     */
    public boolean verifyAdopter(int adopterId) {
        String sql = "UPDATE adopters SET is_verified = true WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, adopterId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Adopter verified: ID " + adopterId);
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Error verifying adopter: " + e.getMessage());
            return false;
        }
    }

    /**
     * Helper method to create Adopter object from ResultSet
     */
    private Adopter createAdopterFromResultSet(ResultSet rs) throws SQLException {
        Adopter adopter = new Adopter(
                rs.getString("name"),
                rs.getString("city"),
                rs.getInt("adoption_count"),
                rs.getBoolean("is_verified")
        );
        adopter.setId(rs.getInt("id"));
        return adopter;
    }
}