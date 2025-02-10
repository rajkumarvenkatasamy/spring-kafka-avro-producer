# About

This is a demo project created for the purpose of demonstrating how to produce avro messages with Schema registry and 
Spring Boot Kafka

## How to run

1. Start zookeeper, Kafka, and Schema Registry using docker-compose
```shell
docker-compose -f docker-compose-zk-kafka-sr.yaml up -d
```
2. Start Redpanda console container using docker-compose
```shell
docker-compose -f docker-compose-redpanda-console.yaml up -d
```
3. Create topics in Kafka - demo_json, demo_avro
4. Configure the values in KafkaProducerConfig.java as per your environment
5. Build the gradle project
6. Run the project
7. Hit the POST endpoint `http://localhost:8080/api/kafka/send?name=John&age=30` to produce an avro message 
8. Use Redpanda console to view the messages in the topics. You can also use the Kafka Console Consumer in the Kafka container
```shell
JMX_PORT=9998 kafka-console-consumer --bootstrap-server localhost:9092 --topic demo_json --from-beginning --property p
rint.key=true --property print.value=true

JMX_PORT=9998 kafka-console-consumer --bootstrap-server localhost:9092 --topic demo_avro --from-beginning --property p
rint.key=true --property print.value=true
```
9. You can also use Kafka Avro Console Consumer in the Schema registry container to consume the message
10. Hit the POST endpoint `http://localhost:8080/api/kafka/sendJson?name=John&age=30` to produce a json message


## Unique Needs of the Project

1. Avro schema: src/main/avro/User.avsc
2. Avro related plugin, dependencies, task and configurations in build.gradle