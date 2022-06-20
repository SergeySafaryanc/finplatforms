package ru.finplatforms.filecollector;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        final String fileName = System.getProperty("fcFileName", "res.txt");
        final String fromDir = System.getProperty("fcFrom", "/home/quantum/IdeaProjects/finplatforms/data");
        final String toDir = System.getProperty("fcTo", "/home/quantum/IdeaProjects/finplatforms");

        try (CamelContext context = new DefaultCamelContext()) {

            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("file:" + fromDir + "?include=.*\\.txt&recursive=true&sortBy=reverse:file:name")
                            .log(LoggingLevel.INFO, "Scanning file ${file:name.noext}.${file:name.ext}...")
                            .to("file:" + toDir + "?fileName=" + fileName + "&fileExist=Append");
                }
            });
            context.start();

            Thread.sleep(3000);
        } catch (Exception e) {
            log.error(e);
        }
    }
}
