package com.github.clboettcher.bonappetit.server.listener;

import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@RepositoryEventHandler
@Component
public class StaffListingEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaffListingEventHandler.class);

    @HandleBeforeSave
    public void handleStaffListingSave(StaffListing staffListing) {
        logIt(staffListing);
    }

    @HandleBeforeCreate
    public void handleStaffListingCreate(StaffListing staffListing) {
        logIt(staffListing);
    }

    @HandleAfterCreate
    public void handleStaffListingAfterCreate(StaffListing staffListing) {
        LOGGER.info(String.format("Staff listing with ID '%s' saved", staffListing.getId()));
    }

    private void logIt(StaffListing staffListing) {
        LOGGER.info(String.format("Saving staff listing with %d entries.", staffListing.getStaffMembers().size()));
    }
}
