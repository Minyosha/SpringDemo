package gb.springDemo.hw_1.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SerializeService {

    private static final String DIRECTORY = "Files";
    private final Gson gson;

    public SerializeService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void serializeToJson(Object object, String fileName) {
        Path directoryPath = Paths.get(DIRECTORY);
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        Path filePath = directoryPath.resolve(fileName);
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T deserializeFromJson(String fileName, Class<T> classOfT) {
        Path filePath = Paths.get(DIRECTORY, fileName);
        try (FileReader reader = new FileReader(filePath.toFile())) {
            return gson.fromJson(reader, classOfT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}