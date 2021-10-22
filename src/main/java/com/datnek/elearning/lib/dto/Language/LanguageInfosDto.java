package com.datnek.elearning.lib.dto.Language;

import com.datnek.elearning.lib.dto.BaseActiveModelDto;
import com.datnek.elearning.lib.dto.BaseModelDto;
import com.datnek.elearning.lib.dto.Language.config.LanguageTypeDto;
import com.datnek.elearning.lib.dto.Language.config.LevelDto;
import com.datnek.elearning.lib.models.models.language.config.LanguageType;
import com.datnek.elearning.lib.models.models.language.config.Level;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageInfosDto extends BaseModelDto {

    private Collection<LanguageTypeDto> languageTypes;
    private Collection<LevelDto> levels;

    @Override
    public String getLog() {
        return null;
    }

    @Override
    public String getLogDetail() {
        return null;
    }
}
