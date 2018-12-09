package pl.jch.tests.cxf.baeldung.jaxb;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import static java.util.stream.Collectors.toMap;

public class StudentMapAdapter extends XmlAdapter<StudentMap, Map<Integer, Student>> {

    @Override
    public StudentMap marshal(Map<Integer, Student> boundMap) {
        final StudentMap result = new StudentMap();

        boundMap.forEach((Integer id, Student student) -> {
            final StudentMap.StudentEntry studentEntry = new StudentMap.StudentEntry();
            studentEntry.setId(id);
            studentEntry.setStudent(student);
            result.getEntries().add(studentEntry);
        });

        return result;
    }

    @Override
    public Map<Integer, Student> unmarshal(StudentMap valueMap) {
        return valueMap.getEntries()
                .stream()
                .collect(
                        toMap(
                                StudentMap.StudentEntry::getId,
                                StudentMap.StudentEntry::getStudent,
                                (Student v1, Student v2) -> {
                                    throw new RuntimeException(
                                            String.format("Duplicate key for values %s and %s", v1, v2));
                                },
                                LinkedHashMap::new
                        ));
    }
}
