var exec = require('cordova/exec');

/**
 *
 * @param packageName
 * @example com.example.myapp
 * @param success
 * @param error
 * @returns {Promise<unknown>}
 * @constructor
 */
exports.Uninstall = function (packageName, success, error) {
    return new Promise(function(resolve,reject){
        if (typeof packageName == 'string'){
            exec(resolve, reject, 'UninstallApk', 'Set', [packageName]);
        }else{
            resolve('Incorrect package name.');
        }

    });

};

/**
 * @param packageName
 * @example com.example.myapp
 * @param success
 * @param error
 * @returns {Promise<unknown>}
 */
exports.AppIsInstalled = function (packageName, success, error) {
    return new Promise(function(resolve,reject){
        if (typeof packageName == 'string'){
            exec(resolve, reject, 'UninstallApk', 'appIsInstalled', [packageName]);
        }else{
            resolve('Incorrect package name.');
        }

    });

};
