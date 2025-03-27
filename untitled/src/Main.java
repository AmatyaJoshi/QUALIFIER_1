import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        try {

            String path = "D:\\CODES\\JAVA\\QUALIFIER_1\\untitled\\src\\input.json";
            String json = new String(Files.readAllBytes(Paths.get(path)));

            String firstName = extractValue(json, "first_name").toLowerCase().trim();
            String rollNumber = extractValue(json, "roll_number").toLowerCase().trim();

            String concatenatedString = firstName + rollNumber;

            String md5Hash = generateMD5(concatenatedString);
            Files.write(Paths.get("output.txt"), md5Hash.getBytes());

            System.out.println("MD5 Hash: " + md5Hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String extractValue(String json, String key) {
        int start = json.indexOf("\"" + key + "\"") + key.length() + 3;
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }

    public static String generateMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
