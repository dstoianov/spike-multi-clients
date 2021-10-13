package se.techinsight.client;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.techinsight.dto.UserDto;
import se.techinsight.view.Views;

import java.util.List;

@Tag(name = "Users Controller", description = "I am an awesome description for controller")
@FeignClient(name = "test-service")
@RequestMapping(value = "/users")
public interface UserApi {

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> getAll();

    @Operation(summary = "Create URL object")
    @JsonView(Views.Internal.class)
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto getById(@PathVariable("id") Long id);

    @DeleteMapping(value = "/id/{id}")
    void delete(@PathVariable("id") Long id);

    @JsonView(Views.Public.class)
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto create(@RequestBody UserDto payload);
}
