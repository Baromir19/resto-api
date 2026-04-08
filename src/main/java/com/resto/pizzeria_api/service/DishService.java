package com.resto.pizzeria_api.service;

import com.resto.pizzeria_api.exception.ApiNotFoundException;
import com.resto.pizzeria_api.model.Dish;
import com.resto.pizzeria_api.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish getDishById(final Integer id) throws ApiNotFoundException {
        return dishRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException(
                        "Plat n'a pas été trouvé"));
    }

    public Dish saveDish(final Dish dish) {
        return dishRepository.save(dish);
    }

    public void deleteDish(final Integer id) throws ApiNotFoundException {
        if (!dishRepository.existsById(id)) {
            throw new ApiNotFoundException("Plat n'a pas été trouvé");
        }

        dishRepository.deleteById(id);
    }
}