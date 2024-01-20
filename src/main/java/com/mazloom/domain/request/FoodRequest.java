package com.mazloom.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequest {

    @Schema(description = "Food picture url.")
    private String pictureUrl;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("FoodCreateRequest")
    @EqualsAndHashCode(callSuper = true)
    public static class Create extends FoodRequest {
        @NotNull(message = "{food.name.is.required}")
        @NotEmpty(message = "{food.name.is.required}")
        @Schema(description = "Food name.", required = true)
        private String name;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("FoodUpdateRequest")
    @EqualsAndHashCode(callSuper = true)
    public static class Update extends FoodRequest {
        @Schema(description = "Food name.")
        private String name;
    }

}