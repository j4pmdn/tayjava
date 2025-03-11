package vn.hdoan.controller.request;/*
 * @project backend-service
 * @author Huy
 */

import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserPasswordRequest implements Serializable {
    private Long id;
    private String password;
    private String confirmPassword;
}
