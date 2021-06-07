package org.blackapple.marvelapi.repostiroies;

import org.blackapple.marvelapi.entities.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    Page<Character> findByNameContaining(String name, Pageable pageable);

    Page<Character> findByComicsId(Long id, Pageable pageable);
    Page<Character> findByComicsIdAndNameContaining(Long id, String name, Pageable pageable);
}
