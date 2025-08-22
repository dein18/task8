package ru.dse.task8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dse.task8.entity.UserLimit;

public interface UserLimitRepository  extends JpaRepository<UserLimit, Long> {
}
