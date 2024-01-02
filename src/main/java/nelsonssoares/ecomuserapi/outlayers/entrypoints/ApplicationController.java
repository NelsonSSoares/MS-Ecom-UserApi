package nelsonssoares.ecomuserapi.outlayers.entrypoints;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static nelsonssoares.ecomuserapi.constants.ControllerConstants.API_BASE_URL;
import static nelsonssoares.ecomuserapi.constants.ControllerConstants.API_PRODUCES;

@Tag(name = "Ecommerce - User API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = API_BASE_URL, produces = API_PRODUCES)
//@SecurityRequirement(name = "bearer-key")
public class ApplicationController {



}
