package mustafasamet.devs.dataAccess.concretes;

import mustafasamet.devs.entities.concretes.ProgrammingTechnology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingTechnologyRepository extends JpaRepository<ProgrammingTechnology, Integer> {
}
