package com.resto.pizzeria_api.controller;

import com.resto.pizzeria_api.exception.ApiNotFoundException;
import com.resto.pizzeria_api.model.Dish;
import com.resto.pizzeria_api.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dish getDishById(
            @PathVariable final Integer id
    ) throws ApiNotFoundException {
        return dishService.getDishById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dish createDish(@RequestBody final Dish dish) {
        return dishService.saveDish(dish);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dish updateDish(
            @PathVariable final Integer id,
            @RequestBody final Dish updated
    ) throws ApiNotFoundException {
        final Dish existing = dishService.getDishById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setDescription(updated.getDescription());
        return dishService.saveDish(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(
            @PathVariable final Integer id
    ) throws ApiNotFoundException {
        dishService.deleteDish(id);
    }
}
