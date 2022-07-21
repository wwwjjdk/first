package myCode;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException, LifecycleException {
        //создание и настройка tomcat
        final var tomcat = new Tomcat();
        final var baseDir = Files.createTempDirectory("tomcat");//создание временной директории для работы
        baseDir.toFile().deleteOnExit();//указание удаления всех файлов по завершению работы
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());//уставление абсолютного пути для томката, куда сохарнять файлы

        final var connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("",".");

        tomcat.start();
        tomcat.getServer().await();//команда ожидания заверщения потока

    }
}
