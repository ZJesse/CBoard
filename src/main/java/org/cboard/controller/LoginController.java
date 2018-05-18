package org.cboard.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.cboard.dto.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Resource(name="brUserDetailsService")
    UserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(String u) {

        SecurityContext sc = SecurityContextHolder.getContext();
        String[] vut=null;
        try {
//			根据加密后的字符串，得到用户，时间戳
            String aa = PasswordEncryptUtil.getDESPlusInstance().decrypt(u);
            vut = StringUtils.split(aa, "#");
            if(null==vut || vut.length!=3){
                throw new Exception();
            }

            String uscode = vut[0];
            long t = Long.valueOf(vut[1]);
            String chy = vut[2];
            String[] chyinfo = StringUtils.split(chy, "_");
            long tnow = System.currentTimeMillis();
            //    	看看时间是否已经超过了120分钟。跳转到原系统的登录页
            if(Math.abs(tnow-t) < 120*60*1000){
                Authentication authentication = sc.getAuthentication();
                UserDetails userd = userDetailsService.loadUserByUsername(uscode);

                User user = (User)userd;
                user.setAdmin(Boolean.valueOf(chyinfo[0]));//管理员标识
                user.setChyCode(chyinfo[1]);
                user.setChyPcode(chyinfo[2]);
                UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                        user, authentication.getCredentials(), user.getAuthorities());

                sc.setAuthentication(result);
            }else{
                //    		跳转到原系统登录页
                throw new Exception();
            }
        } catch (Exception e) {
            //本地启动以管理员身份登录
			/*Authentication authentication = sc.getAuthentication();
			UserDetails user = userDetailsService.loadUserByUsername("admin");

			UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
					user, authentication.getCredentials(), user.getAuthorities());

			sc.setAuthentication(result);*/
            //return "login";
            return "redirect:/login2.do";//跳转到LMP系统登录页
        }
        return "starter";



        //return "login";
    }

    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}