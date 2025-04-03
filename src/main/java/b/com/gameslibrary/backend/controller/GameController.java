package b.com.gameslibrary.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import b.com.gameslibrary.backend.Gameslibrary1Application;
import b.com.gameslibrary.backend.dto.GameDTO;
import b.com.gameslibrary.backend.entity.Game;
import b.com.gameslibrary.backend.repository.GameRepository;

@RestController
public class GameController {

    private final Gameslibrary1Application gameslibrary1Application;

	@Autowired
	private GameRepository gameRepository;

    GameController(Gameslibrary1Application gameslibrary1Application) {
        this.gameslibrary1Application = gameslibrary1Application;
    }
	
	@GetMapping("/game/{id}")
	private ResponseEntity<Object> getGame(@PathVariable Long id) {
		Optional<Game> gameOptional = gameRepository.findById(id);
		
		if (gameOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(gameOptional.get());
	}
	
	@PostMapping("/game")
	private ResponseEntity<Object> postGame(@RequestBody GameDTO body) {
		Optional<Game> gameOptional = gameRepository.findById(body.getId());
		
		if(gameOptional.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		Game dbGame = new Game();
		dbGame.setImagePath(body.getImagePath());
		dbGame.setCategory(body.getCategory());
		dbGame.setNome(body.getNome());
		dbGame.setPlataform(body.getPlataform());
		dbGame.setRating(body.getRating());
		dbGame.setReleaseDate(body.getReleaseDate());
		
		return ResponseEntity.ok(gameRepository.save(dbGame));
	}
	
	@PutMapping("/game")
	private ResponseEntity<Object> putGame(@RequestBody GameDTO body) {
		Optional<Game> gameOptional = gameRepository.findById(body.getId());
		
		if(gameOptional.isPresent()) {
			Game dbGame = gameOptional.get();
			dbGame.setImagePath(body.getImagePath());
			dbGame.setCategory(body.getCategory());
			dbGame.setNome(body.getNome());
			dbGame.setPlataform(body.getPlataform());
			dbGame.setRating(body.getRating());
			dbGame.setReleaseDate(body.getReleaseDate());
			
			return ResponseEntity.ok(gameRepository.save(dbGame));
			
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	
	@DeleteMapping("/game/{id}")
	private ResponseEntity<Object> deleteGame(@PathVariable Long id) {
		Optional<Game> gameOptional = gameRepository.findById(id);
		
		if(gameOptional.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		gameRepository.deleteById(id);
		
		return ResponseEntity.ok().body(null);
	}
	
}
