function defaultXhrHeader(xmlHttp) {
    xmlHttp.setRequestHeader('Access-Control-Allow-Origin','*');
    xmlHttp.setRequestHeader('Access-Control-Allow-Headers','Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie, token');
    xmlHttp.setRequestHeader('Access-Control-Allow-Method','POST,GET');
}

function setXhrToken(xmlHttp, token) {
    xmlHttp.setRequestHeader("Platform-Token", token);
}

const URL_REG = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");


function getRequestParams() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = {};
    if (url.indexOf("?") !== -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}