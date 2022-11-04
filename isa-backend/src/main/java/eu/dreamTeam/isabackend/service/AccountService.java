package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.model.Account;
import eu.dreamTeam.isabackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean validate(String email, String password) {
        Account account = accountRepository.getAccountByEmail(email);
        if(account != null)
            return passwordEncoder.matches(password, account.getPassword());
        return false;
    }

    public Account updatePassword(String email, String password) {
        Account account = accountRepository.getAccountByEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        return accountRepository.save(account);
    }
}
