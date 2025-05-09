package com.example.userService.service;

import com.example.userService.model.User;
import com.example.userService.model.WearableDevice;
import com.example.userService.repository.UserRepository;
import com.example.userService.repository.WearableDeviceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WearableDeviceService {

    private final WearableDeviceRepository wearableDeviceRepository;
    private final UserRepository userRepository; // تم حقن UserRepository
    private String deviceIdentifier;

    public WearableDevice createWearableDevice(String deviceIdentifier) {
        WearableDevice device = new WearableDevice();
        device.setDeviceIdentifier(deviceIdentifier);
        return wearableDeviceRepository.save(device);
    }

    public Optional<WearableDevice> getWearableDeviceById(Integer id) {
        return wearableDeviceRepository.findById(id);
    }

    public Optional<WearableDevice> getWearableDeviceByDeviceIdentifier(String deviceIdentifier) {
        return wearableDeviceRepository.findByDeviceIdentifier(deviceIdentifier);
    }

    public Optional<WearableDevice> getWearableDeviceByUserId(Integer userId) {
        return wearableDeviceRepository.findByUserId(userId); // تم تصحيح نوع المعرف
    }

    @Transactional
    public WearableDevice pairDeviceToUser(WearableDevice device, User user) {
        device.setUser(user); // تعيين المستخدم للجهاز
        user.setWearableDevice(device); // تعيين الجهاز للمستخدم
        userRepository.save(user); // حفظ المستخدم لتحديث علاقة WearableDevice
        return wearableDeviceRepository.save(device); // حفظ الجهاز
    }

    public void sendDataFromDevice(WearableDevice device, String measurement) {
        System.out.println("Sending data: " + measurement + " from device " + this.deviceIdentifier);
        device.sendData(measurement);
    }

    public void storeDataLocally(WearableDevice device, String data) {
        device.storeLocallyInCache(data);
    }
}
