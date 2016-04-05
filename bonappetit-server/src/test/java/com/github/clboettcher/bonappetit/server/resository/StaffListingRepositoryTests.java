package com.github.clboettcher.bonappetit.server.resository;

import com.github.clboettcher.bonappetit.server.BonappetitServerApplication;
import com.github.clboettcher.bonappetit.server.entity.builder.StaffListingBuilder;
import com.github.clboettcher.bonappetit.server.entity.builder.StaffMemberBuilder;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;
import com.github.clboettcher.bonappetit.server.repository.StaffListingRepository;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link StaffListingRepository}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = BonappetitServerApplication.class)
public class StaffListingRepositoryTests {

    @Autowired
    private StaffListingRepository staffListingRepository;

    @Test
    public void testSave() throws Exception {
        StaffListing staffListing = StaffListingBuilder.aStaffListing()
                .withStaffMembers(Sets.newHashSet(
                        StaffMemberBuilder.aStaffMember()
                                .withFirstName("John")
                                .withLastName("Smith")
                                .build(),
                        StaffMemberBuilder.aStaffMember()
                                .withFirstName("Agathe")
                                .withLastName("Bauer")
                                .build()
                ))
                .build();
        staffListingRepository.save(staffListing);

        StaffListing dbStaffListing = staffListingRepository.findOne(staffListing.getId());
        assertThat(dbStaffListing.getStaffMembers().size(), is(2));
        assertThat(dbStaffListing.getStaffMembers().iterator().next().getFirstName(), not(isEmptyOrNullString()));
        assertThat(dbStaffListing.getStaffMembers().iterator().next().getLastName(), not(isEmptyOrNullString()));
    }
}
