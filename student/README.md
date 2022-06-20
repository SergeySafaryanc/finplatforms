## Сборка

Для создания Docker-образа приложения необходимо выполнить команду:

````
docker build -t ssafaryanc/student .
````

## Запуск

Для запуска Docker-образа необходимо выполнить команду:

```
docker run \
-p 0.0.0.0:8080:8080 \
-e LOG_NAME=example.log \
-v $HOME/logs/:/log \
ssafaryanc/student:latest
```

Переходите [http://0.0.0.0:8080/swagger-ui](http://0.0.0.0:8080/swagger-ui) 
