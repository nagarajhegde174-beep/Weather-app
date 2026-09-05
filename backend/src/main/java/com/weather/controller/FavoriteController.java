package com.weather.controller;

import com.weather.dto.FavoriteRequest;
import com.weather.dto.FavoriteResponse;
import com.weather.service.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
@Slf4j
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<FavoriteResponse> getFavorites() {
        log.info("GET /api/favorites");
        return ResponseEntity.ok(favoriteService.getFavorites());
    }

    @PostMapping
    public ResponseEntity<FavoriteResponse> addFavorite(@Valid @RequestBody FavoriteRequest request) {
        log.info("POST /api/favorites city={}", request.getCity());
        return ResponseEntity.ok(favoriteService.addFavorite(request.getCity()));
    }

    @PostMapping("/add")
    public ResponseEntity<FavoriteResponse> addFavoriteParam(@RequestParam String city) {
        log.info("POST /api/favorites/add city={}", city);
        return ResponseEntity.ok(favoriteService.addFavorite(city));
    }

    @DeleteMapping("/{city}")
    public ResponseEntity<FavoriteResponse> removeFavorite(@PathVariable String city) {
        log.info("DELETE /api/favorites/{}", city);
        return ResponseEntity.ok(favoriteService.removeFavorite(city));
    }
}
