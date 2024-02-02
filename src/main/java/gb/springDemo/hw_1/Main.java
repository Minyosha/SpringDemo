package gb.springDemo.hw_1;

import gb.springDemo.hw_1.model.Person;
import gb.springDemo.hw_1.services.SerializeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class Main implements CommandLineRunner {

    private final SerializeService serializeService;

    public Main(SerializeService serializeService) {
        this.serializeService = serializeService;
    }

    @Override
    public void run(String... args) throws Exception {
        Person person = new Person();
        person.setFirstName("Иван");
        person.setLastName("Иванов");
        person.setAge(30);

        // Сериализация объекта в JSON и запись в файл
        serializeService.serializeToJson(person, "person.json");

        // Десериализация объекта из файла и вывод в консоль
        Person deserializedPerson = serializeService.deserializeFromJson("person.json", Person.class);
        if (deserializedPerson != null) {
            System.out.println(deserializedPerson.toString());
        }
    }
}