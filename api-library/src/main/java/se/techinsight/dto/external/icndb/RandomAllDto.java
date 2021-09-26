package se.techinsight.dto.external.icndb;

import lombok.Data;

import java.util.List;

@Data
public class RandomAllDto {
    private String type;
    private List<Joke> value;
}
