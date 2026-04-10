package com.resto.pizzeria_api.service;

import com.resto.pizzeria_api.exception.ApiNotFoundException;
import com.resto.pizzeria_api.model.Dish;
import com.resto.pizzeria_api.repository.DishRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishServiceTest {

    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishService dishService;

    @Test
    @DisplayName("getAllDishes() doit retourner la liste complète des plats")
    void getAllDishes_ShouldReturnListOfDishes() {
        // --- ARRANGE ---
        Dish dish1 = new Dish();
        dish1.setName("Margherita");

        Dish dish2 = new Dish();
        dish2.setName("Pepperoni");

        List<Dish> expectedDishes = Arrays.asList(dish1, dish2);

        when(dishRepository.findAll()).thenReturn(expectedDishes);

        // --- ACT ---
        List<Dish> actualDishes = dishService.getAllDishes();

        // --- ASSERT ---
        assertEquals(2, actualDishes.size(), "La liste doit contenir 2 plats");
        assertEquals("Margherita", actualDishes.getFirst().getName());
        verify(dishRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("getDishById() doit retourner le plat si l'ID existe")
    void getDishById_WhenIdExists_ShouldReturnDish() throws ApiNotFoundException {
        // --- ARRANGE ---
        Integer dishId = 1;
        Dish expectedDish = new Dish();
        expectedDish.setId(dishId);
        expectedDish.setName("Calzone");

        when(dishRepository.findById(dishId)).thenReturn(Optional.of(expectedDish));

        // --- ACT ---
        Dish actualDish = dishService.getDishById(dishId);

        // --- ASSERT ---
        assertNotNull(actualDish);
        assertEquals(dishId, actualDish.getId());
        assertEquals("Calzone", actualDish.getName());
        verify(dishRepository, times(1)).findById(dishId);
    }

    @Test
    @DisplayName("getDishById() doit lever une exception si l'ID n'existe pas")
    void getDishById_WhenIdDoesNotExist_ShouldThrowException() {
        // --- ARRANGE ---
        Integer dishId = 999;
        when(dishRepository.findById(dishId)).thenReturn(Optional.empty());

        // --- ACT & ASSERT ---
        ApiNotFoundException exception = assertThrows(
                ApiNotFoundException.class,
                () -> dishService.getDishById(dishId)
        );

        assertTrue(exception.getMessage().contains("Le plat avec l'ID 999 n'a pas été trouvé."));
        verify(dishRepository, times(1)).findById(dishId);
    }

    @Test
    @DisplayName("saveDish() doit retourner le plat sauvegardé")
    void saveDish_ShouldReturnSavedDish() {
        // --- ARRANGE ---
        Dish newDish = new Dish();
        newDish.setName("Quattro Formaggi");

        when(dishRepository.save(newDish)).thenReturn(newDish);

        // --- ACT ---
        Dish savedDish = dishService.saveDish(newDish);

        // --- ASSERT ---
        assertNotNull(savedDish);
        assertEquals("Quattro Formaggi", savedDish.getName());
        verify(dishRepository, times(1)).save(newDish);
    }

    @Test
    @DisplayName("deleteDish() doit supprimer le plat si l'ID existe")
    void deleteDish_WhenIdExists_ShouldDeleteSuccessfully() throws ApiNotFoundException {
        // --- ARRANGE ---
        Integer dishId = 1;
        when(dishRepository.existsById(dishId)).thenReturn(true);

        // --- ACT ---
        dishService.deleteDish(dishId);

        // --- ASSERT ---
        verify(dishRepository, times(1)).existsById(dishId);
        verify(dishRepository, times(1)).deleteById(dishId);
    }

    @Test
    @DisplayName("deleteDish() doit lever une exception si l'ID n'existe pas")
    void deleteDish_WhenIdDoesNotExist_ShouldThrowException() {
        // --- ARRANGE ---
        Integer dishId = 999;
        when(dishRepository.existsById(dishId)).thenReturn(false);

        // --- ACT & ASSERT ---
        ApiNotFoundException exception = assertThrows(
                ApiNotFoundException.class,
                () -> dishService.deleteDish(dishId)
        );

        assertTrue(exception.getMessage().contains("Impossible de supprimer"));

        verify(dishRepository, times(1)).existsById(dishId);
        verify(dishRepository, never()).deleteById(anyInt());
    }
}