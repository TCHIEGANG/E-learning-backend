package com.datnek.elearning.lib.common.logging;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;

public class LoggableList<E extends Loggable> extends ArrayList<E> implements Loggable {

    /**
     * Constructeur par défaut
     */
    public LoggableList() {
        // Default
    }

    /**
     * Instancie une loggable Liste à partir d'une arrayList
     *
     * @param collection la liste à transformer.
     */
    public LoggableList(Collection collection) {
        super(collection);
    }

    @Override
    @JsonIgnore
    public String getLog() {
        StringBuilder log = new StringBuilder("LoggableList {");
        for (E item : this) {
            log.append("[").append(item.getLog()).append("]");
        }
        log.append("}");
        return log.toString();
    }

    @Override
    @JsonIgnore
    public String getLogDetail() {
        StringBuilder log = new StringBuilder("LoggableList {");
        for (E item : this) {
            log.append("[").append(item.getLogDetail()).append("]");
        }
        log.append("}");
        return log.toString();
    }

    @Override
    public boolean equals(Object list) {
        return super.equals(list);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
