package cn.mldn.mldnspring.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.Realm;

/**
 * 自定义的Realm处理操作实现，主要提供认证的数据信息
 * @author lenovo
 *
 */
public class MySelfDefaultRealm implements Realm{

	@Override
	public String getName() {//名字随意
		return "shiro-default-realm";//名字是随意编写的，主要是区分不同的realm
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;//现在如果使用的是简单的用户名和密码认证
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String mid=(String)token.getPrincipal();//获取输入用户名
		String password=new String((char[])token.getCredentials());//获取输入的密码
		//此时是对固定的信息进行认证处理，所以现在定义的用户名为admin、hello
		if(!"admin".equals(mid)) {//用户名不存在
			throw new UnauthenticatedException("此账号不存在，请确认用户名！");
		}
		if(!"hello".equals(password)) {//密码不正确
			throw new IncorrectCredentialsException("错误的用户名或密码");
		}
		return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),this.getName());
	}

}
