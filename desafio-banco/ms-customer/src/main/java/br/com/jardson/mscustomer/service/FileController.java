package br.com.jardson.mscustomer.service;

import br.com.jardson.mscustomer.entity.DowloadFile;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.logging.Logger;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/v1/file")
public class FileController {

//    final StorageService service;
//
//    private Logger logger = Logger.getLogger(FileController.class.getName());
//
//    @PostMapping("/uploadFile")
//    public DowloadFile uploadFile(@RequestParam("file") MultipartFile file) {
//        logger.info("Storing file to disk");
//
//        var filename = service.storeFile(file);
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/v1/file/downloadFile/")
//                .path(filename)
//                .toUriString();
//
//        return
//                new DowloadFile(
//                        fileDownloadUri);
//    }
//
//    //MY_file.txt
//    @GetMapping("/downloadFile/{filename:.+}")
//    public ResponseEntity<Resource> downloadFile(
//            @PathVariable String filename, HttpServletRequest request) {
//
//        logger.info("Reading a file on disk");
//
//        Resource resource = service.loadFileAsResource(filename);
//        String contentType = "";
//
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (Exception e) {
//            logger.info("Could not determine file type!");
//        }
//
//        if (contentType.isBlank()) contentType = "application/octet-stream";
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(
//                        HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
}
