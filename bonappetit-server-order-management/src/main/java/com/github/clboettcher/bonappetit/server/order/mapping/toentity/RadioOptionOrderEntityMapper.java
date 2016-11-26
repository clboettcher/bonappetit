package com.github.clboettcher.bonappetit.server.order.mapping.toentity;

import com.github.clboettcher.bonappetit.server.menu.impl.dao.RadioItemDao;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.RadioOptionOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.entity.RadioOptionOrderEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class RadioOptionOrderEntityMapper {

    @Autowired
    private RadioItemDao radioItemDao;

    RadioOptionOrderEntity mapToRadioOptionDto(RadioOptionOrderCreationDto radioOption) {
        if (radioOption == null) {
            return null;
        }

        RadioOptionOrderEntity result = new RadioOptionOrderEntity();

        // Existence of referenced radio item has been validate before.
        result.setSelectedRadioItem(radioItemDao.getRadioItemById(radioOption.getSelectedRadioItemId()));

        return result;
    }
}
