package com.datnek.elearning.services.impl;


import com.datnek.elearning.lib.common.enumeration.ResponseMessage;
import com.datnek.elearning.lib.common.exception.GenericException;
import com.datnek.elearning.lib.models.models.language.Language;
import com.datnek.elearning.lib.models.models.language.config.LanguageType;
import com.datnek.elearning.lib.models.models.language.config.Level;
import com.datnek.elearning.lib.models.repositories.language.LanguageRepository;
import com.datnek.elearning.lib.models.repositories.language.config.LanguageTypeRepository;
import com.datnek.elearning.lib.models.repositories.language.config.LevelRepository;
import com.datnek.elearning.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LanguageServiceImpl<LanguageTYpeRepository> implements LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageTypeRepository languageTypeRepository;
    private final LevelRepository levelRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository, LanguageTypeRepository languageTypeRepository, LevelRepository levelRepository) {

        this.languageRepository = languageRepository;
        this.languageTypeRepository = languageTypeRepository;
        this.levelRepository = levelRepository;
    }


    @Override
    public Language saveLanguage(Language language) throws GenericException {
        language.setIdServer(String.valueOf(System.nanoTime()));
        return this.languageRepository.save(language);
    }

    @Override
    public Language updateLanguage(Language language) throws GenericException {
        Language existedLanguage = findLanguageByIdServer(language.getIdServer());
        language.setId(existedLanguage.getId());
        return languageRepository.save(language);
    }

    @Override
    public Language findLanguageByIdServer(String idServer) throws GenericException{
        Optional<Language> language = languageRepository.findByIdServer(idServer);
        if (!language.isPresent()){
            throw new GenericException(ResponseMessage.LANGUAGE_UNIDENTIFIED.getMessage());
        }
        return language.get();
    }

    @Override
    public List<LanguageType> findLanguagesTypeByLocale(String locale) throws GenericException {
        return  languageTypeRepository.findAllByAndLocale(locale).orElseGet(ArrayList::new);
    }

    @Override
    public List<Level> findLevelsTypeByLocale(String locale) throws GenericException {
        return  levelRepository.findAllByAndLocale(locale).orElseGet(ArrayList::new);
    }

    @Override
    public List<Level> findLevesTypeByLocale(String locale) throws GenericException {
        return null;
    }

    @Override
    public boolean deleteLanguage(String idServer) throws GenericException {
        Optional<Language> language = languageRepository.findByIdServer(idServer);
        if (!language.isPresent()){
            throw new GenericException(ResponseMessage.LANGUAGE_NOT_FOUND.getMessage());
        }
        language.get().setDeleted(true);
        languageRepository.save(language.get());
        return true;
    }

    @Override
    public List<Language> findAllLanguages() {
        return languageRepository.findAllByActiveTrueAndDeletedFalse().orElse(new ArrayList<>());
    }

}
