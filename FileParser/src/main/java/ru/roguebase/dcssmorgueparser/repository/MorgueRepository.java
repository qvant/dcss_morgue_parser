package ru.roguebase.dcssmorgueparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.roguebase.dcssmorgueparser.domain.Morgue;

public interface MorgueRepository extends JpaRepository<Morgue, Long> {
}
