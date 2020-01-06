package pl.coderslab.projectwybankuj.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.coderslab.projectwybankuj.entity.Logo;
import pl.coderslab.projectwybankuj.model.UploadLogoResponse;
import pl.coderslab.projectwybankuj.service.LogoService;

import java.io.IOException;

@RestController
public class LogoController {

    private final LogoService logoService;

    public LogoController(LogoService logoService) {
        this.logoService = logoService;
    }

    @PostMapping("/uploadLogo")
    public UploadLogoResponse uploadLogo(@RequestParam("file") MultipartFile file) throws IOException {
        Logo logo = logoService.uploadLogo(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/resources/static/img/")
                .path(String.valueOf(logo.getId()))
                .toUriString();

        return new UploadLogoResponse(logo.getLogoName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
}
