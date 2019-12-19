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
    let url = location.search; //获取url中"?"符后的字串
    let theRequest = {};
    if (url.indexOf("?") !== -1) {
        let str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

function uploadImage(file) {
    if(file == null) {
        alert("请选择头像");
        return;
    }

    let form = new FormData();
    form.append("file", file);
    const token = sessionStorage.getItem('token');
    if(token == null) {
        console.log("No token found");
        return null;
    }

    const fileXhr = new XMLHttpRequest();
    let imgId = -1;

    fileXhr.open('POST', SEND_HOST + "/api/image/upload", false);
    fileXhr.onreadystatechange = function () {
        if (fileXhr.readyState === 4) {
            if(fileXhr.status === 200) {
                const info = JSON.parse(fileXhr.responseText);
                if(info.code === 0) {
                    imgId = info.data;
                } else {
                    alert("图片上传失败");
                }
            } else if(fileXhr.status === 401) {
                alert("请重新登录");
                location.href = "login.html";
            }
        }
    };

    setXhrToken(fileXhr,token);
    defaultXhrHeader(fileXhr);
    fileXhr.send(form);

    return imgId;
}

function loadImage(imgId, imgElementStr) {
    if(imgId == null || imgId < 0) {
        console.log("Illegal imageId at loadImage");
        return;
    }

    const xhr = new XMLHttpRequest();
    xhr.open("GET", SEND_HOST + "/api/image/load?id=" + imgId);
    xhr.responseType = "blob";
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const contentType = this.getResponseHeader("Content-Type");
            const blob = this.response;
            let bin = [];
            bin.push(blob);
            $(imgElementStr).attr("src", window.URL.createObjectURL(new Blob(bin, {type: contentType})));
        }
    };

    defaultXhrHeader(xhr);
    xhr.send();
}

function sendFormData(formData, callback, method, uri, async) {
    const token = sessionStorage.getItem('token');
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open(method, SEND_HOST + uri, async);
    xmlHttp.onreadystatechange = callback(xmlHttp);
    if(token != null) {
        setXhrToken(xmlHttp, token);
    }
    defaultXhrHeader(xmlHttp);
    if(formData != null) {
        xmlHttp.send(formData);
    } else {
        xmlHttp.send();
    }
}
