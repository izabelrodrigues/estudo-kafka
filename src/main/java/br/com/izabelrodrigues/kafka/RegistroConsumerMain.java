/**
 * 
 */
package br.com.izabelrodrigues.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * @author Izabel Rodrigues
 *
 */
public class RegistroConsumerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getPropertiesConfig());
		consumer.subscribe(Collections.singletonList("CLIENTES_ADD_CLIENT"));
		ConsumerRecords<String, String> registros = consumer.poll(Duration.ofMillis(1000));
		if(registros.isEmpty()) {
			System.out.println("Não encontrei registros no tópico CLIENTES_ADD_CLIENT");
			return;
		}else {
			System.out.println("Processando registros....");
			for (ConsumerRecord<String, String> consumerRecord : registros) {
				System.out.println("KEY: " + consumerRecord.key() + "::: VALUE: " + consumerRecord.value());
			}
		}

	}
	
	private static Properties getPropertiesConfig() {
		Properties prop = new Properties();
		prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, RegistroConsumerMain.class.getSimpleName());
		return prop;
	}

}
