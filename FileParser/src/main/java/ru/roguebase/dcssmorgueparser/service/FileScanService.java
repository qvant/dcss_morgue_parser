package ru.roguebase.dcssmorgueparser.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.roguebase.dcssmorgueparser.domain.Server;
import ru.roguebase.dcssmorgueparser.repository.CharacterRepository;
import ru.roguebase.dcssmorgueparser.repository.MorgueRepository;
import ru.roguebase.dcssmorgueparser.repository.ServerRepository;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileScanService {
    private final ServerRepository serverRepository;
    private final CharacterRepository characterRepository;
    private final MorgueRepository morgueRepository;
    private final FileParseService fileParseService;
    private final CharacterService characterService;


    //@Scheduled(initialDelay = 2000, fixedRate = 3000)
    public void processFiles() throws IOException {
        var servers = serverRepository.findAll();
        for (Server server : servers
                ) {
            processFolder(server);
        }
    }

    private void processFolder(Server server) throws IOException {
        Set<String> fileSet = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(server.getInputFolder()))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    try {
                        characterService.readCharacter(server, path);
                    }
                    catch (Exception exception){
                        log.error(exception.getMessage());
                        log.error(Arrays.toString(exception.getStackTrace()));
                        Path errorFolder = Paths.get(server.getErrorFolder() + path.getFileName().toString());
                        Files.move(path, errorFolder, REPLACE_EXISTING);
                        throw new RuntimeException(exception.getMessage());
                    }
//                    fileSet.add(path.getFileName()
//                            .toString());
                }
            }
        }
    }
}
