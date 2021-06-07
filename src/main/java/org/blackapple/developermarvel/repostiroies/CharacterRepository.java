package org.blackapple.developermarvel.repostiroies;

import org.blackapple.developermarvel.entities.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    Page<Character> findByComicsId(Long id, Pageable pageable);
}
