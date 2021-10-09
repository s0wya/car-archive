package pl.conmir.cararchive.domain;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.conmir.cararchive.domain.originalFile.FileData;
import pl.conmir.cararchive.domain.originalFile.FileName;
import pl.conmir.cararchive.domain.originalFile.OriginalFile;
import pl.conmir.cararchive.exception.FileStorageException;

import java.io.IOException;

@Component
public class OriginalFileFactory {
    public OriginalFile createFile(MultipartFile file) {
        try {
            return OriginalFile.builder()
                    .name(FileName.of(StringUtils.cleanPath(file.getOriginalFilename())))
                    .type(file.getContentType())
                    .data(FileData.of(file.getBytes()))
                    .build();
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file. " + ex);
        }
    }
}
