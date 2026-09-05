package com.weather.service;

import com.weather.dto.FavoriteResponse;
import com.weather.exception.InvalidCityException;
import com.weather.model.FavoriteCity;
import com.weather.util.CityValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteService {

    private static final int MAX_FAVORITES = 10;
    private final CityValidator cityValidator;
    private final List<FavoriteCity> favorites = Collections.synchronizedList(new ArrayList<>());

    public FavoriteResponse addFavorite(String city) {
        String validCity = cityValidator.validateAndNormalize(city);

        synchronized (favorites) {
            boolean exists = favorites.stream()
                    .anyMatch(f -> f.getCity().equalsIgnoreCase(validCity));
            if (exists) {
                return getFavoriteResponse("City '" + validCity + "' is already in favorites.");
            }

            if (favorites.size() >= MAX_FAVORITES) {
                throw new InvalidCityException("Maximum favorites limit (" + MAX_FAVORITES + ") reached.");
            }

            FavoriteCity favoriteCity = FavoriteCity.builder()
                    .city(validCity)
                    .addedAt(LocalDateTime.now())
                    .build();

            favorites.add(favoriteCity);
        }

        log.info("Added favorite city: {}", validCity);
        return getFavoriteResponse("Successfully added '" + validCity + "' to favorites.");
    }

    public FavoriteResponse removeFavorite(String city) {
        if (city == null || city.isBlank()) {
            throw new InvalidCityException("City name cannot be empty.");
        }
        String normalized = city.trim();

        synchronized (favorites) {
            boolean removed = favorites.removeIf(f -> f.getCity().equalsIgnoreCase(normalized));
            if (!removed) {
                return getFavoriteResponse("City '" + normalized + "' was not found in favorites.");
            }
        }

        log.info("Removed favorite city: {}", normalized);
        return getFavoriteResponse("Successfully removed '" + normalized + "' from favorites.");
    }

    public FavoriteResponse getFavorites() {
        return getFavoriteResponse("Favorites retrieved successfully.");
    }

    public int getFavoritesCount() {
        return favorites.size();
    }

    public boolean isFavorite(String city) {
        if (city == null || city.isBlank()) return false;
        String normalized = city.trim();
        synchronized (favorites) {
            return favorites.stream().anyMatch(f -> f.getCity().equalsIgnoreCase(normalized));
        }
    }

    private FavoriteResponse getFavoriteResponse(String message) {
        synchronized (favorites) {
            List<FavoriteCity> copy = new ArrayList<>(favorites);
            return FavoriteResponse.builder()
                    .favorites(copy)
                    .totalFavorites(copy.size())
                    .message(message)
                    .build();
        }
    }
}
