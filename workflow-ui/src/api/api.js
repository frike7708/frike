import axios from 'axios';

let base =  global.HOST;

export const deployDelete = params => { return axios.get(`${base}/process/deployed/delete?deploymentId=` + params.id);};

export const deployModel = params => { return axios.get(`${base}/process/model/deploy?id=` + params.id);};

export const exportModel = params => { return axios.get(`${base}/process/model/export?id=` + params.id);};

export const removeModel = params => { return axios.get(`${base}/process/model/delete?id=` + params.id);};

//查询已部署流程列表
export const getProcessList = params => {return axios.post(`${base}/process/deployed/selectProcessList`,params );};

//查询模型列表
export const getModelList = params => { return axios.post(`${base}/process/model/list`,params );};

//查询运行中流程（监控）
export const getProcessMonitorList = params => { return axios.post(`${base}/process/monitor/selectActivitiProcessList`,params );};

//查询表单列表
export const getFormList = params => { return axios.post(`${base}/form/list`,params );};

//删除表单
export const removeForm = params => { return axios.get(`${base}/form/delete?id=` + params.id);};

//创建表单
export const editForm = params => { return axios.get(`${base}/form/edit?code=` + params.code);};

//查询用户组列表
export const getUserGroupList = params => { return axios.post(`${base}/user/group/list  `,params );};

//当前组用户
export const currentGroupUsers = params => { return axios.get(`${base}/user/group/currentGroupUsers?id=` + params.id);};

//非当前组用户
export const otherGroupUsers = params => { return axios.get(`${base}/user/group/otherGroupUsers?id=` + params.id);};

//用户列表
export const selectUser = params => { return axios.get(`${base}/user/selectUserList`);};

//添加用户组
export const addGroup = params => { return axios.post(`${base}/user/group/add`,params);};

//删除用户组
export const removeGroup = params => { return axios.get(`${base}/user/group/delete?id=` + params.id);};

//用户组添加成员
export const addUserToGroup = params => { return axios.post(`${base}/user/group/addUserToGroup`,params);};

//编辑用户组名称
export const editGroupName = params => { return axios.post(`${base}/user/group/editGroupName`,params);};

//查询用户列表
export const getUserList = params => { return axios.post(`${base}/user/list`,params);};

//新添加用户
export const addUser = params => { return axios.post(`${base}/user/add`,params);};

//删除用户
export const removeUser = params => { return axios.get(`${base}/user/delete?id=` + params.id);};

//编辑用户名称
export const editUserInfo = params => { return axios.post(`${base}/user/editUserInfo`,params);};

//用户登录
export const requestUserLogin = params => { return axios.post(`${base}/user/login2`,params);};

