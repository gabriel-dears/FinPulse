package com.fin_pulse.domain.user.repository;

import com.fin_pulse.domain.user.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {
}
