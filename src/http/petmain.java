package http;
public class petmain {
    public static void main(String[] args) {
        try {
            SimpleHttpServer.main(args);
        } catch (Exception e) {
            System.err.println("critical error");
            e.printStackTrace();
        }
    }
}