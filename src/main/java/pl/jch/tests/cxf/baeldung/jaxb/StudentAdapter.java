package pl.jch.tests.cxf.baeldung.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StudentAdapter extends XmlAdapter<StudentImpl, Student> {

    @Override
    public StudentImpl marshal(Student student) {
        if (student instanceof StudentImpl) {
            return (StudentImpl) student;
        }

        return new StudentImpl();
    }

    @Override
    public Student unmarshal(StudentImpl student) {
        return student;
    }
}
