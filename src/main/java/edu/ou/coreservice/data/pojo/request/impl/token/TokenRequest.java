package edu.ou.coreservice.data.pojo.request.impl.token;

import edu.ou.coreservice.data.pojo.request.base.IBaseRequest;
import lombok.Data;

@Data
public class TokenRequest implements IBaseRequest {
    private String token;
    private String refreshToken;
}
