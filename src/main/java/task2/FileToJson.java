package task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileToJson {
    public static void main(String[] args) {
        String inputFileName = "file.txt";
        String outputFileName = "user.json";
        List<User> users = readUsersFromFile(inputFileName);
        writeUsersToJsonFile(users, outputFileName);

    }

    private static List<User> readUsersFromFile(String fileName) {

        ArrayList<User> users = new ArrayList<User>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");

                if (parts.length >= 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);

                    User user = new User(name, age);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    private static void writeUsersToJsonFile(List<User> users, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
