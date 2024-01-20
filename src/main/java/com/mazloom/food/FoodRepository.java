package com.mazloom.food;

import com.mazloom.domain.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("from Food f where f.name = :name and f.removed = false")
    Food getFoodByName(String name);

    @Query("from Food f where f.name = :name and f.id <> :foodId and f.removed = false")
    Food getFoodByName(Long foodId, String name);

    @Query("from Food f where f.id = :foodId and f.removed = false")
    Food getFoodById(long foodId);

    @Query("from Food f where f.removed = false")
    List<Food> getAllFoods();

}
