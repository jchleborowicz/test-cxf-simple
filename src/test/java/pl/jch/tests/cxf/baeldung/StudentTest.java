package pl.jch.tests.cxf.baeldung;

import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.junit.Before;
import org.junit.Test;
import pl.jch.tests.cxf.baeldung.jaxb.Student;
import pl.jch.tests.cxf.baeldung.jaxb.StudentImpl;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * You must exec <b>mvn exec:java</b> to get this test to work.
 */
public class StudentTest {

    private static final String NAMESPACE_URI = "http://baeldung.cxf.tests.jch.pl/";
    private static final QName SERVICE_NAME = new QName(NAMESPACE_URI, "HelloService");
    private static final QName PORT_NAME = new QName(NAMESPACE_URI, "HelloServicePort");
    public static final String ENDPOINT_ADDRESS = "http://localhost:8888/hello";

    private final Service service;
    private HelloService helloServiceProxy;
    private HelloServiceImpl helloServiceImpl;

    {
        this.service = Service.create(SERVICE_NAME);
        this.service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_ADDRESS);
    }

    @Before
    public void setUp() {
        helloServiceImpl = new HelloServiceImpl();
        helloServiceProxy = this.service.getPort(PORT_NAME, HelloService.class);
    }

    @Test
    public void whenUsingHelloMethodThenCorrect() {
        final String endpointResponse = this.helloServiceProxy.hello("Jacek");
        final String localResponse = this.helloServiceImpl.hello("Jacek");

        assertThat(localResponse).isEqualTo(endpointResponse);
    }

    @Test
    public void whenUsingHelloStudentMethodThenCorrect() {
        final Student student = new StudentImpl("John Doe");
        final String endpointResponse = this.helloServiceProxy.helloStudent(student);
        final String localResponse = this.helloServiceImpl.helloStudent(student);

        assertThat(localResponse).isEqualTo(endpointResponse);
    }

    @Test
    public void whenUsingGetStudentsMethodThenCorrect() {
        final Student student1 = new StudentImpl("Adam");
        this.helloServiceProxy.helloStudent(student1);

        final Student student2 = new StudentImpl("Eve");
        this.helloServiceProxy.helloStudent(student2);

        final Map<Integer, Student> students = this.helloServiceProxy.getStudents();
        final Set<String> names = students.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .map(Student::getName)
                .collect(toSet());

        assertThat(names).contains("Adam");
        assertThat(names).contains("Eve");
    }
}
