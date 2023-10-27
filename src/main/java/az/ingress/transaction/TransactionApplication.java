package az.ingress.transaction;

import az.ingress.transaction.domain.Account;
import az.ingress.transaction.repository.AccountRepository;
import az.ingress.transaction.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class TransactionApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final AccountService accountService;


    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Account from = accountRepository.findById(1L).get();
        Account to=accountRepository.findById(2L).get();
//        accountService.transfer(from,to,30.0);
        accountService.TransferProxy(1L,2L,30.0);


    }
}
