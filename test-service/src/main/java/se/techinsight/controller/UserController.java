package se.techinsight.controller;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.RestController;
import se.techinsight.client.UserApi;
import se.techinsight.dto.UserDto;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
public class UserController implements UserApi {

    private final Faker faker = new Faker();
    private final AtomicLong atomicLong = new AtomicLong(1);

    private List<UserDto> userRepository;

    @PostConstruct
    private void init() {
        userRepository = generateUsers();
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository;
    }

    @Override
    public UserDto getById(Long id) {
        return userRepository.stream()
            .filter(userDto -> userDto.getId().equals(id))
            .findFirst()
            .orElseThrow(RuntimeException::new);
    }

    @Override
    public void delete(Long id) {
        userRepository.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public UserDto create(UserDto payload) {
        payload.setId(atomicLong.getAndIncrement());
        userRepository.add(payload);
        return payload;
    }

    private List<UserDto> generateUsers() {
        return LongStream.rangeClosed(1L, 10L)
            .mapToObj(i -> generateUser())
            .collect(Collectors.toList());
    }

    private UserDto generateUser() {
        UserDto user = new UserDto();
        user.setId(atomicLong.getAndIncrement());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        return user;
    }
}
