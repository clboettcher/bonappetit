package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffMemberDto;
import com.github.clboettcher.bonappetit.serverentities.staff.StaffListing;
import com.github.clboettcher.bonappetit.serverentities.staff.StaffMember;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link com.github.clboettcher.bonappetit.server.impl.converter.StaffListingsConverter}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StaffListingsConverterTest.Context.class)
public class StaffListingsConverterTest {

    @Autowired
    private StaffListingsConverter converter;

    @Test
    public void testConversion() throws Exception {
        final StaffListing input = StaffListing.newBuilder()
                .staffMembers(Sets.newHashSet(
                        StaffMember.newBuilder().name("Ranjid").build(),
                        StaffMember.newBuilder().name("Susan").build()
                ))
                .build();

        final StaffListingDto expected = StaffListingDto.newBuilder()
                .staffMembers(Sets.newHashSet(
                        StaffMemberDto.newBuilder().name("Ranjid").build(),
                        StaffMemberDto.newBuilder().name("Susan").build()
                ))
                .build();

        assertThat(converter.convertToDto(input), is(expected));
    }

    @Configuration
    static class Context {
        @Bean
        public StaffListingsConverter staffListingsConverter() {
            return new StaffListingsConverter();
        }
    }
}
