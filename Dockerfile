FROM openjdk:23-slim-bullseye AS build
WORKDIR /app
COPY src /app/src
RUN javac -d out src/*.java
RUN jar cvfe /app/opcua.jar com.shogunautomation.opcuacli.Main -C ./out/ .
CMD java -jar opcua.jar		\
	--host=localhost	\
	--port 16010		\
	--random_ip=192.168.0.1	\
	--name "Jean Val Jean"

