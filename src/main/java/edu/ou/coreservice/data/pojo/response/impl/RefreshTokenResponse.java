package edu.ou.coreservice.data.pojo.response.impl;

import edu.ou.coreservice.data.pojo.response.base.IBaseResponse;
import lombok.Data;

@Data
public class RefreshTokenResponse implements IBaseResponse {
    private String newToken;
    private String newRefreshToken;
}
