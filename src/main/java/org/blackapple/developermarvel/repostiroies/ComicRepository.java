package org.blackapple.developermarvel.repostiroies;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {
    Page<Comic> findByTitleContaining(String title, Pageable pageable);

    Page<Comic> findByCharactersId(Long id, Pageable pageable);
    Page<Comic> findByCharactersIdAndTitleContaining(Long id, String title, Pageable pageable);
}
