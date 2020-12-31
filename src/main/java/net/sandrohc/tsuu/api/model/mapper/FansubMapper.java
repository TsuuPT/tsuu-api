package net.sandrohc.tsuu.api.model.mapper;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.model.dto.FansubDTO;

@Component
public class FansubMapper implements Mapper<Fansub, FansubDTO> {

	@NotNull
	@Override
	public FansubDTO toDTO(@NotNull Fansub fansub) {
		return FansubDTO.builder()
				.id(fansub.getId().toHexString())
				.slug(fansub.getSlug())
				.name(fansub.getName())
				.description(fansub.getDescription())
				.iconSmall(fansub.getIconSmall())
				.iconMedium(fansub.getIconMedium())
				.iconLarge(fansub.getIconLarge())
				.bannerSmall(fansub.getBannerSmall())
				.bannerMedium(fansub.getBannerMedium())
				.bannerLarge(fansub.getBannerLarge())
				.links(fansub.getLinks())
				.members(fansub.getMembers())
				.createdBy(fansub.getCreatedBy())
				.createdAt(fansub.getCreatedAt())
				.modifiedBy(fansub.getModifiedBy())
				.modifiedAt(fansub.getModifiedAt())
				.deleted(fansub.isDeleted())
				.deletedBy(fansub.getDeletedBy())
				.deletedAt(fansub.getDeletedAt())
				.deletedReason(fansub.getDeletedReason())
				.build();
	}

	@NotNull
	@Override
	public Fansub fromDTO(@NotNull FansubDTO dto) {
		return Fansub.builder()
				.id(new ObjectId(dto.getId()))
				.slug(dto.getSlug())
				.name(dto.getName())
				.description(dto.getDescription())
				.iconSmall(dto.getIconSmall())
				.iconMedium(dto.getIconMedium())
				.iconLarge(dto.getIconLarge())
				.bannerSmall(dto.getBannerSmall())
				.bannerMedium(dto.getBannerMedium())
				.bannerLarge(dto.getBannerLarge())
				.links(dto.getLinks())
				.members(dto.getMembers())
				.createdBy(dto.getCreatedBy())
				.createdAt(dto.getCreatedAt())
				.modifiedBy(dto.getModifiedBy())
				.modifiedAt(dto.getModifiedAt())
				.deleted(dto.isDeleted())
				.deletedBy(dto.getDeletedBy())
				.deletedAt(dto.getDeletedAt())
				.deletedReason(dto.getDeletedReason())
				.build();
	}

}
