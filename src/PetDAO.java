import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    public void insertPet(String name, int age, String type) {
        String sql = "INSERT INTO pets (name, age, type) VALUES (?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, type);

            pstmt.executeUpdate();
            System.out.println("Pet added: " + name);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<String> getAllPets() {
        List<String> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets";

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String info = "ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Type: " + rs.getString("type");
                pets.add(info);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return pets;
    }

    public void updatePet(int id, String name, int age) {
        String sql = "UPDATE pets SET name = ?, age = ? WHERE id = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
            System.out.println("Pet updated: ID " + id);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deletePet(int id) {
        String sql = "DELETE FROM pets WHERE id = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            System.out.println("Pet deleted: ID " + id);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}