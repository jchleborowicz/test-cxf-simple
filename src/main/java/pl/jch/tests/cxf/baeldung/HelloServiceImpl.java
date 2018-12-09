package pl.jch.tests.cxf.baeldung;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.jws.WebService;

import pl.jch.tests.cxf.baeldung.jaxb.Student;

@WebService(endpointInterface = "pl.jch.tests.cxf.baeldung.HelloService")
public class HelloServiceImpl implements HelloService {

    private Map<Integer, Student> students = new LinkedHashMap<>();

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

    @Override
    public String helloStudent(Student student) {
        students.put(students.size() + 1, student);
        return hello(student.getName());
    }

    @Override
    public Map<Integer, Student> getStudents() {
        return students;
    }
}
