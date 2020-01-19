package pl.coderslab.projectwybankuj.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.repository.BankRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

class BankServiceTest {

    private BankRepository bankRepositoryMock = Mockito.mock(BankRepository.class);
    private HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
    private BankService bankService = new BankService(bankRepositoryMock, requestMock);

    @Test
    void shouldFindAllBanks() {
        // when
        List<Bank> foundBanks = (List<Bank>) bankService.findAllBanks();

        // then
        Mockito.verify(bankRepositoryMock).findAll();
    }

    @Test
    void shouldFindBankById() {
        // given
        Bank bank = new Bank(1L, "Bank1", "logoBank1.jpg");

        // when
        Optional<Bank> foundBank = bankService.findBankById(1L);

        // then
        Mockito.verify(bankRepositoryMock).findById(1L);
    }

    @Test
    void shouldAddBankWithLogo() throws IOException {
        // given
        String logoName = "logoBank.jpg";
        Bank bank = new Bank(1L, "Bank1", logoName);
        File file = new File("/img/file1");

        // when
        bankService.addBankWithLogo((MultipartFile) file, bank);

        // then
        Mockito.verify(bankRepositoryMock).save(bank);
    }

    @Test
    void shouldEditBank() {
    }

    @Test
    void shouldDeleteBank() {
        // given
        Bank bank = new Bank(1L, "Bank1", "logoBank1.jpg");

        // when
        bankService.deleteBank(1L);

        // then
        Mockito.verify(bankRepositoryMock).deleteById(1L);
    }

    @Test
    void shouldMakePath() {
        // given
        String logoName = "logoName.jpg";

        // when
        File file = new File(String.valueOf(bankService.makePath(logoName)));

        // then
        Mockito.verify(requestMock).getSession().getServletContext().getRealPath("/img");
    }
}