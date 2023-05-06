package ee.vladislav.backend.controller;

import ee.vladislav.backend.service.SelectorsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/selectors/")
public class SelectorsController {

    private final SelectorsService selectorsService;

    public SelectorsController(SelectorsService selectorsService) {
        this.selectorsService = selectorsService;
    }

    @GetMapping("/")
    public Map<String, List<String>> getAll() throws ExecutionException {
        return selectorsService.getAllSelectorData();
    }

}
