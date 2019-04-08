requirejs(['configPath'],function(configPath) {
  requirejs.config(configPath);
  requirejs(['loginM','commonM'], function (loginM,commonM) {
    commonM.init();
    loginM.init();
  });

});



