package pl.jch.tests.cxf.baeldung.jaxb;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Student")
public class StudentImpl implements Student {
    private String name;

    public StudentImpl() {
    }

    public StudentImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
