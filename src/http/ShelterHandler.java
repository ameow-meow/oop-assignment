package http;
import daos.ShelterDAO;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class ShelterHandler extends BaseHandler {
    private final ShelterDAO shelterDAO = new ShelterDAO();

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

    private void handleGet(HttpExchange exchange) throws IOException {
        List<String> list = shelterDAO.getAllShelters();
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < list.size(); i++) {
            String shelterInfo = list.get(i);
            String[] parts = shelterInfo.split(", ");

            String id = parts[0].replace("ID: ", "");
            String name = parts[1].replace("Name: ", "");
            String address = parts[2].replace("Address: ", "");

            json.append(String.format("{\"id\":%s,\"name\":\"%s\",\"location\":\"%s\",\"capacity\":50,\"current\":10}",
                    id, name, address));

            if (i < list.size() - 1) json.append(",");
        }
        json.append("]");

        sendResponse(exchange, json.toString(), 200, "application/json");
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        Map<String, String> params = parseFormData(getRequestBody(exchange));

        String name = params.get("name");
        String location = params.get("location");

        if (name != null && location != null) {
            shelterDAO.insertShelter(name, location);
            sendResponse(exchange, "Created", 201, "text/plain");
        } else {
            sendResponse(exchange, "Invalid Data", 400, "text/plain");
        }
    }

    private void handlePut(HttpExchange exchange) throws IOException {
        Map<String, String> params = parseFormData(getRequestBody(exchange));

        int id = Integer.parseInt(params.get("id"));
        String name = params.get("name");
        String location = params.get("location");

        shelterDAO.updateShelter(id, name, location);
        sendResponse(exchange, "Updated", 200, "text/plain");
    }

    private void handleDelete(HttpExchange exchange, String path) throws IOException {
        String[] pathParts = path.split("/");
        if (pathParts.length < 4) {
            sendResponse(exchange, "Invalid Path", 400, "text/plain");
            return;
        }

        String name = pathParts[3];
        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);

        int id = Integer.parseInt(decodedName);
        shelterDAO.deleteShelter(id);

        sendResponse(exchange, "Deleted", 200, "text/plain");
    }
}