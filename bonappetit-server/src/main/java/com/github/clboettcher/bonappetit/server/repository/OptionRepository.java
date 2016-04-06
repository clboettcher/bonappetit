package com.github.clboettcher.bonappetit.server.repository;

import com.github.clboettcher.bonappetit.server.entity.menu.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
