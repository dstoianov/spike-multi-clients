package se.techinsight.client.external.icndb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import se.techinsight.dto.external.icndb.CategoriesDto;
import se.techinsight.dto.external.icndb.OneJokeDto;
import se.techinsight.dto.external.icndb.RandomAllDto;

import java.util.Map;

@FeignClient(name = "chuck-norris", url = "http://api.icndb.com")
//@RequestMapping(value = "/jokes")
public interface ChuckNorrisApi {

    @GetMapping(value = "/jokes")
    RandomAllDto all();

    @GetMapping("/jokes/random")
    OneJokeDto randomOne();

    @GetMapping("/jokes/random")
    OneJokeDto randomFromCategorie(@RequestParam(value = "limitTo", required = false) String categorie);

    @GetMapping("/jokes/{id}")
    OneJokeDto oneById(@PathVariable("id") Integer id);

    @GetMapping("/jokes/count")
    Map<String, Object> countOfJokes();

    @GetMapping("/jokes/random/{count}")
    RandomAllDto randomFew(@PathVariable("count") Integer count);

    @GetMapping("/categories")
    CategoriesDto listOfCategories();

}
