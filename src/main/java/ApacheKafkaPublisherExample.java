import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Scanner;

public class ApacheKafkaPublisherExample {

    private final static String TOPIC_NAME = "my-topic";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Nachricht zum Senden eingeben (Zum Beenden 'verlassen' eingeben):");
            String message = scanner.nextLine();
            if (message.equals("verlassen")) {
                break;
            }
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, message);
            producer.send(record);
            System.out.println("Gesendete Nachricht: " + message);
        }

        producer.close();
    }

}
