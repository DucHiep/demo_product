package application.controller.api;

import application.constant.Constant;
import application.model.api.FileUploadResult;
import application.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ManhNguyen on 3/1/18.
 */
@RestController
@RequestMapping("/api/upload")
public class UploadApiController {

    @Autowired
    FileStorageService storageService;

    @PostMapping("/upload-image")
    public FileUploadResult uploadImage(
            @RequestParam("file") MultipartFile file) {
        String message = "";
        FileUploadResult result = new FileUploadResult();
        try {
            String newFilename = storageService.store(file);
            message = "You successfully uploaded " +
                    file.getOriginalFilename() + "!";
            result.setMessage(message);
            result.setSuccess(true);
            result.setLink(Constant.PREFIX_LINK_UPLOAD +
                    newFilename);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
