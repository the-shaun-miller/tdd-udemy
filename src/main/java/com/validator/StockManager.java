package com.validator;

public class StockManager {

    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;

    public void setWebService(ExternalISBNDataService webService) {
        this.webService = webService;
    }

    public void setDatabaseService(ExternalISBNDataService databaseService) {
        this.databaseService = databaseService;
    }

    public String getLocatorCode(String isbn) {

        Book book = databaseService.lookup(isbn);
        if (book == null)
            book = webService.lookup(isbn);

        StringBuilder locator = new StringBuilder();

        String lastFourOfISBN = isbn.substring(isbn.length()-4);
        locator.append(lastFourOfISBN);

        String authorInitial = book.getAuthorLastName().substring(0, 1);
        locator.append(authorInitial);

        String[] separateTitleWords = book.getTitle().split(" ");
        int wordCount = separateTitleWords.length;
        locator.append(wordCount);


        return locator.toString();

    }
}
