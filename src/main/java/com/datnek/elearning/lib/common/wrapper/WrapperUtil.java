package com.datnek.elearning.lib.common.wrapper;

import com.datnek.elearning.lib.common.logging.LoggableList;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitaire apportant un spport aux objets de type wrapper.
 */
public class WrapperUtil {

    private WrapperUtil() {
        // empty constructor
    }

    /**
     * Calcul du statut global de l'appel et determination du message associé.
     * Si status = 2XX (200/201/202...) alors  200
     * Si status = 2XX et autre alors 202
     * Si status tous identiques alors valeur identique
     *
     * @param dtoList la liste des resultats des sous appels
     * @return un objet contenant le code HTTP et le message associé.
     */
    public static HttpStatusMessage computeGlobalHttpStatusFromDto(List<GenericResultDto> dtoList) {
        HttpStatusMessage httpStatusMessage = new HttpStatusMessage();

        if (dtoList.size() == 1) {
            // Si la liste ne contient qu'un seul element, la synthèse est lui-même.
            httpStatusMessage.setHttpStatus(dtoList.get(0).getHttpStatus());
            httpStatusMessage.setMessage(dtoList.get(0).getMessage());
        } else {
            // Sinon, il faut calculer le résultat. On construit la sous liste pour passer à l'autre méthode.
            ArrayList<HttpStatusMessage> httpStatusMessages = new ArrayList<>();
            for (GenericResultDto dto : dtoList) {
                httpStatusMessages.add(new HttpStatusMessage(dto.getHttpStatus(), dto.getMessage()));
            }
            httpStatusMessage = computeGlobalHttpStatusFromStatusMessage(httpStatusMessages);
        }
        return httpStatusMessage;
    }

    /**
     * Calcul du statut global de l'appel et determination du message associé.
     * Si status = 2XX (200/201/202...) alors 200
     * Si status = 2XX et autre alors 202
     * Si status tous identiques alors valeur identique
     *
     * @param httpStatusMessageList la liste des resultats des sous appels
     * @return un objet contenant le code HTTP et le message associé.
     */
    public static HttpStatusMessage computeGlobalHttpStatusFromStatusMessage(List<HttpStatusMessage> httpStatusMessageList) {
        HttpStatusMessage httpStatusMessage = new HttpStatusMessage();

        if (httpStatusMessageList.size() == 1) {
            // Si la liste ne contient qu'un seul element, la synthèse est lui-même.
            httpStatusMessage.setHttpStatus(httpStatusMessageList.get(0).getHttpStatus());
            httpStatusMessage.setMessage(httpStatusMessageList.get(0).getMessage());
        } else {
            // Sinon, il faut calculer le résultat. On construit la sous liste pour passer à l'autre méthode.
            ArrayList<HttpStatus> httpStatusList = new ArrayList<>();
            for (HttpStatusMessage httpStatus : httpStatusMessageList) {
                httpStatusList.add(httpStatus.getHttpStatus());
            }
            httpStatusMessage = computeGlobalHttpStatus(httpStatusList);
        }

        return httpStatusMessage;
    }

    /**
     * Calcul du statut global de l'appel et determination du message associé.
     * Si status = 2XX (200/201/202...) alors 200
     * Si status = 2XX et autre alors 202
     * Si status tous identiques alors valeur identique
     *
     * @param httpStatusList la liste des resultats des sous appels
     * @return un objet contenant le code HTTP et le message associé.
     */
    public static HttpStatusMessage computeGlobalHttpStatus(List<HttpStatus> httpStatusList) {
        HttpStatusMessage httpStatusMessage = new HttpStatusMessage();

        if (httpStatusList.size() == 1) {
            // Si la liste ne contient qu'un seul element, la synthèse est lui-même.
            httpStatusMessage.setHttpStatus(httpStatusList.get(0));
        } else {
            // Suppression des doublons pour voir si on a un seul code.
            List<HttpStatus> httpStatusListWithoutDuplicates = httpStatusList.stream().distinct().collect(Collectors.toList());
            // Si on a plus qu'un seul résultat, c'est gagné
            if (httpStatusListWithoutDuplicates.size() == 1) {
                httpStatusMessage.setHttpStatus(httpStatusListWithoutDuplicates.get(0));
            } else {
                computeListStatus(httpStatusMessage, httpStatusListWithoutDuplicates);

            }
        }

        // Par defaut on set le message associé au code HTTP... A affiner si besoin
        httpStatusMessage.setMessage(httpStatusMessage.getHttpStatus().getReasonPhrase());

        return httpStatusMessage;
    }

