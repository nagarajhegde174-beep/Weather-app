package com.weather.service;

import com.weather.dto.FavoriteResponse;
import com.weather.exception.InvalidCityException;
import com.weather.util.CityValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteServiceTest {

    private FavoriteService favoriteService;

    @BeforeEach
    void setUp() {
        CityValidator validator = new CityValidator();
        favoriteService = new FavoriteService(validator);
    }

    @Test
    void addFavorite_Success() {
        FavoriteResponse response = favoriteService.addFavorite("Tokyo");
        assertEquals(1, response.getTotalFavorites());
        assertTrue(favoriteService.isFavorite("Tokyo"));
    }

    @Test
    void addFavorite_PreventDuplicates() {
        favoriteService.addFavorite("Paris");
        FavoriteResponse response = favoriteService.addFavorite("Paris");
        assertEquals(1, response.getTotalFavorites());
        assertTrue(response.getMessage().contains("already in favorites"));
    }

    @Test
    void removeFavorite_Success() {
        favoriteService.addFavorite("London");
        FavoriteResponse response = favoriteService.removeFavorite("London");
        assertEquals(0, response.getTotalFavorites());
        assertFalse(favoriteService.isFavorite("London"));
    }

    @Test
    void addFavorite_ExceedLimit_ThrowsException() {
        String[] cities = {"Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta", "Iota", "Kappa", "Lambda"};
        for (int i = 0; i < 10; i++) {
            favoriteService.addFavorite(cities[i]);
        }
        assertThrows(InvalidCityException.class, () -> favoriteService.addFavorite(cities[10]));
    }
}
