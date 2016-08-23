package com.weheros.framework.core.infrastructure.retrieve;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class GenerateHtmlScaffold {

	 private static Logger log = LoggerFactory
		       .getLogger(GenerateHtmlScaffold.class);
	 
	public static void main(String[] args) throws Exception {
		
		/*if(args==null||args.length<1){
			
			System.out.println("参数错误，至少要带上表名称参数！");
			return;
		}*/
		Scanner in = new Scanner(System.in);  // Reading from System.in
		System.out.println("输入表名称（小写）: ");
		//
		String tableName=in.nextLine();
		System.out.println("输入表名称是："+tableName);
	     String databaseName = "crmdb";
	     String userName = "root";
	     String password = "123456";
	     String mySQLPort = "3306";
	     String hostUrl = "127.0.0.1";

	     // Setup the connection with the DB
	     Class.forName("com.mysql.jdbc.Driver");

	     Connection conn = DriverManager.getConnection("jdbc:mysql://" + hostUrl
	         + ":" + mySQLPort, userName, password);
	     
         
	     Set<Column> columns=retrieveColumns(conn,databaseName,tableName);
         
	     //生成edit jsp page
	     generateEditPage(tableName,columns);
	     
	     //生成sql语句
	     generateSqlFile(tableName,columns);
	     
	     //生成Pojo文件和rowmapper部分代码
	     generatePojoRowMapper(tableName,columns);
         
	}
	
	private static void generatePojoRowMapper(String tableName, Set<Column> columns) throws IOException {
		//pojo java file
		
		String pojoname=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
		 StringBuffer content=new StringBuffer();
		   String hang=System.getProperty("line.separator");
		 content.append("public class "+pojoname+" {").append(hang);
		 content.append(hang);
		 
		 StringBuffer maprow=new StringBuffer();//map row内容代码
		 maprow.append("protected "+pojoname+" map"+pojoname+"(ResultSet rs) throws SQLException { ").append(hang);//begin of method
		 maprow.append(" "+pojoname+" "+tableName+" = "+"new "+pojoname+"();").append(hang);
		 //loop
		  Iterator<Column> it=columns.iterator();  
		  while(it.hasNext()){
			 
			  Column column=it.next();
			  String setstring=".set"+column.name.substring(0, 1).toUpperCase()+column.name.substring(1);
			  if(column.type==java.sql.Types.INTEGER){
				  content.append("private Integer "+column.name+";");
				  //	user.setId(rs.getInt("id"));
				
				  maprow.append(tableName+setstring+"(rs.getInt(\""+column.name+"\"));");
				  
			  }else if(column.type==java.sql.Types.VARCHAR){
				  content.append("private String "+column.name+";");
				  //
				  maprow.append(tableName+setstring+"(rs.getString(\""+column.name+"\"));");
				  
			  }else if(column.type==java.sql.Types.DOUBLE||column.type==java.sql.Types.FLOAT){
				  content.append("private Double "+column.name+";");
				  //
				  maprow.append(tableName+setstring+"(rs.getDouble(\""+column.name+"\"));");
			  }else if(column.type==java.sql.Types.DATE){
				  content.append("private Date "+column.name+";");
				  //
				  maprow.append(tableName+setstring+"(rs.getDate(\""+column.name+"\"));");
			  }else if(column.type==java.sql.Types.DECIMAL){
				  content.append("private BigDecimal "+column.name+";");
				  //getBigDecimal
				  maprow.append(tableName+setstring+"(rs.getBigDecimal(\""+column.name+"\"));");
			  }else if(column.type==java.sql.Types.BOOLEAN){
				  content.append("private boolean "+column.name+";");
				  //getBoolean
				  maprow.append(tableName+setstring+"(rs.getBoolean(\""+column.name+"\"));");
			  }
			  
			  else{
				  //其它类型都生成String字段，然后修改
				  content.append("private String "+column.name+";");
				  //
				  maprow.append(tableName+setstring+"(rs.getString(\""+column.name+"\"));");
			  }
			  content.append(hang);
			  maprow.append(hang);
			  
		  }
		//生成map局部方法
		  maprow.append(" return "+tableName+";").append(hang);
		maprow.append("}").append(hang);

		  content.append(maprow).append(hang);
		  content.append("}").append(hang);//整个文件完成
		 
		 //生成文件
		 FileOutputStream outstream=null;
			
	       String file=templatePath(tableName)+"/"+tableName+"/"+pojoname+".java";
	       File outFile=new File(file);
			try {
	        if(!outFile.exists()){        	
	        	outFile.createNewFile();
	        }

				outstream = new FileOutputStream(outFile);
				outstream.write(content.toString().getBytes());
			} 
			finally{
				
				if(outstream!=null){
					outstream.close();
				}
			}
			
			System.out.println("Pojo文件生成结束："+file);
		
		
	}
	//生成文件存放目录，
	private static String templatePath(String tableName){
		  //
		  Path currentRelativePath = Paths.get("");
		  String templatePath = currentRelativePath.toAbsolutePath().toString()+"/scaffold";
		
		
			

	        //生成文件存放路径
	        File path=new File(templatePath+"/"+tableName);
	        if(!path.exists()){
	        	path.mkdirs();
	        }
	        
	        return templatePath;
	}

	private static void generateSqlFile(String tableName, Set<Column> columns) throws IOException {
	  
	   StringBuffer content=new StringBuffer();
	   content.append("insert into "+tableName);
	   content.append(" (");
	   String hang=System.getProperty("line.separator");
	   //写insert sql语句
	   String masks="";
		  int number=0;
		  Iterator<Column> it=columns.iterator();  
		  while(it.hasNext()){
			 
			  Column column=it.next();
			  if(column.name.equals("id")){
				  //避开id字段
				  continue;
			  }
			  if(number!=0){
				  content.append(",");
				  masks=masks+",";
			  }
			  content.append(column.name);
			  masks=masks+"?";
			  number++;
		  }
		  content.append(") values ( ").append(masks).append(" )").append(hang);
	      //
		  //create the file
		  FileOutputStream outstream=null;
		
	       String sql=templatePath(tableName)+"/"+tableName+"/"+tableName+"_sql.txt";
	       File outFile=new File(sql);
			try {
	        if(!outFile.exists()){        	
	        	outFile.createNewFile();
	        }

				outstream = new FileOutputStream(outFile);
				outstream.write(content.toString().getBytes());
			} 
			finally{
				
				if(outstream!=null){
					outstream.close();
				}
			}
			
			System.out.println("sql文件生成结束："+sql);
		
	}

	private static void generateEditPage(String tableName, Set<Column> columns) throws IOException {
	     StringBuffer content=new StringBuffer();
	     String hang=System.getProperty("line.separator");
	     content.append("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>");
	     content.append(hang);
	     content.append("<%@ include file=\"/WEB-INF/jsp/common/taglibs.jsp\" %>");
	     content.append(hang);
	     content.append("<div class=\"bjui-pageContent\">");
	     content.append(hang);
	     //genrate form
	     String formid=tableName+"_edit_form";
	   
	     content.append("<form method=\"post\" action=\"${ctx}/organization/position_saveorupdate.do\" id=\""+formid+"\" data-toggle=\"validate\" data-alertmsg=\"false\">");
	     content.append(hang);
	           content.append("<input type=\"hidden\" name=\"id\" value=\"${"+tableName+".id}\">");
	           content.append(hang);
	           content.append("<input type=\"hidden\" name=\"${_csrf.parameterName}\" value=\"${_csrf.token}\"/>");
               content.append(hang);
               generateTableInForm(content,hang,tableName,columns);                
               content.append(hang);
               
               
	     content.append("</form>");
	     content.append(hang);
	     
	     content.append("</div>");
	     content.append(hang);
	     //button of edit page.
	     
	     content.append("<div class=\"bjui-pageFooter\">");
	     content.append(hang);
	     
	     content.append("<ul>");content.append(hang);
	         content.append(" <li><button type=\"button\" class=\"btn-close\" data-icon=\"close\">取消</button></li>");
	         content.append(hang);
	         content.append("<li><button type=\"submit\" class=\"btn-default\" data-icon=\"save\">保存</button></li>");
	         content.append(hang);
	         content.append("</ul>");content.append(hang);
	         
	     
	     content.append("</div>");
	     content.append(hang);
	     //生成页面文件
	     generateJspFile(content,tableName);
	   
	     
	     
		
	}

	private static void generateJspFile(StringBuffer buffer, String tableName) throws IOException  {

		FileOutputStream outstream=null;
	
       String templatePath=templatePath(tableName);
       File outFile=new File(templatePath+"/"+tableName+"/"+tableName+"_edit.jsp");
		try {
        if(!outFile.exists()){        	
        	outFile.createNewFile();
        }

			outstream = new FileOutputStream(outFile);
			outstream.write(buffer.toString().getBytes());
		} 
		finally{
			
			if(outstream!=null){
				outstream.close();
			}
		}
		
		System.out.println("文件生成结束："+templatePath+"/"+tableName+"/"+tableName+"_edit.jsp");
	
	}

	private static void generateTableInForm(StringBuffer content, String hang, String tableName, Set<Column> columns) {
		content.append(" <table class=\"table table-condensed table-hover\" width=\"100%\">");
		  content.append(hang);
		  content.append("<tbody>");
		  content.append(hang);
		
		  
		  int number=0;
		  Iterator<Column> it=columns.iterator();  
		  while(it.hasNext()){
			  
			 
			  Column column=it.next();
			  if(column.name.equals("id")){
				  //避开id字段
				  continue;
			  }
			  if(number==0||number%2==0){
				  //增加一行
				  content.append("<tr>");  
				  content.append(hang);
			  }
			  content.append("<td>");
			  content.append(hang);
			  //<td>
             // <label for="position_name" class="control-label x85">职位名称：</label>
             // <input type="text" name="name" id="position_name" value="${position.name}" data-rule="required" size="15">
             // </td>
			        String inputid=tableName+"_"+column.name;
			        content.append("<label for=\""+inputid+"\" class=\"control-label x85\">名称：</label>");
			        content.append(hang);
			        //String valueofcolumn=tableName+"."+column.name;
			        String dataRule=column.isNull?"":"data-rule=\"required\"";
			        content.append("<input type=\"text\" name=\"name\" id=\""+inputid+"\" value=\"${"+tableName+"."+column.name+"}\" "+dataRule+" size=\"15\">");
			        content.append(hang);
			   content.append("</td>");
			   content.append(hang);
			   
			   number++;
			   if(number%2==0){
					  //增加一行
					  content.append("</tr>");  
					  content.append(hang);
				  }
		  }
		  if(number%2==1){
				  //循环结束，奇数个字段数量，增加tr结尾
				  content.append("</tr>");  
				  content.append(hang);
		   }     
                  
        
             
            content.append("</tbody>");
            content.append(hang);
       content.append("</table>");
       content.append(hang);
	}

	private static Set<Column> retrieveColumns(Connection conn, String databaseName, String tableName) throws SQLException {
		   DatabaseMetaData meta = conn.getMetaData();
		   ResultSet resultSet = meta.getColumns(databaseName, null, tableName, "%");
		   Set<Column> all=new HashSet<Column>();
		     while (resultSet.next()) {
		     
		       all.add(new Column(resultSet.getInt("DATA_TYPE"),resultSet.getString("COLUMN_NAME"),resultSet.getBoolean("IS_NULLABLE")));
		       System.out.println("Column Name of table " + tableName + " = "
			           + resultSet.getString("COLUMN_NAME")+",sql type is "+resultSet.getInt("DATA_TYPE")+",whether is null "+resultSet.getBoolean("IS_NULLABLE"));
		     }
		return all;
	}

}
class Column{
	int type;
	String name;
	boolean isNull;
	
	public Column(int type, String name,boolean isNull) {
		super();
		this.type = type;
		this.name = name;
		this.isNull=isNull;
	}

	
}
