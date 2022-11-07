package mustafasamet.devs.business.abstracts;

import mustafasamet.devs.business.requests.programmingLanguage.CreateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.DeleteProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.responses.programmingLanguage.GetAllProgrammingLanguagesResponse;
import mustafasamet.devs.business.responses.programmingLanguage.GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse;
import mustafasamet.devs.business.responses.programmingLanguage.GetByIdProgrammingLanguageResponse;
import mustafasamet.devs.core.utilities.results.DataResult;
import mustafasamet.devs.core.utilities.results.Result;

import java.util.List;

public interface ProgrammingLanguageService {

    DataResult<List<GetAllProgrammingLanguagesResponse>> getAll();
    DataResult<List<GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse>> getAllWithProgrammingTechnologies();
    DataResult<GetByIdProgrammingLanguageResponse> getById(int id);
    Result add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest);
    Result update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest);
    Result delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest);
}
