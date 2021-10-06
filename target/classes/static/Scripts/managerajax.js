function getAllReimbursements(){
    var user = localStorage.getItem("user");
    console.log(user);
    let user_hold = document.getElementById("user_name");
    user_hold.innerText = user;
    let url = 'http://localhost:7000/reimbursement/all';
    let xhr = new XMLHttpRequest(); //ready state 0
    console.log(xhr);
// readystate 3 and 4
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200){
            let reimbursements = JSON.parse(xhr.response);
            console.log(reimbursements);
            for(let reimbursement of reimbursements){

                let reimbursement_container = document.getElementById('reimbursement_container');
                let new_div = document.createElement('div');
                new_div.className = "reimbursement_div";
                let new_h32 = document.createElement('h3');
                new_h32.innerText = reimbursement.reimbursement_id;
                let new_h3 = document.createElement('h3');
                new_h3.innerText = "Employee: " + reimbursement.employee_name;
                let new_para = document.createElement('p');
                new_para.innerText = "Amount $" + reimbursement.reimbursement_amount;
                let new_para2 = document.createElement('p');
                new_para2.innerText = "Description: " + reimbursement.reimbursement_description;
                let new_para3 = document.createElement('p');
                new_para3.innerText = "Status: " + reimbursement.reimbursement_status;

                new_div.appendChild(new_h32);
                new_div.appendChild(new_h3);
                new_div.appendChild(new_para);
                new_div.appendChild(new_para2);
                new_div.appendChild(new_para3);

                let new_button = document.createElement("button");
                new_button.innerText = "Approve";
                new_button.id = reimbursement.reimbursement_id;
                new_button.type = "Approve";
                new_button.onclick = function() {approve(new_button.id)};

                let new_button2 = document.createElement("button");
                new_button2.innerText = "Deny";
                new_button2.id = reimbursement.reimbursement_id;
                new_button2.type = "Deny";
                new_button2.onclick = function() {deny(new_button2.id)};

                new_div.appendChild(new_button);
                new_div.appendChild(new_button2);

                reimbursement_container.appendChild(new_div);
            }
        }
    }
    xhr.open('GET', url); //ready state 1
    xhr.send(); // ready state 2
}
function approve(id) {
    let url = 'http://localhost:7000/reimbursement/approve/';
    url = url + id;
    let xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200){
            window.location.replace("http://localhost:7000/managerhome.html");
        }
    }
    xhr.open('POST', url);
    xhr.send();
}
function deny(id) {
    let url = 'http://localhost:7000/reimbursement/deny/';
    url = url + id;
    let xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200){
            window.location.replace("http://localhost:7000/managerhome.html");
        }
    }
    xhr.open('POST', url);
    xhr.send();
}
function logout(){
    localStorage.setItem("user", null);
    window.location.replace("http://localhost:7000/loginpage.html");
}

window.onload = function(){
    this.getAllReimbursements();
}