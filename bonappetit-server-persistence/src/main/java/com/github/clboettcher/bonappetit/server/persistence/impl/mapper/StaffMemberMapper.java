package com.github.clboettcher.bonappetit.server.persistence.impl.mapper;

import com.github.clboettcher.bonappetit.domain.staff.StaffMember;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.staff.StaffMemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * Bean mapper for {@link StaffMemberEntity} objects to {@link StaffMember}.
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
     * @param staffMemberEntities The {@link StaffMemberEntity}s to map.
     * @return the mapping result.
     */
    Set<StaffMember> mapToStaffMembers(List<StaffMemberEntity> staffMemberEntities);

    /**
     * Maps the given {@code staffMemberEntity} to a {@link StaffMember}.
     *
     * @param staffMemberEntity The {@link StaffMemberEntity} to map.
     * @return the mapping result.
     */
    StaffMember mapToStaffMember(StaffMemberEntity staffMemberEntity);
}
