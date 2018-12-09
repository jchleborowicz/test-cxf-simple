package pl.jch.tests.cxf.baeldung;

import java.util.Map;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import pl.jch.tests.cxf.baeldung.jaxb.Student;
import pl.jch.tests.cxf.baeldung.jaxb.StudentMapAdapter;

@WebService
public interface HelloService {

    String hello(String name);

    String helloStudent(Student student);

    @XmlJavaTypeAdapter(StudentMapAdapter.class)
    Map<Integer, Student> getStudents();
}
