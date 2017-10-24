package cn.mldn.mldnshiro.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import cn.mldn.mldnshiro.service.IMemberService;
import cn.mldn.mldnshiro.vo.Member;

public class MemberServiceImpl implements IMemberService{
	private static final String DRIVER = "org.gjt.mm.mysql.Driver" ;
	private static final String URL = "jdbc:mysql://localhost:3306/mldn" ;
	private static final String USERNAME = "root" ;
	private static final String PASSWORD = "mysqladmin" ;
	private static int count = 0 ;	// 统计次数
	private Connection conn;
	
	 public MemberServiceImpl() {//在构造方法里面进行数据库的连接配置
		 System.out.println("【艰难的打开数据库连接】***** 数据库连接次数：" + (++ count));
		try {
			Class.forName(DRIVER);
			this.conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Member get(String mid) {
		String sql="select mid,name,password,locked from member where mid=?";
		try {
			PreparedStatement ps=this.conn.prepareStatement(sql);
			ps.setString(1, mid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Member vo=new Member();
				vo.setMid(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setLocked(rs.getInt(4));
				return vo;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.close();
		}
		return null;  
	}
	
	public void close() {
		if(this.conn!=null) {
			try {
				this.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Set<String> listRoleByMember(String mid) {
		Set<String> allRoles=new HashSet<String>();
		String sql="select rid from member_role where mid=?";
		try {
			PreparedStatement ps=this.conn.prepareStatement(sql);
			ps.setString(1, mid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				allRoles.add(rs.getString(1));
			}
		}catch(Exception e) {}
		return allRoles;
	}

	@Override
	public Set<String> listActionByMember(String mid) {
		Set<String> allActions=new HashSet<String>();
		String sql="select actid from where rid in (select rid from member_role where mid=?)";
		try{
			PreparedStatement ps=this.conn.prepareStatement(sql);
			ps.setString(1, mid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				allActions.add(rs.getString(1));
			}
		}catch(Exception e) {}
		return allActions;
	}
	
}
