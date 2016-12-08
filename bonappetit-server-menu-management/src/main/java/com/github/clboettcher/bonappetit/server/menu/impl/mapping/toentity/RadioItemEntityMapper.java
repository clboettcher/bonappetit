package com.github.clboettcher.bonappetit.server.menu.impl.mapping.toentity;

import com.github.clboettcher.bonappetit.server.menu.api.dto.read.RadioItemDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.RadioItemCreationDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RadioItemEntityMapper {

    List<RadioItemEntity> mapToRadioItemEntitesForCreation(List<RadioItemCreationDto> radioItem);

    List<RadioItemEntity> mapToRadioItemEntitesForUpdate(List<RadioItemDto> radioItem);

    RadioItemEntity mapToRadioItemEntity(RadioItemCreationDto radioItem);

    RadioItemEntity mapToRadioItemEntity(RadioItemDto radioItem);
}
