package az.ingress.transaction.service;

import az.ingress.transaction.domain.Account;
import az.ingress.transaction.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;

    private final EntityManagerFactory entityManagerFactory;

    public void TransferProxy(Long fromId,Long toId, Double amount) throws Exception {
        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        try{
            Account from=manager.find(Account.class,fromId);
            Account to=manager.find(Account.class,toId);
            transfer(from,to,amount);
        }catch (RuntimeException e){
            manager.getTransaction().rollback();
        }finally {
            manager.getTransaction().commit();
            manager.close();
        }


    }
    @Transactional
    public void transfer(Account from,Account to,Double amount) throws Exception{
        if(from.getBalance()<amount){
            throw new RuntimeException("Balance not enough");
        }
        from.setBalance(from.getBalance()-amount);
        log.info("i am waiting 10 second,because internet connection is not suitable");
        to.setBalance(to.getBalance()+amount);
        Thread.sleep(10000);
    }

}
