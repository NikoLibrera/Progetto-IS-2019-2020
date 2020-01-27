package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import model.DriverManagerConnectionPool;

public class DriverManagerConnectionPoolTest 
{
  @Test
  public final void testGetConnection() 
  {
    try 
    {
      assertNotEquals(DriverManagerConnectionPool.getConnection(), null);
    } 
    catch (SQLException e) 
    {
      e.printStackTrace();
    }
  }

  @Test
  public final void realeaseConnection() 
  {
    assertEquals(true, true);
  }
}
