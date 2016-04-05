package com.github.clboettcher.bonappetit.server.repository;

import com.github.clboettcher.bonappetit.server.entity.menu.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu,Long>{
}
