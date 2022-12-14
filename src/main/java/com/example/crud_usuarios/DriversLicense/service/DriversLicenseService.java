package com.example.crud_usuarios.DriversLicense.service;

import com.example.crud_usuarios.DriversLicense.model.DriversLicenseModel;
import com.example.crud_usuarios.DriversLicense.repository.DriversLicenseRepository;
import com.example.crud_usuarios.Usuarios.model.User;
import com.example.crud_usuarios.Usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriversLicenseService {
    private final DriversLicenseRepository driversLicenseRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public DriversLicenseService(DriversLicenseRepository driversLicenseRepository, UsuarioService usuarioService) {
        this.driversLicenseRepository = driversLicenseRepository;
        this.usuarioService = usuarioService;
    }

    public List<DriversLicenseModel> getDriversLicenses(String userEmail) {
        User user = usuarioService.getUsuarioByEmail(userEmail);
        return driversLicenseRepository.findByUser(user);
    }

    public DriversLicenseModel addDriversLicense(DriversLicenseModel driversLicense, String userEmail) {
        User user = usuarioService.getUsuarioByEmail(userEmail);
        driversLicense.setUser(user);
        driversLicenseRepository.save(driversLicense);
        return driversLicense;
    }

    public void deleteDriversLicense(Long id) {
        driversLicenseRepository.deleteById(id);
    }

    public DriversLicenseModel getDriversLicense(Long id) {
        return driversLicenseRepository.findById(id).orElseThrow();
    }

    public DriversLicenseModel updateDriversLicense(DriversLicenseModel driversLicense, Long id) {
        Optional<DriversLicenseModel> driversLicenseOptional = driversLicenseRepository.findById(id);
        if (driversLicenseOptional.isPresent()) {
            DriversLicenseModel driversLicense1 = driversLicenseOptional.get();

            driversLicense1.setCategory(driversLicense.getCategory());
            driversLicense1.setRegistrationNumber(driversLicense.getRegistrationNumber());
            driversLicense1.setFirstLicenseDate(driversLicense.getFirstLicenseDate());
            driversLicense1.setRegistratioState(driversLicense.getRegistratioState());
            driversLicense1.setRegistrationCity(driversLicense.getRegistrationCity());
            driversLicense1.setMirrorNumber(driversLicense.getMirrorNumber());
            driversLicense1.setCpf(driversLicense.getCpf());
            driversLicense1.setExpirationDate(driversLicense.getExpirationDate());

            driversLicense1.setName(driversLicense.getName());
            driversLicense1.setFathersName(driversLicense.getFathersName());
            driversLicense1.setMothersName(driversLicense.getMothersName());
            driversLicense1.setBirthDate(driversLicense.getBirthDate());
            driversLicense1.setIssueDate(driversLicense.getIssueDate());

            return driversLicense;
        }
        return null;
    }

    public DriversLicenseModel getDriversLicenseByRegistrationNumber(String registrationNumber) {
        return driversLicenseRepository.findByRegistrationNumber(registrationNumber);
    }
}
