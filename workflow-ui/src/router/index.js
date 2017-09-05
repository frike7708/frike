const NotFound = r => require(['@/frame/notfound'], r);
const FlowList = r => require(['@/views/flow_list'], r);
const FlowEdit = r => require(['@/views/flow_edit'], r);
const RobotList = r => require(['@/views/robot_list'], r);
const RobotCreate = r => require(['@/views/robot_create'], r);
const RobotEdit = r => require(['@/views/robot_edit'], r);
const Test = r => require(['@/views/test'], r);
const ProcessList = r => require(['@/views/process_list'], r);
const ProcessDesign = r => require(['@/views/process_design'], r);
const ProcessModel = r => require(['@/views/process_model'], r);
const CreateModel = r => require(['@/views/create_model'], r);
const ProcessModelCreate = r => require(['@/views/process_model_create'], r);
const ProcessMonitor = r => require(['@/views/process_monitor'], r);
const ProcessMonitorDetail = r => require(['@/views/process_monitor_detail'], r);
const FormList = r => require(['@/views/form_list'], r);
const FormBuilder = r => require(['@/views/form_builder'], r);
const FormReader = r => require(['@/views/form_reader'], r);
const GroupList = r => require(['@/views/group_list'], r);
const GroupUserDrag = r => require(['@/views/group_user_drag'], r);
const UserList = r => require(['@/views/user_list'], r);
const LoginSystem = r => require(['@/views/login_system'], r);


// 根目录
const rootPath = '';

// 页面路由
const routes = [
    { path: '/', component: ProcessList, name: 'process_list' },
    { path: '/login', component: LoginSystem, name: 'login_system' },
    { path: '/flow_list', component: FlowList, name: 'flow_list' },
    { path: '/flow_edit', component: FlowEdit, name: 'flow_edit' },
    { path: '/robot_list', component: RobotList, name: 'robot_list' },
    { path: '/robot_create', component: RobotCreate, name: 'robot_create' },
    { path: '/robot_edit', component: RobotEdit, name: 'robot_edit' },

    { path: '/process_list', component: ProcessList, name: 'process_list' },
    { path: '/process_design', component: ProcessDesign, name: 'process_design' },
    { path: '/process_model', component: ProcessModel, name: 'process_model' },
    { path: '/create_model', component: CreateModel, name: 'create_model' },
    { path: '/process_model_create', component: ProcessModelCreate, name: 'process_model_create' },
    { path: '/process_monitor', component: ProcessMonitor, name: 'process_monitor' },
    { path: '/process_monitor_detail', component: ProcessMonitorDetail, name: 'process_monitor_detail' },
    { path: '/form_list', component: FormList, name: 'form_list' },
    { path: '/form_builder', component: FormBuilder, name: 'form_builder' },
    { path: '/form_reader', component: FormReader, name: 'form_reader' },
    { path: '/group_list', component: GroupList, name: 'group_list' },
    { path: '/group_user_drag', component: GroupUserDrag, name: 'group_user_drag' },
    { path: '/user_list', component: UserList, name: 'user_list' },

].map(route => {
    route.path = rootPath + route.path;
    return route
});

// 404 页
routes.push({ path: '/test', component: Test, name: 'Test' });
routes.push({ path: '*', component: NotFound, name: 'notfound' });

export default routes;
