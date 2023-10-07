package br.com.shire42.protobuf;

import br.com.shire42.json.JavaPerson;
import br.com.shire42.proto.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;

public class PerformanceTest {

    public static void main(String[] args) {


        final ObjectMapper mapper = new ObjectMapper();

        //json
        Runnable runnable1 = () -> {
            final JavaPerson jperson = new JavaPerson();
            jperson.setName("Alone");
            jperson.setAge(40);

            try {
                final byte[] bytes = mapper.writeValueAsBytes(jperson);
                final JavaPerson readerPerson = mapper.readValue(bytes, JavaPerson.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        //protobuf
        Runnable runnable2 = () -> {
            final Person person = Person.newBuilder()
                    .setAge(40)
                    .setName("Alone")
                    .build();

            try {
                final byte[] bytes = person.toByteArray();
                final Person personUnmarshall = Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            }

        };

        for (int i = 0; i < 2; i++) {
            runPerformanceTest(runnable1, "JSON");
            runPerformanceTest(runnable2, "PROTO");
        }
    }

    public static void runPerformanceTest(Runnable runnable, String method) {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            runnable.run();
        }
        long time2 = System.currentTimeMillis();
        System.out.println(method + ": " + (time2 - time1) + " ms");
    }

}
