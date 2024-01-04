package nelsonssoares.ecomuserapi.constants;

public class ControllersConstants {
    // API INFO
    public static final String API_TAG = "Ecommerce - User API";
    public static final String API_PRODUCES = "application/json";
    public static final String API_SECURITY_REQUIREMENT = "bearer-key";
    public static final String API_BASE_URL = "ecommerce/usuarios";

    // USER ENDPOINTS

    public static final String EMAIL = "/email/{email}";
    public static final String ID = "/{id}";
    public static final String CPF = "/cpf/{cpf}";

    public static final String NAME = "/nome/{nome}";
    public static final String ACTIVE = "/active/{id}";

    // ADDRESS ENDPOINTS
    
    public static final String ADDRESS = "/endereco";

}
