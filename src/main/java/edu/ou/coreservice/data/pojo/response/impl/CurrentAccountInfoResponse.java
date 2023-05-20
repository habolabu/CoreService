package edu.ou.coreservice.data.pojo.response.impl;

import lombok.Data;

import java.util.List;

@Data
public class CurrentAccountInfoResponse {
    private String username;
    private String password;
    private List<String> permissions;
    private int userId;
    private int accountId;
}
