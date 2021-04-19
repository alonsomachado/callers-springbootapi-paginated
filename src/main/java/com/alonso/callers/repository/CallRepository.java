package com.alonso.callers.repository;

import com.alonso.callers.model.Call;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call, Long> {
    Page<Call> findByCaller(Integer caller, Pageable pageable);
}
