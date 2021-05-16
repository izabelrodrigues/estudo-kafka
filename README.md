# Projeto de Estudo do Kafka

### Pr�-requisitos

* <a href="https://www.apache.org/dyn/closer.cgi?path=/kafka/2.8.0/kafka_2.13-2.8.0.tgz">Kafka vers�o 2.13-2.8.0</a>

* <a href="https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html">Java 8</a>

### Como executar o projeto:

Depois de realizar o download do kafka, extraia para um diret�rio sem espa�os no nome 

#### -  Inicializar o servidor do Zookepper

Acesse o diret�rio onde foi extra�do os arquivos do Kafka e execute o comando abaixo (exemplo do GIT BASH - WINDOWS):


```
./bin/zookeeper-server-start.sh config/zookeeper.properties

```

#### -  Inicializar o servidor do Kafka

Acesse o diret�rio onde foi extra�do os arquivos do Kafka e execute o comando abaixo (exemplo do GIT BASH - WINDOWS):

```
./bin/kafka-server-start.sh config/server.properties

```

#### -  Executar o projeto no eclipse:

##### Executar produtores de mensagem:

Abra a classe NewRegistroProducerMain e com o bot�o direito execute Run As -> Java Application

No console voc� vai poder ver as mensagens sendo enviadas ao t�pico CLIENTES_ADD_CLIENT


##### Executar consumidores simples de mensagem:

Abra a classe RegistroConsumerMain e com o bot�o direito execute Run As -> Java Application

No console voc� vai poder ver as mensagens consumidas do t�pico CLIENTES_ADD_CLIENT
