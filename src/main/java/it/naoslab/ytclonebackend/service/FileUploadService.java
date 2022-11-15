package it.naoslab.ytclonebackend.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Service
public class FileUploadService implements FileService {

    @Override
    public String saveFile(MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get("uploadedMedia");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileCode = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String urlFile;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            urlFile = uploadPath + "/" + fileCode;
        } catch (IOException ioException) {
            throw new IOException("Impossibile salvare il file!", ioException);
        }
        return urlFile;
    }
}
