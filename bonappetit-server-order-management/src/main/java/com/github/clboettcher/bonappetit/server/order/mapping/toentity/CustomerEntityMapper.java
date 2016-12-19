package com.github.clboettcher.bonappetit.server.order.mapping.toentity;

import com.github.clboettcher.bonappetit.server.order.api.dto.write.CustomerCreationDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.FreeTextCustomerCreationDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.StaffMemberCustomerCreationDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.TableCustomerCreationDto;
import com.github.clboettcher.bonappetit.server.order.entity.CustomerEntity;
import com.github.clboettcher.bonappetit.server.order.entity.FreeTextCustomerEntity;
import com.github.clboettcher.bonappetit.server.order.entity.StaffMemberCustomerEntity;
import com.github.clboettcher.bonappetit.server.order.entity.TableCustomerEntity;
import com.github.clboettcher.bonappetit.server.staff.dao.StaffMemberDao;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class CustomerEntityMapper {

    @Autowired
    private StaffMemberDao staffMemberDao;

    public CustomerEntity mapToCustomerEntity(CustomerCreationDto customerCreationDto) {
        if (customerCreationDto == null) {
            return null;
        }

        if (customerCreationDto instanceof FreeTextCustomerCreationDto) {
            FreeTextCustomerCreationDto dto = (FreeTextCustomerCreationDto) customerCreationDto;
            return FreeTextCustomerEntity.builder()
                    .value(dto.getValue())
                    .build();
        } else if (customerCreationDto instanceof TableCustomerCreationDto) {
            TableCustomerCreationDto dto = (TableCustomerCreationDto) customerCreationDto;
            return TableCustomerEntity.builder()
                    .tableNumber(dto.getTableNumber())
                    .build();
        } else if (customerCreationDto instanceof StaffMemberCustomerCreationDto) {
            StaffMemberCustomerCreationDto dto = (StaffMemberCustomerCreationDto) customerCreationDto;
            StaffMemberEntity staffMember = staffMemberDao.getStaffMember(dto.getStaffMemberId());
            return StaffMemberCustomerEntity.builder()
                    .staffMemberId(dto.getStaffMemberId())
                    .staffMemberFirstName(staffMember.getFirstName())
                    .staffMemberLastName(staffMember.getLastName())
                    .build();
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype of %s: %s",
                    CustomerCreationDto.class.getName(),
                    customerCreationDto.getClass().getName()));
        }
    }
}
