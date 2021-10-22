package com.datnek.elearning.lib.models.repositories.language.config;


import com.datnek.elearning.lib.models.models.language.config.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LevelRepository extends JpaRepository<Level, Integer>{
    Optional<Level> findByIdServer(String idServer);
    Optional<Level> findAllByMessageKeyAndLocale(String key, String locale);
    Optional<List<Level>> findAllByAndLocale(String locale);
    }

