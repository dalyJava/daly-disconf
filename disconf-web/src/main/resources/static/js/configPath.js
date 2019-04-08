define([], function () {
  return {
    baseUrl: '/',//static为根目录，不需要额外配置
    paths: {
      vue: 'plug-in/vue/vue',
      bootstrap: 'plug-in/bootstrap/bootstrap4',
      jquery: 'plug-in/jquery/jquery',
      popper: 'plug-in/popper/popper',
      layer: 'plug-in/layer/layer',
      i18n: 'plug-in/jquery/i18n/jquery.i18n.properties',
      cookie: 'plug-in/jquery/cookie/jquery.cookie',
      validate: 'plug-in/jquery/validate/jquery.validate.min',
      chart: 'plug-in/chart/chart.min',
      front: 'plug-in/front/front',
      tether: 'plug-in/tether/tether.min',
      select: 'plug-in/bootstrap/select/bootstrap_select',
      //
      configM: 'js/module/configM',
      loginM: 'js/module/loginM',
      indexM: 'js/module/indexM',
      commonM: 'js/module/commonM',
      utils: 'js/module/utils',
    },
    map: {
      '*': {
        'css': 'plug-in/require/css'
      }
    },
    shim: {
      jquery: {
        exports: '$'
      },
      bootstrap: {
        deps: ['jquery', 'popper', 'tether',
          'css!plug-in/bootstrap/bootstrap4.css']
      },
      select: {
        deps: ['bootstrap','jquery',
          'css!plug-in/bootstrap/bootstrap4.css',
          'css!plug-in/bootstrap/select/bootstrap_select.css']
      },
      layer: {
        deps: ['jquery', 'css!plug-in/layer/theme/default/layer.css']
      },
      i18n: {
        deps: ['jquery']
      },
      chart: {
        deps: ['jquery']
      },
      cookie: {
        deps: ['jquery']
      },
      front: {
        deps: ['jquery', 'cookie']
      },
      loginM: {
        deps: [
          'css!/css/pages/login.css',
          'css!/css/style-default.css',
          'css!/css/fonts-googleapis.css',
          'css!plug-in/font-awesome/font-awesome.css',
          'css!plug-in/font-icon/font-icon-style.css'
        ]
      },
      indexM: {
        deps: [
          'css!/css/style-default.css',
          'css!/css/fonts-googleapis.css',
          'css!plug-in/font-awesome/font-awesome.css',
          'css!plug-in/font-icon/font-icon-style.css',
          'css!/css/style.css',
          'css!/css/ui-elements/card.css'
        ]
      },
      configM: {
        deps: [
          'css!/css/style-default.css',
          'css!plug-in/font-awesome/font-awesome.css',
          'css!plug-in/font-icon/font-icon-style.css',
          'css!/css/pages/config.css',
          'css!plug-in/bootstrap/bootstrap4.css',
          'css!plug-in/bootstrap/select/bootstrap_select.css'
        ]
      }
    }
  }
});

define("popper.js", ["popper"], function (popper) {
  return popper;
});
