package be.ordina.monolith.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Rental implements Serializable{

	@Id
	@Column
	private UUID uuid;

	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "rental")
	private Set<Movie> movies = new HashSet<>();

	private LocalDateTime rentDate;
	private LocalDateTime endDate;


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
