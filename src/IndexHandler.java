public class IndexHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
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
    }
    
}
