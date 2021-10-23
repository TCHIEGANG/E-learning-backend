package com.datnek.elearning.lib.common.helper;

import com.datnek.elearning.lib.dto.Language.LanguageDto;
import com.datnek.elearning.lib.dto.Language.config.LanguageTypeDto;
import com.datnek.elearning.lib.dto.Language.config.LevelDto;
import com.datnek.elearning.lib.models.models.language.Language;
import com.datnek.elearning.lib.models.models.language.config.LanguageType;
import com.datnek.elearning.lib.models.models.language.config.Level;

public class MapperHelper {

    public static LanguageDto asDto(Language language){

        LanguageDto languageDto = new LanguageDto();

        // Insertion des types de langues
        LanguageTypeDto languageTypeDto = new LanguageTypeDto();
        languageTypeDto.setLocale(language.getLanguageType().getLocale());
        languageTypeDto.setIdServer(language.getLanguageType().getIdServer());
        languageTypeDto.setContent(language.getLanguageType().getMessageContent());
        languageTypeDto.setKey(language.getLanguageType().getMessageKey());
        languageTypeDto.setId(language.getLanguageType().getId());

        languageDto.setLanguageType(languageTypeDto);

        // Insertion du niveau parlé
        LevelDto spokenLevelDto = new LevelDto();
        spokenLevelDto.setLocale(language.getSpokenLevel().getLocale());
        spokenLevelDto.setIdServer(language.getSpokenLevel().getIdServer());
        spokenLevelDto.setContent(language.getSpokenLevel().getMessageContent());
        spokenLevelDto.setKey(language.getSpokenLevel().getMessageKey());
        spokenLevelDto.setId(language.getSpokenLevel().getId());

        languageDto.setSpokenLevel(spokenLevelDto);

        // Insertion du niveau écrit
        LevelDto writtenLevelDto = new LevelDto();
        writtenLevelDto.setLocale(language.getWrittenLevel().getLocale());
        writtenLevelDto.setIdServer(language.getWrittenLevel().getIdServer());
        writtenLevelDto.setContent(language.getWrittenLevel().getMessageContent());
        writtenLevelDto.setKey(language.getWrittenLevel().getMessageKey());
        writtenLevelDto.setId(language.getWrittenLevel().getId());

        languageDto.setWrittenLevel(writtenLevelDto);

        // Insertion du niveau de compréhension
        LevelDto comprehensionLevelDto = new LevelDto();
        comprehensionLevelDto.setLocale(language.getComprehensionLevel().getLocale());
        comprehensionLevelDto.setIdServer(language.getComprehensionLevel().getIdServer());
        comprehensionLevelDto.setContent(language.getComprehensionLevel().getMessageContent());
        comprehensionLevelDto.setKey(language.getComprehensionLevel().getMessageKey());
        comprehensionLevelDto.setId(language.getComprehensionLevel().getId());

        languageDto.setComprehensionLevel(comprehensionLevelDto);

        languageDto.setIdServer(language.getIdServer());

        return languageDto;
    }

    public static Language asEntity(LanguageDto languageDto){

        Language language = new Language();
        LanguageType languageType = new LanguageType();
        languageType.setLocale(languageDto.getLanguageType().getLocale());
        languageType.setIdServer(languageDto.getLanguageType().getIdServer());
        languageType.setMessageContent(languageDto.getLanguageType().getContent());
        languageType.setMessageKey(languageDto.getLanguageType().getKey());
        languageType.setId(languageDto.getLanguageType().getId());
        language.setLanguageType(languageType);

        Level spokenLevel = new Level();
        spokenLevel.setLocale(languageDto.getSpokenLevel().getLocale());
        spokenLevel.setIdServer(languageDto.getSpokenLevel().getIdServer());
        spokenLevel.setMessageContent(languageDto.getSpokenLevel().getContent());
        spokenLevel.setMessageKey(languageDto.getSpokenLevel().getKey());
        spokenLevel.setId(languageDto.getSpokenLevel().getId());
        language.setSpokenLevel(spokenLevel);

        Level writtenLevel = new Level();
        writtenLevel.setLocale(languageDto.getWrittenLevel().getLocale());
        writtenLevel.setIdServer(languageDto.getWrittenLevel().getIdServer());
        writtenLevel.setMessageContent(languageDto.getWrittenLevel().getContent());
        writtenLevel.setMessageKey(languageDto.getWrittenLevel().getKey());
        writtenLevel.setId(languageDto.getWrittenLevel().getId());
        language.setWrittenLevel(writtenLevel);

        Level comprehensionLevel = new Level();
        comprehensionLevel.setLocale(languageDto.getComprehensionLevel().getLocale());
        comprehensionLevel.setIdServer(languageDto.getComprehensionLevel().getIdServer());
        comprehensionLevel.setMessageContent(languageDto.getComprehensionLevel().getContent());
        comprehensionLevel.setMessageKey(languageDto.getComprehensionLevel().getKey());
        comprehensionLevel.setId(languageDto.getComprehensionLevel().getId());
        language.setComprehensionLevel(comprehensionLevel);

        language.setIdServer(languageDto.getIdServer());

        return language;

    }
}
