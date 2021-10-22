package com.datnek.elearning.lib.mapper.language;

import com.datnek.elearning.lib.dto.Language.LanguageDto;
import com.datnek.elearning.lib.mapper.GenericMapper;
import com.datnek.elearning.lib.models.models.language.Language;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class LanguageMapper {

    private static LanguageMapper singleton;

    /**
     * Récupère ou créer l'instance
     *
     * @return LanguageMapper
     */
    public static LanguageMapper getInstance() {
        if (singleton == null) {
            singleton = new LanguageMapper();
        }
        return singleton;
    }

    /**
     * maps list of languages into list of LanguageDto
     *
     * @param languages list of permission to be mapped
     * @return list of LanguageDto mapped
     */
    public List<LanguageDto> asDtos(List<Language> languages){
        List<LanguageDto> languageDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(languages)){
            for (Language language: languages){
                LanguageDto languageDto = GenericMapper.INSTANCE.asDto(language);
                /*languageDto.setLanguageType(language.getLanguageType());
                languageDto.setSpokenLevel(language.getSpokenLevel());
                languageDto.setWrittenLevel(language.getWrittenLevel());
                languageDto.setComprehensionLevel(language.getComprehensionLevel());
                languageDto.setIdServer(language.getIdServer());*/
                languageDtos.add(languageDto);
            }
        }
        return languageDtos;
    }


}
