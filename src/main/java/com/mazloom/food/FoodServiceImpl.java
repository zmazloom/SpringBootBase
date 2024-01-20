package com.mazloom.food;

import com.mazloom.domain.model.Food;
import com.mazloom.domain.request.FoodRequest;
import com.mazloom.domain.srv.FoodSrv;
import com.mazloom.domain.vo.ItemsWithTotal;
import com.mazloom.domain.vo.Pagination;
import com.mazloom.exception.ResourceConflictException;
import com.mazloom.exception.ResourceNotFoundException;
import com.mazloom.message.FoodMessage;
import com.mazloom.utils.ModelUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public FoodSrv createFood(FoodRequest.Create foodRequest) {
        validateFoodCreateVO(foodRequest);

        Food food = Food.from(foodRequest);

        foodRepository.saveAndFlush(food);

        return FoodSrv.from(food);
    }

    @Override
    public FoodSrv updateFood(Long foodId, FoodRequest.Update foodRequest) {
        Food food = getAndCheckFood(foodId);

        validateAndFillFoodUpdateVO(food, foodRequest);

        foodRepository.saveAndFlush(food);

        return FoodSrv.from(food);
    }

    @Override
    public void deleteFood(Long foodId) {
        Food food = getAndCheckFood(foodId);

        food.setRemoved(true);

        foodRepository.saveAndFlush(food);
    }

    @Override
    public ItemsWithTotal<FoodSrv> getFoods(Pagination pagination) {
        List<Food> foods = foodRepository.getAllFoods();
        long totalSize = foods.size();

        int offset = pagination.getOffset();
        foods = foods.subList((Math.min(offset, foods.size())), (Math.min(offset + pagination.size(), foods.size())));

        return ItemsWithTotal.<FoodSrv>builder()
                .items(FoodSrv.from(foods))
                .total(totalSize)
                .build();
    }

    @Override
    public FoodSrv getFood(Long foodId) {
        Food food = getAndCheckFood(foodId);

        return FoodSrv.from(food);
    }

    private void validateFoodCreateVO(FoodRequest.Create foodRequest) {
        if (foodRepository.getFoodByName(foodRequest.getName()) != null)
            throw ResourceConflictException.getInstance(FoodMessage.foodNameIsDuplication());
    }

    private void validateAndFillFoodUpdateVO(Food food, FoodRequest.Update foodRequest) {
        if (ModelUtils.isNotEmpty(foodRequest.getName())) {
            if (foodRepository.getFoodByName(food.getId(), foodRequest.getName()) != null)
                throw ResourceConflictException.getInstance(FoodMessage.foodNameIsDuplication());

            food.setName(foodRequest.getName());
        }
    }

    @Override
    public Food getAndCheckFood(long foodId) {
        Food food = foodRepository.getFoodById(foodId);

        if (food == null)
            throw ResourceNotFoundException.getInstance(FoodMessage.foodNotFound());

        return food;
    }

}
