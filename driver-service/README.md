Driver Service (local folder)

Files added:
- src/main/java/com/bits/ride/hailing/DriverServiceApplication.java
- src/main/resources/application.yml (port 8082)
- pom.xml (standalone for driver-service)

Run (from driver-service folder):

mvn spring-boot:run -Dspring-boot.run.mainClass=com.bits.ride.hailing.DriverServiceApplication -Dspring-boot.run.arguments="--spring.config.location=file:./src/main/resources/application.yml"

Or build and run jar (from repo root):

mvn -DskipTests package
java -jar target\rider-service-0.0.1-SNAPSHOT.jar --spring.config.location=file:./driver-service/src/main/resources/application.yml

Note: To be fully independent, move driver controllers/services into this module's `src/main/java` (I can do that next).
