package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.menu.CheckboxOptionDto;
import com.github.clboettcher.bonappetit.common.dto.menu.IntegerOptionDto;
import com.github.clboettcher.bonappetit.common.dto.menu.OptionDto;
import com.github.clboettcher.bonappetit.common.printing.PrintStrategy;
import com.github.clboettcher.bonappetit.serverentities.menu.CheckboxOption;
import com.github.clboettcher.bonappetit.serverentities.menu.IntegerOption;
import com.github.clboettcher.bonappetit.serverentities.menu.Option;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link com.github.clboettcher.bonappetit.server.impl.converter.OptionsConverter}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OptionsConverterTest.Context.class)
public class OptionsConverterTest {

    @Autowired
    private OptionsConverter optionsConverter;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConvertIntegerOption() throws Exception {
        IntegerOption input = new IntegerOption();
        input.setId(1);
        input.setDefaultValue(5);
        input.setPriceDiff(new BigDecimal("2.5"));
        input.setPrintStrategy(PrintStrategy.EMPHASISE);
        input.setIndex(2);
        input.setTitle("Test Integer-Option");

        IntegerOptionDto expected = new IntegerOptionDto();
        expected.setId(1L);
        expected.setDefaultValue(5);
        expected.setPriceDiff(new BigDecimal("2.5"));
        expected.setTitle("Test Integer-Option");

        final Set<OptionDto> expectedResult = Sets.<OptionDto>newHashSet(expected);
        assertThat(optionsConverter.convert(Sets.<Option>newHashSet(input)), is(expectedResult));
    }

    @Test
    public void testConvertCheckboxOption() throws Exception {
        CheckboxOption input = new CheckboxOption();
        input.setId(1337);
        input.setDefaultChecked(true);
        input.setPriceDiff(new BigDecimal("2.5"));
        input.setPrintStrategy(PrintStrategy.EMPHASISE);
        input.setIndex(5);
        input.setTitle("Test Checkbox-Option");

        CheckboxOptionDto expected = new CheckboxOptionDto();
        expected.setId(1337L);
        expected.setDefaultChecked(true);
        expected.setPriceDiff(new BigDecimal("2.5"));
        expected.setTitle("Test Checkbox-Option");

        Set<OptionDto> expectedOutput = Sets.<OptionDto>newLinkedHashSet(Lists.newArrayList(expected));
        assertThat(optionsConverter.convert(Sets.<Option>newHashSet(input)), is(expectedOutput));
    }

    @Test
    public void testFailOnUnknownOptionType() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Unknown");
        optionsConverter.convert(Sets.<Option>newHashSet(new UnknownOption()));
    }

    @Configuration
    static class Context {
        @Bean
        public OptionsConverter optionsConverter() {
            return new OptionsConverter(null);
        }
    }

    /**
     * Subtype of {@link Option} unknown to the converter.
     */
    static class UnknownOption extends Option {
    }
}
