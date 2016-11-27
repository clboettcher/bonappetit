package com.github.clboettcher.bonappetit.server.menu.impl.mapping.toentity;

import com.github.clboettcher.bonappetit.server.menu.api.dto.write.RadioItemCreationDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RadioItemEntityMapper {

    List<RadioItemEntity> mapToRadioItemEntites(List<RadioItemCreationDto> radioItem);

    RadioItemEntity mapToRadioItemEntity(RadioItemCreationDto radioItem);
}
