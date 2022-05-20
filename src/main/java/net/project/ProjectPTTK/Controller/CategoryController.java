package net.project.ProjectPTTK.Controller;

import net.project.ProjectPTTK.Entities.Category;
import net.project.ProjectPTTK.Entities.ResponseObject;
import net.project.ProjectPTTK.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/category")
    public List<Category> list() {
        return service.listAll();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ResponseObject> get(@PathVariable Integer id) {
        Category category = null;
        try {
            category = service.get(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query product successfully", category)
            );
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cannot find product with id = " + id, category)
            );
        }
    }

    @PostMapping("/category/insert")
    public void add(@RequestBody Category category) {

        service.save((category));
    }

    @PutMapping("/category/update/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Integer id) {
        try {
            Category existCategory = service.get(id);
            existCategory.setName(category.getName());
            existCategory.setH4_content(category.getH4_content());
            existCategory.setH5_content(category.getH5_content());
            service.save(existCategory);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable Integer id) {
        Category category = null;
        try {
            category = service.get(id);
            if(category.getId() == null) {
                service.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Delete category successfully", "")
                );
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cannot find category with id = " + id, "")
            );
        }

        return null;
    }
}
