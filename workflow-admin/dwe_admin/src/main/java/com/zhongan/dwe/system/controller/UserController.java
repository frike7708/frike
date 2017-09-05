package com.zhongan.dwe.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhongan.dsf.consumer.annotation.Consumer;
import com.zhongan.dwe.common.page.PageBT;
import com.zhongan.dwe.dao.dto.ActIdGroupDTO;
import com.zhongan.dwe.dao.dto.ActIdUserDTO;
import com.zhongan.dwe.dao.service.ActIdGroupService;
import com.zhongan.dwe.dao.service.ActIdUserService;
import com.zhongan.dwe.dao.service.GroupAndUsersService;
import com.zhongan.dwe.model.Response;
import com.zhongan.dwe.system.model.GroupDTO;
import com.zhongan.dwe.system.model.UserDTO;
import com.zhongan.dwe.system.service.IGroupService;
import com.zhongan.dwe.system.service.IUserService;

@RestController  
@RequestMapping(value = "/user")
public class UserController {
	
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	protected Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Consumer(connectTimeoutMillis=20000)
	private ActIdGroupService groupService;
	
	@Consumer(connectTimeoutMillis=20000)
	private GroupAndUsersService groupUserService;
	
	@Consumer(connectTimeoutMillis=20000)
	private ActIdUserService userService;
	
	@Autowired
	private IGroupService igroupService;
	
	@Autowired
	private IUserService iuserService;
	
	@Autowired
	private IdentityService identityService;
	
