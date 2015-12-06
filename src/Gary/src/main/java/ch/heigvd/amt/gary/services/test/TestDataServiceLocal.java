package ch.heigvd.amt.gary.services.test;

import javax.ejb.Local;

/*
 * Author     : Jan Purro
 */
@Local
public interface TestDataServiceLocal 
{
  void generateTestData(); 
}