package manager;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import model.DriverManagerConnectionPool;

public class DriverManagerConnectionPoolTest 
{
  @Test
  public final void testGetConnection() throws SQLException 
  {
    Connection con=DriverManagerConnectionPool.getConnection();
    assertNotNull(con);
    DriverManagerConnectionPool.releaseConnection(con);
  }

  @Test
  public final void realeaseConnection() throws SQLException 
  {
	  Connection con=DriverManagerConnectionPool.getConnection();
	  DriverManagerConnectionPool.releaseConnection(con);
	  assertTrue(con.isClosed());
  }
}
