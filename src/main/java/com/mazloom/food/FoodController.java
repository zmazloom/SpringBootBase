package com.mazloom.food;

import com.mazloom.domain.request.FoodRequest;
import com.mazloom.domain.srv.ApiErrorSrv;
import com.mazloom.domain.srv.FoodSrv;
import com.mazloom.domain.srv.response.ResFact;
import com.mazloom.domain.srv.response.Result;
import com.mazloom.domain.vo.Pagination;
import com.mazloom.message.CommonMessage;
import com.mazloom.message.FoodMessage;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Log4j2
@Validated
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/food")
@Api(tags = {"Food"})
public class FoodController {

    private final FoodService foodService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "Create food.", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 400, message = "bad request!", response = ApiErrorSrv.class),
            @ApiResponse(code = 401, message = "not authorized!", response = ApiErrorSrv.class),
            @ApiResponse(code = 403, message = "access denied!", response = ApiErrorSrv.class),
            @ApiResponse(code = 405, message = "method not allowed!", response = ApiErrorSrv.class),
            @ApiResponse(code = 500, message = "internal server error!", response = ApiErrorSrv.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "food", value = "Food information", required = true, dataType = "FoodCreateRequest", dataTypeClass = FoodRequest.Create.class, paramType = "body")
    })
    public ResponseEntity<Result<FoodSrv>> createFood(
            @RequestBody @NotNull @Validated FoodRequest.Create foodRequest
    ) {

        return ResponseEntity.ok(ResFact.<FoodSrv>build()
                .setMessage(FoodMessage.foodCreated())
                .setResult(foodService.createFood(foodRequest))
                .setTotal(1)
                .get());
    }

    @PutMapping(value = "/{foodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "Update food.", httpMethod = "PUT")
    @ApiResponses({
            @ApiResponse(code = 400, message = "bad request!", response = ApiErrorSrv.class),
            @ApiResponse(code = 401, message = "not authorized!", response = ApiErrorSrv.class),
            @ApiResponse(code = 403, message = "access denied!", response = ApiErrorSrv.class),
            @ApiResponse(code = 404, message = "food not found!", response = ApiErrorSrv.class),
            @ApiResponse(code = 405, message = "method not allowed!", response = ApiErrorSrv.class),
            @ApiResponse(code = 500, message = "internal server error!", response = ApiErrorSrv.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "foodId", value = "Food identifier", required = true, dataType = "Long", dataTypeClass = Long.class, paramType = "path", example = "1"),
            @ApiImplicitParam(name = "food", value = "Food information", required = true, dataType = "FoodUpdateRequest", dataTypeClass = FoodRequest.Update.class, paramType = "body")
    })
    public ResponseEntity<Result<FoodSrv>> updateFood(
            @PathVariable(name = "foodId") @NotNull Long foodId,
            @RequestBody @NotNull @Validated FoodRequest.Update foodRequest
    ) {

        return ResponseEntity.ok(ResFact.<FoodSrv>build()
                .setMessage(FoodMessage.foodUpdated())
                .setResult(foodService.updateFood(foodId, foodRequest))
                .setTotal(1)
                .get());
    }

    @DeleteMapping(value = "/{foodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "Delete food.", httpMethod = "DELETE")
    @ApiResponses({
            @ApiResponse(code = 400, message = "bad request!", response = ApiErrorSrv.class),
            @ApiResponse(code = 401, message = "not authorized!", response = ApiErrorSrv.class),
            @ApiResponse(code = 403, message = "access denied!", response = ApiErrorSrv.class),
            @ApiResponse(code = 404, message = "food not found!", response = ApiErrorSrv.class),
            @ApiResponse(code = 405, message = "method not allowed!", response = ApiErrorSrv.class),
            @ApiResponse(code = 500, message = "internal server error!", response = ApiErrorSrv.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "foodId", value = "Food identifier", required = true, dataType = "Long", dataTypeClass = Long.class, paramType = "path", example = "1")
    })
    public ResponseEntity<Result<Boolean>> deleteFood(
            @PathVariable(name = "foodId") @NotNull Long foodId
    ) {

        foodService.deleteFood(foodId);

        return ResponseEntity.ok(ResFact.<Boolean>build()
                .setMessage(FoodMessage.foodRemoved())
                .setResult(true)
                .setTotal(1)
                .get());
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "Get foods.", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 400, message = "bad request!", response = ApiErrorSrv.class),
            @ApiResponse(code = 401, message = "not authorized!", response = ApiErrorSrv.class),
            @ApiResponse(code = 403, message = "access denied!", response = ApiErrorSrv.class),
            @ApiResponse(code = 405, message = "method not allowed!", response = ApiErrorSrv.class),
            @ApiResponse(code = 500, message = "internal server error!", response = ApiErrorSrv.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "Page (start from 1)", dataType = "Integer", dataTypeClass = Integer.class, paramType = "query", defaultValue = "1", example = "1"),
            @ApiImplicitParam(name = "size", value = "Size of page (1 < size < 100)", dataType = "Integer", dataTypeClass = Integer.class, paramType = "query", defaultValue = "10", example = "10")
    })
    public ResponseEntity<Result<List<FoodSrv>>> getFoods(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {

        return ResponseEntity.ok(ResFact.<List<FoodSrv>>build()
                .setMessage(CommonMessage.ok())
                .setItemsWithTotal(foodService.getFoods(Pagination.of(page, size)))
                .get());
    }

    @GetMapping(value = "/{foodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "Get food by id.", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 400, message = "bad request!", response = ApiErrorSrv.class),
            @ApiResponse(code = 401, message = "not authorized!", response = ApiErrorSrv.class),
            @ApiResponse(code = 403, message = "access denied!", response = ApiErrorSrv.class),
            @ApiResponse(code = 404, message = "food not found!", response = ApiErrorSrv.class),
            @ApiResponse(code = 405, message = "method not allowed!", response = ApiErrorSrv.class),
            @ApiResponse(code = 500, message = "internal server error!", response = ApiErrorSrv.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "foodId", value = "Food identifier", required = true, dataType = "Long", dataTypeClass = Long.class, paramType = "path", example = "1")
    })
    public ResponseEntity<Result<FoodSrv>> getFood(
            @PathVariable(name = "foodId") @NotNull Long foodId
    ) {

        return ResponseEntity.ok(ResFact.<FoodSrv>build()
                .setMessage(CommonMessage.ok())
                .setResult(foodService.getFood(foodId))
                .setTotal(1)
                .get());
    }

}
