FROM Openjdk:17

COPY target/customer-management-service-0.0.1-SNAPSHOT.jar /usr/app/

WORDDIR /usr/app/

EXPOSE 8070

ENTRYPOINT["java", "-jar", "customer-management-service-0.0.1-SNAPSHOT.jar"