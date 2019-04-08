define(['utils', 'layer','jquery','i18n'], function (utils, layer,$,jQuery) {
  return {
    init() {
      this.getAuth();
    },
    getAuth() {
      this.isLogin();

      // 获取是否有该页面的权限

      // 2没有拿到就跳转到登录页面
    },
    isLogin() {
      let currentUser = utils.cookie.get(utils.constants.currentUser);
      if (currentUser === '') {
        window.location.href = utils.constants.baseUrl
            + utils.constants.loginPage;
      }
    },
    hasAuth(url) {
      $.get(utils.constants.prefixAuth + url, function (data) {
        if (data.success !== true) {
          layer.msg($.i18n.prop('unAuthMsg'));
          window.history.back(-1);
        }
      });
    },
    // loadProperties(){
    //   jQuery.i18n.properties({//加载资浏览器语言对应的资源文件
    //     name:'web-i18n', //资源文件名称
    //     path:'messages/', //资源文件路径
    //     mode:'map', //用Map的方式使用资源文件中的值
    //     language:Util.getLanguage(),//
    //     callback: function() {//加载成功后设置显示内容
    //         alert("1111111111");
    //     }
    //   });
    // }
  };
});