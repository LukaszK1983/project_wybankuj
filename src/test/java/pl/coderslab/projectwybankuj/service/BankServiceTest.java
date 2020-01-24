package pl.coderslab.projectwybankuj.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.repository.BankRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
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
        Assert.assertNotNull(foundBank);
    }

    @Test
    void shouldDeleteBank() {
        // given
        Bank bank = new Bank(1L, "Bank1", "logoBank1.jpg");

        // when
        bankService.deleteBank(1L);

        // then
        Mockito.verify(bankRepositoryMock).deleteById(any(Long.class));
    }

    @Test
    void shouldDeleteLogo() {
        // given
        File fileToDelete = new File("/logo.jpg");

        // when
        fileToDelete.delete();

        // then
        assertFalse(fileToDelete.delete());
    }

    @Test
    void shouldMakePath() {
        // given
        String logoName = "logoName.jpg";
        String path = "/img";

        // when
        File file = new File(path + "/" + logoName);

        // then
        assertEquals(new File("/img/logoName.jpg"), file);
    }
}