package gafelix.mvcbackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class TokenDto {

    private String token;
    private String type;

}
