package nz.co.kindergarten.application.database;

import nz.co.kindergarten.application.database.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByIdAndActive(Long id, Boolean active);
    Teacher findByEmailIdAndActive(String emailId, Boolean active);
}
