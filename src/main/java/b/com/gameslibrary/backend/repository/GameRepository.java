package b.com.gameslibrary.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import b.com.gameslibrary.backend.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
}
