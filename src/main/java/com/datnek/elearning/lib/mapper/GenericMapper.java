package com.datnek.elearning.lib.mapper;

import com.datnek.elearning.lib.dto.Language.LanguageDto;
import com.datnek.elearning.lib.dto.Language.config.LanguageTypeDto;
import com.datnek.elearning.lib.dto.Language.config.LevelDto;
import com.datnek.elearning.lib.dto.account.AccountDto;
import com.datnek.elearning.lib.models.models.accounts.Account;
import com.datnek.elearning.lib.models.models.language.Language;
import com.datnek.elearning.lib.models.models.language.config.LanguageType;
import com.datnek.elearning.lib.models.models.language.config.Level;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Selma;

@Mapper(withIgnoreMissing = IgnoreMissing.ALL)
public interface GenericMapper {

    /**
     * Instance of the mapper
     */
    GenericMapper INSTANCE = Selma.getMapper(GenericMapper.class);

    Language asEntity(LanguageDto languageDto);
    LanguageType asEntity(LanguageTypeDto languageTypeDto);
    Level asEntity(LevelDto levelDto);
    Account asEntity(AccountDto accountDto);

    LanguageDto asDto(Language language);
    LanguageTypeDto asDto(LanguageType languageType);
    LevelDto asDto(Level level);
    AccountDto asDto(Account account);
}
