import axios from  'axios'

export const initMenu = (router, store) => {

    if (store.state.routes.length > 0) {
        return;
    }
    axios.get('http://localhost:8010/menu/getMenu/'+store.state.token.id).then(function (resp) {
        if (resp.status==200){
       var fmtRoutes=formatRoutes(resp.data);
        router.addRoutes(fmtRoutes);
        store.commit('initMenu', fmtRoutes);
        }
    })

}
export const formatRoutes = (routes) => {
    let fmRoutes = [];
    routes.forEach(router => {
        let {
            path,
            name,
            show,
            component,
            meta,
            iconCls,
            children
        } = router;
        if (children && children instanceof Array) {
            children = formatRoutes(children);
        }
        let fmRouter = {
            path: path,
            name: name,
            show:show,
            iconCls: iconCls,
            meta: meta,
            children: children,
            component(resolve) {
                if (component.startsWith("index")) {
                    require(['../views/Public/' + component + '.vue'], resolve);
                } else if (component.startsWith("Apply")) {
                    require(['../views/Apply/' + component + '.vue'], resolve);
                } else if (component.startsWith("Email")) {
                    require(['../views/Email/' + component + '.vue'], resolve);
                } else if (component.startsWith("Job")) {
                    require(['../views/Job/' + component + '.vue'], resolve);
                } else if (component.startsWith("Meet")) {
                    require(['../views/Meetting/' + component + '.vue'], resolve);
                } else if (component.startsWith("Review")) {
                    require(['../views/Review/' + component + '.vue'], resolve);
                }else if (component.startsWith("User")) {
                    require(['../views/User/' + component + '.vue'], resolve);
                }
            }
        }
        fmRoutes.push(fmRouter);
    })
    return fmRoutes;
}