package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.dto.staff.StaffMemberDto;

public class StaffMemberDtoBuilder {
    private String firstName;
    private String lastName;
    private Long id;

    private StaffMemberDtoBuilder() {
    }

    public static StaffMemberDtoBuilder aStaffMemberDto() {
        return new StaffMemberDtoBuilder();
    }

    public StaffMemberDtoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StaffMemberDtoBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StaffMemberDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public StaffMemberDtoBuilder but() {
        return aStaffMemberDto().withFirstName(firstName).withLastName(lastName).withId(id);
    }

    public StaffMemberDto build() {
        StaffMemberDto staffMemberDto = new StaffMemberDto();
        staffMemberDto.setFirstName(firstName);
        staffMemberDto.setLastName(lastName);
        staffMemberDto.setId(id);
        return staffMemberDto;
    }
}
