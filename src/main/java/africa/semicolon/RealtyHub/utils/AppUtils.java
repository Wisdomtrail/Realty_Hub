package africa.semicolon.RealtyHub.utils;

import africa.semicolon.RealtyHub.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static java.time.Instant.now;

public class AppUtils {
    public static final String EMAIL_URL="https://api.brevo.com/v3/smtp/email";

    public static final String APP_EMAIL="no-reply@realtyhub.africa";

    public static final String APP_NAME="realtyhub inc.";
    public static final String EMPTY_SPACE_VALUE = " ";
    public static final String ACCESS_TOKEN_VALUE = "access_token";

    public static final String MAIL_API_KEY="${sendinblue.api.key}";
    public static final String API_KEY_VALUE = "api-key";

    public static final String FIELD_MUST_NOT_BE_EMPTY ="field must not be empty";
    public static final String SENDER = "sender";
    public static final String TO = "to";
    public static final String HTML_CONTENT_VALUE = "htmlContent";
    public static final String SUBJECT = "subject";
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String CLAIM_VALUE = "claim";
    public static final String MAIL_TEMPLATE_LOCATION = "C:\\Users\\ADMIN\\Downloads\\RealtyHub\\src\\main\\resources\\static\\activation.txt";
    public static final String ACTIVATION_LINK_VALUE = "Activation Link";
    public static final String FRONTEND_BASE_URL = "https://www.realtyhub.com";
    public static final String ID = "id";
    public static final String USER_API_VALUE = "/api/v1/user";
    public static final String CLAIMS_VALUE = "ROLES";
    public static final String LOGIN_ENDPOINT = "/api/v1/login";
    public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
    public static final int ONE = 1;

    public static final String JWT_SIGNING_SECRET = "${jwt.signing.secret}";
    public static final String USER_WITH_ID_NOT_FOUND = "User with id %s not found";

    public static final String ACTIVATE_ACCOUNT_URL = "localhost:8080/api/v1/customer/verify";
    private static final int DEFAULT_PAGE_NUMBER = 1 ;
    private static final int DEFAULT_PAGE_LIMIT = 10;
    public static final int ZERO = 0;

    public static final String NOT_NULL = "Cannot be null";
    public static final String NOT_BLANK = "Cannot be blank";
    private static final String GET_ALL_USERS = "/api/v1/user/all";

    public static Pageable buildPageRequest(int page, int items){
        if (page<=ZERO) page=DEFAULT_PAGE_NUMBER;
        if (items<=ZERO) items = DEFAULT_PAGE_LIMIT;
        page-=ONE;
        return PageRequest.of(page, items);
    }
    public static String generateToken(User user, String secret){
        return JWT.create()
                .withIssuedAt(now())
                .withExpiresAt(now().plusSeconds(200L))
                .withClaim(ID, user.getId())
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }
    public static List<String> getAuthWhiteList() {
        return List.of(
                USER_API_VALUE, LOGIN_ENDPOINT, GET_ALL_USERS
        );
    }
}

