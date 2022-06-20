## Сборка JAR

### Java 17

```
mvn -f pom.xml clean install
```

### Запуск джарика

```shell
java -jar -DfcFrom=<Директория с txt-файлами> -DfcTo=<Директория сохранения результата> -DfcFileName=<Название файла> file-collector-jar-with-dependencies.jar
```
