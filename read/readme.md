using only docker-
create a network

sudo docker network create -d bridge yamlok 


run mysql image
sudo docker run -d -p 3307:3306 --network="yamlok" --name=read-mysql-container --env="MYSQL_ROOT_PASSWORD=welcome" --env="MYSQL_DATABASE=read_db" mysql


building web image
docker build -t read-image .

run web-container linking with mysql container
docker run -t --name=read-web-container --network="yamlok" -p 8081:8080 read-image
this is only proper connecting to 3306 default port.

to login into container
docker exec -it <container-name> bash
