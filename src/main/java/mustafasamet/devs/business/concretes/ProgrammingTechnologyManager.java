package mustafasamet.devs.business.concretes;

import mustafasamet.devs.business.abstracts.ProgrammingTechnologyService;
import mustafasamet.devs.business.requests.programmingLanguage.DeleteProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingTechnology.CreateProgrammingTechnologyRequest;
import mustafasamet.devs.business.requests.programmingTechnology.DeleteProgrammingTechnologyRequest;
import mustafasamet.devs.business.requests.programmingTechnology.UpdateProgrammingTechnologyRequest;
import mustafasamet.devs.business.responses.programmingTechnology.GetAllProgrammingTechnologiesResponse;
import mustafasamet.devs.business.responses.programmingTechnology.GetByIdProgrammingTechnologyResponse;
import mustafasamet.devs.dataAccess.concretes.ProgrammingLanguageRepository;
import mustafasamet.devs.dataAccess.concretes.ProgrammingTechnologyRepository;
import mustafasamet.devs.entities.concretes.ProgrammingLanguage;
import mustafasamet.devs.entities.concretes.ProgrammingTechnology;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgrammingTechnologyManager implements ProgrammingTechnologyService {

    private ProgrammingTechnologyRepository _programmingTechnologyRepository;
    private ProgrammingLanguageRepository _programmingLanguageRepository;

    public ProgrammingTechnologyManager(ProgrammingTechnologyRepository programmingTechnologyRepository, ProgrammingLanguageRepository programmingLanguageRepository) {
        _programmingTechnologyRepository = programmingTechnologyRepository;
        _programmingLanguageRepository = programmingLanguageRepository;
    }

    //business rules will add

    @Override
    public List<GetAllProgrammingTechnologiesResponse> getAll() {
        List<ProgrammingTechnology> programmingTechnologies = _programmingTechnologyRepository.findAll();
        List<GetAllProgrammingTechnologiesResponse> programmingTechnologiesResponse = new ArrayList<>();

        for(ProgrammingTechnology programmingTechnology : programmingTechnologies){
            GetAllProgrammingTechnologiesResponse responseItem = new GetAllProgrammingTechnologiesResponse();
            responseItem.setId(programmingTechnology.getId());
            responseItem.setName(programmingTechnology.getName());
            responseItem.setLanguageName(programmingTechnology.getProgrammingLanguage().getName());
            programmingTechnologiesResponse.add(responseItem);
        }

        return programmingTechnologiesResponse;
    }

    @Override
    public GetByIdProgrammingTechnologyResponse getById(int id) {
        ProgrammingTechnology programmingTechnology = _programmingTechnologyRepository.findById(id).get();
        GetByIdProgrammingTechnologyResponse responseItem = new GetByIdProgrammingTechnologyResponse();
        responseItem.setId(programmingTechnology.getId());
        responseItem.setName(programmingTechnology.getName());
        responseItem.setProgrammingLanguageName(programmingTechnology.getProgrammingLanguage().getName());

        return responseItem;
    }

    @Override
    public void add(CreateProgrammingTechnologyRequest createProgrammingTechnologyRequest) {
        ProgrammingTechnology programmingTechnology = new ProgrammingTechnology();
        ProgrammingLanguage programmingLanguage = _programmingLanguageRepository.findById(createProgrammingTechnologyRequest.getProgrammingLanguageId()).get();

        programmingTechnology.setName(createProgrammingTechnologyRequest.getName());
        programmingTechnology.setProgrammingLanguage(programmingLanguage);

        _programmingTechnologyRepository.save(programmingTechnology);
    }

    @Override
    public void update(UpdateProgrammingTechnologyRequest updateProgrammingTechnologyRequest) {
        ProgrammingTechnology programmingTechnology = _programmingTechnologyRepository.findById(updateProgrammingTechnologyRequest.getId()).get();
        ProgrammingLanguage programmingLanguage = _programmingLanguageRepository.findById(updateProgrammingTechnologyRequest.getProgrammingLanguageId()).get();

        programmingTechnology.setName(updateProgrammingTechnologyRequest.getName());
        programmingTechnology.setProgrammingLanguage(programmingLanguage);

        _programmingTechnologyRepository.save(programmingTechnology);
    }

    @Override
    public void delete(DeleteProgrammingTechnologyRequest deleteProgrammingTechnologyRequest) {
        _programmingTechnologyRepository.deleteById(deleteProgrammingTechnologyRequest.getId());
    }
}
