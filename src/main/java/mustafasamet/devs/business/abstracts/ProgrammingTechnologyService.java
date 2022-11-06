package mustafasamet.devs.business.abstracts;

import mustafasamet.devs.business.requests.programmingLanguage.DeleteProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingTechnology.CreateProgrammingTechnologyRequest;
import mustafasamet.devs.business.requests.programmingTechnology.DeleteProgrammingTechnologyRequest;
import mustafasamet.devs.business.requests.programmingTechnology.UpdateProgrammingTechnologyRequest;
import mustafasamet.devs.business.responses.programmingTechnology.GetAllProgrammingTechnologiesResponse;
import mustafasamet.devs.business.responses.programmingTechnology.GetByIdProgrammingTechnologyResponse;

import java.util.List;

public interface ProgrammingTechnologyService {

    List<GetAllProgrammingTechnologiesResponse> getAll();
    GetByIdProgrammingTechnologyResponse getById(int id);

    void add(CreateProgrammingTechnologyRequest createProgrammingTechnologyRequest);
    void update(UpdateProgrammingTechnologyRequest updateProgrammingTechnologyRequest);
    void delete(DeleteProgrammingTechnologyRequest deleteProgrammingTechnologyRequest);
}
