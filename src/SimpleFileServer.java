import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Request;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.net.InetSocketAddress;

public class SimpleFileServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {

            String uriPath = exchange.getRequestURI().getPath();

            if (uriPath.equals("/")) {
                uriPath = "/index.html";
            }

            Path baseDir = Path.of("src", "public").toAbsolutePath();
            Path filePath = baseDir.resolve(uriPath.substring(1));
            System.out.println("Serving file: " + filePath);

            if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
                Path errorPage = Path.of("src", "public", "404.html").toAbsolutePath();
                byte[] notFoundBytes = Files.readAllBytes(errorPage);

                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(404, notFoundBytes.length);

                OutputStream os = exchange.getResponseBody();
                os.write(notFoundBytes);
                os.close();
                return;
            }


            String extension = "";
            int dotIndex = filePath.toString().lastIndexOf('.');
            if (dotIndex > 0) {
                extension = filePath.toString().substring(dotIndex + 1);
            }
            String mime = MimeType.getMimeByExtension(extension);

            byte[] fileBytes = Files.readAllBytes(filePath);

            exchange.getResponseHeaders().set("Content-Type", mime);
            exchange.sendResponseHeaders(200, fileBytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(fileBytes);
            os.close();
        });

        server.start();
        System.out.println("Server running at http://localhost:8080");
    }
}
