package com.datnek.elearning.services;


import com.datnek.elearning.lib.common.exception.GenericException;
import com.datnek.elearning.lib.models.models.language.Language;
import com.datnek.elearning.lib.models.models.language.config.LanguageType;
import com.datnek.elearning.lib.models.models.language.config.Level;

import java.util.List;
import java.util.Optional;

public interface LanguageService {
    Language saveLanguage(Language language) throws GenericException;
    boolean deleteLanguage(String idServer) throws GenericException;
    Language updateLanguage(Language language) throws GenericException;
    Language findLanguageByIdServer(String idServer) throws GenericException;
    List<LanguageType> findLanguagesTypeByLocale(String locale) throws GenericException;
    List<Level> findLevelsTypeByLocale(String locale) throws GenericException;
    List<Level> findLevesTypeByLocale(String locale) throws GenericException;
    List<Language> findAllLanguages();


}
