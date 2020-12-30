package net.sandrohc.tsuu.api.model;

import java.time.OffsetDateTime;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import net.sandrohc.tsuu.api.model.enums.RevisionStatus;

@Document
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FansubRevision {

	@Id
	@ToString.Include
	@EqualsAndHashCode.Include
	private ObjectId id;

	private Fansub fansub;

	private int createdBy;
	private OffsetDateTime createdAt;

	private int reviewedBy;
	private OffsetDateTime reviewedAt;

	private RevisionStatus status;
	private String comment;

}
