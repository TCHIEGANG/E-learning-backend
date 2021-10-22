package com.datnek.elearning.lib.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseActiveModelDto extends BaseModelDto {

    protected boolean active = true;

    protected boolean deleted = false;

    protected Date createdAt;

    protected Date modifiedAt;

    protected long revision;
}
