package net.sandrohc.tsuu.api.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Fansub {

	@Id
	@ToString.Include
	@EqualsAndHashCode.Include
	private ObjectId id;

	@Indexed(unique = true)
	@EqualsAndHashCode.Include
	private String slug;

	@ToString.Include
	private String name;

	private String description;

	private String iconSmall;
	private String iconMedium;
	private String iconLarge;

	private String bannerSmall;
	private String bannerMedium;
	private String bannerLarge;

	@Builder.Default
	private List<FansubLink> links = new ArrayList<>(0);

	@Builder.Default
	private List<FansubMember> members = new ArrayList<>(0);


	private int createdBy;
	private OffsetDateTime createdAt;

	private Integer modifiedBy;
	private OffsetDateTime modifiedAt;

	private boolean deleted;
	private Integer deletedBy;
	private OffsetDateTime deletedAt;
	private String deletedReason;

}
