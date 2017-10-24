package cn.mldn.mldnshiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.mldn.mldnshiro.service.IMemberService;
import cn.mldn.mldnshiro.service.impl.MemberServiceImpl;
import cn.mldn.mldnshiro.vo.Member;

public class MemberRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//此方法主要用户用户的授权处理操作
		System.err.println("===================2、进行用户授权处理操作（doGetAuthorizationInfo()）==============");
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();//返回授权的信息
		String mid=(String) principals.getPrimaryPrincipal();//获得用户名
		IMemberService memberService=new MemberServiceImpl();
		info.setRoles(memberService.listRoleByMember(mid));//将所有的角色信息保存在授权信息中
		info.setStringPermissions(memberService.listActionByMember(mid));//保存所有的权限
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//此方法主要是实现用户的认证处理操作
		System.err.println("==============1、进行用户认证处理操作（doGetAuthenticationInfo()）===============");
		IMemberService memberService=new MemberServiceImpl();//假装获得了一个业务对象
		String mid=(String)token.getPrincipal();//获得用户名的信息
		Member member=memberService.get(mid);//根据用户名插叙处用户的完整信息
		if(member==null) {//用户信息不存在，不存在的信息应该抛出未知的账户异常
			throw new UnknownAccountException("账户"+mid+"不存在");
		}
		String password=new String((char[])token.getCredentials());//获的密码
		if(!member.getPassword().equals(password)) {//用户名或者密码错误
			throw new IncorrectCredentialsException("错误的用户名或者密码！");

		}
		if(member.getLocked().equals(1)) {//用户被锁定了
			throw new LockedAccountException(mid+"账户信息已经被锁定，无法登录！");
		}
		return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),"memberRealm");
	}
}
