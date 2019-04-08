define(function () {
  return {
    cookie: {
      isValidKey(key){
        return (new RegExp(
            "^[^\\x00-\\x20\\x7f\\(\\)<>@,;:\\\\\\\"\\[\\]\\?=\\{\\}\\/\\u0080-\\uffff]+\x24"))
        .test(key);
      },
      get(key){
        if (this.isValidKey(key)) {
          const reg = new RegExp("(^| )" + key + "=([^;]*)(;|\x24)"),
              result = reg
              .exec(document.cookie);
          if (result) {
            return result[2] || null;
          }
        }
        return null;
      },
      set(key, value, options){
        if (this.isValidKey(key)) {
          return;
        }
        options = options || {};
        var expires = options.expires;
        if ('number' == typeof options.expires) {
          expires = new Date();
          expires.setTime(expires.getTime() + options.expires);
        }
        document.cookie = key + "=" + encodeURIComponent(value)
            + (options.path ? "; path=" + options.path : "")
            + (expires ? "; expires=" + expires.toUTCString() : "")
            + (options.domain ? "; domain=" + options.domain : "")
            + (options.secure ? "; secure" : '');
      },
      remove(key, options){
        options = options || {};
        options.expires = new Date(0);
        this.set(key, '', options);
      }
    },
    constants:{
      unAuthMsg:'你无权访问此权限',
      currentUser:'dis_conf_login_user',
      baseUrl:'http:localhost:8080',
      heightFixedPx:150,
      widthFixedPx:260,
      loginPage:'/login.html',
      prefixAuth:'/api/account/hasAuth/',
      prefixMenu:'/api/menu'
    }
  };
});