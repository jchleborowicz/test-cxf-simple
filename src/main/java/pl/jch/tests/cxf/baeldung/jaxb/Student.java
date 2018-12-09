package pl.jch.tests.cxf.baeldung.jaxb;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(StudentAdapter.class)
public interface Student {

    String getName();

}
