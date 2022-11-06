package mustafasamet.devs.business.concretes;

import mustafasamet.devs.business.abstracts.ProgrammingLanguageService;
import mustafasamet.devs.business.requests.programmingLanguage.CreateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.DeleteProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.responses.programmingLanguage.GetAllProgrammingLanguagesResponse;
import mustafasamet.devs.business.responses.programmingLanguage.GetByIdProgrammingLanguageResponse;
import mustafasamet.devs.dataAccess.concretes.ProgrammingLanguageRepository;
import mustafasamet.devs.entities.concretes.ProgrammingLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

    private ProgrammingLanguageRepository _programmingLanguageRepository;

    @Autowired
    public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository) {
        _programmingLanguageRepository = programmingLanguageRepository;
    }

    //business rules will add

    @Override
    public List<GetAllProgrammingLanguagesResponse> getAll() {
        List<ProgrammingLanguage> programmingLanguages = _programmingLanguageRepository.findAll();
        List<GetAllProgrammingLanguagesResponse> programmingLanguagesResponse = new ArrayList<>();

        for(ProgrammingLanguage programmingLanguage : programmingLanguages){
            GetAllProgrammingLanguagesResponse responseItem = new GetAllProgrammingLanguagesResponse();
            responseItem.setId(programmingLanguage.getId());
            responseItem.setName(programmingLanguage.getName());

            programmingLanguagesResponse.add(responseItem);
        }

        return programmingLanguagesResponse;
    }

    @Override
    public GetByIdProgrammingLanguageResponse getById(int id) {
        ProgrammingLanguage programmingLanguage = _programmingLanguageRepository.findById(id).get();
        GetByIdProgrammingLanguageResponse responseItem = new GetByIdProgrammingLanguageResponse();
        responseItem.setId(programmingLanguage.getId());
        responseItem.setName(programmingLanguage.getName());

        return responseItem;
    }

    @Override
    public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) {
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
        programmingLanguage.setName(createProgrammingLanguageRequest.getName());
        _programmingLanguageRepository.save(programmingLanguage);
    }

    @Override
    public void update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {
        ProgrammingLanguage programmingLanguage = _programmingLanguageRepository.findById(updateProgrammingLanguageRequest.getId()).get();
        programmingLanguage.setName(updateProgrammingLanguageRequest.getName());
        _programmingLanguageRepository.save(programmingLanguage);
    }

    @Override
    public void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) {
        _programmingLanguageRepository.deleteById(deleteProgrammingLanguageRequest.getId());
    }

}
