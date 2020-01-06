package pl.coderslab.projectwybankuj.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.projectwybankuj.entity.Logo;
import pl.coderslab.projectwybankuj.repository.LogoRepository;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LogoService {

    Logger logger = Logger.getLogger(getClass().getName());

    private LogoRepository logoRepository;

    public LogoService(LogoRepository logoRepository) {
        this.logoRepository = logoRepository;
    }

    public Logo uploadLogo(MultipartFile file) throws IOException {
        String logoName = StringUtils.cleanPath(file.getOriginalFilename());

        if (logoName.contains("..")) {
            logger.log(Level.SEVERE, "Błąd");
        }
        Logo logo = new Logo(logoName, file.getContentType(), file.getBytes());
        return logoRepository.save(logo);
    }

    public Logo getLogo(String logoId) {
        return logoRepository.findById(logoId).orElseThrow();
    }
}