	/** 
     * 组列表
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/group/list",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object selectGroupList(@RequestBody PageBT pageBt) throws UnsupportedEncodingException {
    	int page = pageBt.getPage();//当前页
    	int limit = pageBt.getLimit();//条数
    	int start = (page == 1)? 0 :((page -1) *limit);
    	//查询列表
    	int total = groupService.selectGroupList().size();
    	List<ActIdGroupDTO> selectGroupList = groupService.selectGroupList(start, limit);
    	logger.info("查询用户组列表 == " + selectGroupList.size());
    	JSONArray json = new JSONArray();
    	for (ActIdGroupDTO actIdGroupDTO : selectGroupList) {
    		JSONObject jo = new JSONObject();
    		String groupId = actIdGroupDTO.getId();
    		List<Map<String, Object>> selectGroupUserList = groupUserService.selectGroupUserList(groupId);
    		String users = listToString(selectGroupUserList);
            jo.put("id", actIdGroupDTO.getId());
            jo.put("name", actIdGroupDTO.getName());
            jo.put("users", users);
            json.add(jo);
		}
    	JSONObject result = new JSONObject();
    	result.put("total", total);
    	result.put("list", json);
    	return result;
    }
    
    private String listToString(List<Map<String, Object>> list){
    	StringBuilder builder = new StringBuilder();
    	for (Map<String, Object> map : list) {
    		if(builder.toString().length() >= 1){
    			builder.append(",");
    		}
    		builder.append(map.get("userId"));
		}
    	return builder.toString();
    }
    
    
    /** 
     * 当前组用户
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/group/currentGroupUsers",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object currentGroupUsers(@RequestParam String id) throws UnsupportedEncodingException {
		List<Map<String, Object>> selectGroupUserList = groupUserService.selectGroupUserList(id);
    	JSONArray json = new JSONArray();
    	for (Map<String, Object> map : selectGroupUserList) {
    		JSONObject jo = new JSONObject();
    		Object UserId = map.get("userId");
            jo.put("id", UserId);
            jo.put("title", UserId);
            json.add(jo);
		}
    	JSONObject result = new JSONObject();
    	result.put("list", json);
    	return result;
    }
    
    
    /** 
     * 非当前组用户
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/group/otherGroupUsers",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object otherGroupUsers(@RequestParam String id) throws UnsupportedEncodingException {
		List<Map<String, Object>> selectGroupUserList = groupUserService.otherGroupUserList(id);
    	JSONArray json = new JSONArray();
    	for (Map<String, Object> map : selectGroupUserList) {
    		JSONObject jo = new JSONObject();
    		Object UserId = map.get("userId");
            jo.put("id", UserId);
            jo.put("title", UserId);
            json.add(jo);
		}
    	JSONObject result = new JSONObject();
    	result.put("list", json);
    	return result;
    }
    
    /** 
     * 选择用户列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectUserList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getUserList(){
    	List<ActIdUserDTO> selectUserList = userService.selectUserList();
    	JSONArray json = new JSONArray();
    	for (ActIdUserDTO actIdUserDTO : selectUserList) {
    		JSONObject jo = new JSONObject();
    		String id = actIdUserDTO.getId();
            jo.put("value", id);
            jo.put("label", id);
            json.add(jo);
		}
    	JSONObject result = new JSONObject();
    	result.put("list", json);
    	return result;
    }
    
    
    
    /** 
     * 组用户列表
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/group/add",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object addGroup(@RequestBody GroupDTO dto) throws UnsupportedEncodingException {
    	Response response = new Response();
    	try{
    		igroupService.insertGroupAndMembership(dto);
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("添加失败");
    	}
    	return response.toString();
    }
    
    @RequestMapping(value = "/group/delete",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object delete(@RequestParam String id){
    	Response response = new Response();
    	try{
    		igroupService.delete(id);
    	}catch(NullPointerException e){
    		response.setCode(-1);
    		response.setMsg("请勿重复操作");
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("删除失败");
    	}
    	return response.toString();
    }
    
    /** 
     * 向用户组中添加用户
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/group/addUserToGroup",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object addUserForGroup(@RequestBody GroupDTO dto) throws UnsupportedEncodingException {
    	Response response = new Response();
    	try{
    		igroupService.addGroupUsers(dto);
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("添加失败");
    	}
    	return response.toString();
    }
    
    
    /** 
     * 修改用户组名称
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/group/editGroupName",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object editGroupName(@RequestBody GroupDTO dto) throws UnsupportedEncodingException {
    	Response response = new Response();
    	try{
    		igroupService.updateGroupName(dto);
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("修改失败");
    	}
    	return response.toString();
    }
    
    @RequestMapping(value = "/list",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getUserList(@RequestBody PageBT pageBt) throws UnsupportedEncodingException {
    	int page = pageBt.getPage();//当前页
    	int limit = pageBt.getLimit();//条数
    	int start = (page == 1)? 0 :((page -1) *limit);
    	//查询列表
    	int total = userService.selectUserList().size();
    	List<ActIdUserDTO> selectUserList = userService.selectUserList(start, limit);
    	logger.info("查询用户列表 == " + selectUserList.size());
    	JSONArray json = new JSONArray();
    	for (ActIdUserDTO actIdUserDTO : selectUserList) {
    		JSONObject jo = new JSONObject();
    		jo.put("id", actIdUserDTO.getId());
    		jo.put("firstName", actIdUserDTO.getFirst());
    		jo.put("lastName", actIdUserDTO.getLast());
    		jo.put("email", actIdUserDTO.getEmail());
            json.add(jo);
		}
    	JSONObject result = new JSONObject();
    	result.put("total", total);
    	result.put("list", json);
    	return result;
    }
    
    
    @RequestMapping(value = "/add",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object addUser(@RequestBody UserDTO dto) throws UnsupportedEncodingException {
    	Response response = new Response();
    	try{
    		iuserService.add(dto);
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("添加失败");
    	}
    	return response.toString();
    }
    
    @RequestMapping(value = "/delete",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object deleteUser(@RequestParam String id){
    	Response response = new Response();
    	try{
    		userService.delete(id);
    	}catch(NullPointerException e){
    		response.setCode(-1);
    		response.setMsg("请勿重复操作");
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("删除失败");
    	}
    	return response.toString();
    }
    
    @RequestMapping(value = "/editUserInfo",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object editUserInfo(@RequestBody UserDTO dto) throws UnsupportedEncodingException {
    	Response response = new Response();
    	try{
    		iuserService.update(dto);
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("修改失败");
    	}
    	return response.toString();
    }
    
    @RequestMapping(value = "/login2",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object requestLogin(@RequestBody UserDTO dto,HttpSession session) throws UnsupportedEncodingException {
    	Response response = new Response();
    	String userName = dto.getId();
    	String password = dto.getPwd();
    	logger.debug("logon request: {username={}, password={}}", userName, password);
        boolean checkPassword = identityService.checkPassword(userName, password);
        if (checkPassword) {
        	//用户登录  -- 
            User user = identityService.createUserQuery().userId(userName).singleResult();
            List<Group> groupList = identityService.createGroupQuery().groupMember(userName).list();
            session.setAttribute("groups", groupList);
            String[] groupNames = new String[groupList.size()];
            for (int i = 0; i < groupNames.length; i++) {
                logger.info(groupList.get(i).getName());
                groupNames[i] = groupList.get(i).getName();
            }
            session.setAttribute("groupNames", ArrayUtils.toString(groupNames));
            session.setAttribute("login_name", userName);
            response.setResult(userName);
        } else {
        	response.setCode(-1);
    		response.setMsg("密码或者账号不正确");
        }
        return response.toString();
    }
    
}
