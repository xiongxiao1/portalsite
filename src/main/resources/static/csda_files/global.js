/** _debug = true：表示所有参数从后端取，避免频烦部署阶段前端代码还需要维护配置信息
 *  _debug = false: 表示所有参数从前端配置，此时所有配置项从前端js中直接读取，减少了访问后端的压力
 */
var _debug = true;
var _callStatu = false;
var _configObj = null;

var ctx;
var _domainCtx = "http://www.csda.cn.com/csda";
var _appId = "wx11c4fa9e51ca1c4a";

$(function(){
	ctx = getContextPath();
	initConfig();
});

/**
 * 初始化配置信息
 * @returns
 */
function initConfig(){
    if(_debug && _callStatu){//调过就不再调用
    	return;
    }
	 $.ajax({
	        type : "POST",  //提交方式POST/GET
	        url : getContextPath() + "/config/getGlobalConfig.spv",//路径
	        dataType : "json",
	        async:false, 
	        contentType:"application/json; charset=utf-8",
	        /*data : JSON.stringify({
				"id":id,
				"status":value
	        }),*/
	        success : function(result) {//返回数据根据结果进行相应的处理
	        	if(result.resultCode = "success"){
	        		_configObj = result.values;
	        	}else{
	        		var resultMessage = result.resultMessage;
	        		alert(resultMessage);
	        	}
	        },error : function(e) {
	            alert("加载配置信息失败!");
	        }
	    });
	 _callStatu = true;
	 
}
/**
 * 获取微信公众号的appId
 * @returns
 */
function getAppId(){
	initConfig();
	if(_debug){
		_appId = _configObj.wechat_appId;
	}
	return _appId;
}

/**
 * 获取本应用的访问路径,形如:http://www.xxx.cn.com/csdl
 * @returns
 */
function getDomainCtx(){
	initConfig();
	if(_debug){
		_domainCtx = _configObj.domainCtx;
	}
	return _domainCtx;
}

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}


function getRequestCodeUrl(redirectUrl){	
	var resultStr = getRequestCodeUrlGener("snsapi_userinfo", redirectUrl,"wx_login_first");
	return resultStr;
}

function getRequestCodeUrlUserInfo(redirectUrl,state){	
	var resultStr = getRequestCodeUrlGener("snsapi_userinfo", redirectUrl,state);
	return resultStr;
}

function getRequestCodeUrlBaseInfo(redirectUrl,state){	
	var resultStr = getRequestCodeUrlGener("snsapi_base", redirectUrl ,state);
	return resultStr;
}

function getRequestCodeUrlGener(scope,redirectUrl,state){	
	var urlEncodeUrl = null;
	try {
		urlEncodeUrl = redirectUrl;
		//urlEncodeUrl = encodeURI(redirectUrl);
	} catch (e) {
		alert("编码错误");
	}
	if(state == null || typeof(state) == undefined ){
		state = "";
	}
	if(scope != "snsapi_userinfo" && scope != "snsapi_base"){
		alert("不支持的scope["+scope+"]");
	}
	            //"https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state={3}#wechat_redirect
	var tempStr = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state={3}#wechat_redirect";
	var appId = getAppId();
	var resultStr = tempStr.format(appId, urlEncodeUrl, scope ,state);
	return resultStr;
}


function getUriParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}




String.prototype.format = function(args) {
	var result = this;
	if (arguments.length > 0) {
		if (arguments.length == 1 && typeof (args) == "object") {
			for ( var key in args) {
				if (args[key] != undefined) {
					var reg = new RegExp("({" + key + "})", "g");
					result = result.replace(reg, args[key]);
				}
			}
		} else {
			for (var i = 0; i < arguments.length; i++) {
				if (arguments[i] != undefined) {
					// var reg = new RegExp("({[" + i + "]})",
					// "g");//这个在索引大于9时会有问题，谢谢何以笙箫的指出
					var reg = new RegExp("({)" + i + "(})", "g");
					result = result.replace(reg, arguments[i]);
				}
			}
		}
	}
	return result;
}
String.format = function() {
	if (arguments.length == 0)
		return null;

	var str = arguments[0];
	for (var i = 1; i < arguments.length; i++) {
		var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
		str = str.replace(re, arguments[i]);
	}
	return str;
}

function dateFtt(fmt,date)   
{ //author: meizz   
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "h+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 


function returnFloat(value) {
	var value = Math.round(parseFloat(value) * 100) / 100;
	var xsd = value.toString().split(".");
	if (xsd.length == 1) {
		value = value.toString() + ".00";
		return value;
	}
	if (xsd.length > 1) {
		if (xsd[1].length < 2) {
			value = value.toString() + "0";
		}
		return value;
	}
}

/**
 * 删除左右两端的空格
 */
String.prototype.trim=function()
{
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
/**
 * 删除左边的空格
 */
String.prototype.ltrim=function()
{
    return this.replace(/(^\s*)/g,"");
}
/**
 * 删除右边的空格
 */
String.prototype.rtrim=function()
{
    return this.replace(/(\s*$)/g,"");
}

jQuery.fn.extend({
    disableRepeatClick: function (fun, delayTime) {
        //默认2秒内不能重复执行
        var defaultTime = 2000;
        if (delayTime) defaultTime = delayTime;
        var _this = $(this);

        if (_this.hasClass('clicked')) {
           // weui.alert('请勿重复点击!');
            return false;
        }
        _this.addClass('clicked');
        fun();
        setTimeout(function () {
            _this.removeClass('clicked');
        }, defaultTime);
    }
})
