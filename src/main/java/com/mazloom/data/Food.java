package com.mazloom.data;

import com.mazloom.domain.request.FoodRequest;
import com.mazloom.utils.ModelUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dining_food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created")
    @CreationTimestamp
    private Date created;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "score")
    private Integer score;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "removed", columnDefinition = "boolean default false")
    private boolean removed;

    @Nullable
    public static Food from(@Nullable FoodRequest.Create foodRequest) {
        if (foodRequest == null)
            return null;

        return ModelUtils.getModelMapper().map(foodRequest, Food.class);
    }

}
