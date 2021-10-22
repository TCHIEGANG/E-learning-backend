package com.datnek.elearning.lib.mapper.account;

import com.datnek.elearning.lib.dto.account.AccountDto;
import com.datnek.elearning.lib.mapper.GenericMapper;
import com.datnek.elearning.lib.models.models.accounts.Account;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AccountMapper {

    private static AccountMapper singleton;

    /**
     * Récupère ou créer l'instance
     *
     * @return AccountMapper
     */
    public static AccountMapper getInstance() {
        if (singleton == null) {
            singleton = new AccountMapper();
        }
        return singleton;
    }

    /**
     * maps list of accounts into list of AccountDto
     *
     * @param accounts list of permission to be mapped
     * @return list of AccountDto mapped
     */
    public List<AccountDto> asDtos(List<Account> accounts){
        List<AccountDto> accountDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(accounts)){
            for (Account account: accounts){
                accountDtos.add(GenericMapper.INSTANCE.asDto(account));
            }
        }
        return accountDtos;
    }


}
