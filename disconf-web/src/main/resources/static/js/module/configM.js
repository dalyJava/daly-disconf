define(['bootstrap', 'vue', 'jquery', 'utils', 'layer', 'select', 'popper'],
    function (bootstrap, vue, $, utils, layer, select, popper) {
      return {
        init() {
          const that = this;
          that.vueInit();
          that.getAppList();
          that.getEnvList();
        },
        pageVue: {},
        vueInit() {
          const that = this;
          that.pageVue = new vue({
            el: '#box',
            data: {
              appIdSelected: '',
              appItem: {},
              envItems: {},
              configItems:{},
              versionItems:{}
            },
            methods: {
              getEnvConfig(appId, envId) {
                layer.msg("appId == " + appId + ",envId == " + envId);
                let paramJson = {appId: appId, envId: envId}
                $.get('/api/web/config/versionlist', paramJson, function (res) {
                  if (res.success === "true") {
                    that.pageVue.versionItems = res.result;
                  } else {
                    layer.msg("版本信息获取失败");
                  }
                })
              },
              changeConfigItem(version){
                let paramJson = {appId:'1',envId:'1',version:version};
                $.get('/api/web/config/list',paramJson,function (res) {
                  console.log(res);
                  that.pageVue.configItems = res.result;
                });
              },
              upDateConfig(configId){
                layer.msg("update config where id = "+configId)
              },
              downloadConfig(configId){
                layer.msg("download config where id = "+configId)
              },
              delConfig(configId){
                layer.msg("del config where id =  "+configId)
              }
            },
            mounted() {
              $('#appItems').on('hidden.bs.select', function (e) {
                console.log(e);
                console.log($('#appItems').selectpicker('val'));
                that.pageVue.appIdSelected = $('#appItems').selectpicker('val');
              });
            }
          });
        },
        getAppList: function () {
          const that = this;
          $.get("/api/app/list", function (res) {
            console.log(res);
            if (res.success === 'true') {
              res.result.forEach(app => {
                $("#appItems").append(
                    '<option value="' + app.id + '" >' + app.name
                    + '</option>');
              });
              $('#appItems').selectpicker();
              that.pageVue.appIdSelected = $('#appItems').selectpicker('val');
            } else {
              layer.msg("app 获取失败");
            }
          });
        },
        getEnvList() {
          const that = this;
          $.get("/api/env/list", function (res) {
            if (res.success === 'true') {
              that.pageVue.envItems = res.result;
            } else {
              layer.msg("env 获取失败");
            }
          });
        }
      }
    });