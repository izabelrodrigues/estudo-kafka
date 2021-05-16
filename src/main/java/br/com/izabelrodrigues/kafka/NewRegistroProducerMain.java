/**
 * 
 */
package br.com.izabelrodrigues.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author Izabel Rodrigues
 *
 */
public class NewRegistroProducerMain {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		KafkaProducer producer = new KafkaProducer<String,String>(getPropertiesConfig());
		
		for (int i = 1; i < 10; i++) {
			ProducerRecord<String, String> record = buildRecord(i);
			producer.send(record, (data, exception) ->{
				if(exception != null) {
					exception.printStackTrace();
					return;
				}
				
				System.out.println("SUCESSO: Enviando a mensagem para o topico " + data.topic() + "::: particao: " + data.partition() + "::: timestamp: " + data.timestamp() + "::: offset: "+ data.offset() );
				
			}).get();
		}
		

	}

	/**
	 * Controi registros genericos para serem enviados a um tópico
	 * @param id
	 * @return
	 */
	private static ProducerRecord<String, String> buildRecord(int id) {
		
		StringBuilder value = new StringBuilder("cl");
		value.append(id);
		value.append(", client");
		value.append(id);
		value.append(",");
		value.append(100 * id);
		
		StringBuilder key = new StringBuilder("key");
		key.append(id);
		
		return new ProducerRecord<String, String>("CLIENTES_ADD_CLIENT", key.toString(), value.toString());
	}

	private static Properties getPropertiesConfig() {
		Properties prop = new Properties();
		prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return prop;
	}

}
