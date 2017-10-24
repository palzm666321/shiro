package cn.mldn.mldnshiro.service;

import java.util.Set;

import cn.mldn.mldnshiro.vo.Member;

public interface IMemberService {
	
	/**
	 * 根据用户编号获取一个用户的信息，用户身份存在、锁定状态、密码验证都要交给Realm完成
	 * @param mid 用户的ID
	 * @return 如果用户信息存在则以vo的形式返回如果不存在返回null
	 */
	public Member get(String mid);
	/**
	 * 根据用户的编号插叙处该用户对应的所有的角色信息，插叙的是member_role对应表
	 * @param mid 用户编号
	 * @return 所有的角色信息
	 */
	public Set<String> listRoleByMember(String mid);
	
	/**
	 * 根据用户编号查询出对应的所有权限信息
	 * @param mid 用户编号
	 * @return 所有的权限信息
	 */
	public Set<String> listActionByMember(String mid);
}
