package http;
import daos.PetDAO;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class PetHandler extends BaseHandler {
    private final PetDAO petDAO = new PetDAO();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        try {
            switch (method) {
                case "GET":    handleGet(exchange); break;
                case "POST":   handlePost(exchange); break;
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
        List<String> list = petDAO.getAllPets();
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < list.size(); i++) {
            String petInfo = list.get(i);
            String[] parts = petInfo.split(", ");

            String id = parts[0].replace("ID: ", "");
            String name = parts[1].replace("Name: ", "");
            String age = parts[2].replace("Age: ", "");
            String type = parts[3].replace("Type: ", "");

            String code = "P" + String.format("%03d", Integer.parseInt(id));

            json.append(String.format("{\"id\":%s,\"code\":\"%s\",\"name\":\"%s\",\"age\":%s,\"type\":\"%s\"}",
                    id, code, name, age, type));

            if (i < list.size() - 1) json.append(",");
        }
        json.append("]");

        sendResponse(exchange, json.toString(), 200, "application/json");
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        Map<String, String> params = parseFormData(getRequestBody(exchange));

        String name = params.get("name");
        String ageStr = params.get("age");
        String type = params.get("type");

        if (name != null && ageStr != null && type != null) {
            int age = Integer.parseInt(ageStr);
            petDAO.insertPet(name, age, type);
            sendResponse(exchange, "Pet Added Successfully", 201, "text/plain");
        } else {
            sendResponse(exchange, "Invalid Data", 400, "text/plain");
        }
    }

    private void handleDelete(HttpExchange exchange, String path) throws IOException {
        String[] pathParts = path.split("/");
        if (pathParts.length < 4) {
            sendResponse(exchange, "Invalid Path", 400, "text/plain");
            return;
        }

        String code = pathParts[3];
        String decodedCode = URLDecoder.decode(code, StandardCharsets.UTF_8);

        int id = Integer.parseInt(decodedCode.replace("P", ""));
        petDAO.deletePet(id);

        sendResponse(exchange, "Pet Deleted", 200, "text/plain");
    }
}