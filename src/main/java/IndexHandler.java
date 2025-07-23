import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class IndexHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String uriPath = exchange.getRequestURI().getPath();

        if (uriPath.equals("/")) {
            uriPath = "/index.html";
        }

        String resourcePath = "public" + uriPath;
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource(resourcePath);
        LogController.logInfo("Serving file: " + resourceUrl);
        LogController.logInfo("Resource path: " + resourcePath);
        System.out.println("Serving file: " + resourceUrl);
        System.out.println("Resource path: " + resourcePath);

        if (resourceUrl == null) {
            System.out.println("Serving file: 404");
            // Файл не знайдено, повертаємо 404.html
            URL errorPageUrl = classLoader.getResource("public/404.html");

            byte[] notFoundBytes = Utility.readBytesFromResource(errorPageUrl);
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(404, notFoundBytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(notFoundBytes);
            }
            return;
        }

        // Визначити розширення
        String extension = "";
        int dotIndex = uriPath.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = uriPath.substring(dotIndex + 1);
        }

        String mime = MimeType.getMimeByExtension(extension);
        byte[] fileBytes = Utility.readBytesFromResource(resourceUrl);

        exchange.getResponseHeaders().set("Content-Type", mime);
        exchange.sendResponseHeaders(200, fileBytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(fileBytes);
        }
    }
}
