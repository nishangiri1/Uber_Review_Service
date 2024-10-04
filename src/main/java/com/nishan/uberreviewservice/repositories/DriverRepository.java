package com.nishan.uberreviewservice.repositories;

import com.entity.uberprojectentityservice.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

//    Optional<Driver> findByIdAndLicense_number(Long id,Long licenseNumber);
//    @Query("SELECT new com.nishan.uberreviewservice.models.CustomerDriver(d.id,d.name) from Driver as d where d.license_number= :lic and d.id= :id")
//    CustomerDriver hqlFindByIdAndLicense_Number(String lic,Long id);
Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    List<Driver> findAllByIdIn(List<Long> driverIds);
}
