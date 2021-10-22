package com.datnek.elearning.lib.models.models.accounts;

import com.datnek.elearning.lib.models.models.BaseActiveModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = Account.TABLE_NAME)
public class Account extends BaseActiveModel {
    public static final String TABLE_NAME = "account";
    public static final String FIRSTNAME_COLUMN = "firstname";
    public static final String LASTNAME_COLUMN = "lastname";
    public static final String ADDRESS_COLUMN = "address";
    public static final String EMAIL_COLUMN = "email";
    public static final String PHONE_NUMBER_COLUMN = "phoneNumber";


    @Column(name = FIRSTNAME_COLUMN, nullable = false)
    private String firstname;

    @Column(name = LASTNAME_COLUMN, nullable = false)
    private String lastname;

    @Column(name = ADDRESS_COLUMN)
    private String address;

    @Column(name = EMAIL_COLUMN, nullable = false, unique = true)
    private String email;

    @Column(name = PHONE_NUMBER_COLUMN)
    private String phoneNumber;

    @Override
    public String getLog() {
        return null;
    }

    @Override
    public String getLogDetail() {
        return null;
    }
}
