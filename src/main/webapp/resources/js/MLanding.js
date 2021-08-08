/**
 * 
 */
 let userid;
 
 let verifyLoggedIn = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getSession');
	let obj = await res.json();
	
	if(obj.userid < 0){
		location.href = "../html/login.html";
	}
	else{
		userId = obj.userid;
	}
	
}
document.getElementById("logout").addEventListener('click', async () => {
	let res = await fetch('http://localhost:8080/project1/api/logout');
	userId = -1;
	verifyLoggedIn();
});
let populateTable = (obj) => {
	let table = document.getElementById("rtable");
	table.innerHTML= '<tr><th>Status</th><th>Type</th><th>Submitted By</th><th>Amount</th><th>Submitted On</th><th>Resolved On</th><th>Approved By</th></tr>';
	console.log(obj);
	obj.forEach((obj) => {
		let count =1;
		let row=table.insertRow(count++);
		row.id = obj.id;

		
		let status = row.insertCell(0);
		status.innerHTML = obj.rStatus;
		
		let type = row.insertCell(1);
		type.innerHTML = obj.type;
		
		let amount = row.insertCell(2);
		amount.innerHTML = Number(obj.reimb_Amount).toFixed(2);
		
		let description = row.insertCell(3);
		description.innerHTML = obj.description;
		
		let submitted = row.insertCell(4);
		submitted.innerHTML = new Date(obj.submitted).toDateString();
		
		let resolved = row.insertCell(5);
		if(obj.resolveddate !== null){
			resolved.innerHTML = new Date(obj.resolved).toDateString();
		}else{
			resolved.innerHTML = 'Unresolved ATM';
		}
		let manager = row.insertCell(6);
		if(obj.manager !== null){
			manager.innerHTML = obj.manager.username;
		}else{
			manager.innerHTML = 'Unresolved ATM';
		}
		let drow = table.insertRow(count++);
		let desc = drow.insertCell(0);
		desc.setAttribute("colspan", "7");
		desc.className = "description";
		desc.innerHTML = `<h3>Description:</h><p>${obj.description}</p>`;
		
	});
}
let init = async () => {
	await verifyLoggedIn();
	let res = await fetch(`http://localhost:8080/project1/api/getUser?userid=${userId}`);
	let user = await res.json();
	let username = user.username;
	document.getElementById("pain").innerText = `Existance is pain. Enjoy your stay ${username}!`;
	let rows = await retreiveAllReimbursements();
	populateTable(rows);
}

document.getElementById("submitF").addEventListener('click',async() => {
	let status = document.getElementById("status").value;
	console.log(status);
	if(status<3){
		let res = await fetch(`http://localhost:8080/project1/api/getUser?userid=${userId}`);
		let user = await res.json();
		console.log(user);
		if(status == 0){
			let obj = await retreiveAllPendingReimbursements();
			populateTable(obj);
		}else if(status == 1){
			let obj = await retreiveAllApprovedReimbursements();
			populateTable(obj);
		}else if(status == 2){
			let obj = await retreiveAllDeniedReimbursements();
			populateTable(obj);
		}
	}else{
		let obj = await retreiveAllReimbursements();
		populateTable(obj);
	}
});

let retreiveAllPendingReimbursements = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllPending');
	let obj = await res.json();
	return obj;
}
let retreiveAllReimbursements = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllReimbursements');
	let obj = await res.json();
	return obj;
}
let retreiveAllDeniedReimbursements = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllDenied');
	let obj = await res.json();
	return obj;
}
let retreiveAllApprovedReimbursements = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllApproved');
	let obj = await res.json();
	return obj;
}
init();
