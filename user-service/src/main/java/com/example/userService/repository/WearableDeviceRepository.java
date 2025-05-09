package com.example.userService.repository;

import com.example.userService.model.User;
import com.example.userService.model.WearableDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface WearableDeviceRepository extends JpaRepository<WearableDevice, Integer> {
    Optional<WearableDevice> findByDeviceIdentifier(String deviceIdentifier);
    Optional<WearableDevice> findByUserId(Integer userId);
}
