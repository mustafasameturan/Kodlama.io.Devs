package mustafasamet.devs.webApi.controllers;

import mustafasamet.devs.business.abstracts.ProgrammingTechnologyService;
import mustafasamet.devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import mustafasamet.devs.business.requests.programmingTechnology.CreateProgrammingTechnologyRequest;
import mustafasamet.devs.business.requests.programmingTechnology.DeleteProgrammingTechnologyRequest;
import mustafasamet.devs.business.requests.programmingTechnology.UpdateProgrammingTechnologyRequest;
import mustafasamet.devs.business.responses.programmingTechnology.GetAllProgrammingTechnologiesResponse;
import mustafasamet.devs.business.responses.programmingTechnology.GetByIdProgrammingTechnologyResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class ProgrammingTechnologiesController {

    private ProgrammingTechnologyService _programmingTechnologyService;

    public ProgrammingTechnologiesController(ProgrammingTechnologyService progProgrammingTechnologyService) {
        _programmingTechnologyService = progProgrammingTechnologyService;
    }

    @GetMapping("/getall")
    public List<GetAllProgrammingTechnologiesResponse> getAll(){
        return _programmingTechnologyService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdProgrammingTechnologyResponse getById(int id){
        return _programmingTechnologyService.getById(id);
    }

    @PostMapping("/add")
    public void add(CreateProgrammingTechnologyRequest createProgrammingTechnologyRequest){
        _programmingTechnologyService.add(createProgrammingTechnologyRequest);
    }

    @PutMapping("/update")
    public void update(UpdateProgrammingTechnologyRequest updateProgrammingTechnologyRequest){
        _programmingTechnologyService.update(updateProgrammingTechnologyRequest);
    }

    @DeleteMapping("/delete")
    public void delete(DeleteProgrammingTechnologyRequest deleteProgrammingTechnologyRequest){
        _programmingTechnologyService.delete(deleteProgrammingTechnologyRequest);
    }
}
