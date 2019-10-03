package source.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import source.model.ClassRoom;
import source.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    Iterable<Student> findAllByClassRoom(ClassRoom classRoom);

    Page<Student> findAllByClassRoom_Name(String name, Pageable pageable);
}
