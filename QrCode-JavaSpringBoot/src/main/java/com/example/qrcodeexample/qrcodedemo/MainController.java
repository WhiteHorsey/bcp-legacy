package com.example.qrcodeexample.qrcodedemo;

import com.example.qrcodeexample.qrcodedemo.dto.GenerateQrReqDTO;
import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/qrCode")
public class MainController {

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    @GetMapping(value = "/genrateAndDownloadQRCode/{codeText}/{width}/{height}")
    public void download(
            @PathVariable("codeText") String codeText,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        QRCodeGenerator.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
    }

    @GetMapping(value = "/generate")
    public String generateQRCode(@RequestBody GenerateQrReqDTO reqDTO) throws Exception {
        return Base64.getEncoder().encodeToString(QRCodeGenerator.getQRCodeImage(reqDTO));
    }
}
