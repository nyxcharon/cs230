/*
Barry Martin
Hw10.java
CS 440
*/

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.sql.REF;
import oracle.sql.STRUCT;


public class Hw10 {
	protected Connection conn;

	public void run() throws SQLException, ClassNotFoundException 
	{
		setConnection();
		//Reset the table before each run
		Statement stmt = conn.createStatement();
		String sql = "drop table baseball_tab";
		stmt.executeUpdate(sql);
		
	    method_1();
		method_2();
		method_3();
		method_5("NL",1952);
		method_7(10);
		
		conn.close();
	}

	protected void setConnection() throws ClassNotFoundException,
			SQLException 
			{
				Class.forName("oracle.jdbc.OracleDriver");
				//DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
				conn = DriverManager.getConnection("jdbc:oracle:thin:@cs440.systems.wvu.edu:2222:cs440", "bmartin4", "thisisntmyrealpassword");
			}
    
	private void method_1() throws SQLException
	{
		Statement stmt = conn.createStatement();
		String sql = "create or replace type baseball_obj as object (name varchar2(50), "
				+ "city varchar2(20),year number,position varchar2(20),league varchar2(20))";
		stmt.executeUpdate(sql);
	}
	
	private void method_2() throws SQLException
	{
		Statement stmt = conn.createStatement();
		String sql = "create table baseball_tab of baseball_obj";
		stmt.executeUpdate(sql);
	}
	
	private void method_3() throws SQLException
	{
		Statement stmt = conn.createStatement();
		String sql = "insert into baseball_tab select name, city, year, position, league "
				+ " from ramorehead.MLB";
		stmt.executeUpdate(sql);
	}
	
	public Baseball method_4(String league, int year) throws SQLException 
	{
		Baseball baseball = null;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select ref(p) from baseball_tab p where upper(league)='"
						+ league.toUpperCase() + "' and year=" + year);
		if (rs.next()) 
		{
			REF ref = (REF)rs.getObject(1);
			STRUCT results = (STRUCT) ref.getValue();
			Object[] obj = results.getAttributes();
			BigDecimal curyear = (BigDecimal)obj[2];
			baseball = new Baseball((String)obj[0], (String)obj[1],
					(String)obj[3], (String)obj[4],curyear.intValue());
		}
		return baseball;
	}
	
	public void method_5(String league,int year) throws SQLException
	{
		System.out.println(method_4(league,year));
	}

	public ResultSet method_6(int numCities) throws SQLException
	{
		Statement stmt=conn.createStatement();
		String sql="select * from (select city,count(city) from baseball_tab"
				+ " group by city order by count(city) desc) where ROWNUM <= "+numCities;
		return stmt.executeQuery(sql);
	}
	
	public void method_7(int numCities) throws SQLException
	{
		ResultSet rs=method_6(numCities);
		System.out.println("Top "+numCities+ " Cities");
		System.out.println("--------------------");
		while (rs.next())
		{
			System.out.println(rs.getString(1));
		}
	}
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		new Hw10().run();
	}

}
