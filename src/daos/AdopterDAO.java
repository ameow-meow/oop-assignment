package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdopterDAO {

    public boolean insertAdopter(String name, String city) {
        String sql = "INSERT INTO adopters (name, city) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, city);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Adopter inserted successfully!");
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting adopter: " + e.getMessage());
            return false;
        }
    }

    public List<String> getAllAdopters() {
        List<String> adopters = new ArrayList<>();
        String sql = "SELECT * FROM adopters ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");

                adopters.add(String.format("ID: %d, Name: %s, City: %s",
                        id, name, city));
            }

        } catch (SQLException e) {
            System.err.println("Error reading adopters: " + e.getMessage());
        }

        return adopters;
    }

    public String getAdopterById(int id) {
        String sql = "SELECT * FROM adopters WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return String.format("ID: %d, Name: %s, City: %s",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("city"));
            }

        } catch (SQLException e) {
            System.err.println("Error reading adopter by ID: " + e.getMessage());
        }

        return "Adopter not found";
    }

    public List<String> getAdoptersByCity(String city) {
        List<String> adopters = new ArrayList<>();
        String sql = "SELECT * FROM adopters WHERE city = ? ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, city);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String adopterCity = rs.getString("city");

                adopters.add(String.format("ID: %d, Name: %s, City: %s",
                        id, name, adopterCity));
            }

        } catch (SQLException e) {
            System.err.println("Error reading adopters by city: " + e.getMessage());
        }

        return adopters;
    }

    public boolean updateAdopter(int id, String name, String city) {
        String sql = "UPDATE adopters SET name = ?, city = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, city);
            pstmt.setInt(3, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Adopter updated successfully!");
            }
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating adopter: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAdopter(int id) {
        String sql = "DELETE FROM adopters WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Adopter deleted successfully!");
            }
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting adopter: " + e.getMessage());
            return false;
        }
    }
}