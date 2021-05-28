package org.blackapple.developermarvel.repostiroies;

import org.blackapple.developermarvel.entities.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {
}
