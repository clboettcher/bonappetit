package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffMemberDto;

import java.util.Set;

public class StaffListingDtoBuilder {
    private Set<StaffMemberDto> staffMemberDtos;
    private Long id;

    private StaffListingDtoBuilder() {
    }

    public static StaffListingDtoBuilder aStaffListingDto() {
        return new StaffListingDtoBuilder();
    }

    public StaffListingDtoBuilder withStaffMemberDtos(Set<StaffMemberDto> staffMemberDtos) {
        this.staffMemberDtos = staffMemberDtos;
        return this;
    }

    public StaffListingDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public StaffListingDtoBuilder but() {
        return aStaffListingDto().withStaffMemberDtos(staffMemberDtos).withId(id);
    }

    public StaffListingDto build() {
        StaffListingDto staffListingDto = new StaffListingDto();
        staffListingDto.setStaffMemberDtos(staffMemberDtos);
        staffListingDto.setId(id);
        return staffListingDto;
    }
}
