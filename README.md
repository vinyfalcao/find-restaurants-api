# find-restaurants-api

Async and Non-blocking Api to find registered Restaurants on the Database and Available Restaurants to register on Google Places API


<h3>Requirements:</h3>

docker

docker-compose

<h3>How to use</h3>
<h4>To up Database</h4>
<br/>docker-compose up -d

<br/>
<h4>To up Application</h4>
docker build . 

<h3>Technologies</h3>

<b>Spring Boot</b>

<b>Database:</b> Spring Data and PostgreSQL

<b>Database Versioning:</b> Flyway

<b>Test Container:</b> docker-client (Spotify)

<b>Swagger: </b>Spring FOX

<b>Entity Converting: Orika

<b>Aditional things:</b>

<b>Lombok</b> for write less code


<b>To see Swagger 2 UI:</b>
<br/>
http://localhost:8080/swagger-ui.html

<b>Tests</b>
The Integration Tests are using Docker-client and Flyway to populate database using JUnit ClassRule 
