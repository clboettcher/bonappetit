package com.github.clboettcher.bonappetit.server.menu.impl.dao.impl;

import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.*;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link MenuValidator}.
 */
public class MenuValidatorTestsFoo {

    private MenuValidator validator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        this.validator = new MenuValidator();
    }

    @Test
    public void testMenuWithoutItems() throws Exception {
        MenuEntity menu = new MenuEntity();
        validator.assertNewMenuValid(menu);

        menu.setId(1L);
        validator.assertMenuUpdateValid(menu);
    }

    @Test
    public void testMenuWithItems() throws Exception {
        MenuEntity menu = MenuEntity.builder()
                .items(Lists.<ItemEntity>newArrayList(
                        ItemEntity.builder()
                                .title("Pommes")
                                .build(),
                        ItemEntity.builder()
                                .title("Cola")
                                .build()))
                .build();
        validator.assertNewMenuValid(menu);

        menu.setId(1L);
        menu.getItems().get(0).setId(1L);
        menu.getItems().get(1).setId(2L);
        validator.assertMenuUpdateValid(menu);
    }

    @Test
    public void testMenuWithItemWithOptions() throws Exception {
        MenuEntity menu = MenuEntity.builder()
                .items(Lists.<ItemEntity>newArrayList(
                        ItemEntity.builder()
                                .title("Pommes")
                                .options(Lists.newArrayList(
                                        ValueOptionEntity.builder()
                                                .title("Ketchup")
                                                .build(),
                                        CheckboxOptionEntity.builder()
                                                .title("Extra Salz")
                                                .build()
                                ))
                                .build()
                ))
                .build();
        validator.assertNewMenuValid(menu);

        menu.setId(1L);
        menu.getItems().get(0).setId(2L);
        menu.getItems().get(0).getOptions().get(0).setId(3L);
        menu.getItems().get(0).getOptions().get(0).setId(4L);
        validator.assertMenuUpdateValid(menu);
    }

    @Test
    public void testMenuWithItemWithRadioOption() throws Exception {
        RadioItemEntity defaultSelected = RadioItemEntity.builder()
                .title("groß")
                .build();

        MenuEntity menu = MenuEntity.builder()
                .items(Lists.<ItemEntity>newArrayList(
                        ItemEntity.builder()
                                .title("Augustiner Hell")
                                .options(Lists.newArrayList(
                                        RadioOptionEntity.builder()
                                                .title("Größe")
                                                .defaultSelected(defaultSelected)
                                                .radioItems(Lists.newArrayList(
                                                        defaultSelected,
                                                        RadioItemEntity.builder()
                                                                .title("klein")
                                                                .build()))
                                                .build()
                                ))
                                .build()
                ))
                .build();
        validator.assertNewMenuValid(menu);

        menu.setId(1L);
        menu.getItems().get(0).setId(2L);
        RadioOptionEntity radioOption = (RadioOptionEntity) menu.getItems().get(0).getOptions().get(0);
        radioOption.setId(3L);
        radioOption.getRadioItems().get(0).setId(4L);
        radioOption.getRadioItems().get(1).setId(5L);
        validator.assertMenuUpdateValid(menu);
    }
}
