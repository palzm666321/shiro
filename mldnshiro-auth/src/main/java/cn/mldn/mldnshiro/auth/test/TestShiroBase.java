package cn.mldn.mldnshiro.auth.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import junit.framework.TestCase; 



public class TestShiroBase extends TestCase {
	private static final String USERNAME = "admin" ;	// 用户输入的用户名
	private static final String PASSWORD = "hello" ;	// 用户输入的密码
	public void testAuth() { // 进行认证的处理操作测试
		// 1、定义安全管理的控制工厂类，现在的安全管理的信息都是通过配置文件定义出来的
		Factory<org.apache.shiro.mgt.SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2、通过SecuritManagerFactory创建一个SecurityManager对象
		org.apache.shiro.mgt.SecurityManager securityManager = securityManagerFactory.getInstance() ;
		// 3、将安全管理器配置到安全工具处理类之中，以后所有的认证检测由此工具类负责
		SecurityUtils.setSecurityManager(securityManager);
		// 4、如果要进行认证肯定是要针对于某一个用户实现的认证处理，而每一个用户都使用Subject描述；
		Subject subject = SecurityUtils.getSubject() ;
		// 5、随后现在如果要想进行认证处理，还需要将用户输入的认证信息设置到一个Token里面
		AuthenticationToken token = new UsernamePasswordToken(USERNAME,PASSWORD) ;
		// 6、利用Subject进行Token认证处理，Subject之中包含有之前配置的ini文件的全部信息
		// 登录成功之后会自动将用户名保存在自己的SessionManager之中
		try {
		
			subject.login(token); // 登录认证，登录失败抛出异常
			System.err.println("用户认证通过（登录成功），用户名为" + subject.getPrincipal()); // 获取用户名
			
			
			/*
			 * 	检测单个权限
				  if(subject.isPermitted("news:edit")) {
					System.err.println("拥有news:edit权限");
				  }
			*/
				//检测多个权限
			 	boolean[] permitted=subject.isPermitted("news:edit","member:list");
			 	System.err.println(Arrays.toString(permitted));
			 	
			 	System.err.println(subject.hasRole("dept"));
			 	System.err.println(subject.hasRole("dept"));
			 	System.err.println(subject.hasRole("dept"));
			 	System.err.println(subject.hasRole("dept:list"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}