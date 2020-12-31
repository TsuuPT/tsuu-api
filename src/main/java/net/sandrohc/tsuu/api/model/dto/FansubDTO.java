package net.sandrohc.tsuu.api.model.dto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;

import net.sandrohc.tsuu.api.model.FansubLink;
import net.sandrohc.tsuu.api.model.FansubMember;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FansubDTO {

	@Id
	@ToString.Include
	@EqualsAndHashCode.Include
	private String id;

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
