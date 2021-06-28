import com.validator.Book;
import com.validator.ExternalISBNDataService;
import com.validator.StockManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    final String ISBN = "0684801523"; // The Great Gatsby ISBN
    final String THE_GREAT_GATSBY = "The Great Gatsby";
    final String FITZGERALD = "Fitzgerald";

    ExternalISBNDataService webService;
    ExternalISBNDataService databaseService;

    StockManager stockManager = new StockManager();


    @BeforeEach
    public void setup(){

        webService = mock(ExternalISBNDataService.class);

        databaseService = mock(ExternalISBNDataService.class);

        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

    }

    @Test
    public void canGetCorrectLocatorCode(){

        //Given
        when(webService.lookup(anyString())).thenReturn(new Book(ISBN, THE_GREAT_GATSBY, FITZGERALD));
        when(databaseService.lookup(anyString())).thenReturn(null);

        //When
        String result = stockManager.getLocatorCode(ISBN);

        //Then
        String expected = "1523F3";//last 4 of isbn + author initial + word count in title
        assertEquals(expected, result);
    }

    @Test
    public void databaseIsUsedIfBookDataInDatabase(){

        //Given
        when(databaseService.lookup(ISBN)).thenReturn(new Book(ISBN, THE_GREAT_GATSBY, FITZGERALD));

        //When
        String locatorCode = stockManager.getLocatorCode(ISBN);

        //Then
        verify(databaseService, times(1)).lookup(ISBN);
        verify(webService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfBookDataNotInDatabase(){

        //Given
        when(databaseService.lookup(ISBN)).thenReturn(null);
        when(webService.lookup(ISBN)).thenReturn(new Book(ISBN, THE_GREAT_GATSBY, FITZGERALD));

        //When
        String locatorCode = stockManager.getLocatorCode(ISBN);

        //Then
        verify(databaseService, times(1)).lookup(anyString());
        verify(webService, times(1)).lookup(ISBN);
    }

}
