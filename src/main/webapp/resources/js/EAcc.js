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
document.getElementById("home").addEventListener('click', () => {
	location.href = "../html/ELanding.html";
	});
document.getElementById("logout").addEventListener('click', async () => {
	let res = await fetch('http://localhost:8080/project1/api/logout');
	userId = -1;
	verifyLoggedIn();
	location.href = "../html/login.html";
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
		
		let subBy = row.insertCell(2);
		subBy.innerHTML = obj.employee.username;
		
		let amount = row.insertCell(3);
		amount.innerHTML = Number(obj.reimb_Amount).toFixed(3);
		
		
		
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
	let rows = await retreiveAllReimbursementsByUser();
	populateTable(rows);
}

document.getElementById("submitF").addEventListener('click',async() => {
	let status = document.getElementById("status").value;
	console.log(status);
	if(status<3){
		if(status == 0){
			let obj = await retreiveAllPendingReimbursementsByUser();
			populateTable(obj);
		}else if(status == 1){
			let obj = await retreiveAllApprovedReimbursementsByUser();
			populateTable(obj);
		}else if(status == 2){
			let obj = await retreiveAllDeniedReimbursementsByUser();
			populateTable(obj);
		}
	}else{
		let obj = await retreiveAllReimbursementsByUser();
		populateTable(obj);
	}
});

let retreiveAllPendingReimbursementsByUser = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllPendingReimbursementsByUser');
	let obj = await res.json();
	return obj;
}
let retreiveAllReimbursementsByUser = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllByUser');
	let obj = await res.json();
	return obj;
}
let retreiveAllDeniedReimbursementsByUser = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllDeniedReimbursementsByUser');
	let obj = await res.json();
	return obj;
}
let retreiveAllApprovedReimbursementsByUser = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllApprovedReimbursementsByUser');
	let obj = await res.json();
	return obj;
}
init();







