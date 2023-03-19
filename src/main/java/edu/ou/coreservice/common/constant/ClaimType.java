package edu.ou.coreservice.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClaimType {
    public static final String AUTHORIZED_PARTIES = "azp";
    public static final String EXPIRED = "exp";
    public static final String ISSUER = "iss";
    public static final String AUDIENCE = "aud";
}
