package com.mj.sso.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mj.sso.core.conf.Conf;
import com.mj.sso.core.login.SsoTokenLoginHelper;
import com.mj.sso.core.login.SsoWebLoginHelper;
import com.mj.sso.core.store.SsoLoginStore;
import com.mj.sso.core.user.XxlSsoUser;
import com.mj.sso.core.store.SsoSessionIdHelper;
import com.mj.sso.core.util.CookieUtil;
import com.mj.sso.core.util.StringUtils;
import com.mj.sso.server.config.WxConfig;
import com.mj.sso.server.core.model.*;
import com.mj.sso.server.core.result.ReturnT;
import com.mj.sso.server.service.CmsAttachmentService;
import com.mj.sso.server.service.SysSsoService;
import com.mj.sso.server.service.SysUserService;
import com.mj.sso.server.service.UserService;
import com.mj.sso.server.util.AesEncryptUtil;
import com.mj.sso.server.util.HttpClientUtils;
import com.mj.sso.server.util.StringUtil;
import com.sun.xml.internal.ws.api.message.Attachment;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * sso server (for web)
 *
 * @author xuxueli 2017-08-01 21:39:47
 */
@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysSsoService sysSsoService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {

        // login check
        XxlSsoUser xxlUser = SsoWebLoginHelper.loginCheck(request, response);

        if (xxlUser == null) {
            return "redirect:/login";
        } else {
            Integer userId = Integer.parseInt(xxlUser.getUserid());
            model.addAttribute("xxlUser", xxlUser);
            model.addAttribute("wx_appid", WxConfig.APP_ID);
            model.addAttribute("sm_redirecturi", WxConfig.SM_REDIRECTURI);
            SysUser user = sysUserService.findById(userId);
            if(StringUtils.hasText(user.getOpenid())){
                model.addAttribute("openid_flag", 1);
            }else{
                model.addAttribute("openid_flag", 0);
            }
            //
            SysSso sysSso = new SysSso();
            sysSso.setStatus(1);
            List<SysSso> sysSsoList = sysSsoService.findSysSsoList(sysSso,userId);
            model.addAttribute("sysSsoList",sysSsoList);
            return "index";
        }
    }

    @RequestMapping(value = "/bindWeiXin",name = "进入微信登录方法")
    public String bindWeiXin(Model model,@RequestParam("code") String code, @RequestParam("state") String state,
                              HttpServletRequest request,HttpServletResponse response) {

        String base64Str = state.replaceAll(" ","+");
        byte[] decode = Base64Utils.decodeFromString(base64Str);
        String descUid = new String(decode);
        XxlSsoUser xxlUser = SsoLoginStore.get(descUid);
        if(xxlUser == null){
            return "redirect:/login";
        }
        model.addAttribute("xxlUser", xxlUser);
        model.addAttribute("wx_appid", WxConfig.APP_ID);
        model.addAttribute("sm_redirecturi", WxConfig.SM_REDIRECTURI);
        //1.根据code获取access_token和openId
        String atUtl = "https://api.weixin.qq.com/sns/oauth2/access_token" + "?code=" + code + "&appid="+WxConfig.APP_ID+"&secret="+WxConfig.SECRET+"&grant_type=authorization_code";  //微信请求地址标准格式
        //向微信发送请求获取access_token与openid
        Map<String, Object> map1 = HttpClientUtils.sendGet(atUtl);
        //获取用户access_token
        Object access_token = map1.get("access_token");
        //获取用户openid
        Object openid = map1.get("openid").toString();
        if (access_token == null && openid == null) {
            return "index";
        }
        //2.根据access_token和openId获取微信用户信息
        /*可通过获取用户基本信息中的unionid来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号，用户的unionid是唯一的。换句话说，同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。 */
        String wxurl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid; //获取用户信息微信请求连接标准格式
        //map2集合中拥有用户所有信息，此次获取用户在微信的唯一标示unionid
        Map<String, Object> map2 = HttpClientUtils.sendGet(wxurl);
        Object unionid = map2.get("unionid");
        if(unionid == null){
            return "index";
        }
        // valid login
        SysUser sysUser = new SysUser();
        sysUser.setUserId(Long.parseLong(descUid));
        sysUser.setOpenid(String.valueOf(openid));
        sysUser.setUnionid(String.valueOf(unionid));
        sysUserService.updateSysUser(sysUser);//String.valueOf(openid), String.valueOf(unionid)
        model.addAttribute("openid_flag", 1);
        return "index";
    }

    @RequestMapping(value = "/weixinlogin",name = "进入微信登录方法")
    public String weixinlogin(Model model,@RequestParam("code") String code, @RequestParam("state") String state,
                              HttpServletRequest request,HttpServletResponse response) {
        //System.out.println(code);  查看是获取到code
        //1.根据code获取access_token和openId
        String atUtl = "https://api.weixin.qq.com/sns/oauth2/access_token" + "?code=" + code + "&appid="+WxConfig.APP_ID+"&secret="+WxConfig.SECRET+"&grant_type=authorization_code";  //微信请求地址标准格式
        //向微信发送请求获取access_token与openid
        Map<String, Object> map1 = HttpClientUtils.sendGet(atUtl);
        //获取用户access_token
        Object access_token = map1.get("access_token");
        //获取用户openid
        Object openid = map1.get("openid").toString();
        if (access_token == null && openid == null) {
            model.addAttribute("exceptionMsg","获取用户openid失败");
            return "common/common_exce";
        }
        //2.根据access_token和openId获取微信用户信息
        /*可通过获取用户基本信息中的unionid来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号，用户的unionid是唯一的。换句话说，同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。 */
        String wxurl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid; //获取用户信息微信请求连接标准格式
        //map2集合中拥有用户所有信息，此次获取用户在微信的唯一标示unionid
        Map<String, Object> map2 = HttpClientUtils.sendGet(wxurl);
        Object unionid = map2.get("unionid");
        if(unionid == null){
            model.addAttribute("exceptionMsg","获取用户unionid失败");
            return "common/common_exce";
        }
        // valid login
        ReturnT<SysUser> result = sysUserService.findUserByWX(String.valueOf(openid), String.valueOf(unionid));
        String redirectUrl = state.substring(33, state.length());
        if (result.getCode() != ReturnT.SUCCESS_CODE) {
            RedirectAttributesModelMap redirectAttributesModelMap = new RedirectAttributesModelMap();
            redirectAttributesModelMap.addAttribute("errorMsg", result.getMsg());
            redirectAttributesModelMap.addAttribute(Conf.REDIRECT_URL, redirectUrl);
            return "redirect:/login";
        }
        // 1、make mj.sso user
        SysUser sysUser = result.getData();
        XxlSsoUser xxlUser = new XxlSsoUser();
        xxlUser.setUserid(String.valueOf(sysUser.getUserId()));
        xxlUser.setUsername(sysUser.getLoginName());
        xxlUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        xxlUser.setExpireMinute(SsoLoginStore.getRedisExpireMinute());
        xxlUser.setExpireFreshTime(System.currentTimeMillis());
        xxlUser.setFromSys(sysUser.getFromSys());
        // 2、make session id
        String sessionId = SsoSessionIdHelper.makeSessionId(xxlUser);

        // 3、login, store storeKey + cookie sessionId
        SsoWebLoginHelper.login(response, sessionId, xxlUser, false);

        // 4、return, redirect sessionId

        if (redirectUrl==null || redirectUrl.trim().length()==0) {
            return "redirect:/";
        }else{
            String redirectUrlFinal = redirectUrl + "?" + Conf.SSO_SESSIONID + "=" + sessionId;
            return "redirect:" + redirectUrlFinal;
        }
    }

    /**
     * Login page
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(Conf.SSO_LOGIN)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {

        // login check
        XxlSsoUser xxlUser = SsoWebLoginHelper.loginCheck(request, response);

        if (xxlUser != null) {

            // success redirect
            String redirectUrl = request.getParameter(Conf.REDIRECT_URL);
            if (redirectUrl!=null && redirectUrl.trim().length()>0) {

                String sessionId = SsoWebLoginHelper.getSessionIdByCookie(request);
                String redirectUrlFinal = redirectUrl + "?" + Conf.SSO_SESSIONID + "=" + sessionId;;

                return "redirect:" + redirectUrlFinal;
            } else {
                return "redirect:/";
            }
        }

        model.addAttribute("errorMsg", request.getParameter("errorMsg"));
        model.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
        model.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
        model.addAttribute(Conf.WX_REDIRECTURI, WxConfig.REDIRECT_URI);
        return "login";
    }

    /**
     * Login
     *
     * @param request
     * @param redirectAttributes
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request,
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes,
                        String username,
                        String password,
                        String ifRemember) {

        boolean ifRem = (ifRemember!=null&&"on".equals(ifRemember))?true:false;

        // valid login
        ReturnT<SysUser> result = sysUserService.findUserByUsername(username);
        if (result.getCode() != ReturnT.SUCCESS_CODE) {
            redirectAttributes.addAttribute("errorMsg", result.getMsg());
            redirectAttributes.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
            return "redirect:/login";
        }
        SysUser sysUser = result.getData();
        String md5Pwd = new Md5Hash(username + password +sysUser.getSalt()).toHex();
        if(!result.getData().getPassword().equals(md5Pwd)){
            log.info("账号或者密码错误");
            redirectAttributes.addAttribute("errorMsg", "账号或者密码错误");
            redirectAttributes.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
            return "redirect:/login";
        }
        // 1、make mj.sso user
        XxlSsoUser xxlUser = new XxlSsoUser();
        xxlUser.setUserid(String.valueOf(sysUser.getUserId()));
        xxlUser.setUsername(sysUser.getLoginName());
        xxlUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        xxlUser.setExpireMinute(SsoLoginStore.getRedisExpireMinute());
        xxlUser.setExpireFreshTime(System.currentTimeMillis());
        xxlUser.setFromSys(sysUser.getFromSys());
        // 2、make session id
        String sessionId = SsoSessionIdHelper.makeSessionId(xxlUser);

        // 3、login, store storeKey + cookie sessionId
        SsoWebLoginHelper.login(response, sessionId, xxlUser, ifRem);

        // 4、return, redirect sessionId
        String redirectUrl = request.getParameter(Conf.REDIRECT_URL);
        if (redirectUrl!=null && redirectUrl.trim().length()>0) {
            String redirectUrlFinal = redirectUrl + "?" + Conf.SSO_SESSIONID + "=" + sessionId;
            return "redirect:" + redirectUrlFinal;
        } else {
            return "redirect:/";
        }

    }

    /**
     * Logout
     *
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(Conf.SSO_LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        // logout
        SsoWebLoginHelper.logout(request, response);

        redirectAttributes.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
        return "redirect:/login";
    }


}
