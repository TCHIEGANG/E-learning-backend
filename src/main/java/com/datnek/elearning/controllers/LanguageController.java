package com.datnek.elearning.controllers;


import com.datnek.elearning.api.LanguageApi;
import com.datnek.elearning.lib.common.enumeration.ResponseMessage;
import com.datnek.elearning.lib.common.exception.GenericException;
import com.datnek.elearning.lib.common.helper.MapperHelper;
import com.datnek.elearning.lib.common.logging.LoggingUtil;
import com.datnek.elearning.lib.common.wrapper.GenericResultDto;
import com.datnek.elearning.lib.dto.Language.LanguageDto;
import com.datnek.elearning.lib.dto.Language.LanguageInfosDto;
import com.datnek.elearning.lib.dto.Language.config.LanguageTypeDto;
import com.datnek.elearning.lib.dto.Language.config.LevelDto;
import com.datnek.elearning.lib.mapper.GenericMapper;
import com.datnek.elearning.lib.mapper.language.LanguageMapper;
import com.datnek.elearning.lib.mapper.language.config.LanguageTypeMapper;
import com.datnek.elearning.lib.mapper.language.config.LevelMapper;
import com.datnek.elearning.lib.models.models.language.Language;
import com.datnek.elearning.lib.models.models.language.config.LanguageType;
import com.datnek.elearning.lib.models.models.language.config.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class LanguageController extends AbstractController implements LanguageApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageController.class);
    private static final String defaultValueSizePage = "32";
    private static final String defaultValuePage = "0";

    @Override
    public ResponseEntity<GenericResultDto> findLanguages() throws GenericException {
        LOGGER.info("{}" , LoggingUtil.START );
        try {

            List<Language> languageList = languageService.findAllLanguages();

            result.setResultObjects(LanguageMapper.getInstance().asDtos(languageList));
            genericResultDtoSuccess();
            LOGGER.info("{}" , LoggingUtil.END);
            return new ResponseEntity<>(result, result.getHttpHeaders(), result.getHttpStatus());

        }catch (Exception e){
            return genericResultDtoError(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<GenericResultDto> findLanguageById(@PathVariable String languageId) throws GenericException {
        LOGGER.info("{}" , LoggingUtil.START );
        try {
        Language language = languageService.findLanguageByIdServer(languageId);

        if (language == null){
            return genericResultDtoError(ResponseMessage.LANGUAGE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }

        result.setResultObject(language);
        genericResultDtoSuccess();
        LOGGER.info("{}" , LoggingUtil.END);
        return new ResponseEntity<>(result, result.getHttpHeaders(), result.getHttpStatus());

    }catch (Exception e){
        return genericResultDtoError(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    }

    @Override
    public ResponseEntity<GenericResultDto> findLanguageInfos(@PathVariable String locale) throws GenericException {
        LOGGER.info("{}" , LoggingUtil.START);
        try {

            List<LanguageType> languageList = languageService.findLanguagesTypeByLocale(locale);
            List<Level> levelList =  languageService.findLevelsTypeByLocale(locale);
            LanguageInfosDto languageInfosDto = new LanguageInfosDto();

            languageInfosDto.setLevels(LevelMapper.getInstance().asDtos(levelList));
            languageInfosDto.setLanguageTypes(LanguageTypeMapper.getInstance().asDtos(languageList));

            result.setResultObject(languageInfosDto);

            result.setHttpStatus(HttpStatus.OK);
            result.setMessage("Success");
            LOGGER.info("{}" , LoggingUtil.END);
            return new ResponseEntity<>(result, result.getHttpStatus());
        }catch (Exception e){
            return genericResultDtoError(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<GenericResultDto> createLanguage(@RequestBody LanguageDto languageDto) throws GenericException {
        LOGGER.info("{}" , LoggingUtil.START);
        try {

            //Language language = GenericMapper.INSTANCE.asEntity(languageDto);

            Language language = MapperHelper.asEntity(languageDto);

            language = languageService.saveLanguage(language);

            result.setResultObject(MapperHelper.asDto(language));
            result.setHttpStatus(HttpStatus.OK);
            result.setMessage("Success");
            LOGGER.info("{}" , LoggingUtil.END);
            return new ResponseEntity<>(result, result.getHttpStatus());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return genericResultDtoError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<GenericResultDto> updateLanguage(@RequestBody LanguageDto languageDto) throws GenericException {
        LOGGER.info("{}" , LoggingUtil.START );

        try {
            //Language language = GenericMapper.INSTANCE.asEntity(languageDto);

            Language language = MapperHelper.asEntity(languageDto);

            language = languageService.updateLanguage(language);

            result.setResultObject(MapperHelper.asDto(language));
            genericResultDtoSuccess();
            LOGGER.info("{}" , LoggingUtil.END);
            return new ResponseEntity<>(result, result.getHttpStatus());
        }catch (Exception e){
            return genericResultDtoError(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<GenericResultDto> deleteLanguage(@PathVariable String languageId) throws GenericException {
        LOGGER.info("{}" , LoggingUtil.START );
        try {

            languageService.deleteLanguage(languageId);
            genericResultDtoSuccess();
            LOGGER.info("{}" , LoggingUtil.END);
            return new ResponseEntity<>(result, result.getHttpStatus());

        }catch (Exception e){
            return genericResultDtoError(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



}
