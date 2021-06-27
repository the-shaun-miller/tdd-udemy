package com.validator;

public class ValidateISBN {

    public static final int LONG_ISBN_MULTIPLIER = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    private static final int SHORT_ISBN_LENGTH = 10;
    private static final int LONG_ISBN_LENGTH = 13;


    public boolean checkISBN(String isbn) {
        if (isbn.length() == SHORT_ISBN_LENGTH)
            return checkISBN10(isbn);
        if (isbn.length() == LONG_ISBN_LENGTH)
            return checkISBN13(isbn);
        else
            throw new NumberFormatException("Valid ISBN numbers must be 10 or 13 digits");
    }

    private boolean checkISBN13(String isbn) {
        int total = 0;

        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {

            if (Character.isDigit(isbn.charAt(i)) == false)
                throw new IllegalArgumentException("Characters must be digits 0-9. Used char " + isbn.charAt(i));

            total += Character.getNumericValue(isbn.charAt(i)) * (Math.pow(3,(i%2)));
        }
        return (total % LONG_ISBN_MULTIPLIER == 0);
    }

    private boolean checkISBN10(String isbn) {
        Character x = 'X';
        int total = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {

            if (i == 9 && x.equals(isbn.charAt(i)))
                total += 10; // (10-9)=1 so no need to include

            else {

                if (Character.isDigit(isbn.charAt(i)) == false)
                    throw new IllegalArgumentException("Characters must be digits 0-9. Used char " + isbn.charAt(i));

                total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);

            }
        }
        return (total % SHORT_ISBN_MULTIPLIER == 0);
    }
}