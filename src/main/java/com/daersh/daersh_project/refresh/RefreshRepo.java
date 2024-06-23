package com.daersh.daersh_project.refresh;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RefreshRepo extends CrudRepository<Refresh,String> {

    boolean existsByRefresh(String refresh);
    @Transactional
    void deleteByRefresh(String refresh);
}
