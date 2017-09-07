package be.ordina.monolith.rental;

import be.ordina.monolith.movie.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Rental implements Serializable {

	@Id
	@Column(name = "uuid")
	private UUID uuid;

	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "rental")
	private Set<Movie> movies = new HashSet<>();

	private LocalDate rentDate;
	private LocalDate endDate;


	private void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@PrePersist
	public void ensureUuid() {
		if (getUuid() == null) {
			setUuid(UUID.randomUUID());
		}
	}

	@JsonIgnore
	private UUID getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public LocalDate getRentDate() {
		return rentDate;
	}

	public void setRentDate(LocalDate rentDate) {
		this.rentDate = rentDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getUuid())
				.toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Rental) {
			final Rental other = (Rental) obj;
			return new EqualsBuilder()
					.append(getUuid(), other.getUuid())
					.isEquals();
		} else {
			return false;
		}
	}

}
