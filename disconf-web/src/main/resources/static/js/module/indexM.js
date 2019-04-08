define(['bootstrap', 'jquery', 'vue', 'chart', 'tether', 'popper', 'front',
      'validate', 'cookie', 'utils', 'layer'],
    function (bootstrap, jquery, vue, chart, tether, popper, front, validate,
        cookie, utils, layer) {
      return {
        init() {
          const that = this;
          that.vueInit();
          that.menuInit();
        },
        pageVue: {},
        pageIframe: '',
        vueInit() {
          const that = this;
          that.pageVue = new vue({
            el: '#box',
            data: {
              menuTree: {}
            },
            methods: {
              goPage(pageUrl) {
                that.pageIframe.src = pageUrl;
              }
            },
            mounted() {
              that.pageIframe = this.$refs.iframeBox;
              const deviceWidth = document.documentElement.clientWidth;
              const deviceHeight = document.documentElement.clientHeight;
              that.pageIframe.style.width = (Number(deviceWidth)
                  - utils.constants.widthFixedPx) + 'px'; //数字是页面布局宽度差值
              that.pageIframe.style.height = (Number(deviceHeight)
                  - utils.constants.heightFixedPx) + 'px'; //数字是页面布局高度差
            }
          });
        },
        menuInit() {
          const that = this;
          $.get(utils.constants.prefixMenu + '/tree', function (res) {
            if (res.success === 'false') {
              layer.msg('菜单获取失败！');
            } else {
              that.pageVue.menuTree = res.result.child;
            }
          });
        }
      }
    });