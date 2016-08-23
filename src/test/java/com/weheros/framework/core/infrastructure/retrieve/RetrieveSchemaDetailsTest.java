package com.weheros.framework.core.infrastructure.retrieve;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetrieveSchemaDetailsTest {
	   private static Logger log = LoggerFactory
		       .getLogger(RetrieveSchemaDetailsTest.class);
	
	 public static void main(String args[]) throws Exception {
	

	     String databaseName = "crmdb";
	     String userName = "root";
	     String password = "123456";
	     String mySQLPort = "3306";
	     String hostUrl = "127.0.0.1";

	     // Setup the connection with the DB
	     Class.forName("com.mysql.jdbc.Driver");

	     Connection conn = DriverManager.getConnection("jdbc:mysql://" + hostUrl
	         + ":" + mySQLPort, userName, password);

	     // --- LISTING DATABASE SCHEMA NAMES ---
	     //retrieveSchemas(conn);
	   

	     // --- LISTING DATABASE TABLE NAMES ---
	     //retrieveTables( conn,  databaseName);
	   

	     // --- LISTING DATABASE COLUMN NAMES ---
	     
	     retrieveColumns(conn,databaseName,"position");
	  
	   }

	private static void retrieveSchemas(Connection conn) throws SQLException {
		  ResultSet resultSet = conn.getMetaData().getCatalogs();

		     while (resultSet.next()) {
		       log.info("Schema Name = " + resultSet.getString("TABLE_CAT"));
		     }
		     resultSet.close();
		
	}

	private static void retrieveTables(Connection conn, String databaseName) throws SQLException {
		  String[] types = { "TABLE" };
		  ResultSet resultSet = conn.getMetaData()
		         .getTables(databaseName, null, "%", types);
		     String tableName = "";
		     while (resultSet.next()) {
		       tableName = resultSet.getString(3);
		       log.info("Table Name = " + tableName);
		       
		     }
		     resultSet.close();
		
	}

	private static void retrieveColumns(Connection conn, String databaseName, String tableName) throws SQLException {
		   DatabaseMetaData meta = conn.getMetaData();
		   ResultSet resultSet = meta.getColumns(databaseName, null, tableName, "%");
		
		     while (resultSet.next()) {
		       log.info("Column Name of table " + tableName + " = "
		           + resultSet.getString(4)+",sql type is "+resultSet.getInt("DATA_TYPE"));
		     }
		
	}
	   
	   

}
