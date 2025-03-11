package vn.hdoan.repository;/*
 * @project backend-service
 * @author Huy
 */

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hdoan.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
