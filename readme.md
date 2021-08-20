# create custom network
sudo docker network create -d bridge amt 

# start zookeeper
sudo docker run -p 2181:2181 --network=amt --name=zookeeper --env="ZOOKEEPER_CLIENT_PORT=2181" confluentinc/cp-zookeeper:latest

# start kafka
sudo docker run -p 29092:9092 --network=amt --name=kafka --env="KAFKA_BROKER_ID=1" --env="KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181" --env="KAFKA_INTER_BROKER_LISTENER_NAME=PLAINTEXT" --env="KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1" --env="KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092" --env="KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT"  confluentinc/cp-kafka:latest 


## write microservice

# write db service 
sudo docker run -p 3307:3306 --network="amt" --name=write-mysql-container --env="MYSQL_ROOT_PASSWORD=welcome" --env="MYSQL_DATABASE=write_db" mysql


# go to location where build.gradle for write service is present
cd $project_home/write/

# write jar generate executable jar or project
gradle clean build

# write-image build image from Dockerfile
sudo docker build -t write-image .

# write-service run
sudo docker run -t --name=write-service --network="amt" -p 8080:8080 write-image


## read microservice

# read db service
sudo docker run -p 3308:3306 --network="amt" --name=read-mysql-container --env="MYSQL_ROOT_PASSWORD=welcome" --env="MYSQL_DATABASE=read_db" mysql


# go to location where build.gradle for write service is present
cd $project_home/read/

# read jar generate executable jar or project
gradle clean build

# read-image build image from Dockerfile
sudo docker build -t read-image .

# read-service run
sudo docker run -t --name=read-service --network="amt" -p 8081:8080 read-image


## replicator


# go to location where build.gradle for write service is present
cd $project_home/replicator/

# read jar generate executable jar or project
gradle clean build

# read-image build image from Dockerfile
sudo docker build -t rpl-image .

# read-service run
sudo docker run -t --name=rpl-service --network="amt" -p 8082:8080 rpl-image




--------------------------------------------------------------
test data

# insert data in write service

curl -X POST \
  http://localhost:8080/customer \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 247a98ab-8c2c-cc6e-34b4-69cc7b4c41fc' \
  -d '{
	"name":"amit",
	"marketingPreference":"EMAIL"
	
curl -X POST \
  http://localhost:8080/customer \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 247a98ab-8c2c-cc6e-34b4-69cc7b4c41fc' \
  -d '{
	"name":"amit",
	"marketingPreference":"SMS"
	
curl -X POST \
  http://localhost:8080/customer \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 247a98ab-8c2c-cc6e-34b4-69cc7b4c41fc' \
  -d '{
	"name":"prateek",
	"marketingPreference":"POST"


# to check from read service
curl -X GET \
  http://localhost:8081/customer \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 2bc9bb05-2525-3934-327f-b63a103722d3'


