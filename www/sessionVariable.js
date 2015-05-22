/**
 * Created by sourabh on 22/5/15.
 */
var exec = require('cordova/exec');

var functionExport = {};


function sessionVariable() { console.log("sessionVariable.js: is created");
}

functionExport.setKey = function(key, value, successCallback, errorCallback) {
    exec(successCallback, errorCallback, "sessionVariable", "setKey", [key, value]);
}

functionExport.getKey = function(key, successCallback, errorCallback) {
    exec(successCallback, errorCallback, "sessionVariable", "getKey", [key]);
}

functionExport.removeKey = function(key, successCallback, errorCallback) {
    exec(successCallback, errorCallback, "sessionVariable", "removeKey", [key]);
}

module.exports = functionExport;
