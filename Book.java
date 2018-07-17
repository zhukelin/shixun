package oop.JDBC;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Book {
		    private Connection getConnection(){
		    	try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String dbURl="jdbc:mysql://localhost:3306/bookSystem?useSSL=false&serverTimezone=UTC";
					try {
						Connection connection=DriverManager.getConnection(dbURl, "root", "SQLMM0724");
						return connection;

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					return null;
		    }
		    private void close(Connection cont,Statement stat,ResultSet result){
		    
					try {
						if(result!=null){
						result.close();
					} 
						if(stat!=null){
							stat.close();
						}
						if(cont!=null){
							cont.close();
						}
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
					}
		    private void testInsertDate(String name,String publishers,String author){
//		    	1.创建数据库连接
		    	Connection connection=null;
		    	Statement statement=null;
		    	ResultSet resultSet=null;
		    	try {
							connection=getConnection();
				
		    	
//		    	2.构建添加数据的SQL语句
		    	String sql="insert into tbook (book_name,book_publishers,book_author)"+" values ('"+name+"','"+publishers+"','"+author+"')";
		    	
//		    	3.执行SQL语句.
		    	statement =connection.createStatement();
		    	
//		    	4.得到执行后的结果，确定是否添加成功
		    	int rows=statement.executeUpdate(sql);
		    	if(rows>=1){
		    	System.out.println("添加成功!"); 
		    	}  	
				} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
		    	finally{
		    		this.close(connection,statement,resultSet);
				}
				
			}

		    private void testDeleteDate(int id){
		    	Connection connection=null;
		    	Statement statement=null;
		    	ResultSet resultSet=null;
		    	
//		    	1.创建数据库连接
		    	try {
						Connection connection1=DriverManager.getConnection("jdbc:mysql//localhost:3306/class11?serverTimezone=GMT%2B8&amp;useSSL=false","root","SQLMM0724");
						connection1=getConnection();
//						2.构建删除数据的sql语句
						String sql="delete from tbook where id="+id;					
//				    	3.执行语句
						statement=connection1.createStatement();
						
//				    	4.得到执行结果，确定添加成功
						int rows=statement.executeUpdate(sql);
						if(rows>=1){
					    	System.out.println("删除成功!"); 
					    	}  	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						this.close(connection,statement,resultSet);
					}
		    	
	
		    }
		    private void testUpdateDate(int id,String book_name,String book_publishers,String book_author){
//		    	1.创建数据库连接
		    	Connection connection=null;
		    	Statement statement=null;
		    	ResultSet resultSet=null;
		    	try {
						connection=getConnection();
						//			    	2.创建update sql语句
						String sql="update tbook set book_name='"+book_name+"',book_publishers='"+book_publishers+"',book_author="+book_author+" where id=" + id;
						//			    	3.执行语句
						statement=connection.createStatement();
//				    	4查看结果，是否成功
						int rows=statement.executeUpdate(sql);
						if(rows>=1){
					    	System.out.println("成功!"); 
					    	}  	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	finally{
		    			this.close(connection,statement,resultSet);
					}
	    }
		    private String[][] bestFindAllDate(){
		    	//声明二维数组
		    	String[][] datas=new String[100][3];
		    	Connection connection=null;
		    	Statement statement=null;
		    	ResultSet resultSet=null;
//		    	1.获取数据库连接
		    	connection=getConnection();
		    	
//		    	2.构建sql语句
		    	String sql="select * from tbook";
		    
//		    	3.执行sql语句
				try {
					statement=connection.createStatement();
					resultSet =statement.executeQuery(sql);
//			    	4.结果
					int index=0;
					while(resultSet.next()){
						int id=resultSet.getInt("id");
						String book_name=resultSet.getString("book_name");
						String book_publishers =resultSet.getString("book_publishers");
						String book_author=resultSet.getString("book_author");
						datas[index][0]=String.valueOf(id);
						datas[index][1]=book_name;
						datas[index][2]=book_publishers;
						datas[index][3]=book_author;
						index ++;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					this.close(connection,statement,resultSet);
				}
		    	return datas;
		    }
		    private void showfindAllDatasOutput(){
		    	String[][] datas=bestFindAllDate();
//		    	结果
		    	StringBuffer buff= new StringBuffer();
				buff.append("================================================"+System.lineSeparator());
				buff.append("          id          |        name        |         password     "+System.lineSeparator());	
				buff.append("================================================"+System.lineSeparator());
				for(int i=0;i<datas.length;i++){
					String values[]=datas[i];
					if(values[0]!=null){
						buff.append(String.format("          %s          |       %s        |         %s       |        %s     ",values[0],values[1],values[2],values[3]));
						buff.append(System.lineSeparator());
					}
				}
				System.out.println(buff.toString());
		    }
		    
		    private String[] findAccountDataById(int id){
		    	String[] datas= new String[100];
		    	Connection connection=null;
		    	Statement statement=null;
		    	ResultSet resultSet=null;
//		    	1.建立数据库连接
		    	connection=getConnection();
//		    	2.构建sql语句
		    	String sql="select * from tbook where id="+id;
//		    	3.执行语句
		    	try {
					statement=connection.createStatement();
					resultSet =statement.executeQuery(sql);
//				4.遍历结果
					int index=0;
					while(resultSet.next()){
						int id1=resultSet.getInt("id");
						String book_name=resultSet.getString("book_name");
						String book_publishers =resultSet.getString("book_publishers");
						String book_author=resultSet.getString("book_author");
						datas[index]="\t  "+String.valueOf(id1)+"\t\t|\t"+book_name+"\t|\t  "+book_publishers+"\t|\t  "+book_author+"\t";
						index ++;
							}
					
					}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					this.close(connection,statement,resultSet);
				}
		    	return datas;
		    }
		    private void findAccountDataByIdFmatOutput(int id){
		    		
			    	String[] datas=findAccountDataById(id);
//			    	遍历结果
			    	StringBuffer buff= new StringBuffer();
					buff.append("=================================================================="+System.lineSeparator());
					buff.append("          id          |        name        |         password     "+System.lineSeparator());	
					buff.append("=================================================================="+System.lineSeparator());
					for(int i=0;i<datas.length;i++){
						String[] values=datas;
						if(values[i]!=null){
							buff.append(String.format("      %s     ",values[i]));
							buff.append(System.lineSeparator());
						}
					}
					System.out.println(buff.toString());
			    }
		    	
		    private String[][] findAccountDateLikeKeyWord(String KeyWord){
		    	String[][] datas=new String[100][3];
		    	Connection connection=null;
		    	Statement statement=null;
		    	ResultSet resultSet=null;
//		    	1.创建数据库连接
		    	connection=getConnection();
//		    	2.构建sql语句
		    	String sql="select * from tbook where book_name like '%"+KeyWord+"%' or book_publishers like '%"+KeyWord+""
		    			+ "' or id like '%"+KeyWord+"%'"+" or book_author like '%"+KeyWord+"%'";
//		    	3.执行SQL语句
		    	
				try {
					statement = connection.createStatement();
					resultSet =statement.executeQuery(sql);
//				4.遍历结果
					int index=0;
					while(resultSet.next()){
						int id=resultSet.getInt("id");
						String book_name=resultSet.getString("book_name");
						String book_publishers =resultSet.getString("book_publishers");
						String book_author=resultSet.getString("book_author");
						datas[index][0]=String.valueOf(id);
						datas[index][1]=book_name;
						datas[index][2]=book_publishers;
						datas[index][3]=book_author;
						index++;
							}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					this.close(connection,statement,resultSet);
				}
				return datas;
		    }
		    private void findDatasLikeKeyWordFmatOutput(String KeyWord){
		    	String[][] datas=findAccountDateLikeKeyWord(KeyWord);
		    	StringBuffer buffer= new StringBuffer();
		    	buffer.append("=================================================================="+System.lineSeparator());
				buffer.append("          id          |        name        |         password     "+System.lineSeparator());	
				buffer.append("=================================================================="+System.lineSeparator());
				
				for(int i=0;i<datas.length;i++){
					String[] values=datas[i];
					if(values[0]!=null&&values[1]!=null&values[2]!=null){
						buffer.append(String.format("          %s          |       %s        |         %s       |        %s     ",values[0],values[1],values[2],values[3]));
						buffer.append(System.lineSeparator());
					}
					
				}
		    	System.out.println(buffer.toString());
		    	
		    }	    
		public  static  void main(String[] args){
			Book book=new Book();
			while(true){
		      Scanner scanner=new Scanner(System.in);
		      System.out.println("===========================欢迎进入班级管理系统====================================");
		      System.out.println("+---------------------------------------------------------------------------+");
		      System.out.println("|       1.添加数据                 2.修改数据                 3.删除数据                  4.查询数据                        6.退出                 |");
		      System.out.println("+---------------------------------------------------------------------------+");
		      System.out.println("===============================请输入操作=======================================");
		      int select;
		      int reselect;
		      select= scanner.nextInt();
		      while((select>6||select<1)){
		    	  System.out.println("操作有误，请重新输入操作：");
		    	  select=scanner.nextInt();
		      }
		      String value=null;
		      
		      if(select == 1){//添加
		    	  System.out.println("请输入要添加的书名，出版社以及作者，用英文逗号分隔开，举例：《书名》,XXX出版社,作者");
		    	  value=scanner.next(); 
			      String[] values=value.split(",");
			      book.testInsertDate(values[0],values[1],values[2]);
		      }
		      else if(select==2){//修改
		    	  System.out.println("请输入要修改的记录id：");
		    	  value=scanner.next();
		    	  String[] values=value.split(",");
		    	  book.testUpdateDate(Integer.parseInt((values[0])),values[1],values[2],values[3]);
		    	  
		      }
		      else if(select == 3){//删除
		    	  int id=0;
		    	  System.out.println("请输入要删除的记录id：");
		    	  id=scanner.nextInt();
		    	  book.testDeleteDate(id);
		      }
		      else if(select==4){
		    	  
		    	  System.out.println("请输入查询方式：1.查询所有 2.按id查询 3.按关键字查询");
		    	  reselect=scanner.nextInt();
		    	  if(reselect==1){
		    		  System.out.println("====================查询结果如下=====================");
		    		  book.showfindAllDatasOutput();
		    	  }
		    	  else if(reselect==2){
		    		  System.out.println("请输入要查询的id：");
		    		  value=scanner.next();
		    		  System.out.println("====================查询结果如下=====================");
		    		  book.findAccountDataByIdFmatOutput(Integer.parseInt(value));
		    	  }
		    	  else if(reselect==3){
		    		  System.out.println("请输入关要查询的键字：");
		    		  value=scanner.next();
			    	  System.out.println("====================查询结果如下=====================");
		    		  book.findDatasLikeKeyWordFmatOutput(value);
		    		  
		    	  }
		      }
		      if(select==5){//测试通道
		    	  System.out.println("当前暂无测试项目！");
		    	  System.out.println("请输入任意字符继续！");
		    	  value=scanner.next();
		    	 
		    	  
		      }
			 if(select== 6){
				 System.out.println("老板慢走，下次再来~");
				 System.exit(-1);
			 }

			}
		}
		
		}

