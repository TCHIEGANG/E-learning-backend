package com.datnek.elearning.lib.mapper.language.config;

import com.datnek.elearning.lib.dto.Language.config.LanguageTypeDto;
import com.datnek.elearning.lib.mapper.GenericMapper;
import com.datnek.elearning.lib.models.models.language.config.LanguageType;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class LanguageTypeMapper {

    private static LanguageTypeMapper singleton;

    /**
     * Récupère ou créer l'instance
     *
     * @return LanguageTypeMapper
     */
    public static LanguageTypeMapper getInstance() {
        if (singleton == null) {
            singleton = new LanguageTypeMapper();
        }
        return singleton;
    }

    /**
     * maps list of languageTypes into list of LanguageTypeDto
     *
     * @param languageTypes list of permission to be mapped
     * @return list of LanguageTypeDto mapped
     */
    public List<LanguageTypeDto> asDtos(List<LanguageType> languageTypes){
        List<LanguageTypeDto> languageTypeDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(languageTypes)){
            for (LanguageType languageType: languageTypes){
                LanguageTypeDto languageTypeDto = GenericMapper.INSTANCE.asDto(languageType);
                languageTypeDto.setLocale(languageType.getLocale());
                languageTypeDto.setContent(languageType.getMessageContent());
                languageTypeDto.setKey(languageType.getMessageKey());
                languageTypeDto.setIdServer(languageType.getIdServer());
                languageTypeDto.setId(languageType.getId());
                languageTypeDtos.add(languageTypeDto);
            }
        }
        return languageTypeDtos;
    }


}
