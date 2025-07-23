public enum MimeType {
    HTML("html", "text/html"),
    CSS("css", "text/css"),
    JS("js", "application/javascript"),
    PNG("png", "image/png"),
    JPG("jpg", "image/jpeg"),
    JPEG("jpeg", "image/jpeg"),
    GIF("gif", "image/gif");

    private final String extension;
    private final String mime;

    MimeType(String extension, String mime) {
        this.extension = extension;
        this.mime = mime;
    }

    public String getExtension() {
        return extension;
    }

    public String getMime() {
        return mime;
    }

    public static String getMimeByExtension(String ext) {
        for (MimeType type : values()) {
            if (type.extension.equalsIgnoreCase(ext)) {
                return type.mime;
            }
        }
        return "application/octet-stream"; // тип за замовчуванням
    }
}