    /**
     * Calcul du status http global de la liste
     *
     * @param httpStatusMessage               l'objet dans lequel on set la réponse
     * @param httpStatusListWithoutDuplicates la liste des codes sans duplicatat
     */
    private static void computeListStatus(HttpStatusMessage httpStatusMessage, List<HttpStatus> httpStatusListWithoutDuplicates) {
        // Sinon, il faut calculer le résultat. déclaration des valeurs de boolean.
        boolean number2XX = false;
        boolean number3XX = false;
        boolean number4XX = false;
        boolean number5XX = false;
        // On itère sur les code et set les boolean.
        for (HttpStatus httpStatus : httpStatusListWithoutDuplicates) {
            if (httpStatus.value() < 300) number2XX = true;
            else if (httpStatus.value() < 400) number3XX = true;
            else if (httpStatus.value() < 500) number4XX = true;
            else if (httpStatus.value() > 500) number5XX = true;
        }

        // Si on a que des 2XX, 200 OK
        if (number2XX && !number3XX && !number4XX && !number5XX) httpStatusMessage.setHttpStatus(HttpStatus.OK);
            // Si on a que des erreurs 4XX ou 5XX => 400 BAD REQUEST
        else if (!number2XX && !number3XX && (number4XX || number5XX)) httpStatusMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
            // Si on a autre chose que tout pareil, que des 2XX, ou que des erreurs, alors 202 ACCEPTED
        else httpStatusMessage.setHttpStatus(HttpStatus.ACCEPTED);
    }

    /**
     * Méthode générant un message textuel compréhensible à partir des éléments contenus dans l'objet (statut de l'erreur, erreurs de validation, etc.)
     * @param genericResultDto DTO représentant le résultat d'un appel à un controller
     * @return Chaîne de caractères décrivant les éléments de l'
     */
    public static String computeSupervisionMessage(GenericResultDto<?> genericResultDto){
        StringBuilder msgSupervision = new StringBuilder(genericResultDto.getHttpStatus() + " - " + genericResultDto.getMessage());
        LoggableList<GenericMessageDetails> genericMessageDetailsList = genericResultDto.getGenericMessageDetailsList();
        msgSupervision.append(getValidationErrorsSummary(genericMessageDetailsList));
        return msgSupervision.toString();
    }

    /**
     * Méthode générant un message textuel compréhensible à partir des éléments contenus dans l'objet (statut de l'erreur, erreurs de validation, etc.)
     * @param genericResultDtoList Liste réprésentant les résultats d'un appel à un controller
     * @param globalStatut Statut global calculé en fonction des différents résultats obtenus dans le controller
     * @param globalMessage Message général résumant au mieux les résultats obtenus.
     * @return Chaîne de caractères décrivant l'action et les différents éléments d'informations nécessaires.
     */
    public static String computeSupervisionMessage(LoggableList<GenericResultDto> genericResultDtoList, HttpStatus globalStatut, String globalMessage){
        StringBuilder msgSupervision = new StringBuilder(globalStatut + " - " + globalMessage);

        for(GenericResultDto<?> resultDto : genericResultDtoList){
            LoggableList<GenericMessageDetails> genericMessageDetailsList =  resultDto.getGenericMessageDetailsList();
            msgSupervision.append(getValidationErrorsSummary(genericMessageDetailsList));
        }
        return msgSupervision.toString();
    }

    /**
     * Méthode générant un message textuel représentant les erreurs de validation
     * @param genericMessageDetailsList Liste des messages de validation apportant plus d'informations sur le résultat.
     * @return Chaîne de caractères décrivant les informations de validation/success/erreurs sous la forme "CODE - MESSAGE"
     * Par ex. INVALID_DATA - Le crpcen doit être sûr 5 caractères.
     */
    private static String getValidationErrorsSummary(LoggableList<GenericMessageDetails> genericMessageDetailsList){
        StringBuilder msgSupervision = new StringBuilder();
        if(genericMessageDetailsList != null && !genericMessageDetailsList.isEmpty()){
            for (int i = 0; i< genericMessageDetailsList.size(); i++){
                msgSupervision.append(System.lineSeparator());
                GenericMessageDetails msgDetails = genericMessageDetailsList.get(i);
                msgSupervision.append(msgDetails.getCode()).append(msgDetails.getField() != null ? " - " + msgDetails.getField() : "").append(msgDetails.getMessage() != null ? " - " + msgDetails.getMessage() : "");
            }
        }
        return msgSupervision.toString();
    }
}
