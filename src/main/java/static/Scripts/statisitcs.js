function getStatistics(){
    var user = localStorage.getItem("user");
    console.log(user);
    let user_hold = document.getElementById("user_name");
    user_hold.innerText = user;
    let url = 'http://localhost:7000/reimbursements/all';
    let xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let reimbursements = JSON.parse(xhr.response);
            console.log(reimbursements);

            let statistics_container = document.getElementById('statistics_container');
            let mean_expense = document.createElement('h3');
            let mean_expense_value = null;
            let sum = null;
            let most_expensive_employee = document.createElement('h3');
            let most_expensive_employee_value = null;
            let most_expensive_employee_employee = null;
            let count = 0;
            let approved = document.createElement('h3');
            let approved_count = 0;
            let denied = document.createElement('h3');
            let denied_count = 0;
            let pending = document.createElement('h3');
            let pending_count = 0;
            var map1 = new Map();

            for(let reimbursement of reimbursements){
                if(reimbursement.reimbursement_status = "Approved"){
                    approved_count = approved_count + 1;
                    count = count + 1
                    sum = sum + reimbursement.reimbursement_amount;
                    if(map1.get(reimbursement.employee_name)==null){
                        map1.set(reimbursement.employee_name, reimbursement.reimbursement_amount);
                    }
                    else{
                        map1.set(reimbursement.employee_name, reimbursement.reimbursement_amount + map1.get(reimbursement.employee_name));
                    }
                }
                else if(reimbursement.reimbursement_status=="Denied"){
                    denied_count = denied_count + 1;
                }
                else if(reimbursement.reimbursement_status == "Pending"){
                    pending_count = pending_count + 1;
                }
            }
            for(var [key,value] of map1){
                most_expensive_employee_value = (!most_expensive_employee_value || most_expensive_employee_value < value) ? value : most_expensive_employee_value;
            }
            for (let [key, value] of map1.entries()){
                if(value === most_expensive_employee_value)
                    most_expensive_employee_employee = key;
            }
            mean_expense_value = sum/count;

            mean_expense.innerText = "Average Expense Amount: $" + mean_expense_value;
            most_expensive_employee.innerText = "Most Expensive Employee: " + most_expensive_employee_employee + " with a total cost of $" + most_expensive_employee_value;
            approved.innerText = "Approved Reimbursements: " + approved_count;
            denied.innerText = "Denied Reimbursements: " + denied_count;
            pending.innerText = "Pending Reimbursements: " + pending_count;

            statistics_container.appendChild(mean_expense);
            statistics_container.appendChild(most_expensive_employee);
            statistics_container.appendChild(approved);
            statistics_container.appendChild(denied);
            statistics_container.appendChild(pending);
        }
    }
    xhr.open('GET', url);
    xhr.send();
}
function approve(id){
    let url = 'http://localhost:7000/reimbursement/approve/';
    url = url + id;
    let xhr = new XMLHttpRequest();
    console.log(xhr)
    xhr.onreadystatechange = function(){
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
    this.getStatistics();
                         }