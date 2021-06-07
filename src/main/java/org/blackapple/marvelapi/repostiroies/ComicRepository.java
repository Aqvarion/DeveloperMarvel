package org.blackapple.marvelapi.repostiroies;

import org.blackapple.marvelapi.entities.Comic;
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
