package ee.vladislav.backend.controller;

import ee.vladislav.backend.service.SelectorsService;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/selectors/")
public class SelectorsController {

    @Autowired
    SelectorsService selectorsService;

    @GetMapping("/")
    public List<Any> getAll() throws ExecutionException {
        return selectorsService.getAllSelectorData();
    }
}
