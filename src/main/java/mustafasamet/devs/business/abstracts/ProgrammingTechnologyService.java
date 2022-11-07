package mustafasamet.devs.business.abstracts;

import mustafasamet.devs.business.requests.programmingLanguage.DeleteProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingTechnology.CreateProgrammingTechnologyRequest;
import mustafasamet.devs.business.requests.programmingTechnology.DeleteProgrammingTechnologyRequest;
import mustafasamet.devs.business.requests.programmingTechnology.UpdateProgrammingTechnologyRequest;
import mustafasamet.devs.business.responses.programmingTechnology.GetAllProgrammingTechnologiesResponse;
import mustafasamet.devs.business.responses.programmingTechnology.GetByIdProgrammingTechnologyResponse;
import mustafasamet.devs.core.utilities.results.DataResult;
import mustafasamet.devs.core.utilities.results.Result;

import java.util.List;

public interface ProgrammingTechnologyService {

    DataResult<List<GetAllProgrammingTechnologiesResponse>> getAll();
    DataResult<GetByIdProgrammingTechnologyResponse> getById(int id);
    Result add(CreateProgrammingTechnologyRequest createProgrammingTechnologyRequest);
    Result update(UpdateProgrammingTechnologyRequest updateProgrammingTechnologyRequest);
    Result delete(DeleteProgrammingTechnologyRequest deleteProgrammingTechnologyRequest);
}
