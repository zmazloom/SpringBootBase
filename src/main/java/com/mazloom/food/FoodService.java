package com.mazloom.food;

import com.mazloom.data.Food;
import com.mazloom.domain.request.FoodRequest;
import com.mazloom.domain.srv.FoodSrv;
import com.mazloom.domain.vo.ItemsWithTotal;
import com.mazloom.domain.vo.Pagination;
import com.mazloom.exception.ResourceNotFoundException;

import javax.validation.constraints.NotNull;


public interface FoodService {

    /**
     * Create new food.
     *
     * @param foodRequest new food information.
     */
    FoodSrv createFood(@NotNull FoodRequest.Create foodRequest);

    /**
     * Update food information.
     *
     * @param foodRequest new food information.
     */
    FoodSrv updateFood(@NotNull Long foodId, @NotNull FoodRequest.Update foodRequest);

    /**
     * Delete a food by id.
     *
     * @param foodId food id that must remove.
     */
    void deleteFood(@NotNull Long foodId);

    /**
     * Get list of all foods model vo.
     *
     * @param pagination page and size of output.
     */
    ItemsWithTotal<FoodSrv> getFoods(@NotNull Pagination pagination);

    /**
     * Get food model by id.
     *
     * @param foodId food id.
     */
    FoodSrv getFood(@NotNull Long foodId);

    /**
     * Get food by id.
     *
     * @param foodId food id.
     * @throws ResourceNotFoundException if food not found.
     */
    Food getAndCheckFood(long foodId);

}
