package com.datnek.elearning.lib.mapper.language;

import com.datnek.elearning.lib.common.helper.MapperHelper;
import com.datnek.elearning.lib.dto.Language.LanguageDto;
import com.datnek.elearning.lib.dto.Language.config.LanguageTypeDto;
import com.datnek.elearning.lib.dto.Language.config.LevelDto;
import com.datnek.elearning.lib.mapper.GenericMapper;
import com.datnek.elearning.lib.models.models.language.Language;
import com.datnek.elearning.lib.models.models.language.config.LanguageType;
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
                //LanguageDto languageDto = GenericMapper.INSTANCE.asDto(language);
                LanguageDto languageDto = MapperHelper.asDto(language);
                languageDtos.add(languageDto);
            }
        }
        return languageDtos;
    }


}
