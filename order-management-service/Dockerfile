FROM Openjdk:17

COPY target/order-management-service-0.0.1-SNAPSHOT.jar /usr/app/

WORDDIR /usr/app/

EXPOSE 8071

ENTRYPOINT["java", "-jar", "order-management-service-0.0.1-SNAPSHOT.jar"