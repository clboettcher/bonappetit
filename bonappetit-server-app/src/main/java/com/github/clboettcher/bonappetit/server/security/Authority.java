package com.github.clboettcher.bonappetit.server.security;

public enum Authority {
    ADMIN,
    USER;

    @Override
    public String toString() {
        return name();
    }
}
