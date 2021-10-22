package com.datnek.elearning.lib.models.models.version;

import com.datnek.elearning.lib.models.models.BaseActiveModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = Version.TABLE_NAME)
public class Version extends BaseActiveModel {
    public static final String TABLE_NAME = "version";
    public static final String VERSION_COLUMN = "version";
    public static final String DESCRIPTION_COLUMN = "description";

    @Column(name = VERSION_COLUMN, nullable = false)
    private String version;

    @Column(name = DESCRIPTION_COLUMN, nullable = false)
    private String description;

    @Override
    public String getLog() {
        return null;
    }

    @Override
    public String getLogDetail() {
        return null;
    }
}
