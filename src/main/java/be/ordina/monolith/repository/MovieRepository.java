package be.ordina.monolith.repository;

import be.ordina.monolith.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

	List<Movie> findMoviesByRental(@Param("uuid") UUID uuid);

}
