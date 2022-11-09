package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.handler.exceptions.InvalidPasswordException;
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

    public boolean check(String email) {
        Account account = accountRepository.getAccountByEmail(email);
        if(account != null)
            return true;
        return false;
    }
    public Account validate(String email, String password) {
        Account account = accountRepository.getAccountByEmail(email);
        if(account != null && !passwordEncoder.matches(password, account.getPassword()))
            throw new InvalidPasswordException();
        return account;
    }

    public Account updatePassword(Account account, String newPassword) {
        account.setPassword(passwordEncoder.encode(newPassword));
        return accountRepository.save(account);
    }
}
