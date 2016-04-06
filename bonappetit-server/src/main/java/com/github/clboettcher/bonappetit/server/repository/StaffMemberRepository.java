package com.github.clboettcher.bonappetit.server.repository;

import com.github.clboettcher.bonappetit.server.entity.staff.StaffMember;
import org.springframework.data.repository.CrudRepository;

public interface StaffMemberRepository extends CrudRepository<StaffMember, Long> {
}
