package br.com.shire42.serialization;

import br.com.shire42.proto.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonDeserializeDemo {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("person.obj");
        byte[] bytes = Files.readAllBytes(path);

        Person person =  Person.parseFrom(bytes);
        System.out.println(person);
    }

}
