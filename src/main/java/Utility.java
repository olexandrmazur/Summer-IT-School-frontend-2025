import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Utility {
    public static byte[] readBytesFromResource(URL url) throws IOException {
        try (InputStream is = url.openStream()) {
            return is.readAllBytes();
        }
    }
}
