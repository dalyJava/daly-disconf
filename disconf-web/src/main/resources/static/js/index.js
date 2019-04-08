requirejs(['configPath'],function(configPath) {
  requirejs.config(configPath);
  requirejs(['indexM','commonM'], function (indexM,commonM) {
    commonM.init();
    indexM.init();
  });

});



