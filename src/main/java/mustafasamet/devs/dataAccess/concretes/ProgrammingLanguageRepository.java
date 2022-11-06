package mustafasamet.devs.dataAccess.concretes;

import mustafasamet.devs.entities.concretes.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Integer> {
}
