package net.freetuts.backend.security;

public class SecurityConstant {
	// 1 days expressed in milliseconds
	public static final long   EXPIRATION_TIME          = 3_600_000;
	public static final String TOKEN_PREFIX             = "Bearer ";
	public static final String JWT_TOKEN_HEADER         = "Jwt-Token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
	public static final String ISSUER                   = "Freetuts";
	public static final String AUDIENCE                 = "";
	public static final String AUTHORITIES              = "authorities";
	public static final String Ex_MESSAGE        = "Token is expired at ";

	public static final String FORBIDDEN_MESSAGE        = "You need to log in to access this page";
	public static final String ACCESS_DENIED_MESSAGE    = "You do not have permission to access this page";
    //private static final List<String> EXCLUDE_URL = Arrays.asList("/admin", "/health-check");


}
