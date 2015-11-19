package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.menu.RadioItemDto;
import com.github.clboettcher.bonappetit.serverentities.menu.RadioItem;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Converts {@link com.github.clboettcher.bonappetit.serverentities.menu.RadioItem}s to
 * {@link com.github.clboettcher.bonappetit.common.dto.menu.RadioItemDto}s.
 */
@Component
public class RadioItemsConverter {

    /**
     * Compares {@link RadioItem}s by {@link RadioItem#getIndex()}.
     */
    private Comparator<RadioItem> indexComparator = new Comparator<RadioItem>() {
        @Override
        public int compare(RadioItem o1, RadioItem o2) {
            return o1.getIndex().compareTo(o2.getIndex());
        }
    };

    /**
     * Converts the given {@link RadioItem}s to {@link RadioItemDto}s.
     * <p/>
     * The ordering of the resulting {@link LinkedHashSet} is determined by
     * the indexes of the given {@link RadioItem}s as returned by {@link RadioItem#getIndex()}.
     *
     * @param radioItems The {@link RadioItem}s to convert.
     * @return The resulting {@link RadioItemDto}s.
     */
    /* package-private */ Set<RadioItemDto> convert(Set<RadioItem> radioItems) {
        List<RadioItem> inputSorted = new ArrayList<>(radioItems);
        Collections.sort(inputSorted, indexComparator);
        LinkedHashSet<RadioItemDto> result = new LinkedHashSet<>(inputSorted.size());
        for (RadioItem radioItem : inputSorted) {
            result.add(convert(radioItem));
        }
        return result;
    }

    /**
     * Converts the given {@link RadioItem} to a {@link RadioItemDto}.
     *
     * @param radioItem The {@link RadioItem} to convert.
     * @return The resulting {@link RadioItemDto}.
     */
    private RadioItemDto convert(RadioItem radioItem) {
        return RadioItemDto.newBuilder()
                .id(radioItem.getId())
                .title(radioItem.getTitle())
                .priceDiff(radioItem.getPriceDiff())
                .build();
    }
}
