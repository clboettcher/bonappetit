package com.github.clboettcher.bonappetit.server.impl.mapping;


import org.mapstruct.Mapper;

@Mapper
public abstract class OptionDtoMapper {

    // TODO repair
//    /**
//     * The mapper instance.
//     */
//    public static final OptionDtoMapper INSTANCE = Mappers.getMapper(OptionDtoMapper.class);
//
//    /**
//     * @param option The {@link Option} to map.
//     * @return The mapping result.
//     */
//    public OptionDto mapToOptionDto(Option option) {
//        if (option instanceof RadioOption) {
//            return RadioOptionDtoMapper.INSTANCE.mapToRadioOptionDto((RadioOption) option);
//        } else if (option instanceof ValueOption) {
//            return mapToValueOptionDto((ValueOption) option);
//        } else {
//            throw new IllegalArgumentException(String.format("Unknown subtype: %s", option.getClass().getName()));
//        }
//    }
//
//    /**
//     * @param option The options to map.
//     * @return The mapping result.
//     */
//    public abstract Set<OptionDto> mapToOptionDtos(Set<Option> option);
//
//    /**
//     * @param valueOption The {@link ValueOption} to map.
//     * @return The mapping result.
//     */
//    public abstract ValueOptionDto mapToValueOptionDto(ValueOption valueOption);
}
