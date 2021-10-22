package com.datnek.elearning.lib.models.models.language.config;

import com.datnek.elearning.lib.models.models.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = LanguageType.TABLE_NAME)
public class LanguageType extends BaseModel {
    public static final String TABLE_NAME = "languageType";
    public static final String LOCALE_COLUMN = "locale";
    public static final String KEY_COLUMN = "messageKey";
    public static final String CONTENT_COLUMN = "messageContent";


    @Column(name = LOCALE_COLUMN, nullable = false)
    private String locale;

    @Column(name = KEY_COLUMN, nullable = false)
    private String messageKey;

    @Column(name = CONTENT_COLUMN)
    private String  messageContent;


    @Override
    public String getLog() {
        return null;
    }

    @Override
    public String getLogDetail() {
        return null;
    }
}
