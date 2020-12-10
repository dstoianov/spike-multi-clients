package se.techinsight.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import se.techinsight.view.Views;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDto {

    @JsonView({Views.Internal.class})
    private Long id;

    @JsonView({Views.Public.class})
    private String firstName;

    @JsonView({Views.Public.class})
    private String lastName;
}
