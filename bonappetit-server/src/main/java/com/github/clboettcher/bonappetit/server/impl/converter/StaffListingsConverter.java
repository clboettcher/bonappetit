package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffMemberDto;
import com.github.clboettcher.bonappetit.serverentities.staff.StaffListing;
import com.github.clboettcher.bonappetit.serverentities.staff.StaffMember;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Converts {@link StaffListing}s to {@link StaffListingDto}s.
 */
@Component
public class StaffListingsConverter {

    /**
     * Converts the given {@link StaffListing} to a {@link StaffListingDto}.
     *
     * @param staffListing The {@link StaffListing} to convert.
     * @return The resulting {@link StaffListingDto}.
     */
    /*package-private*/ StaffListingDto convertToDto(StaffListing staffListing) {
        return StaffListingDto.newBuilder()
                .staffMembers(convertToDto(staffListing.getStaffMembers()))
                .build();
    }

    /**
     * Converts the given {@link StaffMember}s to {@link StaffMemberDto}s.
     *
     * @param staffMembers The {@link StaffMember}s to convert.
     * @return The resulting {@link StaffMemberDto}s.
     */
    private Set<StaffMemberDto> convertToDto(Set<StaffMember> staffMembers) {
        Set<StaffMemberDto> staffMemberDtos = new HashSet<>(staffMembers.size());
        for (StaffMember staffMember : staffMembers) {
            staffMemberDtos.add(convertToDto(staffMember));
        }

        return staffMemberDtos;
    }

    /**
     * Converts the given {@link StaffMember} to a {@link StaffMemberDto}.
     *
     * @param staffMember The {@link StaffMember} to convert.
     * @return The resulting {@link StaffMemberDto}.
     */
    private StaffMemberDto convertToDto(StaffMember staffMember) {
        return StaffMemberDto.newBuilder()
                .name(staffMember.getName())
                .build();
    }
}
