package http;

import daos.AdopterDAO;
import objects.Adopter;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdopterHandler extends BaseHandler {
    private final AdopterDAO adopterDAO = new AdopterDAO();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        try {
            switch (method) {
                case "GET":    handleGet(exchange); break;
                case "POST":   handlePost(exchange); break;
                case "PUT":    handlePut(exchange); break;
                case "DELETE": handleDelete(exchange, path); break;
                default:
                    sendResponse(exchange, "Method Not Allowed", 405, "text/plain");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, "Error: " + e.getMessage(), 500, "text/plain");
        }
    }

    /**
     * GET - Get all adopters as JSON
     */
    private void handleGet(HttpExchange exchange) throws IOException {
        List<Adopter> adopters = adopterDAO.getAllAdopters();
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < adopters.size(); i++) {
            Adopter a = adopters.get(i);

            // Escape special characters in strings for JSON
            String name = escapeJson(a.getName());
            String city = escapeJson(a.getCity());

            json.append(String.format(
                    "{\"id\":%d,\"name\":\"%s\",\"city\":\"%s\",\"adoptions\":%d,\"verified\":%b}",
                    a.getId(),
                    name,
                    city,
                    a.getAdoptionCount(),
                    a.isVerified()
            ));

            if (i < adopters.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        sendResponse(exchange, json.toString(), 200, "application/json");
    }

    /**
     * POST - Add a new adopter
     */
    private void handlePost(HttpExchange exchange) throws IOException {
        Map<String, String> params = parseFormData(getRequestBody(exchange));

        String name = params.get("name");
        String city = params.get("city");

        // Validate required fields
        if (name == null || name.trim().isEmpty()) {
            sendResponse(exchange, "Name is required", 400, "text/plain");
            return;
        }

        if (city == null || city.trim().isEmpty()) {
            sendResponse(exchange, "City is required", 400, "text/plain");
            return;
        }

        // Optional fields with defaults
        int adoptionCount = 0;
        boolean isVerified = false;

        try {
            if (params.containsKey("adoptions")) {
                adoptionCount = Integer.parseInt(params.get("adoptions"));
            }
            if (params.containsKey("verified")) {
                isVerified = Boolean.parseBoolean(params.get("verified"));
            }
        } catch (NumberFormatException e) {
            sendResponse(exchange, "Invalid number format", 400, "text/plain");
            return;
        }

        // Create and insert adopter
        Adopter adopter = new Adopter(
                name.trim(),
                city.trim(),
                adoptionCount,
                isVerified
        );

        boolean success = adopterDAO.insertAdopter(adopter);

        if (success) {
            // Return the created adopter with its ID
            String responseJson = String.format(
                    "{\"id\":%d,\"name\":\"%s\",\"city\":\"%s\",\"adoptions\":%d,\"verified\":%b}",
                    adopter.getId(),
                    escapeJson(adopter.getName()),
                    escapeJson(adopter.getCity()),
                    adopter.getAdoptionCount(),
                    adopter.isVerified()
            );
            sendResponse(exchange, responseJson, 201, "application/json");
        } else {
            sendResponse(exchange, "Failed to add adopter", 500, "text/plain");
        }
    }

    /**
     * PUT - Update an existing adopter
     */
    private void handlePut(HttpExchange exchange) throws IOException {
        Map<String, String> params = parseFormData(getRequestBody(exchange));

        // ID is required for update
        if (!params.containsKey("id")) {
            sendResponse(exchange, "ID is required for update", 400, "text/plain");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
            sendResponse(exchange, "Invalid ID format", 400, "text/plain");
            return;
        }

        // Get existing adopter
        Adopter adopter = adopterDAO.getAdopterById(id);
        if (adopter == null) {
            sendResponse(exchange, "Adopter not found", 404, "text/plain");
            return;
        }

        // Update fields if provided
        if (params.containsKey("name") && !params.get("name").trim().isEmpty()) {
            adopter.setName(params.get("name").trim());
        }

        if (params.containsKey("city") && !params.get("city").trim().isEmpty()) {
            adopter.setCity(params.get("city").trim());
        }

        if (params.containsKey("adoptions")) {
            try {
                adopter.setAdoptionCount(Integer.parseInt(params.get("adoptions")));
            } catch (NumberFormatException e) {
                sendResponse(exchange, "Invalid adoption count", 400, "text/plain");
                return;
            }
        }

        if (params.containsKey("verified")) {
            adopter.setVerified(Boolean.parseBoolean(params.get("verified")));
        }

        // Update in database
        boolean success = adopterDAO.updateAdopter(adopter);

        if (success) {
            sendResponse(exchange, "Adopter updated successfully", 200, "text/plain");
        } else {
            sendResponse(exchange, "Failed to update adopter", 500, "text/plain");
        }
    }

    /**
     * DELETE - Delete an adopter by ID
     */
    private void handleDelete(HttpExchange exchange, String path) throws IOException {
        String[] pathParts = path.split("/");

        // Expected format: /api/adopters/{id}
        if (pathParts.length < 4) {
            sendResponse(exchange, "Invalid path. Expected: /api/adopters/{id}", 400, "text/plain");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(pathParts[3]);
        } catch (NumberFormatException e) {
            sendResponse(exchange, "Invalid ID format", 400, "text/plain");
            return;
        }

        boolean success = adopterDAO.deleteAdopter(id);

        if (success) {
            sendResponse(exchange, "Adopter deleted successfully", 200, "text/plain");
        } else {
            sendResponse(exchange, "Failed to delete adopter", 500, "text/plain");
        }
    }

    /**
     * Helper method to escape special characters for JSON
     */
    private String escapeJson(String str) {
        if (str == null) return "";
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}