package mustafasamet.devs.business.responses.programmingLanguage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mustafasamet.devs.entities.concretes.ProgrammingTechnology;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProgrammingLanguagesResponse {
    private int id;
    private String name;
    //private List<ProgrammingTechnology> programmingTechnologies;

}
