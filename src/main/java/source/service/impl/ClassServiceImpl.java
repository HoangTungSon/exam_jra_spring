package source.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import source.model.ClassRoom;
import source.repository.ClassRepository;
import source.service.ClassService;

public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Override
    public Iterable<ClassRoom> findAll() {
        return classRepository.findAll();
    }

    @Override
    public ClassRoom findById(Long id) {
        return classRepository.findOne(id);
    }

    @Override
    public void save(ClassRoom classRoom) {
        classRepository.save(classRoom);
    }

    @Override
    public void remove(Long id) {
        classRepository.delete(id);
    }
}
