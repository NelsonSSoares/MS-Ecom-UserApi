package nelsonssoares.ecomuserapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FieldError {

    private String field;
    private String message;
}
