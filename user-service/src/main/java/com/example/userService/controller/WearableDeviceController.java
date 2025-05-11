package com.example.userService.controller;

import com.example.userService.model.User;
import com.example.userService.repository.UserRepository;
import com.example.userService.model.WearableDevice;
import com.example.userService.repository.UserRepository;
import com.example.userService.repository.WearableDeviceRepository;
import com.example.userService.service.UserService;
import com.example.userService.service.WearableDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/wearable-devices")
@RequiredArgsConstructor
public class WearableDeviceController {

    private final WearableDeviceService wearableDeviceService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<WearableDevice> createDevice(@RequestParam String deviceIdentifier) {
        WearableDevice device = wearableDeviceService.createWearableDevice(deviceIdentifier);
        return new ResponseEntity<>(device, HttpStatus.CREATED);
    }


    @PostMapping("/{deviceId}/pair/{userId}")
    public ResponseEntity<WearableDevice> pairDevice(@PathVariable Integer deviceId, @PathVariable Long userId) {
        return wearableDeviceService.getWearableDeviceById(deviceId)
                .flatMap(device -> {
                    User user = userService.getUserById(userId);
                    return Optional.ofNullable(device) // لف الجهاز في Optional
                            .map(d -> new ResponseEntity<>(wearableDeviceService.pairDeviceToUser(d, user), HttpStatus.OK));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<WearableDevice> getDeviceById(@PathVariable Integer id) {
        return wearableDeviceService.getWearableDeviceById(id)
                .map(device -> new ResponseEntity<>(device, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<WearableDevice> getDeviceByUserId(@PathVariable Long userId) {
        return wearableDeviceService.getWearableDeviceByUserId(userId.intValue())
                .map(device -> new ResponseEntity<>(device, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/send-data")
    public ResponseEntity<Void> sendData(@PathVariable Integer id, @RequestParam String measurement) {
        return wearableDeviceService.getWearableDeviceById(id)
                .map(device -> {
                    wearableDeviceService.sendDataFromDevice(device, measurement);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/store-local")
    public ResponseEntity<Object> storeLocal(@PathVariable Integer id, @RequestParam String data) {
        return wearableDeviceService.getWearableDeviceById(id)
                .map(device -> {
                    wearableDeviceService.storeDataLocally(device, data);
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private final UserRepository userRepository;
    private final WearableDeviceRepository wearableDeviceRepository;

    @GetMapping
    public ResponseEntity<List<WearableDevice>> getUserDevices() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<WearableDevice> devices = wearableDeviceRepository.findAllByUserId(user.getId());
        return ResponseEntity.ok(devices);
    }


}
