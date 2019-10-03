package source.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import source.model.ClassRoom;
import source.model.Student;
import source.repository.StudentRepository;
import source.service.StudentService;

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
        studentRepository.delete(id);
    }

    @Override
    public Iterable<Student> findAllByClassRoom(ClassRoom classRoom) {
        return studentRepository.findAllByClassRoom(classRoom);
    }

    @Override
    public Page<Student> findAllByClassRoomContaining(String classRoom_name, Pageable pageable) {
        return studentRepository.findAllByClassRoom_Name(classRoom_name, pageable);
    }
}
