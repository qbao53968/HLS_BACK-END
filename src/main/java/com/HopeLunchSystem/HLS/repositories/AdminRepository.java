package com.HopeLunchSystem.HLS.repositories;

import com.HopeLunchSystem.HLS.dto.AdminDTO;
import com.HopeLunchSystem.HLS.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
        Optional<Admin> findByUsername(String username);
        boolean existsByUsername(String username);
}
