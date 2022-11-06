package mustafasamet.devs.business.responses.programmingTechnology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProgrammingTechnologiesResponse {

    private int id;
    private String name;
    private String languageName;
}
