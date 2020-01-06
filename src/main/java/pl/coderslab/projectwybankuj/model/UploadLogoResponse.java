package pl.coderslab.projectwybankuj.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UploadLogoResponse {
    private String logoName;
//    private String fileDownloadUri;
    private String fileType;

    public UploadLogoResponse(String logoName, String fileDownloadUri, String fileType, long size) {
    }
}
