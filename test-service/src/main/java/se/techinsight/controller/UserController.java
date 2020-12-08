package se.techinsight.controller;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.RestController;
import se.techinsight.client.UserApi;
import se.techinsight.dto.UserDto;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
public class UserController implements UserApi {

    private final Faker faker = new Faker();
    private List<UserDto> usersRepo;

    @PostConstruct
    private void init() {
        usersRepo = generateUsers();
    }

    @Override
    public List<UserDto> getAll() {
        return usersRepo;
    }

    @Override
    public UserDto getById(Long id) {
        return usersRepo.stream()
            .filter(userDto -> userDto.getId().equals(id))
            .findFirst()
            .orElseThrow(RuntimeException::new);
    }

    @Override
    public void delete(Long id) {
        usersRepo.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public UserDto create(UserDto payload) {
        payload.setId(usersRepo.size() + 10L);
        usersRepo.add(payload);
        return payload;
    }

    private List<UserDto> generateUsers() {
        return LongStream.rangeClosed(1L, 10L)
            .mapToObj(this::generateUser)
            .collect(Collectors.toList());
    }

    private UserDto generateUser(Long id) {
        UserDto user = new UserDto();
        user.setId(id);
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        return user;
    }
}
