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


    /*@Override
    public Language saveLanguage(Language language) throws GenericException {
        try {
            *//*if (languageRepository.findByDeletedFalseAndEmail(language.getEmail()).isPresent()){
                throw new GenericException(ResponseMessage.ACCOUNT_ALREADY_EXISTS.getMessage());
            }
            language.setPassword(StringHelper.generateSecurePassword(language.getPassword(), language.getEmail()));
            language.setIdServer(String.valueOf(System.nanoTime()));
            language.setActive(false); //to be active after email confirmation

            language = languageRepository.save(language);
            try {
                sendConfirmationEmail(language);
            } catch (SkillsmatesMailException | AddressException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
                languageRepository.delete(language);
                throw new GenericException(e.getMessage());
            }*//*
            return language;
        } catch (NoSuchAlgorithmException e) {
            throw new GenericException(ResponseMessage.ERROR_SAVING_ACCOUNT.getMessage());
        }
    }
*/


    @Override
    public List<Language> findAllLanguages() {
        return languageRepository.findAllByActiveTrueAndDeletedFalse().orElse(new ArrayList<>());
    }
/*
    @Override
    public List<Language> findAllLanguages(String idServer, int page, int size) {
        return languageRepository.findByActiveTrueAndDeletedFalseAndIdServerNot(idServer, PageRequest.of(page, size)).orElse(new ArrayList<>());
    }

    @Override
    public Page<Language> findAllLanguagesByPage(int page, int size) throws GenericException {
        return null;
    }

    @Override
    public Language findLanguageByIdServer(String idServer) {
        Optional<Language> language = languageRepository.findByIdServer(idServer);
        return language.orElse(null);
    }

    @Override
    public Language findLanguageByEmail(String email) {
        return languageRepository.findByActiveTrueAndDeletedFalseAndEmail(email).orElse(null);
    }

    @Override
    public boolean deleteLanguage(String idServer) throws GenericException {
        Optional<Language> language = languageRepository.findByIdServer(idServer);
        if (!language.isPresent()){
            throw new GenericException(ResponseMessage.ACCOUNT_NOT_FOUND.getMessage());
        }
        language.get().setDeleted(true);
        languageRepository.save(language.get());
        return true;
    }

    @Override
    public boolean deactivateLanguage(String idServer) throws GenericException {
        Optional<Language> language = languageRepository.findByIdServer(idServer);
        if (!language.isPresent()){
            throw new GenericException(ResponseMessage.ACCOUNT_NOT_FOUND.getMessage());
        }
        language.get().setActive(false);
        languageRepository.save(language.get());
        return true;
    }

    @Override
    public Language activateLanguage(String idServer) throws GenericException {
        try {
            String languageId = cryptUtil.decrypt(idServer);
            Optional<Language> languageOptional = languageRepository.findByIdServer(languageId);
            if (!languageOptional.isPresent()){
                throw new GenericException(ResponseMessage.ACCOUNT_NOT_FOUND.getMessage());
            }

            if (languageOptional.get().isActive()){
                throw new GenericException(ResponseMessage.ACCOUNT_ALREADY_ACTIVE.getMessage());
            }

            Language language = languageOptional.get();
            language.setActive(true);
            languageRepository.save(language);

            sendValidationEmail(language);
            return language;
        } catch (SkillsmatesMailException | AddressException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public List<ActivityArea> findAllActivitiesAreas() {
        return activityAreaRepository.findAll();
    }

    @Override
    public boolean setConnectedStatus(String languageId, boolean connected) throws GenericException {
        Optional<Language> language = languageRepository.findByIdServer(languageId);
        if (!language.isPresent()){
            throw new GenericException(ResponseMessage.ACCOUNT_NOT_FOUND.getMessage());
        }
        language.get().setConnected(connected);
        languageRepository.save(language.get());
        return true;
    }

    @Override
    public List<Language> findAllConnectedLanguages() {
        return languageRepository.findByActiveTrueAndDeletedFalseAndConnectedTrue().orElse(new ArrayList<>());
    }

    @Override
    public List<Language> findAllConnectedLanguages(String idServer, int page, int size) {
        return languageRepository.findByActiveTrueAndDeletedFalseAndConnectedTrueAndIdServerNot(idServer, PageRequest.of(page, size)).orElse(new ArrayList<>());
    }

    @Override
    public List<LanguageSocialInteractions> findLanguagesByFavorite(String languageId) throws GenericException {
        List<Language> favorites = new ArrayList<>();
        Language language = libLanguageService.findLanguageByIdServer(languageId);
        List<SocialInteraction> socialInteractions = libSocialInteractionService.findSocialInteractionsByReceiverAndType(language, SocialInteractionTypeEnum.FAVORITE.name());
        if (!CollectionUtils.isEmpty(socialInteractions)){
            socialInteractions.forEach(socialInteraction -> favorites.add(socialInteraction.getEmitterLanguage()));
        }
        return libLanguageService.convertToLanguageSocialInteractions(favorites);
    }

    @Override
    public LanguageSocialInteractions findLanguageSocialInteractions(String idServer) throws GenericException {
        LanguageSocialInteractions languageSocialInteractions = new LanguageSocialInteractions();
        Language language = libLanguageService.findLanguageByIdServer(idServer);
        languageSocialInteractions.setLanguage(language);
        languageSocialInteractions.setSocialInteractions(socialInteractionRepository.findByActiveTrueAndDeletedFalseAndSocialInteractionMap_Element(language.getIdServer()).orElse(new ArrayList<>()));
        languageSocialInteractions.setNumberPosts(postRepository.countByActiveTrueAndDeletedFalseAndLanguage(language));
        return languageSocialInteractions;
    }

    private void sendConfirmationEmail(Language language) throws SkillsmatesMailException, AddressException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, GenericException {
        String cryptedId = cryptUtil.secureCrypt(language.getIdServer());
        Map<String, String> templateVars = new HashMap<>();
        InternetAddress internetAddress = new InternetAddress(language.getEmail(), true);
        templateVars.put(VariableEnum.VAR_ACCOUNT_NAME.getVariable(), language.getFirstname() + " " + language.getLastname());
        templateVars.put(VariableEnum.VAR_ACTIVATE_LINK.getVariable(), languageUrl + languageActiveLink + languageActivateLink + cryptedId);
        mailService.registrationValidation(templateVars, internetAddress);
    }

    private void sendValidationEmail(Language language) throws SkillsmatesMailException, AddressException {
        Map<String, String> templateVars = new HashMap<>();
        InternetAddress internetAddress = new InternetAddress(language.getEmail(), true);
        templateVars.put(VariableEnum.VAR_ACCOUNT_NAME.getVariable(), language.getFirstname() + " " + language.getLastname());
        templateVars.put(VariableEnum.VAR_HOST_LINK.getVariable(), hostname);
        mailService.confirmationValidation(templateVars, internetAddress);
    }*/
}
