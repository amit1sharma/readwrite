start application by using command

docker-compose up

Note : please make sure docker demon is already running.

You can check status of docker service using command
systemctl status docker

if found inactive use following command to run docker
systemctl start docker

systemctl enable docker

if you get permission denied error use following command
sudo docker-compose up

if docker-compose not updating resources then use
sudo docker-compose build --no-cache
docker-compose up --build --remove-orphans --force-recreate

delete everything
docker system prune -a --volumes



linking docker container to call each other

docker run -t --name=write-web-container --link write-container:mysql -p 8080:8080 write-replicate
docker run -t --name=<web-container-name> --link <mysql-container-name>:<mysqlimage-name> -p 8080:8080 <web-image-name>



using only docker-

run mysql image
sudo docker run -d -p 3306:3306 --name=write-container --env="MYSQL_ROOT_PASSWORD=welcome" --env="MYSQL_DATABASE=write_db" mysql
sudo docker run -d -p 3306:3306 --name=<mysql-container-name> --env="MYSQL_ROOT_PASSWORD=welcome" --env="MYSQL_DATABASE=write_db" mysql


building web image
docker build -t write-replicate .


docker run -t --name=write-web-container --link write-container:mysql -p 8080:8080 write-replicate
docker run -t --name=<web-container-name> --link <mysql-container-name>:<mysqlimage-name> -p 8080:8080 <web-image-name>




mysql container : write-container
write web image name : write-replicate



to login into container
docker exec -it <container-name> bash
