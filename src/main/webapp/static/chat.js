let chatEl = document.getElementById("chat");
let msgInput = document.getElementById("to-send");
let senderEl = document.getElementById("sender");

if(getUsername() === undefined) location.href = "index.jsp";

let socket = createSession();

senderEl.addEventListener("click", ()=>{
    msg = msgInput.value;
    socket.send(getUsername()+":::"+msg);
    msgInput.value = "";
});

msgInput.addEventListener("keypress", (event) =>{
    if(event.key === "Enter"){
        msg = msgInput.value;
        socket.send(getUsername()+":::"+msg);
        msgInput.value = "";
    }
})


function getUsername(){
    let username;
    if(!document.cookie.includes("username=")) document.cookie+=";username=";
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
    socket.onmessage = (event)=>{
        chatEl.innerText+= event.data.split(":::")[1]+"\n";
        chatEl.innerText+= "By "+event.data.split(":::")[0]+"\n";
    }
    return socket;
}