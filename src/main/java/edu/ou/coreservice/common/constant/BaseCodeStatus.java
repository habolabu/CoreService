package edu.ou.coreservice.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BaseCodeStatus {
    public static  final String SUCCESS = "%s00";
    public static  final String INVALID_INPUT = "%s01";
    public static  final String NOT_FOUND = "%s02";
    public static  final String CONFLICT = "%s03";
    public static  final String UNKNOWN = "%s04";
    public static  final String SERVER_ERROR = "%s05";
    public static  final String NOT_EMPTY = "%s06";
}
