package mustafasamet.devs.business.concretes;

import mustafasamet.devs.business.abstracts.ProgrammingTechnologyService;
import mustafasamet.devs.business.constants.Messages;
import mustafasamet.devs.business.requests.programmingLanguage.DeleteProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingTechnology.CreateProgrammingTechnologyRequest;
import mustafasamet.devs.business.requests.programmingTechnology.DeleteProgrammingTechnologyRequest;
import mustafasamet.devs.business.requests.programmingTechnology.UpdateProgrammingTechnologyRequest;
import mustafasamet.devs.business.responses.programmingTechnology.GetAllProgrammingTechnologiesResponse;
import mustafasamet.devs.business.responses.programmingTechnology.GetByIdProgrammingTechnologyResponse;
import mustafasamet.devs.core.utilities.business.BusinessRules;
import mustafasamet.devs.core.utilities.results.*;
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

    @Override
    public DataResult<List<GetAllProgrammingTechnologiesResponse>> getAll() {
        List<ProgrammingTechnology> programmingTechnologies = _programmingTechnologyRepository.findAll();
        List<GetAllProgrammingTechnologiesResponse> programmingTechnologiesResponse = new ArrayList<>();

        for(ProgrammingTechnology programmingTechnology : programmingTechnologies){
            GetAllProgrammingTechnologiesResponse responseItem = new GetAllProgrammingTechnologiesResponse();
            responseItem.setId(programmingTechnology.getId());
            responseItem.setName(programmingTechnology.getName());
            responseItem.setLanguageName(programmingTechnology.getProgrammingLanguage().getName());
            programmingTechnologiesResponse.add(responseItem);
        }

        return new SuccessDataResult<List<GetAllProgrammingTechnologiesResponse>>(programmingTechnologiesResponse, Messages.PROGRAMMING_TECHNOLOGIES_LISTED);
    }

    @Override
    public DataResult<GetByIdProgrammingTechnologyResponse> getById(int id) {
        ProgrammingTechnology programmingTechnology = _programmingTechnologyRepository.findById(id).get();
        GetByIdProgrammingTechnologyResponse responseItem = new GetByIdProgrammingTechnologyResponse();
        responseItem.setId(programmingTechnology.getId());
        responseItem.setName(programmingTechnology.getName());
        responseItem.setProgrammingLanguageName(programmingTechnology.getProgrammingLanguage().getName());

        return new SuccessDataResult<GetByIdProgrammingTechnologyResponse>(responseItem, Messages.PROGRAMMING_TECHNOLOGY_GET_BY_ID);
    }

    @Override
    public Result add(CreateProgrammingTechnologyRequest createProgrammingTechnologyRequest) {
        Result result = BusinessRules.Run(checkIfProgrammingTechnologyNameExist(createProgrammingTechnologyRequest.getName()),
                                          checkIfProgrammingTechnologyNameIsEmpty(createProgrammingTechnologyRequest.getName()));
        if(result != null) {
            return result;
        }
        ProgrammingTechnology programmingTechnology = new ProgrammingTechnology();
        ProgrammingLanguage programmingLanguage = _programmingLanguageRepository.findById(createProgrammingTechnologyRequest.getProgrammingLanguageId()).get();

        programmingTechnology.setName(createProgrammingTechnologyRequest.getName());
        programmingTechnology.setProgrammingLanguage(programmingLanguage);

        _programmingTechnologyRepository.save(programmingTechnology);

        return new SuccessResult(Messages.PROGRAMMING_TECHNOLOGY_ADDED);
    }

    @Override
    public Result update(UpdateProgrammingTechnologyRequest updateProgrammingTechnologyRequest) {
        Result result = BusinessRules.Run(checkIfProgrammingTechnologyIdDoesntExist(updateProgrammingTechnologyRequest.getId()));
        if(result != null) {
            return result;
        }

        ProgrammingTechnology programmingTechnology = _programmingTechnologyRepository.findById(updateProgrammingTechnologyRequest.getId()).get();
        ProgrammingLanguage programmingLanguage = _programmingLanguageRepository.findById(updateProgrammingTechnologyRequest.getProgrammingLanguageId()).get();

        programmingTechnology.setName(updateProgrammingTechnologyRequest.getName());
        programmingTechnology.setProgrammingLanguage(programmingLanguage);

        _programmingTechnologyRepository.save(programmingTechnology);
        return new SuccessResult(Messages.PROGRAMMING_TECHNOLOGY_UPDATED);
    }

    @Override
    public Result delete(DeleteProgrammingTechnologyRequest deleteProgrammingTechnologyRequest) {
        Result result = BusinessRules.Run(checkIfProgrammingTechnologyIdDoesntExist(deleteProgrammingTechnologyRequest.getId()));
        if(result != null) {
            return result;
        }

        _programmingTechnologyRepository.deleteById(deleteProgrammingTechnologyRequest.getId());
        return new SuccessResult(Messages.PROGRAMMING_TECHNOLOGY_DELETED);
    }

    //Business Rules
    public Result checkIfProgrammingTechnologyNameExist(String name){
        List<ProgrammingTechnology> programmingTechnologies = _programmingTechnologyRepository.findAll();
        for(ProgrammingTechnology programmingTechnology : programmingTechnologies){
            if(programmingTechnology.getName().equals(name)){
                return new ErrorResult(Messages.PROGRAMMING_TECHNOLOGY_NAME_EXIST);
            }
        }
        return new SuccessResult();
    }

    public Result checkIfProgrammingTechnologyNameIsEmpty(String name){
        if(name.isEmpty()){
            return new ErrorResult(Messages.PROGRAMMING_TECHNOLOGY_NAME_CANNOT_BE_EMPTY);
        }
        return new SuccessResult();
    }

    public Result checkIfProgrammingTechnologyIdDoesntExist(int id){
        boolean programmingTechnology = _programmingTechnologyRepository.existsById(id);
        if(programmingTechnology == false){
            return new ErrorResult(Messages.PROGRAMMING_TECHNOLOGY_DOESNT_EXIST);
        }

        return new SuccessResult();
    }
}
