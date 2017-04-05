Very good article used to build this project: https://spring.io/blog/2016/06/24/managing-secrets-with-vault


################################################################
# To build
################################################################
mvn clean install


################################################################
# To run
################################################################
- prerequisites:
    - set up and start Consul: see setupConsult.txt under /test/resources
    - set up and start Vault: see setupVault.txt and testApplicationProperties.txt

- with default profile:
    - java -jar target/spring-cloud-vault-0.1.0.jar

- with prod profile:
    - java -jar -Dspring.profiles.active=prod target/spring-cloud-vault-0.1.0.jar


################################################################
# To test the app itself
################################################################
- curl http://localhost:8281/mgmt/health -v -X GET
200 {"description":"Spring Cloud Consul Discovery Client","status":"UP"}


- curl http://localhost:8181 -v -X GET
200 Greetings from Spring Boot: Key is prodKey and Template is prodTemplate


################################################################
# To test the service discovery
################################################################
- see testServiceDiscovery.txt


################################################################
# To test the application properties configuration
################################################################
- see testApplicationProperties.txt


# TODO Use Consul for the backend of Vault
