package ru.roguebase.dcssmorgueparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.roguebase.dcssmorgueparser.domain.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {
}
