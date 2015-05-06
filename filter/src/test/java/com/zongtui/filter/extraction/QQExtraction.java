/**
 * 
 */
package com.zongtui.filter.extraction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ClassName: QQExtraction <br/>
 * Function: QQ信息抽取. <br/>
 * date: 2015年5月6日 下午9:34:22 <br/>
 *
 * @author cloudskyme
 * @version
 * @since JDK 1.7
 */
public class QQExtraction {

	public static void main(String[] args) throws Exception {
		String filePath = "./test/众推(194338168).txt";
		String tt = new String();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(filePath), "UTF-8"));
		String str;
		while ((str = in.readLine()) != null) {
			// tt += str;
			System.out.println(str);

			// QQInfo

		}

		// QQExtraction qc = new QQExtraction();
		// qc.insertQQ();
	}

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/zongtui";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	public void insertQQ() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO qqexct(send_time,send_name,send_numsend_content) values()";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
	}

	
}
