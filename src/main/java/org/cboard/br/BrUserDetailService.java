package org.cboard.br;

import org.cboard.dao.RoleDao;
import org.cboard.dao.UserDao;
import org.cboard.dto.User;
import org.cboard.pojo.DashboardUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class BrUserDetailService implements UserDetailsService {

	private UserDao userDao;
	private RoleDao roleDao;
	@Override
	public UserDetails loadUserByUsername(String username) {
//		DashboardUser duser = userDao.getUserByCode(username);
		DashboardUser duser = userDao.getUserByLoginName(username);
		if (null == duser) {
			throw new RuntimeException("没有找到要登录的用户！");
		}
		User user = new User(duser.getLoginName(), duser.getUserPassword(), true, true, true, true,
				AuthorityUtils.NO_AUTHORITIES);
		user.setUserId(duser.getUserId());
		user.setName(duser.getUserName());

//		加载用户权限(不许用加载权限)
//		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>();
//		SimpleGrantedAuthority s1 = new SimpleGrantedAuthority("普通用户");
//		dbAuths.add(s1);
////		addUserAuthorities(user.getUsername(), dbAuths);
//
//		if (dbAuths.size() == 0) {
//			throw new UsernameNotFoundException("没有给用户授权！");
//		}
//		返回用户详细信息
		return user;

	}

	
	private void addUserAuthorities(String username, List<GrantedAuthority> dbAuths) {
		
	}


	/**
	 * @param username
	 * @return
	 * 从数据库中加载用户
	 */
	private List<UserDetails> loadUsersByUsername(String username) {
//		return getJdbcTemplate().query("SELECT user_id,user_name,login_name, user_password, 1 AS enabled"+
//                          " FROM dashboard_user_v"+
//                         " WHERE login_name = ?",
//			new String[] { username }, new RowMapper<UserDetails>() {
//				public UserDetails mapRow(ResultSet rs, int rowNum)
//						throws SQLException {
//					String username = rs.getString(1);
//					String password = rs.getString(2);
//					boolean enabled = rs.getBoolean(3);
//					return new User(username, password, enabled, true, true, true,
//							AuthorityUtils.NO_AUTHORITIES);
//				}
//			});
		return null;
	}


	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public RoleDao getRoleDao() {
		return roleDao;
	}


	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
