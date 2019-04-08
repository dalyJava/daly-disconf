requirejs(['configPath'],function(configPath) {
  requirejs.config(configPath);
  requirejs(['configM','commonM'], function (configM,commonM) {
    commonM.init();
    configM.init();
  });

});



