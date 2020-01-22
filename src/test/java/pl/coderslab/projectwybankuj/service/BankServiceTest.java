package pl.coderslab.projectwybankuj.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.repository.BankRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;

class BankServiceTest {

    private BankRepository bankRepositoryMock = Mockito.mock(BankRepository.class);
    private HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
    private BankService bankService = new BankService(bankRepositoryMock, requestMock);

    @Before
    public void setUp() {
        Mockito.when(bankRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(new Bank()));
    }

    @Test
    void shouldFindAllBanks() {
        // given
        List<Bank> banks = Arrays.asList(
                new Bank(1L, "Bank1", "logoBank1.jpg"),
                new Bank(2L, "Bank2", "logoBank2.jpg"),
                new Bank(3L, "Bank3", "logoBank3.jpg")
        );

        // when
        List<Bank> foundBanks = (List<Bank>) bankService.findAllBanks();

        // then
        Assert.assertNotNull(foundBanks);
    }

    @Test
    void shouldFindBankById() {
        // given
        Bank bank = new Bank(1L, "Bank1", "logoBank1.jpg");

        // when
        Optional<Bank> foundBank = bankService.findBankById(1L);

        // then
//        Mockito.verify(bankRepositoryMock).findById(1L);
        Assert.assertNotNull(foundBank);
    }

    @Test
    void shouldAddBankWithLogo() throws IOException {
        // given
        String logoName = "logoBank.jpg";
        Bank bank = new Bank(1L, "Bank1", logoName);
//        File file = new File("/img/file1");
        MockMultipartFile file = new MockMultipartFile("file", "filename-1.jpeg", "image/jpeg", "some-image".getBytes());
        Mockito.when(bankRepositoryMock.save(any(Bank.class))).thenReturn(bank);
        // when
//        bankService.addBankWithLogo(file, bank);

        // then
//        Mockito.verify(bankRepositoryMock).save(bank);
    }

    @Test
    void shouldEditBank() {
    }

    @Test
    void shouldDeleteBank() {
        // given
        Bank bank = new Bank(1L, "Bank1", "logoBank1.jpg");

        // when
        bankRepositoryMock.deleteById(1L);

        // then
        Mockito.verify(bankRepositoryMock).deleteById(any(Long.class));
    }

//    @Test
//    void shouldMakePath() {
//        // given
//        String logoName = "logoName.jpg";
//
//        // when
//        File file = new File(String.valueOf(bankService.makePath(logoName)));
//
//        // then
//        Mockito.verify(requestMock).getSession().getServletContext().getRealPath("/img");
//    }
}