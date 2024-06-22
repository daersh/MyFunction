package com.daersh.daersh_project.refresh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RefreshRepo extends JpaRepository<Refresh,Integer> {
    boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);

}
