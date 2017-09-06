package be.ordina.monolith.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Movie implements Serializable {

	@Id
	@Column
	private UUID uuid;

	@Column(unique = true)
	private String title;
	private int runtime;
	private String format;
	private String plot;
	private LocalDateTime release;
	private UUID rental;

	@PrePersist
	public void ensureUuid() {
		if (getUuid() == null) {
			setUuid(UUID.randomUUID());
		}
	}

	private void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getRental() {
		return rental;
	}

	public void setRental(UUID rental) {
		this.rental = rental;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public LocalDateTime getRelease() {
		return release;
	}

	public void setRelease(LocalDateTime release) {
		this.release = release;
	}

	@JsonIgnore
	private UUID getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getUuid())
				.toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Movie) {
			final Movie other = (Movie) obj;
			return new EqualsBuilder()
					.append(getUuid(), other.getUuid())
					.isEquals();
		} else {
			return false;
		}
	}
}
