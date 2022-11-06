package mustafasamet.devs.webApi.controllers;

import mustafasamet.devs.business.abstracts.ProgrammingLanguageService;
import mustafasamet.devs.business.requests.programmingLanguage.CreateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.DeleteProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.responses.programmingLanguage.GetAllProgrammingLanguagesResponse;
import mustafasamet.devs.business.responses.programmingLanguage.GetByIdProgrammingLanguageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<GetAllProgrammingLanguagesResponse> getAll(){
        return _programmingLanguageService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdProgrammingLanguageResponse getById(@RequestParam int id){
        return _programmingLanguageService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateProgrammingLanguageRequest createProgrammingLanguageRequest){
        _programmingLanguageService.add(createProgrammingLanguageRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest){
        _programmingLanguageService.update(updateProgrammingLanguageRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest){
        _programmingLanguageService.delete(deleteProgrammingLanguageRequest);
    }
}
