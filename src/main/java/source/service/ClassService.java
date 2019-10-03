package source.service;

import source.model.ClassRoom;

public interface ClassService {
    Iterable<ClassRoom> findAll();

    ClassRoom findById(Long id);

    void save(ClassRoom classRoom);

    void remove(Long id);

}
