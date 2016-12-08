package com.github.clboettcher.bonappetit.server.menu.impl.mapping.toentity;

import com.github.clboettcher.bonappetit.server.menu.api.dto.read.RadioOptionDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.RadioOptionCreationDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioOptionEntity;
import org.mapstruct.Mapper;

@Mapper(uses = RadioItemEntityMapper.class, componentModel = "spring")
public interface RadioOptionEntityMapper {

    RadioOptionEntity mapToRadioOptionEntity(RadioOptionCreationDto radioOption);

    RadioOptionEntity mapToRadioOptionEntity(RadioOptionDto radioOption);
}
