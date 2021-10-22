package com.datnek.elearning.lib.dto;

import com.datnek.elearning.lib.common.logging.Loggable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseModelDto implements Serializable, Loggable {
    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 1L;

    protected Integer id;

    protected String idServer;
}
