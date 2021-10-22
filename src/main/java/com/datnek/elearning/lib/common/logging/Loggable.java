package com.datnek.elearning.lib.common.logging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

public interface Loggable {
    /**
     * Méthode permettant de contruire une représentation textuelle de l'objet en fonction du niveau de log actif sur la classe de l'objet.
     *
     * @return log
     */
    @JsonIgnore
    String getLog();

    /**
     * Méthode permettant de construire un affichage textuel détaillé de l'objet afin de l'afficher dans des logs.
     *
     * @return logDetail
     */
    @JsonIgnore
    String getLogDetail();

    /**
     * Méthode permettant de retourner le noeud JSON de l'objet à destination des logger business pour faciliter les recherches du SU
     *
     * @return jsonNode
     */
    @JsonIgnore
    default JsonNode getJsonNode() {
        return null;
    }
}
