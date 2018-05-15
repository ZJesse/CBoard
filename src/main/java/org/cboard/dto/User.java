package org.cboard.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by yfyuan on 2016/9/29.
 */
public class User extends org.springframework.security.core.userdetails.User {

    private String userId;
    private String company;
    private String department;
    private String name;
    private String chyCode;
    private String chyPcode;
    private boolean admin;

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChyCode() {
        return chyCode;
    }

    public void setChyCode(String chyCode) {
        this.chyCode = chyCode;
    }

    public String getChyPcode() {
        return chyPcode;
    }

    public void setChyPcode(String chyPcode) {
        this.chyPcode = chyPcode;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}

