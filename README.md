# WordCountService

A RESTful API service for analyzing word frequencies in text.  
Provides endpoints to get the frequency of the most common word, the frequency of a specific word, and the top N most common words and their frequencies.

> This project uses Quarkus, the Supersonic Subatomic Java Framework.

---

## Features

- `POST /words/most-common/frequency/` – Returns the frequency of the most frequent word in a given text.
- `POST /words/{word}/frequency/` – Returns the frequency of a specific word in a given text.
- `POST /words/most-common/{n}` – Returns the top N most common words with their frequencies.

---

## Running Locally

You can run the service locally using Docker.

```bash
mvn install
docker build -t wordcountservice:latest .
docker run -p 8080:8080 wordcountservice:latest
```

Alternatively, you can run it in dev mode using Maven:

```bash
mvn quarkus:dev
```

This will start the service on [http://localhost:8080](http://localhost:8080), and a swagger UI will be available at [http://localhost:8080/swagger](http://localhost:8080/swagger).

## Online Access

The service and swagger are also available to use online, at:
- [https://wcs.zeebizon.nl](https://wcs.zeebizon.nl)
- [https://wcs.zeebizon.nl/swagger](https://wcs.zeebizon.nl/swagger)
