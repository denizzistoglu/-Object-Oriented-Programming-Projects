
package project3;

import java.util.Date;

public interface bookable {
    void bookMe(Date bookStart, Date bookEnd, Customer c) throws SorryWeDontHaveThatOneException;
    void cancelMe() throws NoCancellationYouMustPayException;
    Date getBookStart();
    Date getBookEnd();
    boolean getIsBooked();
    Date getBookingDate();
    
    
}
