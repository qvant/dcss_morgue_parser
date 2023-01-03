package ru.roguebase.dcssmorgueparser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roguebase.dcssmorgueparser.domain.Character;
import ru.roguebase.dcssmorgueparser.domain.Morgue;
import ru.roguebase.dcssmorgueparser.domain.Server;
import ru.roguebase.dcssmorgueparser.repository.CharacterRepository;
import ru.roguebase.dcssmorgueparser.repository.MorgueRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RequiredArgsConstructor
@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final MorgueRepository morgueRepository;
    private final FileParseService fileParseService;

    @Transactional
    public void readCharacter(Server server, Path path) throws IOException, ParseException {
        Character character = fileParseService.parse(server.getInputFolder() + path.getFileName().toString());
        Morgue morgue = new Morgue( path.getFileName().toString(), server);
        morgueRepository.save(morgue);
        character.setMorgue(morgue);
        characterRepository.save(character);
        Path successFolder = Paths.get(server.getProceedFolder() + path.getFileName().toString());
        Files.move(path, successFolder, REPLACE_EXISTING);
    }
}
