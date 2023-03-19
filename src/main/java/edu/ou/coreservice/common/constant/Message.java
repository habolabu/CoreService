package edu.ou.coreservice.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Message {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Error {
        public static final String INVALID_INPUT = "error.base-001";
        public static final String NOT_FOUND = "error.base-002";
        public static final String ADD_FAIL = "error.base-003";
        public static final String DELETE_FAIL = "error.base-004";
        public static final String TITLE = "error.base-005";
        public static final String UN_KNOWN = "error.base-006";
        public static final String UPDATE_FAIL = "error.base-007";
        public static final String NOT_EMPTY = "error.base-008";
        public static final String UPLOAD_FAIL = "error.base-009";
        public static final String ACTION_FAIL = "error.base-010";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Success {
        public static final String SUCCESSFUL = "success.base-001";
    }
}
