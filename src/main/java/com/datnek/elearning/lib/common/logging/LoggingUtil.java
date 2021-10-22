package com.datnek.elearning.lib.common.logging;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.MDC;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mickael.bettembourg
 * <p>
 * Cette classe est destinée à contenir toutes les méthodes génériques nécessaire à la gestion des erreurs sur les projets Min.not
 */
public class LoggingUtil {

    /**
     * Clé pour l'identifiant du logger
     */
    public static final String LOGGER_ID = "loggerId";
    /**
     * Clé de transaction ID
     */
    public static final String TRANSACTION_ID = "transactionId";
    /**
     * Clé de nom de classe
     */
    public static final String CLASS_NAME = "className";
    /**
     * Clé de nom de méthode
     */
    public static final String METHOD_NAME = "methodName";
    /**
     * Texte à préfixer devant les logs de début d'appel de méthode
     */
    public static final String START = "START";
    /**
     * Texte à préfixer devant les logs de fin d'appel de méthode
     */
    public static final String END = "END";


    /**
     * Nom du parametre dans le MDC concernant le Statut HTTP d'une requête
     */
    public static final String HTTP_STATUS = "http_status";

    /**
     * empty constructor.
     */
    private LoggingUtil() {
        // empty constructor
    }

    /**
     * Méthode permettant de récupérer un identifiant "unique" sur une journée afin d'identifier rapidement une série de Log. Cet
     * identifiant est contruit sur la base des 8 derniers digit d'un timestamp.
     *
     * @return transactionId
     */
    public static String getTransactionId() {

        // Récupération du timestamp sous forme de string
        String timestampStr = "" + System.currentTimeMillis();
        // récupération des 8 derniers caractères.
        timestampStr = timestampStr.substring(timestampStr.length() - 8);

        return timestampStr;
    }

    /**
     * Construit le message de type {@link Loggable#getLog()} de la collection spécifiée.
     *
     * @param collection La collection de loggable dont le message log est désiré.
     * @return Le message log de la collection.
     */
    public static String getLogFromList(Collection<? extends Loggable> collection) {
        if (collection == null) {
            return null;
        }
        return "[" + collection.stream().map(Loggable::getLog).collect(Collectors.joining(", ")) + "]";
    }

    /**
     * Construit le message de type {@link Loggable#getLogDetail()} de la liste spécifiée.
     *
     * @param list La liste de loggable dont le message log est désiré.
     * @return Le message log de la liste.
     */
    public static String getLogDetailFromList(List<? extends Loggable> list) {
        if (list == null) {
            return null;
        }
        return "[" + list.stream().map(Loggable::getLogDetail).collect(Collectors.joining(", ")) + "]";
    }

    /**
     * Construit le message de type {@link Loggable#getLog()} de la collection spécifiée.
     *
     * @param array La collection de loggable dont le message log est désiré.
     * @return Le message log de la collection.
     */
    public static String getLogFromArray(Loggable[] array) {
        if (array == null) {
            return null;
        }
        return "[" + Arrays.stream(array).map(Loggable::getLog).collect(Collectors.joining(", ")) + "]";
    }

    /**
     * Construit le message de type {@link Loggable#getLogDetail()} de la liste spécifiée.
     *
     * @param array La liste de loggable dont le message log est désiré.
     * @return Le message log de la liste.
     */
    public static String getLogDetailFromArray(Loggable[] array) {
        if (array == null) {
            return null;
        }
        return "[" + Arrays.stream(array).map(Loggable::getLogDetail).collect(Collectors.joining(", ")) + "]";
    }

    /**
     * Construit le message de type {@link Loggable#getLog()} de l'objet loggable spécifié.
     *
     * @param loggable L'objet loggable dont le message log est désiré.
     * @return Le message log de l'objet.
     */
    public static String getLog(Loggable loggable) {
        if (loggable == null) {
            return null;
        }
        return loggable.getLog();
    }

    /**
     * Construit le message de type {@link Loggable#getLogDetail()} de l'objet loggable spécifié.
     *
     * @param loggable L'objet loggable dont le message log est désiré.
     * @return Le message log de l'objet.
     */
    public static String getLogDetail(Loggable loggable) {
        if (loggable == null) {
            return null;
        }
        return loggable.getLogDetail();
    }

    /**
     * Ajoute l'arbre JSON d'un objet {@link Loggable#getJsonNode()}  au contexte MDC
     *
     * @param loggable L'objet loggable dont l'arbre Json doit être ajoute au contexte.
     */
    public static void addLoggableContext(Loggable loggable) {
        if (loggable != null) {
            JsonNode rootNode = loggable.getJsonNode();
            if (rootNode != null) {
                addNodeToContext("", rootNode);
            }
        }
    }

    /**
     * Ajoute le noeud d'un arbre JSON au MDC
     *
     * @param rootPath   Chemin complet dans l'arbre permettant d'accéder au noeud en cours
     * @param parentNode noeud parent pour lequel il faut ajotuer les sous-element au contexte.
     */
    private static void addNodeToContext(String rootPath, JsonNode parentNode) {
        if (parentNode.isObject()) {
            Iterator<String> iter = parentNode.fieldNames();
            while (iter.hasNext()) {
                StringBuilder rootPathSB = new StringBuilder(rootPath);
                String nodeName = iter.next();
                if (rootPathSB.toString().length() > 0) {
                    rootPathSB.append(".").append(nodeName);
                } else {
                    rootPathSB.append(nodeName);
                }

                JsonNode node = parentNode.path(nodeName);
                if (node.isObject()) {
                    addNodeToContext(rootPathSB.toString(), node);
                } else {
                    MDC.put(rootPathSB.toString(), node.asText());
                }
            }
        }
    }
}
