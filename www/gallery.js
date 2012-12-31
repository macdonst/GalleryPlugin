cordova.define("cordova/plugin/gallery",
  function(require, exports, module) {
    var exec = require("cordova/exec");
    var Gallery = function () {};

    Gallery.prototype.add = function(imagePath,success,fail) {
        exec(success,fail,"Gallery","add",[imagePath]);
    };

    var gallery = new Gallery();
    module.exports = gallery;
});