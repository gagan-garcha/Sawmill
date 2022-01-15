# Welcome to Sawmill Manager

Sawmill Manager has different API's that is used to manage the sawmills.

## Requirements

The project requires [Java 11](https://www.oracle.com/java/technologies/downloads/#java11).

The project makes use of Gradle and uses
the [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html), which means you don't need Gradle
installed.

### Run the application

Run the application which will be listening on port `8050`.

```console
$ ./gradlew bootRun
```

## API

Below is a list of API endpoints with their respective input and output. Please note that the application needs to be
running for the following endpoints to work. For more information about how to run the application, please refer
to [run the application](#run-the-application) section above.

### Add Sawmill

Endpoint

```text
POST /api/v1/sawmill
```

Example of request body

```json
{
  "name": <name>,
  "city": <city>,
  "country": <country>
}
```

Parameters

| Parameter            | Description                              |
|----------------------|------------------------------------------|
| `name` `required`    | Sawmill name                             |
| `city`  `required`   | City name in which sawmill is located    |
| `country` `required` | Country name in which sawmill is located |


The following POST request, is an example request using cURL

```console
 curl --location --request POST 'http://localhost:8050/api/v1/sawmill' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "test",
    "city": "pune",
    "country": "India"
}'
```
The above command returns the inserted sawmill object

Example of response

```json
{
    "id": 1,
    "name": "test",
    "city": "pune",
    "country": "India",
    "created_at": "2022-01-15T13:12:05.635+00:00",
    "updated_at": "2022-01-15T13:12:05.635+00:00"
}
```