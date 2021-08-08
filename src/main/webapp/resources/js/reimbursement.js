/**
 * 
 */
 
 let userId;
 let form = document.getElementById('newReimbursement').addEventListener('submit', addReimbursement);

 async function addReimbursement(e){
     e.preventDefault();
     let res = await fetch(`http://localhost:8080/project1/api/getUser?userid=${userId}`);
	 let user_id = await res.json();
	 console.log(user_id);
	 let userID = user_id.id;
	 console.log(userID);
     let select = document.getElementById('type');
     console.log(select);
     console.log("hmmmmm");
     let type = select.options[select.selectedIndex].value;
     let amount =document.getElementById("amount").value;
     let description =document.getElementById("desc").value;
    
 	//how can i get the username from here?
 	//let username = 
     let user = {
		 userID,
         type,
         amount,
         description,
     };
 
     console.log(user);
 
     try{
         let req = await fetch('http://localhost:8080/project1/api/EReimb',{
             method: 'POST',
             headers:{
                 'Content-Type' : 'application/json'
             },
             body: JSON.stringify(user)
         });
         let res = await req.json();
         console.log(res);
         console.log("hmmmmm");
     }catch(e){
         alert("we broke idk y please panic");
         return;
     }
 
     location.href = '../html/ELanding.html'
 }
 let populateTable = (objList) => {

	let table = document.getElementById("re-table");

	table.innerHTML = '<tr><th>Reimbursement Status</th><th>Reimbursement Type</th><th>Reimbursement Amount</th><th>Description</th><th>Date Of Submission</th><th>Date Of Resolution</th><th>Manager</th></tr>';


	objList.forEach((obj) =>{
		let index = 1;
		let row=table.insertRow(index++);
		row.id = obj.reId;

		let status = row.insertCell(0);
		status.innerHTML = obj.status.reimbursement_status;
		let type = row.insertCell(1);
		type.innerHTML = obj.type.reimbursement_type;
		let amount = row.insertCell(2);
		amount.innerHTML = Number (obj.amount).toFixed(2);
		let description = row.insertCell(3);
		description.innerHTML = obj.description;
		//this wont work just a heads up
		let subDate = row.insertCell(4);
		subDate.innerHTML = new Date(obj.submitteddate).toDateString();
		let resDate = row.insertCell(5);

		if(obj.resolveddate !== null){
		resDate.innerHTML = new Date(obj.resolveddate).toDateString();
		}
		else {
		resDate.innerHTML = 'N/A';
		}

		let resolver = row.insertCell(6);
		if (obj.manager !== null) {
			resolver.innerHTML = obj.manager.username;
		}
		else {
			resolver.innerHTML = 'N/A';
		}
	});
}
document.getElementById("logout").addEventListener('click', async () => {
	let res = await fetch('http://localhost:8080/project1/api/logout');
	userId = -1;
	verifyLoggedIn();
});
let verifyLoggedIn = async() => {
	let res = await fetch('http://localhost:8080/project1/api/getSession');
	let obj = await res.json();
	console.log(obj);

	if (obj.userid < 0) {
		location.href = "../html/login.html";
	}
	else {
		userId = obj.userid;
	}
}
 