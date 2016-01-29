package de.kraussdev.redbutton.internal.utils;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by pk on 24.01.16.
 */
public class Preconditions {

    private Preconditions()
    {
        throw new AssertionError("Must not create an instance");
    }

    /**
     * Checks the truth of an expression for an argument.
     *
     * @param expression a boolean expression
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression)
    {
        if (!expression)
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks the truth of an expression for an argument.
     *
     * @param expression   a boolean expression
     * @param errorMessage the exception message to use if the check fails
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, @Nullable String errorMessage)
    {
        if (!expression)
        {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Checks the truth of an expression for an argument.
     *
     * @param expression a boolean expression
     * @throws IllegalStateException if {@code expression} is false
     */
    public static void checkState(boolean expression)
    {
        if (!expression)
        {
            throw new IllegalStateException();
        }
    }

    /**
     * Checks the truth of an expression for a state.
     *
     * @param expression   a boolean expression
     * @param errorMessage the exception message to use if the check fails
     * @throws IllegalStateException if {@code expression} is false
     */
    public static void checkState(boolean expression, @Nullable String errorMessage)
    {
        if (!expression)
        {
            throw new IllegalStateException(errorMessage);
        }
    }

    /**
     * Checks if the reference is not null.
     *
     * @param reference an object reference
     * @return the non-null reference
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference)
    {
        if (reference == null)
        {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * Checks if the reference is not null.
     *
     * @param reference    an object reference
     * @param errorMessage the exception message to use if the check fails
     * @return the non-null reference
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference, @Nullable String errorMessage)
    {
        if (reference == null)
        {
            throw new NullPointerException(errorMessage);
        }
        return reference;
    }

    /**
     * Checks if the {@code String} is null
     *
     * @param string       The {@code String} to be tested
     * @param errorMessage the exception message to use if the check fails
     * @return the non-empty String
     */
    public static String checkNotEmpty(String string, @Nullable String errorMessage)
    {
        if (string == null || string.length() == 0)
        {
            throw new IllegalArgumentException(errorMessage);
        }
        return string;
    }

    /**
     * Checks if the reference is not null.
     *
     * @param reference an object reference
     * @return the non-null reference
     * @throws NullPointerException if {@code reference} is null
     */
    @Nonnull
    public static <T> T get(@Nullable final T reference) {
        if (reference == null) {
            throw new NullPointerException("Assertion for a nonnull object failed.");
        }
        return reference;
    }

}
