package mustafasamet.devs.business.responses.programmingLanguage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mustafasamet.devs.business.responses.programmingTechnology.GetAllProgrammingTechnologiesNameResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse {
    private int id;
    private String name;
    private List<GetAllProgrammingTechnologiesNameResponse> programmingTechnologies;
}
