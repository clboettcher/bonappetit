package com.github.clboettcher.bonappetit.core.printing;

/**
 * Enumerates strategies that control if items are printed or not.
 */
public enum PrintStrategy {

    /**
     * Indicates the corresponding entity should be printed
     * in a way that emphasises it.
     * <p/>
     * Implies that the entity is printed.
     */
    EMPHASISE,

    /**
     * Indicates the corresponding entity should be printed normally.
     */
    DEFAULT,

    /**
     * Indicates the corresponding entity should not be printed at all.
     */
    NOT_PRINTED
}
