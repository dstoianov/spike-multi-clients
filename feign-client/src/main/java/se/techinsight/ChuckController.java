package se.techinsight;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.techinsight.client.external.icndb.ChuckNorrisApi;
import se.techinsight.dto.external.icndb.OneJokeDto;
import se.techinsight.dto.external.icndb.RandomAllDto;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chuck")
public class ChuckController {

    private final ChuckNorrisApi chuckClient;

    @GetMapping(value = "/joke/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public OneJokeDto getOne(@RequestParam(value = "categorie", required = false) Optional<String> categorie) {
        if (categorie.isEmpty()) {
            return chuckClient.randomOne();
        }
        return chuckClient.randomFromCategorie(categorie.get());
    }

    @GetMapping(value = "/joke/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OneJokeDto randomById(@PathVariable("id") Integer id) {
        return chuckClient.oneById(id);
    }

    @GetMapping(value = "/joke/random/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RandomAllDto randomFew(@PathVariable("count") Integer count) {
        return chuckClient.randomFew(count);
    }

    @GetMapping(value = "/joke/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public RandomAllDto all() {
        return chuckClient.all();
    }

    @GetMapping(value = "/joke/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> count() {
        return chuckClient.countOfJokes();
    }

}
