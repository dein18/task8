package ru.dse.task8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dse.task8.entity.UserLimit;
import ru.dse.task8.exception.PaymentException;
import ru.dse.task8.repository.UserLimitRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LimitService {
    @Value("${limit}")
    private BigDecimal DEFAULT_LIMIT;

    private final UserLimitRepository repository;

    @Transactional
    public BigDecimal getLimit(Long userId) {
        UserLimit limit = repository.findById(userId)
                .orElseGet(() -> createNewUserLimit(userId));
        return limit.getLimitValue();
    }

    @Transactional
    public BigDecimal consumeLimit(Long userId, BigDecimal amount) {
        UserLimit limit = repository.findById(userId)
                .orElseGet(() -> createNewUserLimit(userId));

        if (limit.getLimitValue().compareTo(amount) < 0) {
            throw new PaymentException("Недостаточно лимита");
        }

        limit.setLimitValue(limit.getLimitValue().subtract(amount));
        limit.setLastUpdated(LocalDate.now());
        repository.save(limit);
        return limit.getLimitValue();
    }

    @Transactional
    public BigDecimal restoreLimit(Long userId, BigDecimal amount) {
        UserLimit limit = repository.findById(userId)
                .orElseGet(() -> createNewUserLimit(userId));

        limit.setLimitValue(limit.getLimitValue().add(amount));
        limit.setLastUpdated(LocalDate.now());
        repository.save(limit);
        return limit.getLimitValue();
    }

    @Transactional
    public void resetAllLimits() {
        List<UserLimit> all = repository.findAll();
        all.forEach(limit -> {
            limit.setLimitValue(DEFAULT_LIMIT);
            limit.setLastUpdated(LocalDate.now());
        });
        repository.saveAll(all);
    }

    private UserLimit createNewUserLimit(Long userId) {
        UserLimit newLimit = UserLimit.builder()
                .userId(userId)
                .limitValue(DEFAULT_LIMIT)
                .lastUpdated(LocalDate.now())
                .build();
        return repository.save(newLimit);
    }
}