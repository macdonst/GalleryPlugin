cordova.define("cordova/plugin/gallery",
  function(require, exports, module) {
    var exec = require("cordova/exec");
    var Gallery = function () {};

    Gallery.prototype.add = function(imagePath,success,fail) {
        exec(success,fail,"Gallery","add",[imagePath]);
    };

    Gallery.prototype.remove = function(imagePath,success,fail) {
        exec(success,fail,"Gallery","remove",[imagePath]);
    };

    var gallery = new Gallery();
    module.exports = gallery;
});

if (!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.gallery) {
    window.plugins.gallery = cordova.require("cordova/plugin/gallery");
}
