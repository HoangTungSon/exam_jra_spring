package source.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import source.model.ClassRoom;

public interface ClassRepository extends PagingAndSortingRepository<ClassRoom, Long> {
}
