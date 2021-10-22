package com.datnek.elearning.lib.models.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseActiveModel extends BaseModel {
    public static final String ACTIVE_COLUMN = "active";
    public static final String DELETED_COLUMN = "deleted";
    public static final String CREATED_AT_COLUMN = "createdAt";
    public static final String MODIFIED_AT_COLUMN = "modifiedAt";
    public static final String REVISION_COLUMN = "revision";

    @Column(name = ACTIVE_COLUMN)
    protected boolean active = true;

    @Column(name = DELETED_COLUMN)
    protected boolean deleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = CREATED_AT_COLUMN, updatable = false, nullable = false)
    protected Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = MODIFIED_AT_COLUMN)
    protected Date modifiedAt;

    @Column(name = REVISION_COLUMN)
    protected long revision;
}
