package ru.camera_store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.camera_store.models.Category;
import ru.camera_store.services.CategoryService;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/categories")
    public void setCategory(@RequestBody Category category){
        service.addCategory(category);
    }

    @GetMapping("/categories")
    public @ResponseBody List<Category> getAll(){
        return service.getCategories();
    }

    @GetMapping("/categories/{id}")
    public @ResponseBody List<Category> get(@PathVariable long id){
        return service.getCategory(id);
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable long id){
        service.deleteCategory(id);
    }
}
