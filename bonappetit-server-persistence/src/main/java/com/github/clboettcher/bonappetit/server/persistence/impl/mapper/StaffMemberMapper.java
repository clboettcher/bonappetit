package com.github.clboettcher.bonappetit.server.persistence.impl.mapper;

import com.github.clboettcher.bonappetit.domain.staff.StaffMember;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.staff.StaffMemberEntityOld;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * Bean mapper for {@link StaffMemberEntityOld} objects to {@link StaffMember}.
 */
@Mapper
public interface StaffMemberMapper {

    /**
     * A mapper instance.
     */
    StaffMemberMapper INSTANCE = Mappers.getMapper(StaffMemberMapper.class);

    /**
     * Maps the given {@code staffMemberEntities} to {@link StaffMember}s.
     *
     * @param staffMemberEntities The {@link StaffMemberEntityOld}s to map.
     * @return the mapping result.
     */
    Set<StaffMember> mapToStaffMembers(List<StaffMemberEntityOld> staffMemberEntities);

    /**
     * Maps the given {@code staffMemberEntity} to a {@link StaffMember}.
     *
     * @param staffMemberEntityOld The {@link StaffMemberEntityOld} to map.
     * @return the mapping result.
     */
    StaffMember mapToStaffMember(StaffMemberEntityOld staffMemberEntityOld);
}
