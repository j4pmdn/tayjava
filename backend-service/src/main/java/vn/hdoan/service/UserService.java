package vn.hdoan.service;/*
 * @project backend-service
 * @author Huy
 */

import vn.hdoan.controller.request.UserCreationRequest;
import vn.hdoan.controller.request.UserPasswordRequest;
import vn.hdoan.controller.request.UserUpdateRequest;
import vn.hdoan.controller.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();

    UserResponse findById(Long id);

    UserResponse findByUsername(String name);

    UserResponse findByEmail(String email);

    long save(UserCreationRequest req);

    void update(UserUpdateRequest req);

    void changePassword(UserPasswordRequest req);

    void delete(Long id);
}
