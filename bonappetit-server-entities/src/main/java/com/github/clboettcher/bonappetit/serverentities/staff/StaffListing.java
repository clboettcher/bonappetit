package com.github.clboettcher.bonappetit.serverentities.staff;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

/**
 *
 */
public class StaffListing {
    private Set<StaffMember> staffMembers;

    public Set<StaffMember> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(Set<StaffMember> staffMembers) {
        this.staffMembers = staffMembers;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("staffMembers", staffMembers)
                .toString();
    }
}
