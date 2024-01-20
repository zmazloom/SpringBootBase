package com.mazloom.domain.srv;

import com.mazloom.domain.model.Food;
import com.mazloom.utils.ModelUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FoodSrv {

    @Schema(description = "Food identifier")
    private Long id;
    @Schema(description = "Food create date")
    private Date created;
    @Schema(description = "Food name.")
    private String name;

    public static List<FoodSrv> from(@Nullable List<Food> foods) {
        if (foods == null)
            return new ArrayList<>();

        return foods.stream()
                .filter(Objects::nonNull)
                .map(FoodSrv::from)
                .collect(Collectors.toList());
    }

    @Nullable
    public static FoodSrv from(@Nullable Food food) {
        if (food == null)
            return null;

        return ModelUtils.getModelMapper().map(food, FoodSrv.class);
    }

}
