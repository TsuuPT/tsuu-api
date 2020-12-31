package net.sandrohc.tsuu.api.model.mapper;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.model.FansubRevision;
import net.sandrohc.tsuu.api.model.dto.FansubRevisionDTO;

@Component
@RequiredArgsConstructor
public class FansubRevisionMapper implements Mapper<FansubRevision, FansubRevisionDTO> {

	private final FansubMapper fansubMapper;


	@NotNull
	@Override
	public FansubRevisionDTO toDTO(@NotNull FansubRevision revision) {
		return FansubRevisionDTO.builder()
				.id(revision.getId() != null ? revision.getId().toHexString() : null)
				.fansub(fansubMapper.toDTO(revision.getFansub()))
				.createdBy(revision.getCreatedBy())
				.createdAt(revision.getCreatedAt())
				.reviewedBy(revision.getReviewedBy())
				.reviewedAt(revision.getReviewedAt())
				.status(revision.getStatus())
				.comment(revision.getComment())
				.build();
	}

	@NotNull
	@Override
	public FansubRevision fromDTO(@NotNull FansubRevisionDTO dto) {
		return FansubRevision.builder()
				.id(dto.getId() != null ? new ObjectId(dto.getId()) : null)
				.fansub(fansubMapper.fromDTO(dto.getFansub()))
				.createdBy(dto.getCreatedBy())
				.createdAt(dto.getCreatedAt())
				.reviewedBy(dto.getReviewedBy())
				.reviewedAt(dto.getReviewedAt())
				.status(dto.getStatus())
				.comment(dto.getComment())
				.build();
	}
}
