package task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumbersValidator {
    private static final String PHONE_REGEX = "^\\(?(\\d{3})\\)?[ ](\\d{3})[-](\\d{4})$|^\\d{3}[-]\\d{3}[-]\\d{4}$";
    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static void main(String[] args) {
        File file = new File("text.txt");
        if (file.exists()) {
            try (InputStream fis = new FileInputStream(file);
                 Scanner scanner = new Scanner(fis)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (validatePhoneNumber(line)) {
                        System.out.println(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
