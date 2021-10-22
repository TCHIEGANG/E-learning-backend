package com.datnek.elearning.lib.models.repositories.language.config;

import com.datnek.elearning.lib.models.models.language.config.LanguageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LanguageTypeRepository extends JpaRepository<LanguageType, Integer>{
    Optional<LanguageType> findByIdServer(String idServer);
    Optional<LanguageType> findAllByMessageKeyAndLocale(String key, String locale);
    Optional<List<LanguageType>> findAllByAndLocale(String locale);
    }

