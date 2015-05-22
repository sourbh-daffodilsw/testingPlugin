var exec = require('cordova/exec');

var functionExport = {};

functionExport.setKey = function(successCallback, errorCallback, key, value) {
    exec(successCallback, errorCallback, "sessionVariable", "setKey", [key, value]);
}

functionExport.getKey = function(successCallback, errorCallback, key) {
    exec(successCallback, errorCallback, "sessionVariable", "getKey", [key]);
}

functionExport.removeKey = function(successCallback, errorCallback, key) {
    exec(successCallback, errorCallback, "sessionVariable", "removeKey", [key]);
}

module.exports = functionExport;
