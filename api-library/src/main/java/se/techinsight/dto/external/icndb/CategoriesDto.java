package se.techinsight.dto.external.icndb;

import lombok.Data;

import java.util.List;

@Data
public class CategoriesDto {

    private String type;
    private List<String> value;
}
