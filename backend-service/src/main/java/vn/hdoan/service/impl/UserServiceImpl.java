package vn.hdoan.service.impl;/*
 * @project backend-service
 * @author Huy
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.hdoan.common.UserStatus;
import vn.hdoan.controller.request.UserCreationRequest;
import vn.hdoan.controller.request.UserPasswordRequest;
import vn.hdoan.controller.request.UserUpdateRequest;
import vn.hdoan.controller.response.UserResponse;
import vn.hdoan.exception.ResourceNotFoundException;
import vn.hdoan.model.AddressEntity;
import vn.hdoan.model.UserEntity;
import vn.hdoan.repository.AddressRepository;
import vn.hdoan.repository.UserRepository;
import vn.hdoan.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j(topic = "USER-SERVICE")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> findAll() {
        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public UserResponse findByUsername(String name) {
        return null;
    }

    @Override
    public UserResponse findByEmail(String email) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long save(UserCreationRequest req) {
        log.info("Saving user: {}", req);
        UserEntity user = new UserEntity();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setGender(req.getGender());
        user.setBirthday(req.getBirthday());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setUsername(req.getUsername());
        user.setType(req.getType());
        user.setStatus(UserStatus.NONE);
        userRepository.save(user);

        if(user.getId() != null){
            log.info("user id: {}", user.getId());
            List<AddressEntity> addresses = new ArrayList<>();
            req.getAddress().forEach(address -> {
                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setApartmentNumber(address.getApartmentNumber());
                addressEntity.setFloor(address.getFloor());
                addressEntity.setBuilding(address.getBuilding());
                addressEntity.setStreetNumber(address.getStreetNumber());
                addressEntity.setStreet(address.getStreet());
                addressEntity.setCity(address.getCity());
                addressEntity.setCountry(address.getCountry());
                addressEntity.setAddressType(address.getAddressType());
                addressEntity.setUserId(user.getId());
                addresses.add(addressEntity);
            });

            addressRepository.saveAll(addresses);
            log.info("Saved addresses: {}", addresses);
        }

        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserUpdateRequest req) {
        log.info("Updating user: {}", req);

        // Get user by id
        UserEntity user = getUserEntity(req.getId());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setGender(req.getGender());
        user.setBirthday(req.getBirthday());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setUsername(req.getUsername());

        userRepository.save(user);
        log.info("Updated user: {}", req);

        List<AddressEntity> addresses = new ArrayList<>();

        // save address
        req.getAddress().forEach(address -> {
            AddressEntity addressEntity = addressRepository.findByUserIdAndAddressType(user.getId(), address.getAddressType());
            if(addressEntity == null){
                addressEntity = new AddressEntity();
            }
            addressEntity.setApartmentNumber(address.getApartmentNumber());
            addressEntity.setFloor(address.getFloor());
            addressEntity.setBuilding(address.getBuilding());
            addressEntity.setStreetNumber(address.getStreetNumber());
            addressEntity.setStreet(address.getStreet());
            addressEntity.setCity(address.getCity());
            addressEntity.setCountry(address.getCountry());
            addressEntity.setAddressType(address.getAddressType());
            addressEntity.setUserId(user.getId());

            addresses.add(addressEntity);
        });

        //save addresses
        addressRepository.saveAll(addresses);
        log.info("Updated address: {}", addresses);
    }

    @Override
    public void changePassword(UserPasswordRequest req) {
        log.info("Changing password: {}", req);

        // Get user by id
        UserEntity user = getUserEntity(req.getId());
        if(req.getPassword().equals(req.getConfirmPassword())){
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }


        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting user: {}", id);

        // Get user by id
        UserEntity user = getUserEntity(id);
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
        log.info("Deleted user: {}", user);
    }

    /**
     * Get user by id
     * @param id
     * @return user has been id
     */
    private UserEntity getUserEntity(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
    }
}
