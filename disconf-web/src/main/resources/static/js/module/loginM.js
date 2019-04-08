define(['bootstrap', 'vue', 'jquery', 'utils','layer'],
    function (bootstrap, vue, $, utils,layer) {
      return {
        init() {
          const that = this;
          that.vueInit();
          that.pageVue.getVerifyCode();
        },
        pageVue:{},
        vueInit() {
          const that = this;
          this.pageVue = new vue({
            el: "#box",
            data: {
              username: 'admin',
              password: '123',
              verifyCode: '',
              verifyCodeUrl:'' //require(['/api/account/verifyCode'])
            },
            methods: {
              login() {
                let loginInfo = {
                  username: that.pageVue.username,
                  password: that.pageVue.password,
                  verifyCode: that.pageVue.verifyCode
                };
                $.post("/api/account/signin", loginInfo, function (data) {
                  if (data.success === "true") {
                    window.location.href = "/index.html";
                  }else {
                    layer.msg(data.message.error);
                  }
                });
              },
              getVerifyCode() {
                $.get("/api/account/verifyCode",function (res) {
                  that.pageVue.verifyCodeUrl = "data:image/png;base64,"+res;
                })
              }
            }
          });
        }
      };
    });



