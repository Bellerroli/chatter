function getUsername(){
    let username;
    let ca = document.cookie.split(";");
    for (let i = 0; i < ca.length; i++) {
        if (ca[i].includes("username=")) {
            username = ca[i].split("=")[1];
        }
    }
    if(!username.length>0) username = undefined;
    return username;
}

function logOff(){
    let ca = document.cookie.split(";")
    for (let i = 0; i < ca.length; i++) {
        if(ca[i].includes("username=")){
            ca[i] = "username=";
        }
    }
    document.cookie = ca.join(";");
}

function createSession(){
    let socket = new WebSocket('ws://localhost:8765/chatter_war_exploded/echo');
    socket.onopen = (event) =>{
        socket.send(getUsername());
    }
    socket.onmessage = (event)=>{
        chatEl.innerText+= event.data+" ";
    }
    return socket;
}