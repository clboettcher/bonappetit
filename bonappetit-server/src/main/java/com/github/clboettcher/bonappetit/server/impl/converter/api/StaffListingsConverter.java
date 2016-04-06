package com.github.clboettcher.bonappetit.server.impl.converter.api;

import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;

/**
 * Converts {@link StaffListing}s to {@link StaffListingDto}s.
 */
public interface StaffListingsConverter {
    
    /**
     * Converts the given {@link StaffListing} to a {@link StaffListingDto}.
     *
     * @param staffListing The {@link StaffListing} to convert.
     * @return The resulting {@link StaffListingDto}.
     */
    StaffListingDto convertToDto(StaffListing staffListing);
}
