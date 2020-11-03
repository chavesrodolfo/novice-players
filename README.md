# Novice Players API

Pre Requirements: **JDK 8+**, **Maven** and **Kafka 2.13-2.6.0** installed.

## Runing Kafka
Download and configure following the instructions on the [quickstart](https://kafka.apache.org/quickstart).

Run the following commands in order to start all services in the correct order:

```properties
# Start the ZooKeeper service
bin/zookeeper-server-start.sh config/zookeeper.properties
````

Open another terminal session and run:

```properties
# Start the Kafka broker service
bin/kafka-server-start.sh config/server.properties
````

Open another terminal session and run the console consumer client **to create or read the events** 

Create a topic:

```properties
bin/kafka-topics.sh --create --topic novice-players --bootstrap-server localhost:9092
```

Read the topic:

```properties
bin/kafka-console-consumer.sh --topic novice-players --from-beginning --bootstrap-server localhost:9092
```

## Runing Java API

Run the command below using Maven:

```properties
mvn spring-boot:run
```

Compile the project and run test cases:

```properties
# Generate package running all test cases (Kafka must be running)
mvn install
```

Or just compile the project:

```properties
# Generate package skiping test cases
mvn install -DskipTests 
```

Run the java artifact:

```properties
# Run jar file
java -jar target/novice-players-0.0.1-SNAPSHOT.jar 
```

## Testing
Call Http POST *`http://localhost:8080/api/players`* using the payload below.

Header: *Content-Type: application/json*

```json
{
  "players": [
    {
      "name": "Sub zero",
      "type": "expert"
    },
    {
      "name": "Scorpion",
      "type": "novice"
    },
    {
      "name": "Reptile",
      "type": "meh"
    }
  ]
}
```

**Expected result**

The following json:
```json
{
  "result": [
    "player Sub zero stored in DB",
    "player Scorpion sent to Kafka topic",
    "player Reptile did not fit"
  ]
}
```

> You can access H2 database at [*http://localhost:8080/h2*](http://localhost:8080/h2)

More informations about the challenge in [Challenge.md](Challenge.md)