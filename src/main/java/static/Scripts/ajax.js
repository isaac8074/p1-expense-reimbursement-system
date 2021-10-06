function getAllReimbursements(){
    var user = localStorage.getItem("user");
    console.log(user);
    let user_hold = document.getElementById("user_name");
    user_hold.innerText = user;
    document.getElementById("user_input").value = user;
    let url = 'http://localhost:7000/reimbursement/';
    url = url + user_hold.innerText;
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

                reimbursement_container.appendChild(new_div);
            }
            console.log(user_hold.innerText);
        }
    }
    xhr.open('GET', url); //ready state 1
    xhr.send(); // ready state 2
}
function logout(){
    localStorage.setItem("user", null);
    window.location.replace("http://localhost:7000/loginpage.html");
}

window.onload = function(){
    this.getAllReimbursements();
}