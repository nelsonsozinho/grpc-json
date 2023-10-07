package br.com.shire42.serialization;

import br.com.shire42.proto.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonSerializeDemo {

    public static void main(String[] args) throws IOException {
        Person person = Person.newBuilder()
                .setAge(40)
                .setName("Nms")
                .build();

        Path path = Paths.get("person.obj");
        Files.write(path, person.toByteArray());
    }

}
