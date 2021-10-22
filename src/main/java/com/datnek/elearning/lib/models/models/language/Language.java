package com.datnek.elearning.lib.models.models.language;

import com.datnek.elearning.lib.models.models.BaseActiveModel;
import com.datnek.elearning.lib.models.models.language.config.LanguageType;
import com.datnek.elearning.lib.models.models.language.config.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = Language.TABLE_NAME)
public class Language extends BaseActiveModel {
    public static final String TABLE_NAME = "language";
    public static final String SPOKEN_LEVEL_COLUMN = "spokenLevel";
    public static final String WRITTEN_LEVEL_COLUMN = "writtenLevel";
    public static final String COMPREHENSION_LEVEL_COLUMN = "comprehensionLevel";
    public static final String LANGUAGE_TYPE_COLUMN = "languageType";
    public static final String ACCOUNT_COLUMN = "account";


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name=SPOKEN_LEVEL_COLUMN)
    private Level spokenLevel;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name=WRITTEN_LEVEL_COLUMN)
    private Level writtenLevel;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name=COMPREHENSION_LEVEL_COLUMN)
    private Level comprehensionLevel;


    @ManyToOne
    @JoinColumn(name=LANGUAGE_TYPE_COLUMN)
    private LanguageType languageType;

    /*@ManyToOne
    @JoinColumn(name=ACCOUNT_COLUMN )
    private Account account;*/


    @Override
    public String getLog() {
        return null;
    }

    @Override
    public String getLogDetail() {
        return null;
    }
}
