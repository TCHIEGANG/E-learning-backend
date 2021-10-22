package com.datnek.elearning.lib.models.models;


import com.datnek.elearning.lib.common.logging.Loggable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel implements Serializable, Loggable {
    public static final String ID_COLUMN = "id";
    public static final String ID_SERVER_COLUMN = "idServer";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID_COLUMN)
    protected Integer id;

    @Column(name = ID_SERVER_COLUMN, nullable = false, unique = true)
    protected String idServer;
}
