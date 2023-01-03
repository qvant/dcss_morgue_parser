package ru.roguebase.dcssmorgueparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.roguebase.dcssmorgueparser.domain.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
