package gafelix.alurabackendcourse.config.exception;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class FormExceptionDto {

    private String field;
    private String message;

}
