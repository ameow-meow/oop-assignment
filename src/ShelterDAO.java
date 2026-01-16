import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShelterDAO {

    public boolean insertShelter(String name, String address) {
        String sql = "INSERT INTO shelters (name, address) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, address);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Shelter inserted successfully!");
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting shelter: " + e.getMessage());
            return false;
        }
    }

    public List<String> getAllShelters() {
        List<String> shelters = new ArrayList<>();
        String sql = "SELECT * FROM shelters ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");

                shelters.add(String.format("ID: %d, Name: %s, Address: %s",
                        id, name, address));
            }

        } catch (SQLException e) {
            System.err.println("Error reading shelters: " + e.getMessage());
        }

        return shelters;
    }

    public String getShelterById(int id) {
        String sql = "SELECT * FROM shelters WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return String.format("ID: %d, Name: %s, Address: %s",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"));
            }

        } catch (SQLException e) {
            System.err.println("Error reading shelter by ID: " + e.getMessage());
        }

        return "Shelter not found";
    }

    public boolean updateShelter(int id, String name, String address) {
        String sql = "UPDATE shelters SET name = ?, address = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setInt(3, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Shelter updated successfully!");
            }
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating shelter: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteShelter(int id) {
        String sql = "DELETE FROM shelters WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Shelter deleted successfully!");
            }
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting shelter: " + e.getMessage());
            return false;
        }
    }
}
