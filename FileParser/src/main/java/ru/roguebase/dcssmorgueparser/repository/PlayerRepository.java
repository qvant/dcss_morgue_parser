package ru.roguebase.dcssmorgueparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.roguebase.dcssmorgueparser.domain.Player;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByLogin(String login);
}
