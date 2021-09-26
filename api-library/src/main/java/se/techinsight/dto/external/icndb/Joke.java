package se.techinsight.dto.external.icndb;

import lombok.Data;

import java.util.List;

@Data
public class Joke {
    private int id;
    private String joke;
    private List<String> categories;
}
