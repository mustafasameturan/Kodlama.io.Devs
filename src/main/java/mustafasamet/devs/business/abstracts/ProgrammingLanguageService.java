package mustafasamet.devs.business.abstracts;

import mustafasamet.devs.business.requests.programmingLanguage.CreateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.DeleteProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.responses.programmingLanguage.GetAllProgrammingLanguagesResponse;
import mustafasamet.devs.business.responses.programmingLanguage.GetByIdProgrammingLanguageResponse;

import java.util.List;

public interface ProgrammingLanguageService {

    List<GetAllProgrammingLanguagesResponse> getAll();
    GetByIdProgrammingLanguageResponse getById(int id);
    void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest);

    void update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest);
    void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest);
}
