package source.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import source.model.ClassRoom;
import source.model.Student;

public interface StudentService {
    Page<Student> findAll(Pageable pageable);

    Student findById(Long id);

    void save(Student student);

    void remove(Long id);

    Iterable<Student> findAllByClassRoom(ClassRoom classRoom);

    Page<Student> findAllByClassRoomContaining(String classRoom_name, Pageable pageable);

}
