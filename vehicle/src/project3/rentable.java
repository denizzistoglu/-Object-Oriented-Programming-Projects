package project3;

import java.util.Date;

public interface rentable{
    void rentMe(Date starting, Date ending, Customer c) throws SorryWeDontHaveThatOneException;
}
