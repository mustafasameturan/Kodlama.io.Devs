package mustafasamet.devs.webApi.controllers;

import mustafasamet.devs.business.abstracts.ProgrammingLanguageService;
import mustafasamet.devs.business.requests.programmingLanguage.CreateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.DeleteProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.responses.programmingLanguage.GetAllProgrammingLanguagesResponse;
import mustafasamet.devs.business.responses.programmingLanguage.GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse;
import mustafasamet.devs.business.responses.programmingLanguage.GetByIdProgrammingLanguageResponse;
import mustafasamet.devs.core.utilities.results.DataResult;
import mustafasamet.devs.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class ProgrammingLanguagesController {

    private ProgrammingLanguageService _programmingLanguageService;

    @Autowired
    public ProgrammingLanguagesController(ProgrammingLanguageService programmingLanguageService) {
        _programmingLanguageService = programmingLanguageService;
    }

    @GetMapping("/getall")
    public DataResult<List<GetAllProgrammingLanguagesResponse>> getAll(){
        return _programmingLanguageService.getAll();
    }

    @GetMapping("/getallwithprogrammingtechnologies")
    public DataResult<List<GetAllProgrammingLanguagesWithProgrammingTechnologiesResponse>> getAllWithProgrammingTechnologies(){
        return _programmingLanguageService.getAllWithProgrammingTechnologies();
    }

    @GetMapping("/getbyid/{id}")
    public DataResult<GetByIdProgrammingLanguageResponse> getById(@RequestParam int id){
        return _programmingLanguageService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateProgrammingLanguageRequest createProgrammingLanguageRequest){
        return _programmingLanguageService.add(createProgrammingLanguageRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest){
        return _programmingLanguageService.update(updateProgrammingLanguageRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest){
        return _programmingLanguageService.delete(deleteProgrammingLanguageRequest);
    }
}
