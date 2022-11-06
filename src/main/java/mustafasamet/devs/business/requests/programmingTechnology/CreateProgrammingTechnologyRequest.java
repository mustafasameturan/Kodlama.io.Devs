package mustafasamet.devs.business.requests.programmingTechnology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProgrammingTechnologyRequest {
    private String name;
    private int programmingLanguageId;
}
