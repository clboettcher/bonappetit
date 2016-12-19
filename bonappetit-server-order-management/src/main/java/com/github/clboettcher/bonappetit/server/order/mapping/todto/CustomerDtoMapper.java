package com.github.clboettcher.bonappetit.server.order.mapping.todto;

import com.github.clboettcher.bonappetit.server.order.api.dto.read.CustomerDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.FreeTextCustomerDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.StaffMemberCustomerDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.TableCustomerDto;
import com.github.clboettcher.bonappetit.server.order.entity.CustomerEntity;
import com.github.clboettcher.bonappetit.server.order.entity.FreeTextCustomerEntity;
import com.github.clboettcher.bonappetit.server.order.entity.StaffMemberCustomerEntity;
import com.github.clboettcher.bonappetit.server.order.entity.TableCustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class CustomerDtoMapper {

    public CustomerDto mapToCustomerDto(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }

        if (customerEntity instanceof FreeTextCustomerEntity) {
            FreeTextCustomerEntity entity = (FreeTextCustomerEntity) customerEntity;
            return FreeTextCustomerDto.builder()
                    .value(entity.getValue())
                    .build();
        } else if (customerEntity instanceof TableCustomerEntity) {
            TableCustomerEntity entity = (TableCustomerEntity) customerEntity;
            return TableCustomerDto.builder()
                    .tableNumber(entity.getTableNumber())
                    .build();
        } else if (customerEntity instanceof StaffMemberCustomerEntity) {
            StaffMemberCustomerEntity entity = (StaffMemberCustomerEntity) customerEntity;
            return StaffMemberCustomerDto.builder()
                    .staffMemberId(entity.getStaffMemberId())
                    .staffMemberFirstName(entity.getStaffMemberFirstName())
                    .staffMemberLastName(entity.getStaffMemberLastName())
                    .build();
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype of %s: %s",
                    CustomerEntity.class.getName(),
                    customerEntity.getClass().getName()));
        }
    }

}
