package pl.jch.tests.cxf.baeldung;

import java.util.concurrent.TimeUnit;
import javax.xml.ws.Endpoint;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        final HelloServiceImpl implementor = new HelloServiceImpl();

        final String address = "http://localhost:8888/hello";

        Endpoint.publish(address, implementor);

        Thread.sleep(TimeUnit.MINUTES.toMillis(10L));

        System.exit(0);
    }
}
