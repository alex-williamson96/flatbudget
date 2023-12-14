package com.caliber.flatbudget.models.internal.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class Auth0UserResponse {
    private String email;
    private boolean email_verified;
    private String username;
    private String phone_number;
    private boolean phone_verified;
    private String user_id;
    private String created_at;
    private String updated_at;
    private List<Identity> identities;
    private Object app_metadata;
    private Object user_metadata;
    private String picture;
    private String name;
    private String nickname;
    private List<String> multifactor;
    private String last_ip;
    private String last_login;
    private int logins_count;
    private boolean blocked;
    private String given_name;
    private String family_name;

    public Auth0UserResponse(String email, boolean email_verified, String username, String phone_number, boolean phone_verified,
                String user_id, String created_at, String updated_at, List<Identity> identities, Object app_metadata,
                Object user_metadata, String picture, String name, String nickname, List<String> multifactor,
                String last_ip, String last_login, int logins_count, boolean blocked, String given_name,
                String family_name) {
        this.email = email;
        this.email_verified = email_verified;
        this.username = username;
        this.phone_number = phone_number;
        this.phone_verified = phone_verified;
        this.user_id = user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.identities = identities;
        this.app_metadata = app_metadata;
        this.user_metadata = user_metadata;
        this.picture = picture;
        this.name = name;
        this.nickname = nickname;
        this.multifactor = multifactor;
        this.last_ip = last_ip;
        this.last_login = last_login;
        this.logins_count = logins_count;
        this.blocked = blocked;
        this.given_name = given_name;
        this.family_name = family_name;
    }

}

@Getter
@Setter
@Builder
@NoArgsConstructor
class Identity {
    private String connection;
    private String user_id;
    private String provider;
    private boolean isSocial;

    public Identity(String connection, String user_id, String provider, boolean isSocial) {
        this.connection = connection;
        this.user_id = user_id;
        this.provider = provider;
        this.isSocial = isSocial;
    }
}