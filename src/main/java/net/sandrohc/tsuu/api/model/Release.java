package net.sandrohc.tsuu.api.model;

import java.time.OffsetDateTime;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Release {

	@Id
	@ToString.Include
	@EqualsAndHashCode.Include
	private ObjectId id;

	@Indexed
	@ToString.Include
	@EqualsAndHashCode.Include
	private ObjectId fansubId;

	@ToString.Include
	@EqualsAndHashCode.Include
	private ObjectId mediaId;

	@ToString.Include
	private String name; // e.g. "Ep. 1"

	private String link;
	private OffsetDateTime timestamp;

}
