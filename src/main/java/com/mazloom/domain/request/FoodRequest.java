package com.mazloom.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class FoodRequest {

    @Schema(description = "Food picture url.")
    private String pictureUrl;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(ref = "FoodCreateRequest")
    @EqualsAndHashCode(callSuper = true)
    public static class Create extends FoodRequest {
        @NotBlank(message = "{food.name.is.required}")
        @Schema(description = "Food name.", required = true)
        private String name;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(ref = "FoodUpdateRequest")
    @EqualsAndHashCode(callSuper = true)
    public static class Update extends FoodRequest {
        @Schema(description = "Food name.")
        private String name;
    }

}