
## Challenge description

1. download apache kafka locally, (just need to unzip) and run it (zookeeper & kafka servers)
2. create 1 kafka topic called "novice-players"

3. create the controllers, services, repositories and entities needed to do the following:

- a POST endpoint that receives an array of players
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
- for each "player" object you need to check its "type" property
  - if it is "expert" you need to store the player in a H2 database.
  - if it is "novice" you need to send that object via a kafka event to the "novice-players" topic
  - if the type does not fit one of the above, you will let the user know in the endpoint response
the response for the above payload would be:

```json
{
  "result": [
    "player Sub zero stored in DB",
    "player Scorpion sent to Kafka topic",
    "player Reptile did not fit"
  ]
}
````

4. create a README.md with the detailed steps to run the project locally, containing:
   - which version of apache Kafka was used
   - the command used to create the topic as well as the command to read the events of that topic.
   - the commands to run Spring Boot (either maven or gradle)