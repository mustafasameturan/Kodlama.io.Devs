package mustafasamet.devs.business.concretes;

import mustafasamet.devs.business.abstracts.ProgrammingLanguageService;
import mustafasamet.devs.business.constants.Messages;
import mustafasamet.devs.business.requests.programmingLanguage.CreateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.DeleteProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.responses.programmingLanguage.GetAllProgrammingLanguagesResponse;
import mustafasamet.devs.business.responses.programmingLanguage.GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse;
import mustafasamet.devs.business.responses.programmingLanguage.GetByIdProgrammingLanguageResponse;
import mustafasamet.devs.business.responses.programmingTechnology.GetAllProgrammingTechnologiesNameResponse;
import mustafasamet.devs.core.utilities.business.BusinessRules;
import mustafasamet.devs.core.utilities.results.*;
import mustafasamet.devs.dataAccess.concretes.ProgrammingLanguageRepository;
import mustafasamet.devs.dataAccess.concretes.ProgrammingTechnologyRepository;
import mustafasamet.devs.entities.concretes.ProgrammingLanguage;
import mustafasamet.devs.entities.concretes.ProgrammingTechnology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

    private ProgrammingLanguageRepository _programmingLanguageRepository;
    private ProgrammingTechnologyRepository _programmingTechnologyRepository;

    @Autowired
    public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository, ProgrammingTechnologyRepository programmingTechnologyRepository) {
        _programmingLanguageRepository = programmingLanguageRepository;
        _programmingTechnologyRepository = programmingTechnologyRepository;
    }

    @Override
    public DataResult<List<GetAllProgrammingLanguagesResponse>> getAll() {
        List<ProgrammingLanguage> programmingLanguages = _programmingLanguageRepository.findAll();
        List<GetAllProgrammingLanguagesResponse> programmingLanguagesResponse = new ArrayList<>();

        for(ProgrammingLanguage programmingLanguage : programmingLanguages){
            GetAllProgrammingLanguagesResponse responseItem = new GetAllProgrammingLanguagesResponse();
            responseItem.setId(programmingLanguage.getId());
            responseItem.setName(programmingLanguage.getName());

            programmingLanguagesResponse.add(responseItem);
        }

        return new SuccessDataResult<List<GetAllProgrammingLanguagesResponse>>(programmingLanguagesResponse, Messages.PROGRAMMING_LANGUAGES_LISTED);
    }

    @Override
    public DataResult<List<GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse>> getAllWithProgrammingTechnologies() {
        List<ProgrammingLanguage> programmingLanguages = _programmingLanguageRepository.findAll();
        List<ProgrammingTechnology> programmingTechnologies = _programmingTechnologyRepository.findAll();
        List<GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse> programmingLanguagesWithProgrammingTechnologies = new ArrayList<GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse>();

        for(ProgrammingLanguage programmingLanguage : programmingLanguages){
            GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse responseItem = new GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse();
            responseItem.setId(programmingLanguage.getId());
            responseItem.setName(programmingLanguage.getName());

            List<GetAllProgrammingTechnologiesNameResponse> programmingTechnologiesResponse = new ArrayList<GetAllProgrammingTechnologiesNameResponse>();
            for (ProgrammingTechnology programmingTechnology : programmingTechnologies){
                if(programmingTechnology.getProgrammingLanguage().getId() == programmingLanguage.getId()){
                    GetAllProgrammingTechnologiesNameResponse responseItemTechnology = new GetAllProgrammingTechnologiesNameResponse();
                    responseItemTechnology.setId(programmingTechnology.getId());
                    responseItemTechnology.setName(programmingTechnology.getName());
                    programmingTechnologiesResponse.add(responseItemTechnology);
                }
            }

            responseItem.setProgrammingTechnologies(programmingTechnologiesResponse);
            programmingLanguagesWithProgrammingTechnologies.add(responseItem);
        }
        return new SuccessDataResult<List<GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse>>(programmingLanguagesWithProgrammingTechnologies, Messages.PROGRAMMING_LANGUAGES_LISTED_WITH_TECHNOLOGIES);
    }

    @Override
    public DataResult<GetByIdProgrammingLanguageResponse> getById(int id) {
        ProgrammingLanguage programmingLanguage = _programmingLanguageRepository.findById(id).get();
        GetByIdProgrammingLanguageResponse responseItem = new GetByIdProgrammingLanguageResponse();
        responseItem.setId(programmingLanguage.getId());
        responseItem.setName(programmingLanguage.getName());

        return new SuccessDataResult<GetByIdProgrammingLanguageResponse>(responseItem, Messages.PROGRAMMING_LANGUAGE_GET_BY_ID);
    }

    @Override
    public Result add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) {
        Result result = BusinessRules.Run(checkIfProgrammingLanguageNameExist(createProgrammingLanguageRequest.getName()),
                                          checkIfProgrammingLanguageNameIsEmpty(createProgrammingLanguageRequest.getName()));

        if(result != null){
            return result;
        }

        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
        programmingLanguage.setName(createProgrammingLanguageRequest.getName());
        _programmingLanguageRepository.save(programmingLanguage);
        return new SuccessResult(Messages.PROGRAMMING_LANGUAGE_ADDED);
    }

    @Override
    public Result update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {
        Result result = BusinessRules.Run(checkIfProgrammingLanguageIdDoesntExist(updateProgrammingLanguageRequest.getId()));
        if(result != null) {
            return result;
        }

        ProgrammingLanguage programmingLanguage = _programmingLanguageRepository.findById(updateProgrammingLanguageRequest.getId()).get();
        programmingLanguage.setName(updateProgrammingLanguageRequest.getName());
        _programmingLanguageRepository.save(programmingLanguage);

        return new SuccessResult(Messages.PROGRAMMING_LANGUAGE_UPDATED);
    }

    @Override
    public Result delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) {
        Result result = BusinessRules.Run(checkIfProgrammingLanguageIdDoesntExist(deleteProgrammingLanguageRequest.getId()));
        if(result != null){
            return result;
        }
        _programmingLanguageRepository.deleteById(deleteProgrammingLanguageRequest.getId());
        return new SuccessResult(Messages.PROGRAMMING_LANGUAGE_DELETED);
    }

    //Business Rules
    public Result checkIfProgrammingLanguageNameExist(String name){
        List<ProgrammingLanguage> programmingLanguages = _programmingLanguageRepository.findAll();
        for(ProgrammingLanguage programmingLanguage : programmingLanguages){
            if(programmingLanguage.getName().equals(name)){
                return new ErrorResult(Messages.PROGRAMMING_LANGUAGE_NAME_EXIST);
            }
        }
        return new SuccessResult();
    }

    public Result checkIfProgrammingLanguageNameIsEmpty(String name){
        if(name.isEmpty()){
            return new ErrorResult(Messages.PROGRAMMING_LANGUAGE_NAME_CANNOT_BE_EMPTY);
        }

        return new SuccessResult();
    }

    public Result checkIfProgrammingLanguageIdDoesntExist(int id) {
        boolean programmingLanguages = _programmingLanguageRepository.existsById(id);
        if (programmingLanguages == false) {
            return new ErrorResult(Messages.PROGRAMMING_LANGUAGE_DOESNT_EXIST);
        }
        return new SuccessResult();
    }
}
