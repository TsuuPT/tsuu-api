package net.sandrohc.tsuu.api.model.dto;

import java.time.OffsetDateTime;

import lombok.*;
import org.springframework.data.annotation.Id;

import net.sandrohc.tsuu.api.model.enums.RevisionStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FansubRevisionDTO {

	@Id
	@ToString.Include
	@EqualsAndHashCode.Include
	private String id;

	@ToString.Include
	private FansubDTO fansub;

	private int createdBy;
	private OffsetDateTime createdAt;

	private int reviewedBy;
	private OffsetDateTime reviewedAt;

	private RevisionStatus status;
	private String comment;

}
