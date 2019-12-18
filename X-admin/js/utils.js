function defaultXhrHeader(xmlHttp) {
    xmlHttp.setRequestHeader('Access-Control-Allow-Origin','*');
    xmlHttp.setRequestHeader('Access-Control-Allow-Headers','Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie, token');
    xmlHttp.setRequestHeader('Access-Control-Allow-Method','POST,GET');
}

function setXhrToken(xmlHttp, token) {
    xmlHttp.setRequestHeader("Platform-Token", token);
}